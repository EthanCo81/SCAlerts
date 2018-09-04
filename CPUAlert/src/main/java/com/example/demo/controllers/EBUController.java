package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Alert;
import com.example.beans.EBUInfo;
import com.example.demo.services.EBUInfoService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@ApiModel(value = "EBUController", description = "A rest controller to handle HTTP Gets made to /ebu/{countryCode}/{ebuNbr}")
public class EBUController {
	
	@Autowired
	private EBUInfoService ebuService;
	
	/**
	 * Retrieves EBUInfo for a store location
	 * @param ebuNbr - Store number
	 * @param countryCode - The country code e.g. "US"
	 * @return EBUInfo and HTTP status code
	 */
	@ApiOperation(value = "Retrieves EBUInfo for a store location", response = Alert.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "EBUInfo retrieved") } )
	@RequestMapping(value = "/ebu/{countryCode}/{ebuNbr}", method = RequestMethod.GET)
	public ResponseEntity<EBUInfo> getInfo(@PathVariable("countryCode") String countryCode, @PathVariable("ebuNbr") int ebuNbr){
		EBUInfo info = ebuService.getInfo(countryCode, ebuNbr);
		if (info != null) {
			return new ResponseEntity<> (info, HttpStatus.OK);
		}
		return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
	}

}
