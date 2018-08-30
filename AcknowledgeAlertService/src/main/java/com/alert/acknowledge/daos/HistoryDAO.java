package com.alert.acknowledge.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alert.acknowledge.beans.AlertHistory;

@Repository
public interface HistoryDAO extends JpaRepository<AlertHistory, Integer>{

}
