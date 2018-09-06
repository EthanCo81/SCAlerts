package com.example.demo.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.example.beans.Alert;
import com.example.demo.CpuAlertApplication;

/**
 *  Alert Service Testing class
 * 
 * @author Thomas Santillan | September 6, 2018
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=CpuAlertApplication.class)
public class AlertServiceTest {
	
	@Autowired
	private AlertService alertService;
	
	@Test
	public void getAlertTest() {
		List<Alert> alertList =alertService.getAllAlerts();
		if(!alertList.isEmpty()) {
			Alert alert = alertService.getAlert( alertList.get(0).getEbuId().getCountryCode(), 
												alertList.get(0).getEbuId().getEbuNbr());
			assertEquals(alertList.get(0), alert);
		} else {
			assertTrue(alertList.isEmpty());
		}
	}
	
	@Test(expected = NoSuchElementException.class)
	public void getAlertIllegalArgsTest() {
			@SuppressWarnings("unused")
			Alert alert = alertService.getAlert("", 0);
	}
	
	@Test
	public void findAllEBUInfoTest() {
		List<Alert> alertList =alertService.getAllAlerts();
		assertTrue(alertList != null && !alertList.isEmpty());
	}
}
