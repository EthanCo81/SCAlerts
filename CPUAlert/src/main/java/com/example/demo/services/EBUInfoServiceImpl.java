package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.EBUInfo;
import com.example.demo.data.EBUInfoRepository;

@Service
public class EBUInfoServiceImpl implements EBUInfoService{
	
	@Autowired
	private EBUInfoRepository ebuRepo;

	@Override
	public EBUInfo getInfo(int ebuNbr) {
		EBUInfo info = ebuRepo.findById(ebuNbr).get();
		return info;
	}

}
