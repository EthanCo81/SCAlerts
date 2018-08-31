package com.example.controllers;

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
			alert.setAlertStatus(1);
			if (timeZone.isPresent()) {
				//alert.setLastAlertLtz(lastAlertLtz);
			}
			return new ResponseEntity<>(this.createAlertService.createAlert(alert), HttpStatus.CREATED);
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
			alert.setAlertStatus(1);
			if (timeZone.isPresent()) {
				//alert.setLastAlertLtz(lastAlertLtz);
			}
			createAlertService.updateAlert(alert);
			return new ResponseEntity<>(alert, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(oldAlert, HttpStatus.OK);
		}
	}
}
