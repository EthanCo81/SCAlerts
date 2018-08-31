package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.beans.Alert;

@Service
public interface AlertService {
		
	/*
	 * Gets alert for a given EBU if one exists
	 * 
	 * @param ebuNbr
	 * @return Alert 
	 */
	public Alert getAlert(int ebuNbr);
}
