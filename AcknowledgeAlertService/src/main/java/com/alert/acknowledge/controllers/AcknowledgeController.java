package com.alert.acknowledge.controllers;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alert.acknowledge.beans.Alert;
import com.alert.acknowledge.beans.AlertHistory;
import com.alert.acknowledge.beans.AlertIdentity;
import com.alert.acknowledge.services.AcknowledgeService;
import com.alert.acknowledge.services.HistoryService;

@RestController
public class AcknowledgeController {

//	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//	String gmt = "31-08-2018 16:16:16";
//	String ltz = "31-08-2018 08:32:23";

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
		AlertIdentity alertIdentity = new AlertIdentity();
		alertIdentity.setCountryCode(countryCode);
		alertIdentity.setEbuId(ebuId);
		
		//update alert status in "alert" table
		Alert alert = new Alert();
		alert = acknowledgeService.readAlert(alertIdentity);
		alert.setAlertStatus(0);
		acknowledgeService.updateAlert(alert);
		
		//update alert end time in "alert_history" table
		AlertHistory alertHistory = new AlertHistory();
		alertHistory = historyService.readAlertHistory(alertIdentity);
		alertHistory.setAlertEndGmt(ZonedDateTime.now(ZoneId.of("GMT")));
		alertHistory.setAlertEndLtz(ZonedDateTime.now());
		historyService.updateAlertHistory(alertHistory);
		
		System.out.println(alertHistory.toString());
		
		//return "OK" http status code
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
