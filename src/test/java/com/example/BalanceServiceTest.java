package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class BalanceServiceTest {

    @Test
    public void shouldNotEqual() {
        assertEquals(0, 1);
    }

    @Test
    public void shouldNotEqualAsync() throws InterruptedException, ExecutionException {
        Future<Integer> value = CompletableFuture.completedFuture(1);

        assertEquals(0, value.get().intValue());
    }
}
