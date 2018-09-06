package com.example.beans;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Define AlertHistory POJO
 * 
 * @author Larry Kang
 *
 */
@Component

@Entity

@Table(name="alert_history")
@ApiModel(value = "AlertHistory", description = "The history of previous Alerts")
public class AlertHistory {

	

	@EmbeddedId
	@ApiModelProperty(value ="The composite ID of AlertHistory")
	private AlertHistoryId alertHistoryId;

	

	@Column(name="alert_start_ts_ltz")
	@ApiModelProperty(value ="Timestamp in local time of when the alert started")
	private LocalDateTime alertStartLtz;

	

	@Column(name="alert_end_ts_gmt")
	@ApiModelProperty(value ="Timestamp in GMT of when the alert was acknowledged")
	private LocalDateTime alertEndGmt;

	

	@Column(name="alert_end_ts_ltz")
	@ApiModelProperty(value ="Timestamp in local time of when the alert was acknowledged")
	private LocalDateTime alertEndLtz;



	public AlertHistoryId getAlertHistoryId() {
		return alertHistoryId;
	}



	public void setAlertHistoryId(AlertHistoryId alertHistoryId) {
		this.alertHistoryId = alertHistoryId;
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
		result = prime * result + ((alertHistoryId == null) ? 0 : alertHistoryId.hashCode());
		result = prime * result + ((alertStartLtz == null) ? 0 : alertStartLtz.hashCode());
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
		if (alertHistoryId == null) {
			if (other.alertHistoryId != null)
				return false;
		} else if (!alertHistoryId.equals(other.alertHistoryId))
			return false;
		if (alertStartLtz == null) {
			if (other.alertStartLtz != null)
				return false;
		} else if (!alertStartLtz.equals(other.alertStartLtz))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "AlertHistory [alertHistoryId=" + alertHistoryId + ", alertStartLtz=" + alertStartLtz + ", alertEndGmt="
				+ alertEndGmt + ", alertEndLtz=" + alertEndLtz + "]";
	}



	public AlertHistory(AlertHistoryId alertHistoryId, LocalDateTime alertStartLtz, LocalDateTime alertEndGmt,
			LocalDateTime alertEndLtz) {
		super();
		this.alertHistoryId = alertHistoryId;
		this.alertStartLtz = alertStartLtz;
		this.alertEndGmt = alertEndGmt;
		this.alertEndLtz = alertEndLtz;
	}



	public AlertHistory() {
		super();
	}


	
}