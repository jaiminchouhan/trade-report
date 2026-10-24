package com.example;

public class Trade {
    private String tradeId;
    private int quantity;
    private Double pricePerUnit;
    private String clientId;

    public Trade(String tradeId, int quantity, double pricePerUnit, String clientId) {
        this.tradeId = tradeId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.clientId = clientId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public String getClientId() {
        return clientId;
    }
}
