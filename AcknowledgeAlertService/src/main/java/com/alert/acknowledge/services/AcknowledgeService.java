package com.alert.acknowledge.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alert.acknowledge.beans.Alert;
import com.alert.acknowledge.daos.AcknowledgeDAO;

@Service
public class AcknowledgeService {
	
	//wiring in dao for persistence methods
	@Autowired
	private AcknowledgeDAO ad;
	
	//create method
	@Transactional
	public Alert createAlert(Alert alert) {
		return ad.save(alert);
	}
	
	//read by id method
	@Transactional
	public Alert readAlert(int ebuId) {
		return ad.getOne(ebuId);
	}
	
	//read all alerts method
	@Transactional
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
	public void deleteAlert(int ebuId) {
		ad.deleteById(ebuId);
	}
	
}
