package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.AcknowledgeRepository;

import io.swagger.annotations.ApiModel;

import com.example.beans.Alert;
import com.example.beans.EBUid;


/**
 * Implementation for Acknowledge service layer
 * 
 * @author Larry Kang
 *
 */
@Service
@ApiModel (value = "AcknowledgeService", description = "Implementation for the Acknowledge service layer")
public class AcknowledgeService {

	

	//wiring in dao for persistence methods

	@Autowired

	private AcknowledgeRepository ad;

	

	/**
	 * Creates a new alert
	 * 
	 * @param alert - the Alert to be created
	 * @return Alert - The new alert that was created
	 */

	@Transactional

	public Alert createAlert(Alert alert) {

		return ad.save(alert);

	}

	

	/**
	 * Gets an alert by EBUid
	 * 
	 * @param EBUid - The EBUid composed of countryCode and EbuNbr
	 * @return Alert - the retrieved Alert
	 */

	public Alert readAlert(EBUid EBUid) {

		return ad.findById(EBUid).get();

	}

	

	/**
	 * Retrieves a list of alerts
	 * 
	 * @return List<Alert> - A list of all alerts
	 */

	public List<Alert> readAllAlerts(){

		List<Alert> alerts = new ArrayList<>();

		ad.findAll().forEach(alerts::add);

		return alerts;

	}

	

	/**
	 * Updates an existing alert
	 * 
	 * @param alert - The alert to be updated
	 */

	@Transactional

	public void updateAlert(Alert alert) {

		ad.save(alert);

	}

	

	/**
	 * Deletes an alert from the database
	 * 
	 * @param EBUid - The composed ID of the alert by countryCode and ebuNbr
	 */

	@Transactional

	public void deleteAlert(EBUid EBUid) {

		ad.deleteById(EBUid);

	}

	

}
