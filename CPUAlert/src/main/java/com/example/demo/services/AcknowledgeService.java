package com.example.demo.services;

import java.util.List;

import com.example.beans.Alert;
import com.example.beans.EBUid;

public interface AcknowledgeService {

	public Alert createAlert(Alert alert);
	
	public Alert readAlert(EBUid ebuId);
	
	public List<Alert> readAllAlerts();
	
	public void updateAlert(Alert alert);
		
	public void deleteAlert(EBUid ebuId);
	
}
