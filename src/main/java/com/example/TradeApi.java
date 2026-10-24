package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.List;

public interface TradeApi {
    /**
     * @return List with all available trades.
     */
    public List<Trade> getTrades();
}