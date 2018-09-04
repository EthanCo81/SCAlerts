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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@ApiModel(value = "CreateAlertController", description = "A rest controller to handle HTTP Puts and Posts made to /createAlert/{countryCode}/{ebuNbr}")
public class CreateAlertController {
	@Autowired
	CreateAlertServiceImpl createAlertServiceImpl;
	
	@Autowired
	AlertService alertService;
	
	@Autowired
	EBUInfoService ebuInfoService;

	/**
	 * Creates or updates a new 1-hour Alert
	 * @param alert - transient alert
	 * @param ebuNbr - Store number
	 * @param countryCode - The country code e.g. "US"
	 * @param timeZone - Optional request string to use a cached timeZone (if exists)
	 * @return alert and HTTP status code
	 */
	@ApiOperation(value = "Adds a new 1-hour Alert", response = Alert.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Created alert returned") } )
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
			}
			Alert newAlert = setNewAlert(alert, s_timeZone, countryCode, ebuNbr);
			return new ResponseEntity<>(this.createAlertServiceImpl.createAlert(newAlert), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(this.createAlertServiceImpl.createAlert(oldAlert), HttpStatus.CREATED);
		}
	}	
	
	/**
	 * Updates a new 1-hour Alert
	 * @param alert - transient alert
	 * @param ebuNbr - Store number
	 * @param countryCode - The country code e.g. "US"
	 * @param timeZone - Optional request string to use a cached timeZone (if exists)
	 * @return alert and HTTP status code
	 */
	@ApiOperation(value = "Updates a new 1-hour Alert", response = Alert.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Updated alert returned") } )
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
			zone = ZoneId.of(storeTimeZone);
			return zone;
		} catch (DateTimeException e) {
			String storeTimeZone = getEBUTimeZone(countryCode, ebuNbr);
			zone = ZoneId.of(storeTimeZone);
			return zone;
		} catch (NullPointerException e) {
			String storeTimeZone = getEBUTimeZone(countryCode, ebuNbr);
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
