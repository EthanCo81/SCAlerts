package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.AcknowledgeRepository;
import com.example.beans.Alert;
import com.example.beans.EBUid;



@Service

public class AcknowledgeService {

	

	//wiring in dao for persistence methods

	@Autowired

	private AcknowledgeRepository ad;

	

	//create method

	@Transactional

	public Alert createAlert(Alert alert) {

		return ad.save(alert);

	}

	

	//read by id method

	public Alert readAlert(EBUid EBUid) {

		return ad.findById(EBUid).get();

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

	public void deleteAlert(EBUid EBUid) {

		ad.deleteById(EBUid);

	}

	

}
