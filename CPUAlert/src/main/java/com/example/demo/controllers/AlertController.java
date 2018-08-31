package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Alert;
import com.example.demo.services.AlertService;

@RestController
public class AlertController {
	@Autowired
	private AlertService alertService;
	
	@RequestMapping(value = "/alert/{countryCode}/{ebuNbr}", method = RequestMethod.GET)
	public ResponseEntity<Alert> getAlert(@PathVariable("countryCode") String countryCode, @PathVariable("ebuNbr") int ebuNbr){
		Alert a = alertService.getAlert(countryCode, ebuNbr);
		return new ResponseEntity<> (a, HttpStatus.OK);
	}
}
