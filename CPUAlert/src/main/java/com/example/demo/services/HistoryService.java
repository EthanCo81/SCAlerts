package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.beans.Alert;
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
	 * Creates a new AlertHistory in the DB
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
	 * Deletes an AlertHistory from the database
	 * 
	 * @param alertHistoryId - The ID of the AlertHistory to be deleted
	 */
	public void deleteAlertHistory(AlertHistoryId alertHistoryId);

	/**
	 * Gets an AlertHistoryId from a given countryCode, ebuNbr, and Alert
	 * 
	 * @param countryCode - a String representing a country 
	 * @param ebuNbr - the store/club number of an EBU in a specific country
	 * @param alert - the last triggered alert which will be used to timestamp alertStart
	 * @return AlertHistoryId - the composite ID of an AlertHistory
	 */
	public AlertHistoryId getAlertHistoryId(String countryCode, int ebuNbr, Alert alert);

	/**
	 * 
	 * @param alert - the alert from which the latest timestamp is pulled
	 * @param alertHistoryId - the composite ID of alertHistory
	 * @param timeZone - a String representing some time zone
	 * @param countryCode - a String representing a country
	 * @param ebuNbr - the store/club number of an EBU in a specific country
	 */
	public void configureAndInsertAlertHistory(Alert alert, AlertHistoryId alertHistoryId, Optional<String> timeZone, String countryCode, int ebuNbr);
}
