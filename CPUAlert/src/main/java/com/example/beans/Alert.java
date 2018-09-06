
package com.example.beans;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Define Alert POJO
 * 
 * @author Larry Kang | Thomas Santillan | SEP 6, 2018
 *
 */
@ApiModel (value="Alert", description="A new 1-hour Alert")
@Entity
@Table(name="alert")
public class Alert {
	
	@ApiModelProperty(value = "Composite id of the alert consisting of countryCode and ebuNbr")
	@EmbeddedId
	private EBUid ebuId;
	
	@ApiModelProperty(value = "The type of alert (Pick Due(10), Express Order(15), Check-In(20), etc.)")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="alert_type_cd")
   	private AlertType alertType;
	
	@ApiModelProperty(value ="The status of the alert (None(0), Active(1))")
	@Column(name="alert_status_cd")
   	private int alertStatus;

	@ApiModelProperty(value ="Timestamp and offset of when the alert was received")
	@Column(name="last_alert_ts")
	private OffsetDateTime lastAlertTime;

    public Alert() {
           super();
    }

	public Alert(EBUid ebuId, AlertType alertType, int alertStatus, OffsetDateTime lastAlertTime) {
		super();
		this.ebuId = ebuId;
		this.alertType = alertType;
		this.alertStatus = alertStatus;
		this.lastAlertTime = lastAlertTime;
	}

	public EBUid getEbuId() {
		return ebuId;
	}

	public void setEbuId(EBUid ebuId) {
		this.ebuId = ebuId;
	}

	public AlertType getAlertType() {
		return alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}

	public int getAlertStatus() {
		return alertStatus;
	}

	public void setAlertStatus(int alertStatus) {
		this.alertStatus = alertStatus;
	}

	public OffsetDateTime getLastAlertTime() {
		return lastAlertTime;
	}

	public void setLastAlertTime(OffsetDateTime lastAlertTime) {
		this.lastAlertTime = lastAlertTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alertStatus;
		result = prime * result + ((alertType == null) ? 0 : alertType.hashCode());
		result = prime * result + ((ebuId == null) ? 0 : ebuId.hashCode());
		result = prime * result + ((lastAlertTime == null) ? 0 : lastAlertTime.hashCode());
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
		if (alertType == null) {
			if (other.alertType != null)
				return false;
		} else if (!alertType.equals(other.alertType))
			return false;
		if (ebuId == null) {
			if (other.ebuId != null)
				return false;
		} else if (!ebuId.equals(other.ebuId))
			return false;
		if (lastAlertTime == null) {
			if (other.lastAlertTime != null)
				return false;
		} else if (!lastAlertTime.equals(other.lastAlertTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alert [ebuId=" + ebuId + ", alertType=" + alertType + ", alertStatus=" + alertStatus
				+ ", lastAlertTime=" + lastAlertTime + "]";
	}
 
}
