package com.example.demo.repository.impl;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.repository.ClientRepository;

import org.springframework.messaging.rsocket.RSocketRequester;

import reactor.core.publisher.Mono;

public class InMemoryClientRepository implements ClientRepository {

    private Map<String, RSocketRequester> clients = new HashMap<>();


    @Override
    public Mono<RSocketRequester> findClient(String clientId) {
        return Mono.justOrEmpty(clients.get(clientId));
    }

    @Override
    public Mono<Void> saveClient(String clientId, RSocketRequester requester) {
        clients.put(clientId, requester);
        return Mono.empty();
    }

    @Override
    public Mono<Void> deleteClient(String clientId) {
        clients.remove(clientId);
        return Mono.empty();
    }

}