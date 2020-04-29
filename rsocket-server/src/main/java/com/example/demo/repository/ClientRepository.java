package com.example.demo.repository;

import org.springframework.messaging.rsocket.RSocketRequester;

import reactor.core.publisher.Mono;

public interface ClientRepository {

    Mono<RSocketRequester> findClient(String clientId);

    Mono<Void> saveClient(String clientId, RSocketRequester requester);

    Mono<Void> deleteClient(String clientId);

}