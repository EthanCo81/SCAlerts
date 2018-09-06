package com.example.demo.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Alert;
import com.example.beans.AlertHistory;
import com.example.demo.services.AlertService;
import com.example.demo.services.EBUInfoService;
import com.example.demo.services.HistoryService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@ApiModel(value = "AlertController", description = "A rest controller to handle HTTP Requests made to /alert/{countryCode}/{ebuNbr}")
public class AlertController {
	@Autowired
	private AlertService alertService;	

	@Autowired
	private HistoryService historyService;
	
	@Autowired
	EBUInfoService ebuInfoService;
	
	/**
	 * Retrieves a 1-hour Alert
	 * 
	 * @param ebuNbr - Store number
	 * @param countryCode - The country code e.g. "US"
	 * @return alert and HTTP status code
	 */
	@ApiOperation(value = "Retrieves a 1-hour Alert", response = Alert.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Alert retrieved") } )	
	@RequestMapping(value = "/alert/{countryCode}/{ebuNbr}", method = RequestMethod.GET)
	public ResponseEntity<Alert> getAlert(
		
			@PathVariable("countryCode") String countryCode,
			@PathVariable("ebuNbr") int ebuNbr
	){
		try {
			Alert a = alertService.getAlert(countryCode, ebuNbr);
			return new ResponseEntity<> (a, HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Retrieves the AlertHistory
	 * 
	 * @param ebuNbr - Store number
	 * @param countryCode - The country code e.g. "US"
	 * @return alert and HTTP status code
	 */
	@ApiOperation(value = "Retrieves the AlertHistory", response = AlertHistory.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "AlertHistory retrieved") } )	
	@RequestMapping(value = "/alert/history/{countryCode}/{ebuNbr}", method = RequestMethod.GET)
	public ResponseEntity<List<AlertHistory>> getAlertHistory(
		
			@PathVariable("countryCode") String countryCode,
			@PathVariable("ebuNbr") int ebuNbr
	){
		List<AlertHistory> history = historyService.getAllHistories(countryCode, ebuNbr);
		return new ResponseEntity<> (history, HttpStatus.OK);
	}
	
	

	/**
	 * Creates or updates a new 1-hour Alert
	 * 
	 * @param ebuNbr - Store number
	 * @param countryCode - The country code e.g. "US"
	 * @param timeZone - Optional request string to use a cached timeZone (if exists)
	 * @return alert and HTTP status code
	 */
	@ApiOperation(value = "Adds a new 1-hour Alert", response = Alert.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Created alert returned") } )
	@PostMapping(value= {"alert/{countryCode}/{ebuNbr}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<Alert> createAlert(		
			@PathVariable("ebuNbr")int ebuNbr,
			@PathVariable("countryCode") String countryCode,
			@RequestParam("timeZone") Optional<String> timeZone,
			@RequestParam("alertType") int alertType
	){
		//Lookup the Alert by countryCode and ebuNbr. If no Alert exists for that store, create a new Alert.
		Alert alert = alertService.createAlert(new Alert(), countryCode, ebuNbr, timeZone, alertType);
		return new ResponseEntity<>(alert, HttpStatus.CREATED);		
	}	
	
	/**
	 * Updates a new 1-hour Alert
	 * 
	 * @param ebuNbr - Store number
	 * @param countryCode - The country code e.g. "US"
	 * @param timeZone - Optional request string to use a cached timeZone (if exists)
	 * @return alert and HTTP status code
	 */
	@ApiOperation(value = "Updates a new 1-hour Alert", response = Alert.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Alert updated and returned") } )
	@PutMapping(value= {"alert/{countryCode}/{ebuNbr}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<Alert> updateAlert(		
			@PathVariable("ebuNbr")int ebuNbr,
			@PathVariable("countryCode") String countryCode,
			@RequestParam("timeZone") Optional<String> timeZone,
			@RequestParam("alertType") int alertType
	){
		//Lookup the Alert by countryCode and ebuNbr. If no Alert exists for that store, return error.
		Alert alert = alertService.updateAlert(new Alert(), countryCode, ebuNbr, timeZone, alertType);
		
		if (alert != null) {
			return new ResponseEntity<>(alert, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	
}
