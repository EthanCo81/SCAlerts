package com.example.demo.services;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;
import java.util.List;

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
	public Alert createAlert(Alert alert) {
		return alertRepo.saveAndFlush(alert);
	}
	
	@Transactional
	@Override
	public void updateAlert(Alert alert) {
		alertRepo.save(alert);
	}
	
	public Alert setNewAlert(Alert alert, String timeZone, String countryCode, int ebuNbr) {
		alert.setAlertStatus(1);
		LocalDateTime currentGmtTime = ZonedDateTime.now(ZoneId.of("GMT")).toLocalDateTime();
		alert.setLastAlertGmt(currentGmtTime);
		
		ZoneId localTimeZone = validateTimeZone(timeZone, countryCode, ebuNbr);
		LocalDateTime localTime = ZonedDateTime.now(localTimeZone).toLocalDateTime();
		alert.setLastAlertLtz(localTime);
		
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
