package com.example.demo.services;

import com.example.beans.Alert;

public interface AlertService {
		
	/*
	 * Gets alert for a given EBU if one exists
	 * 
	 * @param ebuNbr
	 * @return Alert 
	 */
	public Alert getAlert(int ebuNbr);
}
