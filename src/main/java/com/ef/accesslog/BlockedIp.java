package com.ef.accesslog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Represents a blocked IP and comments of why this happened.
 */
@Entity
public class BlockedIp {

    private Long id;
    private String ip;
    private String comments;

    public BlockedIp(String ip, String comments) {
        this.ip = ip;
        this.comments = comments;
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
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
