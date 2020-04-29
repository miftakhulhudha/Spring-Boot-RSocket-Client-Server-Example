package com.example.demo.rsocket;

import com.example.demo.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Mono;

@Controller
public class RSocketConfig {

    @Autowired
    private ClientRepository clientRepository;

    @ConnectMapping
    Mono<Void> handle(RSocketRequester requester, String clientId) {
        requester
            .rsocket()
            .onClose()
            .subscribe(null, null, () -> clientRepository.deleteClient(clientId).block());
        System.out.println("connected client "+clientId);
        clientRepository.saveClient(clientId, requester);
        return Mono.empty();
    }

}