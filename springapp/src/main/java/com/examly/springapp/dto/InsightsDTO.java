package com.examly.springapp.dto;


public class InsightsDTO {
    private String username;
    private long totalRequests;
    private long highUrgencyRequests;
    private long pendingRequests;

    
    public InsightsDTO() {
    }
    public InsightsDTO(String username, long totalRequests, long highUrgencyRequests, long pendingRequests) {
        this.username = username;
        this.totalRequests = totalRequests;
        this.highUrgencyRequests = highUrgencyRequests;
        this.pendingRequests = pendingRequests;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public long getTotalRequests() {
        return totalRequests;
    }
    public void setTotalRequests(long totalRequests) {
        this.totalRequests = totalRequests;
    }
    public long getHighUrgencyRequests() {
        return highUrgencyRequests;
    }
    public void setHighUrgencyRequests(long highUrgencyRequests) {
        this.highUrgencyRequests = highUrgencyRequests;
    }
    public long getPendingRequests() {
        return pendingRequests;
    }
    public void setPendingRequests(long pendingRequests) {
        this.pendingRequests = pendingRequests;
    }
    



}


