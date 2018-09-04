package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.EBUInfo;
import com.example.beans.EBUid;
import com.example.demo.data.EBUInfoRepository;

@Service
public class EBUInfoServiceImpl implements EBUInfoService{
	
	@Autowired
	private EBUInfoRepository ebuRepo;

	@Override
	public EBUInfo getInfo(String countryCode, int ebuNbr) {
		EBUid id = new EBUid("US",ebuNbr);
		if (ebuRepo.existsById(id)) {
			return ebuRepo.findById(id).get();
		}

		return null;
	}

}
