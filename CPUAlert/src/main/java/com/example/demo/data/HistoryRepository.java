package com.example.demo.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.beans.AlertHistory;
import com.example.beans.EBUid;



@Repository

public interface HistoryRepository extends JpaRepository<AlertHistory, EBUid>{

    @Query("SELECT h FROM AlertHistory h WHERE h.countryCode = :countryCode AND h.ebuNsbr = :ebuNbr")
	public List<AlertHistory> getHistory(@Param ("countryCode")String countryCode, @Param("ebuNbr") int ebuNbr);

}
