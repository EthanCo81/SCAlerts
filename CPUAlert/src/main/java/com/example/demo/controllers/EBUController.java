package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.EBUInfo;
import com.example.demo.services.EBUInfoService;

@RestController
public class EBUController {
	
	@Autowired
	private EBUInfoService ebuService;
	
	@RequestMapping(value = "/ebu/{countryCode}/{ebuNbr}", method = RequestMethod.GET)
	public ResponseEntity<EBUInfo> getInfo(@PathVariable("countryCode") String countryCode, @PathVariable("ebuNbr") int ebuNbr){
		EBUInfo info = ebuService.getInfo(countryCode, ebuNbr);
		return new ResponseEntity<> (info, HttpStatus.OK);
	}

}
