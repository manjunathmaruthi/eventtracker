package com.creditsuisse.assignment.eventtracker;

import com.creditsuisse.assignment.eventtracker.config.Config;
import com.creditsuisse.assignment.eventtracker.service.EventLogParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventtrackerApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(EventtrackerApplication.class);

	@Autowired
	private Config config;

	@Autowired
	private EventLogParserService eventLogParserService;

	public static void main(String[] args) {
		SpringApplication.run(EventtrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(args.length == 0){
			logger.error("No commandline arugments found...");
			return;
		}
		config.startServer();
		config.dataSource();
		eventLogParserService.parser(args[0]);
		config.shutdownServer();
	}
}
