package com.example.demo.controller;

import com.example.demo.model.LocationModel;
import com.example.demo.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/push")
public class LocationControllerPush {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/clients/{clientId}")
    public Mono<Void> pushToClientId(@PathVariable("clientId") String clientId, @RequestBody LocationModel model) {
        return clientRepository.findClient(clientId)
                    .flatMap(requester ->
                        requester.route("receiveCurrentLocation", "")
                        .data(model)
                        .send()
                    );
    }   

}