package com.example.demo.controllers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
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
import com.example.beans.AlertHistory;
import com.example.beans.AlertHistoryId;
import com.example.beans.EBUid;
import com.example.demo.services.AcknowledgeService;
import com.example.demo.services.EBUInfoService;
import com.example.demo.services.HistoryService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@ApiModel(value = "AlertController", description = "A rest controller to handle HTTP Posts made to /acknowledge/{countryCode}/{ebuNbr}")
public class AcknowledgeController {

	@Autowired
	private AcknowledgeService acknowledgeService;

	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private EBUInfoService ebuInfoService;
	
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

		//get ebuId from URL
		EBUid ebuId = new EBUid();
		ebuId = acknowledgeService.getEbuId(countryCode, ebuNbr);

		//read alert from URL and validate inputs
		Alert alert = new Alert();
		try {
			alert = acknowledgeService.readAlert(ebuId);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		//if alert status is 1, update alert status in "alert" table
		if(alert.getAlertStatus() == 1 && alert.getAlertType() == 15) {
			
			//update alert status to 0
			alert.setAlertStatus(0);
			acknowledgeService.updateAlert(alert);
						
			//check if timezone is an input, otherwise pull from ebu_info table in DB
			String s_timeZone = null;
			if (timeZone.isPresent()) {
				s_timeZone = timeZone.get();
			}
			else {	
				s_timeZone = ebuInfoService.getInfo(countryCode, ebuNbr).getTimezone();
			}
			
			//get alert history id from the URL & latest alert, and also validate inputs
			AlertHistoryId alertHistoryId = new AlertHistoryId();
			try {
				alertHistoryId = historyService.getAlertHistoryId(countryCode, ebuNbr, alert);
			} catch(NoSuchElementException e) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			
			//insert alert history into "alert_history" table
			AlertHistory alertHistory = new AlertHistory();
			alertHistory.setAlertHistoryId(alertHistoryId);
			alertHistory.setAlertStartLtz(alert.getLastAlertLtz());
			alertHistory.setAlertEndGmt(ZonedDateTime.now(ZoneId.of("GMT")).toLocalDateTime());
			alertHistory.setAlertEndLtz(ZonedDateTime.now(ZoneId.of(s_timeZone)).toLocalDateTime());
			historyService.createAlertHistory(alertHistory);

			//return "OK" http status code
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		//otherwise no alert is active, so do nothing and return OK status
		else {	
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
