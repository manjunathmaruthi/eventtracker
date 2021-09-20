package com.creditsuisse.assignment.eventtracker.service;

import com.creditsuisse.assignment.eventtracker.dao.EventTrackerDAO;
import com.creditsuisse.assignment.eventtracker.model.EventLog;
import com.creditsuisse.assignment.eventtracker.model.EventLogDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class EventLogParserService {

    private static final Logger logger = LoggerFactory.getLogger(EventLogParserService.class);

    @Autowired
    private EventTrackerDAO eventTrackerDAO;

    public void parser(String file) {

        try (FileInputStream stream = new FileInputStream(file)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                String strLine;

                Map<String, EventLogDTO> map = new HashMap<>();

                while ((strLine = reader.readLine()) != null) {

                    EventLogDTO eventLogDTO = new ObjectMapper().readValue(strLine, EventLogDTO.class);
                    logger.info("Event log..{}", eventLogDTO);
                    if (!isValidLog(eventLogDTO)) {
                        continue;
                    }
                    if (map.containsKey(eventLogDTO.getId())) {
                        EventLogDTO eventLogDTOPrevious = map.get(eventLogDTO.getId());
                        long previousTimeStamp = Long.parseLong(eventLogDTOPrevious.getTimestamp());
                        long latestTimeStamp = Long.parseLong(eventLogDTO.getTimestamp());
                        long ms = Math.max(latestTimeStamp, previousTimeStamp) - Math.min(latestTimeStamp, previousTimeStamp);
                        boolean alert = false;
                        if (ms > 4) {
                            alert = true;
                        }
                        int millisecondInt = (int) ms;
                        String duration = new StringBuilder().append(millisecondInt).append("ms").toString();
                        EventLog eventLog = new EventLog(eventLogDTO.getId(), duration, eventLogDTO.getType(), eventLogDTO.getHost(), alert);
                        eventTrackerDAO.save(eventLog);
                        logger.info("Successfully saved event: {}",eventLog);
                    } else {
                        map.put(eventLogDTO.getId(), eventLogDTO);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("File Not found...", e);
        } catch (IOException e) {
            logger.error("Error..", e);
        }
    }

    private boolean isValidLog(EventLogDTO eventLogDTO){
        if(!eventLogDTO.getState().equals("STARTED") && !eventLogDTO.getState().equals("FINISHED")){
            return false;
        }
        return true;
    }
}