package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.beans.Alert;
import com.example.beans.EBUid;

import io.swagger.annotations.ApiModel;

/**
 * Interface for Acknowledge service layer
 * 
 * @author Larry Kang
 *
 */
@Service
@ApiModel (value = "AcknowledgeService", description = "Implementation for the Acknowledge service layer")
public interface AcknowledgeService {

	/**
	 * Creates a new Alert in the DB
	 * 
	 * @param alert - the Alert to be inserted into the DB
	 * @return Alert - the alert that was inserted into the DB
	 */
	@Transactional
	public Alert createAlert(Alert alert);
	
	
	/**
	 * Gets an alert by EBUid
	 * 
	 * @param ebuId - the composite ID of an alert, consisting of countryCode and ebuNbr
	 * @return Alert - the retrieved Alert
	 */
	public Alert readAlert(EBUid ebuId);
	
	
	/**
	 * Retrieves a list of alerts
	 * 
	 * @return List<Alert> - A list of all alerts
	 */
	public List<Alert> readAllAlerts();

	
	/**
	 * Updates an existing alert
	 * 
	 * @param alert - The alert to be updated
	 */
	@Transactional
	public void updateAlert(Alert alert);

	/**
	 * Deletes an alert from the database
	 * 
	 * @param ebuId - the composite ID of the alert, consisting of countryCode and ebuNbr
	 */
	@Transactional
	public void deleteAlert(EBUid ebuId);

	/**
	 * Gets an EBUid from a given countryCode and ebuNbr
	 * 
	 * @param countryCode - a String representing a country
	 * @param ebuNbr - the store/club number of an EBU in a specific country
	 * @return EBUid - the composite ID of an alert
	 */
	public EBUid getEbuId(String countryCode, int ebuNbr);
}
