package com.example.demo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.Alert;
import com.example.demo.data.AlertRepository;

@Service
public class CreateAlertServiceImpl implements CreateAlertService{
	
	@Autowired
	public CreateAlertServiceImpl() {
		super();
	}
	
	@Autowired
	AlertRepository alertDao;
	
	@Transactional
	@Override
	public Alert createAlert(Alert alert) {
		return alertDao.saveAndFlush(alert);
	}
	
	@Transactional
	@Override
	public void updateAlert(Alert alert) {
		alertDao.save(alert);
	}

}
