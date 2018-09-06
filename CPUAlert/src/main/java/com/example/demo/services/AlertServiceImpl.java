package com.example.demo.services;

import java.time.DateTimeException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.Alert;
import com.example.beans.AlertType;
import com.example.beans.EBUInfo;
import com.example.beans.EBUid;
import com.example.demo.data.AlertRepository;

/**
 * Implementation for Alert service layer
 * 
 * @author Thomas Santillan
 *
 */
@Service
public class AlertServiceImpl  implements AlertService{

	@Autowired 
	private AlertRepository alertRepo;
	
	@Autowired
	private EBUInfoService ebuInfoService;
	
	@Override
	public Alert getAlert(String countryCode, int ebuNbr) {
		EBUid id = new EBUid(countryCode,ebuNbr);
		return alertRepo.findById(id).get();
	}
	
	@Override
	public List<Alert> getAllAlerts() {
		return alertRepo.findAll();
	}
		
	@Transactional
	@Override
	public Alert createAlert(Alert alert, String countryCode, int ebuNbr, Optional<String> timeZone, int alertType) {
		try {
			alert = getAlert(countryCode, ebuNbr);
		} catch (NoSuchElementException e) {
			alert = setOldAlert(countryCode, ebuNbr, alertType);
		}
		
		//Check that the alert flag is not already raised AND that it's a new Express Order alert
		if (alert.getAlertStatus() == 0 && alertType == 15) {
			String s_timeZone = null;
			if (timeZone.isPresent()) {
				s_timeZone = timeZone.get();
			}
			alert = setNewAlert(alert, s_timeZone, countryCode, ebuNbr);
			return alertRepo.saveAndFlush(alert);
		} else {
			return alert;
		}
	}
	
	@Transactional
	@Override
	public Alert updateAlert(Alert alert, String countryCode, int ebuNbr, Optional<String> timeZone, int alertType) {
		try {
			alert = getAlert(countryCode, ebuNbr);
		} catch (NoSuchElementException e) {
			return null;
		}
		
		//Check that the alert flag is not already raised AND that it's a new Express Order alert
		if (alert.getAlertStatus() == 0 && alertType == 15) {
			String s_timeZone = null;
			if (timeZone.isPresent()) {
				s_timeZone = timeZone.get();
			}
			alert = setNewAlert(alert, s_timeZone, countryCode, ebuNbr);
			return alertRepo.save(alert);
		} else {
			return alert;
		}
	}
	
	public Alert setNewAlert(Alert alert, String timeZone, String countryCode, int ebuNbr) {
		alert.setAlertStatus(1);
				
		ZoneId localTimeZone = validateTimeZone(timeZone, countryCode, ebuNbr);
		OffsetDateTime localTime = ZonedDateTime.now(localTimeZone).toOffsetDateTime();
		alert.setLastAlertTime(localTime);
		
		return alert;
	}
	
	public Alert setOldAlert(String countryCode, int ebuNbr, int alertType) {
		Alert oldAlert = new Alert();
		oldAlert.setAlertStatus(0);
		oldAlert.setAlertType(new AlertType(alertType));
		EBUid ebuID = new EBUid(countryCode, ebuNbr);
		oldAlert.setEbuId(ebuID);
		return oldAlert;
	}
	
	public ZoneId validateTimeZone (String timeZone, String countryCode, int ebuNbr) {
		ZoneId zone;
		try {
			zone = ZoneId.of(timeZone);
			return zone;
		} catch (ZoneRulesException e){
			String storeTimeZone = getEBUTimeZone(countryCode, ebuNbr);
			zone = ZoneId.of(storeTimeZone);
			return zone;
		} catch (DateTimeException e) {
			String storeTimeZone = getEBUTimeZone(countryCode, ebuNbr);
			zone = ZoneId.of(storeTimeZone);
			return zone;
		} catch (NullPointerException e) {
			String storeTimeZone = getEBUTimeZone(countryCode, ebuNbr);
			zone = ZoneId.of(storeTimeZone);
			return zone;
		}
	}

	private String getEBUTimeZone(String countryCode, int ebuNbr) {		
		EBUInfo ebuInfo = ebuInfoService.getInfo(countryCode, ebuNbr);
		String storeTimeZone = ebuInfo.getTimezone();
		return storeTimeZone;
	}


}
