package com.example.demo.controllers;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Alert;
import com.example.beans.AlertHistory;
import com.example.beans.AlertHistoryId;
import com.example.beans.EBUid;
import com.example.demo.services.AcknowledgeService;
import com.example.demo.services.HistoryService;



@RestController

public class AcknowledgeController {



	@Autowired

	private AcknowledgeService acknowledgeService;

	

	@Autowired

	private HistoryService historyService;

	

	@RequestMapping("/TestAlert")

	public String testAlert() {

		return acknowledgeService.readAllAlerts().toString();

	}

	

	@RequestMapping(value="/acknowledge/{country_code}/{ebu_nbr}", method=RequestMethod.POST)

	public ResponseEntity<HttpStatus> acknowledgeAlert(@PathVariable("country_code") String countryCode, @PathVariable("ebu_nbr") Integer ebuId){



		//read alert identity from URL

		EBUid EBUid = new EBUid();

		EBUid.setCountryCode(countryCode);

		EBUid.setEbuNbr(ebuId);

		//check alert status (to see if alert history needs to be updated)

		Alert alert = new Alert();

		alert = acknowledgeService.readAlert(EBUid);
		
		if(alert.getAlertStatus() == 1 && alert.getAlertType() == 15) {
			
			//update alert status in "alert" table

			alert.setAlertStatus(0);

			acknowledgeService.updateAlert(alert);

			

			//update alert end time in "alert_history" table
			
			AlertHistoryId alertHistoryId = new AlertHistoryId();
			
			alertHistoryId.setCountryCode(countryCode);
			
			alertHistoryId.setEbuNbr(ebuId);
			
			alertHistoryId.setAlertType(alert.getAlertType());
			
			alertHistoryId.setAlertStartGmt(alert.getLastAlertGmt());

			AlertHistory alertHistory = new AlertHistory();

			alertHistory = historyService.readAlertHistory(alertHistoryId);
			
			alertHistory.setAlertStartGmt(alert.getLastAlertGmt());
		
			alertHistory.setAlertStartLtz(alert.getLastAlertLtz());

			alertHistory.setAlertEndGmt(ZonedDateTime.now(ZoneId.of("GMT")).toLocalDateTime());

			alertHistory.setAlertEndLtz(ZonedDateTime.now().toLocalDateTime());

			historyService.createAlertHistory(alertHistory);
			


			//return "OK" http status code

			return new ResponseEntity<>(HttpStatus.OK);

		}
		
		else {
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		}

	}

	

}
