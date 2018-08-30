package com.alert.acknowledge.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="alert_history")
public class AlertHistory {
	
	@Id
	@Column(name="ebu_nbr")
	private int ebuId;
	
	@Column(name="country_code")
	private int countryCode;
	
	@Column(name="alert_type")
	private int alertType;
	
	@Column(name="alert_start_ts_gmt")
	private int alertStartGmt;
	
	@Column(name="alert_start_ts_ltz")
	private int alertStartLtz;
	
	@Column(name="alert_end_ts_gmt")
	private int alertEndGmt;
	
	@Column(name="alert_end_ts_ltz")
	private int alertEndLtz;

	public AlertHistory() {
		super();
	}

	public AlertHistory(int ebuId, int countryCode, int alertType, int alertStartGmt, int alertStartLtz,
			int alertEndGmt, int alertEndLtz) {
		super();
		this.ebuId = ebuId;
		this.countryCode = countryCode;
		this.alertType = alertType;
		this.alertStartGmt = alertStartGmt;
		this.alertStartLtz = alertStartLtz;
		this.alertEndGmt = alertEndGmt;
		this.alertEndLtz = alertEndLtz;
	}

	@Override
	public String toString() {
		return "AlertHistory [ebuId=" + ebuId + ", countryCode=" + countryCode + ", alertType=" + alertType
				+ ", alertStartGmt=" + alertStartGmt + ", alertStartLtz=" + alertStartLtz + ", alertEndGmt="
				+ alertEndGmt + ", alertEndLtz=" + alertEndLtz + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alertEndGmt;
		result = prime * result + alertEndLtz;
		result = prime * result + alertStartGmt;
		result = prime * result + alertStartLtz;
		result = prime * result + alertType;
		result = prime * result + countryCode;
		result = prime * result + ebuId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlertHistory other = (AlertHistory) obj;
		if (alertEndGmt != other.alertEndGmt)
			return false;
		if (alertEndLtz != other.alertEndLtz)
			return false;
		if (alertStartGmt != other.alertStartGmt)
			return false;
		if (alertStartLtz != other.alertStartLtz)
			return false;
		if (alertType != other.alertType)
			return false;
		if (countryCode != other.countryCode)
			return false;
		if (ebuId != other.ebuId)
			return false;
		return true;
	}

	public int getEbuId() {
		return ebuId;
	}

	public void setEbuId(int ebuId) {
		this.ebuId = ebuId;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public int getAlertType() {
		return alertType;
	}

	public void setAlertType(int alertType) {
		this.alertType = alertType;
	}

	public int getAlertStartGmt() {
		return alertStartGmt;
	}

	public void setAlertStartGmt(int alertStartGmt) {
		this.alertStartGmt = alertStartGmt;
	}

	public int getAlertStartLtz() {
		return alertStartLtz;
	}

	public void setAlertStartLtz(int alertStartLtz) {
		this.alertStartLtz = alertStartLtz;
	}

	public int getAlertEndGmt() {
		return alertEndGmt;
	}

	public void setAlertEndGmt(int alertEndGmt) {
		this.alertEndGmt = alertEndGmt;
	}

	public int getAlertEndLtz() {
		return alertEndLtz;
	}

	public void setAlertEndLtz(int alertEndLtz) {
		this.alertEndLtz = alertEndLtz;
	}
	
}
