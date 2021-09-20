package com.creditsuisse.assignment.eventtracker.config;


import org.hsqldb.Server;

import org.hsqldb.server.ServerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.io.File;

@Component
public class Config {

	private static final Logger logger = LoggerFactory.getLogger(Config.class);
	private Server server;

	@Autowired
	private EventDBProperties eventDBProperties;


	public DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(eventDBProperties.getDriverClassName());
		dataSourceBuilder.url("jdbc:hsqldb:hsql://localhost:"+eventDBProperties.getServerport() + File.separator + eventDBProperties.getDbname());
		dataSourceBuilder.username(eventDBProperties.getUsername());
		dataSourceBuilder.password(eventDBProperties.getPassword());
		return dataSourceBuilder.build();
	}

	public void startServer() {
		if (server == null) {
			server = new Server();
			try {
				server.setPort(eventDBProperties.getServerport());
				server.setDatabaseName(eventDBProperties.getDbnumber(), eventDBProperties.getDbname());
				server.setDatabasePath(eventDBProperties.getDbnumber(),
						"file:../db" + File.separator + "HSQLDatabase" + File.separator + eventDBProperties.getDbname());
				server.setSilent(false);
				server.start();
				logger.info("Server started... ");
			} catch (Exception ex) {
				logger.warn("Exception during startup ", ex);
			}
		}
	}

	public void shutdownServer() {
		if (server.getState() == ServerConstants.SERVER_STATE_ONLINE) {
			server.shutdown();
			logger.info("HSQL server stopped");
		}
	}


}