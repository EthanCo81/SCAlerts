package com.example.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.beans.EBUInfo;
import com.example.beans.EBUid;

@Repository
public interface EBUInfoRepository extends JpaRepository<EBUInfo, EBUid>{

}
