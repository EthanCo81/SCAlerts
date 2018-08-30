package com.alert.acknowledge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alert.acknowledge.beans.Alert;
import com.alert.acknowledge.services.AcknowledgeService;

@RestController
public class AcknowledgeController {

	@Autowired
	private AcknowledgeService acknowledgeService;
	
	@RequestMapping(value="/acknowledge/{country_code}/{ebu_nbr}/", method=RequestMethod.POST)
	public void acknowledgeAlert(@PathVariable("ebuId") Integer ebuId, @PathVariable("country_code") String countryCode){
		Alert alert = new Alert();
		alert = acknowledgeService.readAlert(ebuId);
		alert.setAlertStatus(0);
		acknowledgeService.updateAlert(alert);
	}
	
}
