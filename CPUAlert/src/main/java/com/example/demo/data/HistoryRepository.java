
package com.example.demo.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	  @Query("SELECT h FROM AlertHistory h WHERE h.alertHistoryId.countryCode = :countryCode AND h.alertHistoryId.ebuNbr = :ebuNbr")
		public List<AlertHistory> getAllHistories(@Param ("countryCode")String countryCode, @Param("ebuNbr") int ebuNbr);

}

