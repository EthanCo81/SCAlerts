package com.example.demo.controllers;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Alert;
import com.example.beans.AlertHistory;
import com.example.beans.EBUInfo;
import com.example.beans.EBUid;
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
	 * @param ebuNbr - Store number
	 * @param countryCode - The country code e.g. "US"
	 * @return alert and HTTP status code
	 */
	@ApiOperation(value = "Retrieves a 1-hour Alert", response = Alert.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Alert retrieved") } )	
	@RequestMapping(value = "/alert/{countryCode}/{ebuNbr}", method = RequestMethod.GET)
	public ResponseEntity<Alert> getAlert(@PathVariable("countryCode") String countryCode, @PathVariable("ebuNbr") int ebuNbr){
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
	public ResponseEntity<List<AlertHistory>> getAlertHistory(@PathVariable("countryCode") String countryCode, @PathVariable("ebuNbr") int ebuNbr){
		List<AlertHistory> history = historyService.getAllHistories(countryCode, ebuNbr);
		return new ResponseEntity<> (history, HttpStatus.OK);
	}
	
	

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
	@PostMapping(value= {"alert/{countryCode}/{ebuNbr}"}
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
			return new ResponseEntity<>(this.alertService.createAlert(newAlert), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(this.alertService.createAlert(oldAlert), HttpStatus.CREATED);
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
	@PutMapping(value= {"alert/{countryCode}/{ebuNbr}"} 
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
			alertService.updateAlert(newAlert);
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
