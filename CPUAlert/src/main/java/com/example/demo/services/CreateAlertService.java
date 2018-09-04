package com.example.demo.services;

import com.example.beans.Alert;

import io.swagger.annotations.ApiModel;

/**
 * Interface for CreateAlert service layer
 * 
 * @author Neil Ferman
 * 
 */
@ApiModel(value = "CreateAlertService", description = "Provides interface for the CreateAlert service layer")
public interface CreateAlertService {
	
	/**
	 * 
	 * @param alert - The Alert to be created
	 * @return The created Alert.
	 */
	public Alert createAlert(Alert alert);
	
	/**
	 * 
	 * @param alert - The Alert to be updated
	 */
	public void updateAlert(Alert alert);
}
