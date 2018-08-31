package com.example.controllers;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Alert;
import com.example.beans.EBUInfo;
import com.example.demo.CreateAlertService;
import com.example.demo.services.AlertService;
import com.example.demo.services.EBUInfoService;

@RestController
@RequestMapping(value="/createalert")
public class CreateAlertController {
	@Autowired
	CreateAlertService createAlertService;
	
	@Autowired
	AlertService alertService;
	
	@Autowired
	EBUInfoService ebuInfoService;
	
	@PostMapping(value= {"/{ebuNbr}/{countryCode}/{timeZone}", "/{ebuNbr}/{countryCode}"}, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alert> createAlert(@Valid @RequestBody Alert alert, @PathVariable("ebuNbr")int ebuNbr,
			@PathVariable("countryCode") String countryCode, @PathVariable("timeZone") Optional<String> timeZone){
		Alert oldAlert = alertService.getAlert(countryCode, ebuNbr);
		EBUInfo ebuInfo = ebuInfoService.getInfo(countryCode, ebuNbr);
		//Check that the alert flag is not already raised AND that it's a new Express Order alert
		if (oldAlert.getAlertStatus() == 0 && oldAlert.getAlertType() == 15) {
			String s_timeZone = null;
			if (timeZone.isPresent()) {
				s_timeZone = timeZone.get();
			}
			Alert newAlert = setNewAlert(alert, s_timeZone, ebuInfo);
			return new ResponseEntity<>(this.createAlertService.createAlert(newAlert), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(this.createAlertService.createAlert(oldAlert), HttpStatus.CREATED);
		}
	}	
	
	@PutMapping(value= {"/{ebuNbr}/{countryCode}/{timeZone}", "/{ebuNbr}/{countryCode}"}, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alert> updateAlert(@Valid @RequestBody Alert alert, @PathVariable("ebuNbr")int ebuNbr,
			@PathVariable("countryCode") String countryCode, @PathVariable("timeZone") Optional<String> timeZone){
		Alert oldAlert = alertService.getAlert(countryCode, ebuNbr);
		EBUInfo ebuInfo = ebuInfoService.getInfo(countryCode, ebuNbr);
		//Check that the alert flag is not already raised AND that it's a new Express Order alert
		if (oldAlert.getAlertStatus() == 0 && oldAlert.getAlertType() == 15) {
			String s_timeZone = null;
			if (timeZone.isPresent()) {
				s_timeZone = timeZone.get();
			}
			Alert newAlert = setNewAlert(alert, s_timeZone, ebuInfo);
			createAlertService.updateAlert(newAlert);
			return new ResponseEntity<>(alert, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(oldAlert, HttpStatus.OK);
		}
	}
	
	public Alert setNewAlert(Alert alert, String timeZone, EBUInfo ebuInfo) {
		alert.setAlertStatus(1);
		ZonedDateTime currentGmtTime = ZonedDateTime.now(ZoneId.of("GMT"));
		alert.setLastAlertGmt(currentGmtTime);
		ZoneId localTimeZone = validateTimeZone(timeZone, ebuInfo.getTimezone());
		
		ZonedDateTime localTime = ZonedDateTime.of(currentGmtTime.toLocalDateTime(), localTimeZone);
		alert.setLastAlertLtz(localTime);
		
		return alert;
	}
	
	public ZoneId validateTimeZone (String timeZone, String storeTimeZone) {
		ZoneId zone;
		try {
			zone = ZoneId.of(timeZone);
			return zone;
		} catch (ZoneRulesException e){
			System.out.println("Invalid time zone.");
			zone = ZoneId.of(storeTimeZone);
			return zone;
		} catch (DateTimeException e) {
			System.out.println("Invalid time zone format");
			zone = ZoneId.of(storeTimeZone);
			return zone;
		}
	}
}
