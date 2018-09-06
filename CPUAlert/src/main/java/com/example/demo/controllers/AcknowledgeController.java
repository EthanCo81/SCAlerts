package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Alert;
import com.example.demo.services.AlertService;
import com.example.demo.services.HistoryService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@ApiModel(value = "AlertController", description = "A rest controller to handle HTTP Posts made to /acknowledge/{countryCode}/{ebuNbr}")
public class AcknowledgeController {

	@Autowired AlertService alertService;

	@Autowired
	private HistoryService historyService;
		
	/**
	 * Acknowledges an Alert by updating the alert status and inserts a new Alert History
	 * @param ebuNbr - Store number
	 * @param countryCode - The country code e.g. "US"
	 * @return HTTP status code
	 */
	@ApiOperation(value = "Updates Alert status and inserts a new Alert History", response = Alert.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Alert status updated and Alert History inserted") } )
	@RequestMapping(value="/acknowledge/{country_code}/{ebu_nbr}", method=RequestMethod.POST)

	public ResponseEntity<HttpStatus> acknowledgeAlert(@PathVariable("country_code") String countryCode, @PathVariable("ebu_nbr") Integer ebuNbr, @RequestParam("timeZone") Optional<String> timeZone){
		Alert alert = alertService.getAlert(countryCode, ebuNbr);
		if(alert == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		alertService.acknowledgeAlert(alert);
		historyService.configureAndInsertAlertHistory(alert, timeZone, countryCode, ebuNbr);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
