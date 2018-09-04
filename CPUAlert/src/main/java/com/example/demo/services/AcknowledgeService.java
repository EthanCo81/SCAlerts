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
	 * Creates a new alert
	 * 
	 * @param alert - the Alert to be created
	 * @return Alert - The new alert that was created
	 */

	@Transactional

	public Alert createAlert(Alert alert);

	

	/**
	 * Gets an alert by EBUid
	 * 
	 * @param EBUid - The EBUid composed of countryCode and EbuNbr
	 * @return Alert - the retrieved Alert
	 */

	public Alert readAlert(EBUid EBUid);

	

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
	 * @param EBUid - The composed ID of the alert by countryCode and ebuNbr
	 */

	@Transactional

	public void deleteAlert(EBUid EBUid);

}
