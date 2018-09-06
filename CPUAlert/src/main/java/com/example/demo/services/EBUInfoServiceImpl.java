package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.EBUInfo;
import com.example.beans.EBUid;
import com.example.demo.data.EBUInfoRepository;

/**
 * Implementation for EBUInfo service layer
 * 
 * @author Thomas Santillan
 *
 */
@Service
public class EBUInfoServiceImpl implements EBUInfoService{
	
	@Autowired
	private EBUInfoRepository ebuRepo;

	@Override
	public EBUInfo getInfo(String countryCode, int ebuNbr) {
		EBUid id = new EBUid(countryCode, ebuNbr);
		try {
			return ebuRepo.findById(id).get();
		}catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public List<EBUInfo> getAllEBUInfo() {
		return ebuRepo.findAll();
	}

}
