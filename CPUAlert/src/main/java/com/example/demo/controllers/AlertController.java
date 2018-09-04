package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Alert;
import com.example.beans.AlertHistory;
import com.example.demo.services.AlertService;
import com.example.demo.services.HistoryService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@ApiModel(value = "AlertController", description = "A rest controller to handle HTTP Gets made to /alert/{countryCode}/{ebuNbr}")
public class AlertController {
	@Autowired
	private AlertService alertService;
	

	@Autowired
	private HistoryService historyService;
	
	/**
	 * Retrieves a 1-hour Alert
	 * @param ebuNbr - Store number
	 * @param countryCode - The country code e.g. "US"
	 * @return alert and HTTP status code
	 */
	@ApiOperation(value = "Retrieves a 1-hour Alert", response = Alert.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Alert retrieved") } )	
	@RequestMapping(value = "/alert/{countryCode}/{ebuNbr}", method = RequestMethod.GET)
	public ResponseEntity<Alert> getAlert(@PathVariable("countryCode") String countryCode, @PathVariable("ebuNbr") int ebuNbr){
		Alert a = alertService.getAlert(countryCode, ebuNbr);
		return new ResponseEntity<> (a, HttpStatus.OK);
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
	public ResponseEntity<List<AlertHistory>> getAlertHistory(@PathVariable("countryCode") String countryCode, @PathVariable("ebuNbr") int ebuNbr){
		List<AlertHistory> history = historyService.getAllHistories(countryCode, ebuNbr);
		return new ResponseEntity<> (history, HttpStatus.OK);
	}
}
