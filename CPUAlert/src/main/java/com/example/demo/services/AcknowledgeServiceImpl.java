package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.Alert;
import com.example.beans.EBUid;
import com.example.demo.data.AlertRepository;


/**
 * Implementation of the Acknowledge service layer
 * 
 * @author Larry Kang
 *
 */
@Service

public class AcknowledgeServiceImpl implements AcknowledgeService{

	//wiring in dao for persistence methods
	@Autowired
	private AlertRepository ad;

	//create method
	@Transactional
	public Alert createAlert(Alert alert) {
		return ad.save(alert);
	}

	//read by id method
	public Alert readAlert(EBUid ebuId) {
		return ad.findById(ebuId).get();
	}

	//read all alerts method
	public List<Alert> readAllAlerts(){
		List<Alert> alerts = new ArrayList<>();
		ad.findAll().forEach(alerts::add);
		return alerts;
	}

	//update method
	@Transactional
	public void updateAlert(Alert alert) {
		ad.save(alert);
	}

	//delete by id method
	@Transactional
	public void deleteAlert(EBUid ebuId) {
		ad.deleteById(ebuId);
	}

	//get EBUid method for getting EBUid from URL
	public EBUid getEbuId(String countryCode, int ebuNbr) {
		EBUid ebuId = new EBUid();
		ebuId.setCountryCode(countryCode);
		ebuId.setEbuNbr(ebuNbr);
		return ebuId;
	}
}
