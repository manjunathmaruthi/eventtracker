package com.creditsuisse.assignment.eventtracker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "event.database")
@Configuration("eventDBProperties")
public class EventDBProperties {

    @NotNull
    private Integer serverport;

    @NotNull
    private Integer dbnumber;

    @NotNull
    private String dbname;

    @NotNull
    private String url;

    @NotNull
    private String username;

    private String password;

    @NotNull
    private String driverClassName;

    public Integer getServerport() {
        return serverport;
    }

    public Integer getDbnumber() {
        return dbnumber;
    }

    public String getDbname() {
        return dbname;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setServerport(Integer serverport) {
        this.serverport = serverport;
    }

    public void setDbnumber(Integer dbnumber) {
        this.dbnumber = dbnumber;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
