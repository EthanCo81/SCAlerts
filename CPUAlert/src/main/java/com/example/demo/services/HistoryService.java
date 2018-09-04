package com.example.demo.services;

import java.util.List;

import com.example.beans.AlertHistory;
import com.example.beans.AlertHistoryId;

public interface HistoryService {

	public AlertHistory createAlertHistory(AlertHistory alertHistory);
	
	public AlertHistory readAlertHistory(AlertHistoryId alertHistoryId);
	
	public List<AlertHistory> getAllHistories(String countryCode, int ebuNbr);
	
	public void updateAlertHistory(AlertHistory alertHistory);
	
	public void deleteAlertHistory(AlertHistoryId alertHistoryId);
	
}
