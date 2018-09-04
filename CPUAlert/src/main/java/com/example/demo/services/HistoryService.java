package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.AlertHistory;
import com.example.beans.EBUid;
import com.example.demo.data.HistoryRepository;



@Service

public class HistoryService {



	//wiring in Repository for persistence methods

	@Autowired

	private HistoryRepository hd;

	

	//create method

	@Transactional

	public AlertHistory createAlertHistory(AlertHistory alertHistory) {

		return hd.save(alertHistory);

	}

	

	//read by id method

	@Transactional

	public AlertHistory readAlertHistory(EBUid EBUid) {

		return hd.getOne(EBUid);

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

	public void deleteAlertHistory(EBUid EBUid) {

		hd.deleteById(EBUid);

	}

}