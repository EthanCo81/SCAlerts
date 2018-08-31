package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.Alert;
import com.example.demo.data.AlertRepository;

@Service
public class AlertServiceImpl  implements AlertService{

	@Autowired 
	private AlertRepository alertRepo;
	
	@Override
	public Alert getAlert(int ebuNbr) {
		Alert a = alertRepo.findById(ebuNbr).get();
		return a;
	}
}
