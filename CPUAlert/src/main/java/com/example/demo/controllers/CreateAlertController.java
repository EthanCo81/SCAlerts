package com.example.demo.controllers;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Alert;
import com.example.beans.EBUInfo;
import com.example.beans.EBUid;
import com.example.demo.services.AlertService;
import com.example.demo.services.CreateAlertServiceImpl;
import com.example.demo.services.EBUInfoService;

@RestController
public class CreateAlertController {
	@Autowired
	CreateAlertServiceImpl createAlertServiceImpl;
	
	@Autowired
	AlertService alertService;
	
	@Autowired
	EBUInfoService ebuInfoService;
	
	@PostMapping(value= {"createAlert/{countryCode}/{ebuNbr}"}
			, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Alert> createAlert(@RequestBody Alert alert, @PathVariable("ebuNbr")int ebuNbr,
			@PathVariable("countryCode") String countryCode, @RequestParam("timeZone") Optional<String> timeZone){
			Alert oldAlert = null;
		try{
			oldAlert = alertService.getAlert(countryCode, ebuNbr);
		} catch (NoSuchElementException e) {
			oldAlert = new Alert();
			oldAlert.setAlertStatus(0);
			oldAlert.setAlertType(15);
			EBUid ebuID = new EBUid(countryCode, ebuNbr);
			oldAlert.setEbuId(ebuID);
		}
		//Check that the alert flag is not already raised AND that it's a new Express Order alert
		if (oldAlert.getAlertStatus() == 0 && oldAlert.getAlertType() == 15) {
			String s_timeZone = null;
			if (timeZone.isPresent()) {
				s_timeZone = timeZone.get();
				System.out.println(s_timeZone);
			}
			Alert newAlert = setNewAlert(alert, s_timeZone, countryCode, ebuNbr);
			return new ResponseEntity<>(this.createAlertServiceImpl.createAlert(newAlert), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(this.createAlertServiceImpl.createAlert(oldAlert), HttpStatus.CREATED);
		}
	}	
	
	@PutMapping(value= {"createAlert/{countryCode}/{ebuNbr}"} 
			, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Alert> updateAlert(@RequestBody Alert alert, @PathVariable("ebuNbr")int ebuNbr,
			@PathVariable("countryCode") String countryCode, @RequestParam("timeZone") Optional<String> timeZone){
		Alert oldAlert = alertService.getAlert(countryCode, ebuNbr);
		
		//Check that the alert flag is not already raised AND that it's a new Express Order alert
		if (oldAlert.getAlertStatus() == 0 && oldAlert.getAlertType() == 15) {
			String s_timeZone = null;
			if (timeZone.isPresent()) {
				System.out.println(timeZone.get());
				s_timeZone = timeZone.get();
			}
			Alert newAlert = setNewAlert(alert, s_timeZone, countryCode, ebuNbr);
			createAlertServiceImpl.updateAlert(newAlert);
			return new ResponseEntity<>(alert, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(oldAlert, HttpStatus.OK);
		}
	}
	
	public Alert setNewAlert(Alert alert, String timeZone, String countryCode, int ebuNbr) {
		alert.setAlertStatus(1);
		LocalDateTime currentGmtTime = ZonedDateTime.now(ZoneId.of("GMT")).toLocalDateTime();
		alert.setLastAlertGmt(currentGmtTime);
		
		ZoneId localTimeZone = validateTimeZone(timeZone, countryCode, ebuNbr);
		LocalDateTime localTime = ZonedDateTime.now(localTimeZone).toLocalDateTime();
		alert.setLastAlertLtz(localTime);
		
		return alert;
	}
	
	public ZoneId validateTimeZone (String timeZone, String countryCode, int ebuNbr) {
		ZoneId zone;
		try {
			zone = ZoneId.of(timeZone);
			return zone;
		} catch (ZoneRulesException e){
			String storeTimeZone = getEBUTimeZone(countryCode, ebuNbr);
			System.out.println("Invalid time zone., instead using: " + storeTimeZone);
			zone = ZoneId.of(storeTimeZone);
			return zone;
		} catch (DateTimeException e) {
			String storeTimeZone = getEBUTimeZone(countryCode, ebuNbr);
			System.out.println("Invalid time zone format, instead using: " + storeTimeZone);
			zone = ZoneId.of(storeTimeZone);
			return zone;
		} catch (NullPointerException e) {
			String storeTimeZone = getEBUTimeZone(countryCode, ebuNbr);
			System.out.println("Null Time Zone, instead using: " + storeTimeZone);
			zone = ZoneId.of(storeTimeZone);
			return zone;
		}
	}

	private String getEBUTimeZone(String countryCode, int ebuNbr) {
		EBUInfo ebuInfo = ebuInfoService.getInfo(countryCode, ebuNbr);
		String storeTimeZone = ebuInfo.getTimezone();
		return storeTimeZone;
	}
}
