package com.example;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BalanceServiceImplTest {

    @Test
    void getClientTotalTradeCostReturnsZeroForEmptyList() {

        TradeApi tradeApi = () -> List.of();

        ClientApi clientApi = null;

        BalanceServiceImpl underTest = new BalanceServiceImpl(tradeApi, clientApi);

        Double result = underTest.getClientTotalTradeCost("1");

        assertEquals(0, result);
    }

    @Test
    void getClientTotalTradeCostReturnsCorrectTotalValueForMultipleTrades() {

        TradeApi tradeApi = () -> List.of(new Trade("1", 2, 500.0, "12345"),
                new Trade("1", 5, 250.0, "12345"));

        ClientApi clientApi = null;

        BalanceServiceImpl underTest = new BalanceServiceImpl(tradeApi, clientApi);

        Double result = underTest.getClientTotalTradeCost("12345");

        assertEquals(2250, result);
    }

    @Test
    void getClientTotalTradeCostReturnsCorrectValueAndIgnoresOtherClients() {

        TradeApi tradeApi = () -> List.of(new Trade("1", 2, 500.0, "12345"),
                new Trade("2", 6, 200, "54321"));

        ClientApi clientApi = null;

        BalanceServiceImpl underTest = new BalanceServiceImpl(tradeApi, clientApi);

        Double result = underTest.getClientTotalTradeCost("12345");

        assertEquals(1000, result);
    }


    @Test
    public void doTest() {
        TradeApi tradeApi = () -> List.of(new Trade("1", 2, 500.0, "1"),
                new Trade("2", 5, 500.0, "3"));

        ClientApi clientApi = new ClientApi() {
            @Override
            public CompletableFuture<List<Client>> getClients() {
                List<Client> clients = List.of(
                        new Client("1", "Alice", "UK"),
                        new Client("2", "Bob", "USA"),
                        new Client("3", "Charlie", "UK")
                );
                return CompletableFuture.completedFuture(clients);
            }
        };

        BalanceServiceImpl underTest = new BalanceServiceImpl(tradeApi, clientApi);

        Future<TradeReport> tradeReportFuture = underTest.createTradeReport("UK");

        try {
            System.out.println(tradeReportFuture.get().getClientCount());
            System.out.println(tradeReportFuture.get().getCountry());
            System.out.println(tradeReportFuture.get().getTotalTradeVolume());

            assertEquals("UK",tradeReportFuture.get().getCountry() );
            assertEquals(2,tradeReportFuture.get().getClientCount() );
            assertEquals(7.0,tradeReportFuture.get().getTotalTradeVolume() );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}