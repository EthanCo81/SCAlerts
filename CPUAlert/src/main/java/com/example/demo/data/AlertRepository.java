package com.example.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.beans.Alert;
import com.example.beans.EBUid;

/**
 * DAO repository for Alert utilization
 * 
 * @author Thomas Santillan
 *
 */
@Repository
public interface AlertRepository extends JpaRepository<Alert, EBUid>{

}
