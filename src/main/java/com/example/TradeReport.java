package com.example;

public class TradeReport {
    private String country;
    private int clientCount;
    private double totalTradeVolume;

    public TradeReport(String country, int clientCount, double totalTradeVolume) {
        this.country = country;
        this.clientCount = clientCount;
        this.totalTradeVolume = totalTradeVolume;
    }

    public String getCountry() {
        return country;
    }

    public int getClientCount() {
        return clientCount;
    }

    public double getTotalTradeVolume() {
        return totalTradeVolume;
    }
}