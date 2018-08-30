package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.beans.Alert;
import com.example.demo.data.AlertDAO;

public class AlertServiceImpl  implements AlertService{

	@Autowired 
	private AlertDAO alertRepo;
	
	@Override
	public Alert getAlert(int ebuNbr) {
		return alertRepo.getOne(ebuNbr);
	}
}
