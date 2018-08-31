package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZoneId;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.beans.Alert;
import com.example.controllers.CreateAlertController;

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
		assertThat(z).isEqualTo(cac.validateTimeZone("America/Chicago"));
	}
	
	@Test
	public void testTimeZoneValidationDoesNotValidateTimeZoneInvalidZone() {
		CreateAlertController cac = new CreateAlertController();
		ZoneId z = ZoneId.of("GMT");
		assertThat(z).isEqualTo(cac.validateTimeZone("Amica/Chicgo"));
	}
	
	@Test
	public void testTimeZoneValidationDoesNotValidateTimeZoneInvalidFormat() {
		CreateAlertController cac = new CreateAlertController();
		ZoneId z = ZoneId.of("GMT");
		assertThat(z).isEqualTo(cac.validateTimeZone("1155"));
	}
	
	@Test
	public void testAlertIsSetCorrectly() {
		CreateAlertController cac = new CreateAlertController();
		//Alert to be changed
		Alert a = new Alert();
		a.setAlertStatus(0);
		a.setAlertType(15);
		a.setCountryCode("US");
		a.setEbuId(469721);
		a.setLastAlertGmt(null);
		a.setLastAlertLtz(null);
		
		cac.setNewAlert(a, "America/Chicago");
		
		assertThat(a.getAlertStatus()).isEqualTo(1);
		assertThat(a.getLastAlertGmt()).isNotNull();
		assertThat(a.getLastAlertLtz()).isNotNull();
	}

}
