package com.alert.acknowledge.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

@Embeddable
@Component
public class AlertIdentity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7583449638462761718L;

	@Column(name="ebu_nbr")
	private int ebuId;
	
	@Column(name="country_code")
	private String countryCode;
	
	public int getEbuId() {
		return ebuId;
	}
	public void setEbuId(int ebuId) {
		this.ebuId = ebuId;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
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
		AlertIdentity other = (AlertIdentity) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (ebuId != other.ebuId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AlertIdentity [ebuId=" + ebuId + ", countryCode=" + countryCode + "]";
	}
	public AlertIdentity(int ebuId, String countryCode) {
		super();
		this.ebuId = ebuId;
		this.countryCode = countryCode;
	}
	public AlertIdentity() {
		super();
	}
	
}