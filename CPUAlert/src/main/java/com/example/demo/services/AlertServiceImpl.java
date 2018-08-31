package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.Alert;
import com.example.beans.EBUid;
import com.example.demo.data.AlertRepository;

@Service
public class AlertServiceImpl  implements AlertService{

	@Autowired 
	private AlertRepository alertRepo;
	
	@Override
	public Alert getAlert(String countryCode, int ebuNbr) {
		EBUid id = new EBUid(countryCode,ebuNbr);
		Alert a = alertRepo.findById(id).get();
		return a;
	}
}
