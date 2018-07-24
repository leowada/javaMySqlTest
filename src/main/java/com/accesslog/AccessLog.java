package com.accesslog;

import java.time.LocalDate;

/**
 * Represents a line in the access log file.
 *
 * Date, IP, Request, Status, User Agent (pipe delimited, open the example file in text editor)
 *
 * Date Format: "yyyy-MM-dd HH:mm:ss.SSS"
 */
public class AccessLog {

    private LocalDate localDate;
    private String ip;
    private String request;
    private Integer status;
    private String userAgent;

    public AccessLog(LocalDate localDate, String ip, String request, Integer status, String userAgent) {
        this.localDate = localDate;
        this.ip = ip;
        this.request = request;
        this.status = status;
        this.userAgent = userAgent;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

}
