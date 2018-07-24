package com.arguments;

public class Arguments {
    private String accessLogPath;
    private String startDate;
    private String duration;
    private Integer threshold;

    public Arguments(String accessLogPath, String startDate, String duration, Integer threshold) {
        this.accessLogPath = accessLogPath;
        this.startDate = startDate;
        this.duration = duration;
        this.threshold = threshold;
    }

    public String getAccessLogPath() {
        return accessLogPath;
    }

    public void setAccessLogPath(String accessLogPath) {
        this.accessLogPath = accessLogPath;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }
}
