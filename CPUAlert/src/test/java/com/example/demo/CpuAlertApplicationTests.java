package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZoneId;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.beans.Alert;
import com.example.beans.EBUInfo;
import com.example.beans.EBUid;
import com.example.demo.controllers.CreateAlertController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CpuAlertApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testTimeZoneValidationValidatesTimeZone() {
		CreateAlertController cac = new CreateAlertController();
		ZoneId z = ZoneId.of("America/Chicago");
		assertThat(z).isEqualTo(cac.validateTimeZone("America/Chicago", "GMT"));
	}
	
	@Test
	public void testTimeZoneValidationDoesNotValidateTimeZoneInvalidZone() {
		CreateAlertController cac = new CreateAlertController();
		ZoneId z = ZoneId.of("GMT");
		assertThat(z).isEqualTo(cac.validateTimeZone("Amica/Chicgo", "GMT"));
	}
	
	@Test
	public void testTimeZoneValidationDoesNotValidateTimeZoneInvalidFormat() {
		CreateAlertController cac = new CreateAlertController();
		ZoneId z = ZoneId.of("GMT");
		assertThat(z).isEqualTo(cac.validateTimeZone("1155", "GMT"));
	}
	
	@Test
	public void testAlertIsSetCorrectly() {
		CreateAlertController cac = new CreateAlertController();
		//Alert to be changed
		EBUid ebuID = new EBUid("US", 4969);
		EBUInfo ebuInfo = new EBUInfo();
		ebuInfo.setCity("Chicago");
		ebuInfo.setEbuId(ebuID);
		ebuInfo.setState("Illinois");
		ebuInfo.setTimezone("America/Chicago");
		
		Alert a = new Alert();
		a.setAlertStatus(0);
		a.setAlertType(15);
		a.setEbuId(ebuID);
		a.setLastAlertGmt(null);
		a.setLastAlertLtz(null);
		
		cac.setNewAlert(a, "America/Chicago", ebuInfo);
		
		assertThat(a.getAlertStatus()).isEqualTo(1);
		assertThat(a.getLastAlertGmt()).isNotNull();
		assertThat(a.getLastAlertLtz()).isNotNull();
	}

}
