package com.ef.accesslog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Represents a line in the access log file.
 */
@Entity
public class AccessLog {

    private Long id;
    private LocalDateTime localDate;
    private String ip;
    private String request;
    private Integer status;
    private String userAgent;

    public AccessLog(LocalDateTime localDate, String ip, String request, Integer status, String userAgent) {
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
    public LocalDateTime getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDateTime localDate) {
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
