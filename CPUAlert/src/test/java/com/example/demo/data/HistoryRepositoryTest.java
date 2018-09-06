package com.example.demo.data;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.beans.AlertHistory;
import com.example.beans.EBUInfo;
import com.example.demo.CpuAlertApplication;

/**
 *  Alert History Repository Testing class
 * 
 * @author Thomas Santillan | September 6, 2018
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=CpuAlertApplication.class)
public class HistoryRepositoryTest {
	
	@Autowired
	private HistoryRepository historyRepo;
	
	@Autowired
	private EBUInfoRepository infoRepo;
	
	@Test
	public void findAllHistoryQueryTest() {
		List<EBUInfo> infoList =infoRepo.findAll();
		if(!infoList.isEmpty()) {
			List<AlertHistory> history =historyRepo.findByAlertHistoryId_CountryCodeAndAlertHistoryId_EbuNbrOrderByAlertEndTimeDesc
										(infoList.get(0).getEbuId().getCountryCode(), infoList.get(0).getEbuId().getEbuNbr());
			
			assertNotNull(history != null);
		} else {
			assertTrue(infoList.isEmpty());
		}
	}
	

}
