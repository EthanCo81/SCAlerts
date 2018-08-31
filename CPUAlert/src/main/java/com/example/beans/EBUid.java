package com.example.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EBUid implements Serializable{
	
	@Column(name = "country_code")
	private String countryCode;
	
	@Column(name = "ebu_nbr")
	private int ebuNbr;

	public EBUid() {
		super();
	}

	public EBUid(String countryCode, int ebuNbr) {
		super();
		this.countryCode = countryCode;
		this.ebuNbr = ebuNbr;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		EBUid other = (EBUid) obj;
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
		return "EBUid [countryCode=" + countryCode + ", ebuNbr=" + ebuNbr + "]";
	}
	
}
