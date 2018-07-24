package com.ef.accesslog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Represents a line in the access log file.
 *
 * Date, IP, Request, Status, User Agent (pipe delimited, open the example file in text editor)
 *
 * Date Format: "yyyy-MM-dd HH:mm:ss.SSS"
 */
@Entity
public class AccessLog {

    private Long id;
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

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Column
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Column
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column
    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

}
