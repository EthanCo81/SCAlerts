package com.example.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alert_type")
public class AlertType {
	
	@Id
	@Column(name="alert_type_cd")
	private int alertTypeCode;
	
	@Column(name="alert_type_name")
	private String alertTypeName;

	public AlertType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AlertType(int alertTypeCode) {
		super();
		this.alertTypeCode = alertTypeCode;
	}

	public AlertType(int alertTypeCode, String alertTypeName) {
		super();
		this.alertTypeCode = alertTypeCode;
		this.alertTypeName = alertTypeName;
	}

	public int getAlertTypeCode() {
		return alertTypeCode;
	}

	public void setAlertTypeCode(int alertTypeCode) {
		this.alertTypeCode = alertTypeCode;
	}

	public String getAlertTypeName() {
		return alertTypeName;
	}

	public void setAlertTypeName(String alertTypeName) {
		this.alertTypeName = alertTypeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alertTypeCode;
		result = prime * result + ((alertTypeName == null) ? 0 : alertTypeName.hashCode());
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
		AlertType other = (AlertType) obj;
		if (alertTypeCode != other.alertTypeCode)
			return false;
		if (alertTypeName == null) {
			if (other.alertTypeName != null)
				return false;
		} else if (!alertTypeName.equals(other.alertTypeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlertType [alertTypeCode=" + alertTypeCode + ", alertTypeName=" + alertTypeName + "]";
	}
	
	
}
