package com.example.controllers;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjuster;
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
import com.example.demo.CreateAlertService;
import com.example.demo.services.AlertService;

@RestController
@RequestMapping(value="/createalert")
public class CreateAlertController {
	@Autowired
	CreateAlertService createAlertService;
	
	@Autowired
	AlertService alertService;
	
	@PostMapping(value= {"/{ebuNbr}/{countryCode}/{timeZone}", "/{ebuNbr}/{countryCode}"}, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alert> createAlert(@Valid @RequestBody Alert alert, @PathVariable("ebuNbr")int ebuNbr,
			@PathVariable("countryCode") int countryCode, @PathVariable("timeZone") Optional<String> timeZone){
		Alert oldAlert = alertService.getAlert(ebuNbr);
		//Check that the alert flag is not already raised AND that it's a new Express Order alert
		if (oldAlert.getAlertStatus() == 0 && oldAlert.getAlertType() == 15) {
			String s_timeZone = null;
			if (timeZone.isPresent()) {
				s_timeZone = timeZone.get();
			}
			Alert newAlert = setNewAlert(alert, s_timeZone);
			return new ResponseEntity<>(this.createAlertService.createAlert(newAlert), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(this.createAlertService.createAlert(oldAlert), HttpStatus.CREATED);
		}
	}	
	
	@PutMapping(value= {"/{ebuNbr}/{countryCode}/{timeZone}", "/{ebuNbr}/{countryCode}"}, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alert> updateAlert(@Valid @RequestBody Alert alert, @PathVariable("ebuNbr")int ebuNbr,
			@PathVariable("countryCode") int countryCode, @PathVariable("timeZone") Optional<String> timeZone){
		Alert oldAlert = alertService.getAlert(ebuNbr);
		
		//Check that the alert flag is not already raised AND that it's a new Express Order alert
		if (oldAlert.getAlertStatus() == 0 && oldAlert.getAlertType() == 15) {
			String s_timeZone = null;
			if (timeZone.isPresent()) {
				s_timeZone = timeZone.get();
			}
			Alert newAlert = setNewAlert(alert, s_timeZone);
			createAlertService.updateAlert(newAlert);
			return new ResponseEntity<>(alert, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(oldAlert, HttpStatus.OK);
		}
	}
	
	public Alert setNewAlert(Alert alert, String timeZone) {
		alert.setAlertStatus(1);
		ZonedDateTime currentGmtTime = ZonedDateTime.now(ZoneId.of("GMT"));
		alert.setLastAlertGmt(currentGmtTime);
		if (timeZone != null) {
			ZonedDateTime localTime = ZonedDateTime.of(currentGmtTime.toLocalDateTime(), ZoneId.of(timeZone));
			alert.setLastAlertLtz(localTime);
		} else {
			
		}
		
		return alert;
	}
	
	public ZoneId validateTimeZone (String timeZone) {
		ZoneId zone;
		try {
			zone = ZoneId.of(timeZone);
			return zone;
		} catch (ZoneRulesException e){
			System.out.println("Invalid time zone.");
			zone = ZoneId.of("GMT");
			return zone;
		} catch (DateTimeException e) {
			System.out.println("Invalid time zone format");
			zone = ZoneId.of("GMT");
			return zone;
		}
	}
}
