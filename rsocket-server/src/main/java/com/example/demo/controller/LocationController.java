package com.example.demo.controller;

import java.time.Duration;

import com.example.demo.model.LocationModel;
import com.example.demo.model.LocationRequest;
import com.example.demo.repository.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @MessageMapping("curentLocation")
    public Flux<LocationModel> myLocation(LocationRequest request) {
        return Flux.interval(Duration.ofMillis(10))
                .map(dur -> locationRepository.findByName(request.getName()).block());
    }

    @MessageMapping("allLocation")
    public Flux<LocationModel> allLocation() {
        return locationRepository.findAll();
    }

    @MessageMapping("newLocation")
    public Mono<Void> addLocation(LocationModel location) {
        return locationRepository.save(location);
    }

}