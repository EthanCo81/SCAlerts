package com.alert.acknowledge.beans;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="alert_history")
public class AlertHistory {
	
	@EmbeddedId
	private AlertIdentity alertIdentity;
	
	@Column(name="alert_type_cd")
	private int alertType;
	
	@Column(name="alert_start_ts_gmt")
	private LocalDateTime alertStartGmt;
	
	@Column(name="alert_start_ts_ltz")
	private LocalDateTime alertStartLtz;
	
	@Column(name="alert_end_ts_gmt")
	private LocalDateTime alertEndGmt;
	
	@Column(name="alert_end_ts_ltz")
	private LocalDateTime alertEndLtz;

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

	public LocalDateTime getAlertStartGmt() {
		return alertStartGmt;
	}

	public void setAlertStartGmt(LocalDateTime alertStartGmt) {
		this.alertStartGmt = alertStartGmt;
	}

	public LocalDateTime getAlertStartLtz() {
		return alertStartLtz;
	}

	public void setAlertStartLtz(LocalDateTime alertStartLtz) {
		this.alertStartLtz = alertStartLtz;
	}

	public LocalDateTime getAlertEndGmt() {
		return alertEndGmt;
	}

	public void setAlertEndGmt(LocalDateTime alertEndGmt) {
		this.alertEndGmt = alertEndGmt;
	}

	public LocalDateTime getAlertEndLtz() {
		return alertEndLtz;
	}

	public void setAlertEndLtz(LocalDateTime alertEndLtz) {
		this.alertEndLtz = alertEndLtz;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alertEndGmt == null) ? 0 : alertEndGmt.hashCode());
		result = prime * result + ((alertEndLtz == null) ? 0 : alertEndLtz.hashCode());
		result = prime * result + ((alertIdentity == null) ? 0 : alertIdentity.hashCode());
		result = prime * result + ((alertStartGmt == null) ? 0 : alertStartGmt.hashCode());
		result = prime * result + ((alertStartLtz == null) ? 0 : alertStartLtz.hashCode());
		result = prime * result + alertType;
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
		if (alertEndGmt == null) {
			if (other.alertEndGmt != null)
				return false;
		} else if (!alertEndGmt.equals(other.alertEndGmt))
			return false;
		if (alertEndLtz == null) {
			if (other.alertEndLtz != null)
				return false;
		} else if (!alertEndLtz.equals(other.alertEndLtz))
			return false;
		if (alertIdentity == null) {
			if (other.alertIdentity != null)
				return false;
		} else if (!alertIdentity.equals(other.alertIdentity))
			return false;
		if (alertStartGmt == null) {
			if (other.alertStartGmt != null)
				return false;
		} else if (!alertStartGmt.equals(other.alertStartGmt))
			return false;
		if (alertStartLtz == null) {
			if (other.alertStartLtz != null)
				return false;
		} else if (!alertStartLtz.equals(other.alertStartLtz))
			return false;
		if (alertType != other.alertType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlertHistory [alertIdentity=" + alertIdentity + ", alertType=" + alertType + ", alertStartGmt="
				+ alertStartGmt + ", alertStartLtz=" + alertStartLtz + ", alertEndGmt=" + alertEndGmt + ", alertEndLtz="
				+ alertEndLtz + "]";
	}

	public AlertHistory(AlertIdentity alertIdentity, int alertType, LocalDateTime alertStartGmt,
			LocalDateTime alertStartLtz, LocalDateTime alertEndGmt, LocalDateTime alertEndLtz) {
		super();
		this.alertIdentity = alertIdentity;
		this.alertType = alertType;
		this.alertStartGmt = alertStartGmt;
		this.alertStartLtz = alertStartLtz;
		this.alertEndGmt = alertEndGmt;
		this.alertEndLtz = alertEndLtz;
	}

	public AlertHistory() {
		super();
	}

}