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
import com.example.beans.EBUInfo;
import com.example.demo.CpuAlertApplication;

/**
 *  EBU Info Service Testing class
 * 
 * @author Thomas Santillan | September 6, 2018
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=CpuAlertApplication.class)
public class EBUInfoServiceTest {
	
	@Autowired
	private EBUInfoService infoService;
	
	@Test
	public void getEBUInfoTest() {
		List<EBUInfo> infoList =infoService.getAllEBUInfo();
		if(!infoList.isEmpty()) {
			EBUInfo info = infoService.getInfo( infoList.get(0).getEbuId().getCountryCode(), 
												infoList.get(0).getEbuId().getEbuNbr());
			assertEquals(infoList.get(0), info);
		} else {
			assertTrue(infoList.isEmpty());
		}
	}
	
	@Test(expected = NoSuchElementException.class)
	public void getEBUInfoIllegalArgsTest() {
			@SuppressWarnings("unused")
			EBUInfo info = infoService.getInfo("", 0);
	}
	
	@Test
	public void getAllEBUInfoTest() {
		List<EBUInfo> infoList =infoService.getAllEBUInfo();
		assertTrue(infoList != null && !infoList.isEmpty());
	}

}
