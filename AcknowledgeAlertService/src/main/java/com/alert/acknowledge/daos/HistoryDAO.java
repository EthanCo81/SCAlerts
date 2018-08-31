package com.alert.acknowledge.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alert.acknowledge.beans.AlertHistory;
import com.alert.acknowledge.beans.AlertIdentity;

@Repository
public interface HistoryDAO extends JpaRepository<AlertHistory, AlertIdentity>{

}
