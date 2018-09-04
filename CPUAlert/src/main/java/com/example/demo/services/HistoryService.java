package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.beans.AlertHistory;
import com.example.beans.AlertHistoryId;

import io.swagger.annotations.ApiModel;


/**
 * Interface for History service layer
 * 
 * @author Larry Kang
 *
 */
@Service
@ApiModel(value = "HistoryService", description = "Implementation for the History service layer")
public interface HistoryService {
	

	/**
	 * Creates a new AlertHistory
	 * 
	 * @param alertHistory - The AlertHistory to be created
	 * @return AlertHistory - The AlertHistory that was created
	 */
	public AlertHistory createAlertHistory(AlertHistory alertHistory);

	

	/**
	 * Retrieves an AlertHistory by Id
	 * 
	 * @param alertHistoryId - The ID of the AlertHistory
	 * @return AlertHistory - The AlertHistory that was retrieved
	 */
	public AlertHistory readAlertHistory(AlertHistoryId alertHistoryId);

	

	/**
	 * Retrieves all AlertHistories
	 * 
	 * @return List<AlertHistor> - A list of all AlertHistories
	 */

	public List<AlertHistory> getAllHistories(String countryCode, int ebuNbr);

	

	/**
	 * Updates an AlertHistory
	 * 
	 * @param alertHistory - The AlertHistory to be updated
	 */

	public void updateAlertHistory(AlertHistory alertHistory);

	

	/**
	 * Deletes an AlertHistory from database
	 * 
	 * @param alertHistoryId - The ID of the AlertHistory to be deleted
	 */
	public void deleteAlertHistory(AlertHistoryId alertHistoryId);

}
