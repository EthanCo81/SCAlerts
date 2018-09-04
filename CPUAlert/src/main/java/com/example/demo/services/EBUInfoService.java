package com.example.demo.services;

import com.example.beans.EBUInfo;

import io.swagger.annotations.ApiModel;

/**
 * Interface for EBUInfo service layer
 * 
 * @author Thomas Santillan
 * 
 */
@ApiModel(value = "EBUInfoService", description = "Provides interface for the EBUInfo service layer")
public interface EBUInfoService {
	
	/**
	 * 
	 * @param countryCode - The country code string e.g. "US"
	 * @param ebuNbr - The store location's number
	 * @return EBUInfo - the EBUInfo by countryCode and ebuNbr
	 */
	public EBUInfo getInfo(String countryCode,int ebuNbr);

}
