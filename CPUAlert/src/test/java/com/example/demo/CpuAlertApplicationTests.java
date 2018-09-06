package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZoneId;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.beans.Alert;
import com.example.beans.AlertType;
import com.example.beans.EBUInfo;
import com.example.beans.EBUid;
import com.example.demo.controllers.AlertController;
import com.example.demo.services.AlertService;
import com.example.demo.services.AlertServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CpuAlertApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	/*@Test
	public void testTimeZoneValidationValidatesTimeZone() {
		AlertService as = new AlertServiceImpl();
		ZoneId z = ZoneId.of("America/Chicago");
		assertThat(z).isEqualTo(as.validateTimeZone("America/Chicago", "US", 4969));
	}
	
	@Ignore
	@Test
	public void testTimeZoneValidationDoesNotValidateTimeZoneInvalidZone() {
		AlertController ac = new AlertController();
		ZoneId z = ZoneId.of("America/Chicago");
		assertThat(z).isEqualTo(ac.validateTimeZone("Amica/Chicgo", "US", 4969));
	}
	
	@Ignore
	@Test
	public void testTimeZoneValidationDoesNotValidateTimeZoneInvalidFormat() {
		AlertController ac = new AlertController();
		ZoneId z = ZoneId.of("America/Chicago");
		assertThat(z).isEqualTo(ac.validateTimeZone("1155", "US", 4969));
	}
	
	@Test
	public void testAlertIsSetCorrectly() {
		AlertController ac = new AlertController();
		//Alert to be changed
		EBUid ebuID = new EBUid("US", 4969);
		EBUInfo ebuInfo = new EBUInfo();
		ebuInfo.setCity("Chicago");
		ebuInfo.setEbuId(ebuID);
		ebuInfo.setState("Illinois");
		ebuInfo.setTimezone("America/Chicago");
		
		Alert a = new Alert();
		a.setAlertStatus(0);
		a.setAlertType(new AlertType(15));
		a.setEbuId(ebuID);
		a.setLastAlertGmt(null);
		a.setLastAlertLtz(null);
		
		ac.setNewAlert(a, "America/Chicago", "US", 4969);
		
		assertThat(a.getAlertStatus()).isEqualTo(1);
		assertThat(a.getLastAlertGmt()).isNotNull();
		assertThat(a.getLastAlertLtz()).isNotNull();
	}*/

}
