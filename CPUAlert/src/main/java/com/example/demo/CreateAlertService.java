package com.example.demo;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.Alert;
import com.example.demo.data.AlertRepository;

@Service
public class CreateAlertService {
	
	@Autowired
	public CreateAlertService() {
		super();
	}
	
	@Autowired
	AlertRepository alertDao;
	
	@Transactional
	public Alert createAlert(Alert alert) {
		return alertDao.save(alert);
	}
	
	@Transactional
	public void updateAlert(Alert alert) {
		alertDao.save(alert);
	}

}
