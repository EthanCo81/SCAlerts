package com.alert.acknowledge.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alert.acknowledge.beans.Alert;

@Repository
public interface AcknowledgeDAO extends JpaRepository<Alert, Integer>{
	
}
