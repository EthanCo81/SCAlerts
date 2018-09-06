package com.example.beans;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Define AlertHistory POJO
 * 
 * @author Larry Kang | Thomas Santillan | SEP 6, 2018
 *
 */

@Entity
@Table(name="alert_history")
@ApiModel(value = "AlertHistory", description = "The history of previous Alerts")
public class AlertHistory {

	

	@EmbeddedId
	@ApiModelProperty(value ="The composite ID of AlertHistory")
	private AlertHistoryId alertHistoryId;	

	@Column(name="alert_end_ts")
	@ApiModelProperty(value ="Timestamp in and offset of when the alert was acknowledged")
	private OffsetDateTime alertEndTime;

	public AlertHistory() {
		super();
	}

	public AlertHistory(AlertHistoryId alertHistoryId, OffsetDateTime alertEndTime) {
		super();
		this.alertHistoryId = alertHistoryId;
		this.alertEndTime = alertEndTime;
	}

	public AlertHistoryId getAlertHistoryId() {
		return alertHistoryId;
	}

	public void setAlertHistoryId(AlertHistoryId alertHistoryId) {
		this.alertHistoryId = alertHistoryId;
	}

	public OffsetDateTime getAlertEndTime() {
		return alertEndTime;
	}

	public void setAlertEndTime(OffsetDateTime alertEndTime) {
		this.alertEndTime = alertEndTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alertEndTime == null) ? 0 : alertEndTime.hashCode());
		result = prime * result + ((alertHistoryId == null) ? 0 : alertHistoryId.hashCode());
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
		if (alertEndTime == null) {
			if (other.alertEndTime != null)
				return false;
		} else if (!alertEndTime.equals(other.alertEndTime))
			return false;
		if (alertHistoryId == null) {
			if (other.alertHistoryId != null)
				return false;
		} else if (!alertHistoryId.equals(other.alertHistoryId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlertHistory [alertHistoryId=" + alertHistoryId + ", alertEndTime=" + alertEndTime + "]";
	}

}