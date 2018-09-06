package com.example.demo.data;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.example.beans.EBUInfo;
import com.example.demo.CpuAlertApplication;

/**
 *  Alert History Re Testing class
 * 
 * @author Thomas Santillan | September 6, 2018
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=CpuAlertApplication.class)
public class HistoryRepositoryTest {

}
