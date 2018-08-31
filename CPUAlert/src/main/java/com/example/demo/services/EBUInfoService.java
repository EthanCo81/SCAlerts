package com.example.demo.services;

import com.example.beans.EBUInfo;

public interface EBUInfoService {
	
	public EBUInfo getInfo(String countryCode,int ebuNbr);

}
