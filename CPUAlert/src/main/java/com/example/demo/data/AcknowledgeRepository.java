package com.example.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.beans.Alert;
import com.example.beans.EBUid;


/**
 * DAO repository for Acknowledge utilization
 * 
 * @author Larry Kang
 *
 */
@Repository

public interface AcknowledgeRepository extends JpaRepository<Alert, EBUid>{

	

}