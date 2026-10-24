package com.example;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class BalanceServiceImpl implements BalanceService {

    private final TradeApi tradeApi;
    private final ClientApi clientApi;

    public BalanceServiceImpl(TradeApi tradeApi, ClientApi clientApi) {
        this.tradeApi = tradeApi;
        this.clientApi = clientApi;
    }

    @Override
    public Double getClientTotalTradeCost(String clientId) {

        List<Trade> tradesList = tradeApi.getTrades();

        Double total = tradesList.stream()
                .filter(t -> clientId.equals(t.getClientId()))
                .mapToDouble(t -> calculateTotalCostPerTrade(t.getPricePerUnit(), t.getQuantity()))
                .sum();

        return total;
    }

    private Double calculateTotalCostPerTrade(final Double pricePerUnit, final int quantity) {
        return pricePerUnit * quantity;
    }

    @Override
    public Future<TradeReport> createTradeReport(String country) {

        final List<Trade> tradeList = tradeApi.getTrades();
        final CompletableFuture<List<Client>> clientListFuture = clientApi.getClients();

        try {
            final List<Client> clientList = clientListFuture.get();

            final List<String> clientsByCountry = clientList.stream()
                    .filter(c -> c.getCountry().equals(country))
                    .map(Client::getClientId)
                    .collect(Collectors.toList());

            ExecutorService executor = Executors.newSingleThreadExecutor();

            Future<TradeReport> future = executor.submit(() -> {
                try {
                    List<Trade> filteredTrades = tradeList.stream()
                            .filter(t -> clientsByCountry.contains(t.getClientId()))
                            .toList();

                    int count = filteredTrades.size();
                    int sum = filteredTrades.stream().mapToInt(Trade::getQuantity).sum();

                    return new TradeReport(country, count, sum);
                } finally {
                    executor.shutdown(); // Shutdown to avoid thread leak
                }
            });

            return future;

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error creating trade report", e);
        }
    }

}
