package com.example.beans;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ebu_info")
public class EBUInfo {
	
	@EmbeddedId
	private EBUid ebuId;
	
	@Column(name="ebu_city")
	private String city;
	
	@Column(name="ebu_state")
	private String state;
	
	@Column(name="ebu_timezone")
	private String timezone;
	
	public EBUInfo() {
		super();
	}

	public EBUInfo(EBUid ebuId, String city, String state, String timezone) {
		super();
		this.ebuId = ebuId;
		this.city = city;
		this.state = state;
		this.timezone = timezone;
	}

	public EBUid getEbuId() {
		return ebuId;
	}

	public void setEbuId(EBUid ebuId) {
		this.ebuId = ebuId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((ebuId == null) ? 0 : ebuId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((timezone == null) ? 0 : timezone.hashCode());
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
		EBUInfo other = (EBUInfo) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (ebuId == null) {
			if (other.ebuId != null)
				return false;
		} else if (!ebuId.equals(other.ebuId))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (timezone == null) {
			if (other.timezone != null)
				return false;
		} else if (!timezone.equals(other.timezone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EBUInfo [ebuId=" + ebuId + ", city=" + city + ", state=" + state + ", timezone=" + timezone + "]";
	}
	

	
}
