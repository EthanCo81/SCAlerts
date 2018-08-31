package com.alert.acknowledge.beans;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="alert")
public class Alert {
	
	@EmbeddedId
	private AlertIdentity alertIdentity;
	
	@Column(name="alert_type_cd")
	private int alertType;
	
	@Column(name="alert_status_cd")
	private int alertStatus;

	@Column(name="last_alert_ts_gmt")
	private ZonedDateTime lastAlertGmt;
	
	@Column(name="last_alert_ts_ltz")
	private ZonedDateTime lastAlertLtz;

	public AlertIdentity getAlertIdentity() {
		return alertIdentity;
	}

	public void setAlertIdentity(AlertIdentity alertIdentity) {
		this.alertIdentity = alertIdentity;
	}

	public int getAlertType() {
		return alertType;
	}

	public void setAlertType(int alertType) {
		this.alertType = alertType;
	}

	public int getAlertStatus() {
		return alertStatus;
	}

	public void setAlertStatus(int alertStatus) {
		this.alertStatus = alertStatus;
	}

	public ZonedDateTime getLastAlertGmt() {
		return lastAlertGmt;
	}

	public void setLastAlertGmt(ZonedDateTime lastAlertGmt) {
		this.lastAlertGmt = lastAlertGmt;
	}

	public ZonedDateTime getLastAlertLtz() {
		return lastAlertLtz;
	}

	public void setLastAlertLtz(ZonedDateTime lastAlertLtz) {
		this.lastAlertLtz = lastAlertLtz;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alertIdentity == null) ? 0 : alertIdentity.hashCode());
		result = prime * result + alertStatus;
		result = prime * result + alertType;
		result = prime * result + ((lastAlertGmt == null) ? 0 : lastAlertGmt.hashCode());
		result = prime * result + ((lastAlertLtz == null) ? 0 : lastAlertLtz.hashCode());
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
		Alert other = (Alert) obj;
		if (alertIdentity == null) {
			if (other.alertIdentity != null)
				return false;
		} else if (!alertIdentity.equals(other.alertIdentity))
			return false;
		if (alertStatus != other.alertStatus)
			return false;
		if (alertType != other.alertType)
			return false;
		if (lastAlertGmt == null) {
			if (other.lastAlertGmt != null)
				return false;
		} else if (!lastAlertGmt.equals(other.lastAlertGmt))
			return false;
		if (lastAlertLtz == null) {
			if (other.lastAlertLtz != null)
				return false;
		} else if (!lastAlertLtz.equals(other.lastAlertLtz))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alert [alertIdentity=" + alertIdentity + ", alertType=" + alertType + ", alertStatus=" + alertStatus
				+ ", lastAlertGmt=" + lastAlertGmt + ", lastAlertLtz=" + lastAlertLtz + "]";
	}

	public Alert(AlertIdentity alertIdentity, int alertType, int alertStatus, ZonedDateTime lastAlertGmt,
			ZonedDateTime lastAlertLtz) {
		super();
		this.alertIdentity = alertIdentity;
		this.alertType = alertType;
		this.alertStatus = alertStatus;
		this.lastAlertGmt = lastAlertGmt;
		this.lastAlertLtz = lastAlertLtz;
	}

	public Alert() {
		super();
	}

}
