package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.beans.Alert;

import io.swagger.annotations.ApiModel;

/**
 * Interface for Alert service layer
 * 
 * @author Thomas Santillan | Neil Ferman
 * 
 */
@ApiModel(value = "AlertService", description = "Provides interface for the Alert service layer")
public interface AlertService {
		
	/**
	 * Gets alert for a given EBU if one exists
	 * 
	 * @param ebuNbr - The store location's number
	 * @return Alert - The retrieved Alert
	 */
	public Alert getAlert(String countryCode, int ebuNbr);
	
	/**
	 * Gets all alerts in database
	 * @return List<Alert> - The list of all Alert
	 */
	public List<Alert> getAllAlerts();
	
	/**
	 * 
	 * @param alert - The Alert to be created
	 * @return The created Alert.
	 */
	public Alert createAlert(Alert alert, String countryCode, int ebuNbr, Optional<String> timeZone, int alertType);
	
	/**
	 * 
	 * @param alert - The Alert to be updated
	 * @param countryCode - The 2 letter code for the country e.g. "US"
	 * @param ebuNbr - The store location's number
	 * @param timeZone - The time zone sent from the cache of the web service
	 * @param alertType The code number for the type of alert e.g. 15 (Express Order)
	 */
	public Alert updateAlert(Alert alert, String countryCode, int ebuNbr, Optional<String> timeZone, int alertType);
	
	/**
	 * sets alert status to 0 and persists to DB
	 * 
	 * @param a -alert updated
	 */
	public void acknowledgeAlert(Alert a);
	/**
	 * Changes the Alert flag from 0 to 1, and timestamps the alert
	 * 
	 * @param oldAlert - The Alert to be changed
	 * @param s_timeZone - The time zone sent from the cache of the web service
	 * @param countryCode - The 2 letter code for the country e.g. "US"
	 * @param ebuNbr - The store location's number
	 * @return - the modified Alert
	 */
	public Alert setNewAlert(Alert oldAlert, String s_timeZone, String countryCode, int ebuNbr);
	
	/**
	 * If no alert is found in the system but the ebuNbr is valid,
	 * create a new alert to put into the system
	 * 
	 * @param countryCode - The 2 letter code for the country e.g. "US"
	 * @param ebuNbr - The store location's number
	 * @param alertType - The code number for the type of alert e.g. 15 (Express Order)
	 * @return - the newly created alert
	 */
	public Alert setOldAlert(String countryCode, int ebuNbr, int alertType);
}
