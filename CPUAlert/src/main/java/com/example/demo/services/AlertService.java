package com.example.demo.services;

import com.example.beans.Alert;

import io.swagger.annotations.ApiModel;

/**
 * Interface for Alert service layer
 * 
 * @author Thomas Santillan
 * 
 */
@ApiModel(value = "AlertService", description = "Provides interface for the Alert service layer")
public interface AlertService {
		
	/*
	 * Gets alert for a given EBU if one exists
	 * 
	 * @param ebuNbr - the store location's number
	 * @return Alert - the retrieved Alert
	 */
	public Alert getAlert(String countryCode, int ebuNbr);
}
