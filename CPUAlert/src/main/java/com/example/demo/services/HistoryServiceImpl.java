package com.example.demo.services;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.Alert;
import com.example.beans.AlertHistory;
import com.example.beans.AlertHistoryId;
import com.example.demo.data.HistoryRepository;


/**
 * Implementation of the History service layer
 * 
 * @author Larry Kang
 *
 */
@Service

public class HistoryServiceImpl implements HistoryService{

	//wiring in Repository for persistence methods
	@Autowired
	private HistoryRepository hd;
	
	//wiring in ebuInfoService for timeZone methods
	@Autowired
	private EBUInfoService ebuInfoService;
	
	//create method
	@Transactional
	public AlertHistory createAlertHistory(AlertHistory alertHistory) {
		return hd.save(alertHistory);
	}

	//read by id method
	@Transactional
	public AlertHistory readAlertHistory(AlertHistoryId alertHistoryId) {
		return hd.getOne(alertHistoryId);
	}
	
	@Transactional
	//read all alerts method
		public List<AlertHistory> getAllHistories(String countryCode, int ebuNbr) {
			//List<AlertHistory> history = hd.getAllHistories(countryCode, ebuNbr);
			List<AlertHistory> history = hd.findByAlertHistoryId_CountryCodeAndAlertHistoryId_EbuNbrOrderByAlertStartLtzDesc(countryCode, ebuNbr);
			return history;	
	}

	//update method
	@Transactional
	public void updateAlertHistory(AlertHistory alertHistory) {
		hd.save(alertHistory);
	}

	//delete by id method
	@Transactional
	public void deleteAlertHistory(AlertHistoryId alertHistoryId) {
		hd.deleteById(alertHistoryId);
	}
	
	//get alertHistoryId method for getting alertHistoryId from the URL and the latest alert
	public AlertHistoryId getAlertHistoryId(String countryCode, int ebuNbr, Alert alert) {
		AlertHistoryId alertHistoryId = new AlertHistoryId();
		alertHistoryId.setCountryCode(countryCode);
		alertHistoryId.setEbuNbr(ebuNbr);
		alertHistoryId.setAlertType(alert.getAlertType());
		alertHistoryId.setAlertStartTime(alert.getLastAlertTime());
		return alertHistoryId;
	}
	
	//configureAndInsert alertHistory method for setting fields and inserting into DB
	public void configureAndInsertAlertHistory(Alert alert, AlertHistoryId alertHistoryId, Optional<String> timeZone, String countryCode, int ebuNbr) {
		
		//check if timezone is an input, otherwise pull from ebu_info table in DB
		String s_timeZone = null;
		if (timeZone.isPresent()) {
			s_timeZone = timeZone.get();
		}
		else {	
			s_timeZone = ebuInfoService.getInfo(countryCode, ebuNbr).getTimezone();
		}
		
		//set alertHistory fields
		AlertHistory alertHistory = new AlertHistory();
		alertHistory.setAlertHistoryId(alertHistoryId);
		alertHistory.setAlertEndTime(ZonedDateTime.now(ZoneId.of(s_timeZone)).toOffsetDateTime());
		
		//create new alertHistory 
		createAlertHistory(alertHistory);
	}
}