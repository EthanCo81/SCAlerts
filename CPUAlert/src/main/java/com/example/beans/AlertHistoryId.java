package com.example.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Composite Id for AlertHistory
 * 
 * @author Larry Kang
 *
 */
@Embeddable
@ApiModel(value = "AlertHistoryId", description = "A composite Id for AlertHistory")
public class AlertHistoryId implements Serializable{

	@Column(name = "country_code")
	@ApiModelProperty(value ="CountryCode of the store eg. \"US\" ")
	private String countryCode;
	
	@Column(name = "ebu_nbr")
	@ApiModelProperty(value ="Store location's number")
	private int ebuNbr;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="alert_type_cd")
	@ApiModelProperty(value ="Type of alert (eg 15 (ExpressOrder))")
	private AlertType alertType;
	
	@Column(name = "alert_start_ts_gmt")
	@ApiModelProperty(value ="Timestamp in GMT time when the alert was received")
	private LocalDateTime alertStartGmt;

	public AlertHistoryId() {
		super();
	}

	public AlertHistoryId(String countryCode, int ebuNbr, AlertType alertType, LocalDateTime alertStartGmt) {
		super();
		this.countryCode = countryCode;
		this.ebuNbr = ebuNbr;
		this.alertType = alertType;
		this.alertStartGmt = alertStartGmt;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getEbuNbr() {
		return ebuNbr;
	}

	public void setEbuNbr(int ebuNbr) {
		this.ebuNbr = ebuNbr;
	}

	public AlertType getAlertType() {
		return alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}

	public LocalDateTime getAlertStartGmt() {
		return alertStartGmt;
	}

	public void setAlertStartGmt(LocalDateTime alertStartGmt) {
		this.alertStartGmt = alertStartGmt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alertStartGmt == null) ? 0 : alertStartGmt.hashCode());
		result = prime * result + ((alertType == null) ? 0 : alertType.hashCode());
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ebuNbr;
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
		AlertHistoryId other = (AlertHistoryId) obj;
		if (alertStartGmt == null) {
			if (other.alertStartGmt != null)
				return false;
		} else if (!alertStartGmt.equals(other.alertStartGmt))
			return false;
		if (alertType == null) {
			if (other.alertType != null)
				return false;
		} else if (!alertType.equals(other.alertType))
			return false;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (ebuNbr != other.ebuNbr)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlertHistoryId [countryCode=" + countryCode + ", ebuNbr=" + ebuNbr + ", alertType=" + alertType
				+ ", alertStartGmt=" + alertStartGmt + "]";
	}
}
