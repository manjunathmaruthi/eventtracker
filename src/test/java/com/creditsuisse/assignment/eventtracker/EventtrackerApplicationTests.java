package com.creditsuisse.assignment.eventtracker;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;



import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(args = "logfile.txt")
public class EventtrackerApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testEventTrackerInserts() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM Event_Log ", Integer.class);
        assertTrue("No rows selected", count > 0);

    }
    

    
    
    
}