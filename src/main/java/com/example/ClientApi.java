package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.List;

public interface ClientApi {
    /**
     * @return List with all available clients.
     */
    public CompletableFuture<List<Client>> getClients();
}