package com.example.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.beans.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer>{

}
