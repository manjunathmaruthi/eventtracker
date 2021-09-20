package com.creditsuisse.assignment.eventtracker;

import com.creditsuisse.assignment.eventtracker.service.EventLogParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventtrackerApplication implements ApplicationRunner {

	@Autowired
	private EventLogParserService eventLogParserService;

	public static void main(String[] args) {
		SpringApplication.run(EventtrackerApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		eventLogParserService.parser(args.getSourceArgs()[0]);
	}

}
