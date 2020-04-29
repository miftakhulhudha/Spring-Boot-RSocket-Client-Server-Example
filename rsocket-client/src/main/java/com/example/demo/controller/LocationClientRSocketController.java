package com.example.demo.controller;

import com.example.demo.model.LocationModel;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Mono;

@Controller
public class LocationClientRSocketController {

    @MessageMapping("receiveCurrentLocation")
    public Mono<Void> receiveLocation(LocationModel location) {
        System.out.println("receave location x : " + location.getX() + " y : " + location.getY());
        return Mono.empty();
    }

}