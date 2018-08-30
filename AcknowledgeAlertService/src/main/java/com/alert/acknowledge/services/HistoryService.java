package com.alert.acknowledge.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alert.acknowledge.beans.AlertHistory;
import com.alert.acknowledge.daos.HistoryDAO;

@Service
public class HistoryService {

	//wiring in dao for persistence methods
	@Autowired
	private HistoryDAO hd;
	
	//create method
	@Transactional
	public AlertHistory createAlertHistory(AlertHistory alertHistory) {
		return hd.save(alertHistory);
	}
	
	//read by id method
	@Transactional
	public AlertHistory readAlertHistory(int ebuId) {
		return hd.getOne(ebuId);
	}
	
	//read all alerts method
	@Transactional
	public List<AlertHistory> readAllAlertHistories(){
		List<AlertHistory> alertHistories = new ArrayList<>();
		hd.findAll().forEach(alertHistories::add);
		return alertHistories;
	}
	
	//update method
	@Transactional
	public void updateAlertHistory(AlertHistory alertHistory) {
		hd.save(alertHistory);
	}
	
	//delete by id method
	@Transactional
	public void deleteAlertHistory(int ebuId) {
		hd.deleteById(ebuId);
	}
}
