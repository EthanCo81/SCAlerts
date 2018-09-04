package com.example.beans;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="alert")
public class Alert {
	   
	@EmbeddedId
	private EBUid ebuId;
       
	@Column(name="alert_type_cd")
   	private int alertType;
   
	@Column(name="alert_status_cd")
   	private int alertStatus;

	@Column(name="last_alert_ts_gmt")
	private LocalDateTime lastAlertGmt;
   
	@Column(name="last_alert_ts_ltz")
	private LocalDateTime lastAlertLtz;
   
    public Alert() {
           super();
    }

	public Alert(EBUid ebuId, int alertType, int alertStatus, LocalDateTime lastAlertGmt, LocalDateTime lastAlertLtz) {
		super();
		this.ebuId = ebuId;
		this.alertType = alertType;
		this.alertStatus = alertStatus;
		this.lastAlertGmt = lastAlertGmt;
		this.lastAlertLtz = lastAlertLtz;
	}

	public EBUid getEbuId() {
		return ebuId;
	}

	public void setEbuId(EBUid ebuId) {
		this.ebuId = ebuId;
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

	public LocalDateTime getLastAlertGmt() {
		return lastAlertGmt;
	}

	public void setLastAlertGmt(LocalDateTime lastAlertGmt) {
		this.lastAlertGmt = lastAlertGmt;
	}

	public LocalDateTime getLastAlertLtz() {
		return lastAlertLtz;
	}

	public void setLastAlertLtz(LocalDateTime lastAlertLtz) {
		this.lastAlertLtz = lastAlertLtz;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alertStatus;
		result = prime * result + alertType;
		result = prime * result + ((ebuId == null) ? 0 : ebuId.hashCode());
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
		if (alertStatus != other.alertStatus)
			return false;
		if (alertType != other.alertType)
			return false;
		if (ebuId == null) {
			if (other.ebuId != null)
				return false;
		} else if (!ebuId.equals(other.ebuId))
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
		return "Alert [ebuId=" + ebuId + ", alertType=" + alertType + ", alertStatus=" + alertStatus + ", lastAlertGmt="
				+ lastAlertGmt + ", lastAlertLtz=" + lastAlertLtz + "]";
	}
       
}
