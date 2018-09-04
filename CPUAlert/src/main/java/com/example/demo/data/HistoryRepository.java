package com.example.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.beans.AlertHistory;
import com.example.beans.AlertHistoryId;


/**
 * DAO repository for AlertHistory utilization
 * 
 * @author Larry Kang
 *
 */
@Repository

public interface HistoryRepository extends JpaRepository<AlertHistory, AlertHistoryId>{



}
