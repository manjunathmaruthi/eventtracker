package com.creditsuisse.assignment.eventtracker.dao;

import com.creditsuisse.assignment.eventtracker.model.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface EventTrackerDAO extends JpaRepository<EventLog, Id> {


}
