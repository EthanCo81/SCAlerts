package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.AlertHistory;
import com.example.beans.AlertHistoryId;
import com.example.demo.data.HistoryRepository;

import io.swagger.annotations.ApiModel;


/**
 * Implementation for History service layer
 * 
 * @author Larry Kang
 *
 */
@Service
@ApiModel(value = "HistoryService", description = "Implementation for the History service layer")
public class HistoryService {



	//wiring in Repository for persistence methods

	@Autowired

	private HistoryRepository hd;

	

	/**
	 * Creates a new AlertHistory
	 * 
	 * @param alertHistory - The AlertHistory to be created
	 * @return AlertHistory - The AlertHistory that was created
	 */

	@Transactional

	public AlertHistory createAlertHistory(AlertHistory alertHistory) {

		return hd.save(alertHistory);

	}

	

	/**
	 * Retrieves an AlertHistory by Id
	 * 
	 * @param alertHistoryId - The ID of the AlertHistory
	 * @return AlertHistory - The AlertHistory that was retrieved
	 */

	@Transactional

	public AlertHistory readAlertHistory(AlertHistoryId alertHistoryId) {

		return hd.getOne(alertHistoryId);

	}

	

	/**
	 * Retrieves all AlertHistories
	 * 
	 * @return List<AlertHistor> - A list of all AlertHistories
	 */

	@Transactional

	public List<AlertHistory> readAllAlertHistories(){

		List<AlertHistory> alertHistories = new ArrayList<>();

		hd.findAll().forEach(alertHistories::add);

		return alertHistories;

	}

	

	/**
	 * Updates an AlertHistory
	 * 
	 * @param alertHistory - The AlertHistory to be updated
	 */

	@Transactional

	public void updateAlertHistory(AlertHistory alertHistory) {

		hd.save(alertHistory);

	}

	

	/**
	 * Deletes an AlertHistory from database
	 * 
	 * @param alertHistoryId - The ID of the AlertHistory to be deleted
	 */

	@Transactional

	public void deleteAlertHistory(AlertHistoryId alertHistoryId) {

		hd.deleteById(alertHistoryId);

	}

}