package com.example.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Alert;
import com.example.demo.AlertService;

@RestController
@RequestMapping(name="/createalert")
public class AlertController {
	@Autowired
	AlertService alertService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alert> createAlert(@Valid @RequestBody Alert alert){
		return new ResponseEntity<>(this.alertService.createAlert(alert), HttpStatus.CREATED);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alert> updateAlert(@Valid @RequestBody Alert alert){
		alertService.updateAlert(alert);
		return new ResponseEntity<>(alert, HttpStatus.OK);
	}
}
