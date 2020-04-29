package com.example.demo.repository.impl;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.model.LocationModel;
import com.example.demo.repository.LocationRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class InMemoryLocationRepository implements LocationRepository{


    private Map<String, LocationModel> data = new HashMap<>();


    @Override
    public Mono<LocationModel> findByName(String name) {
        return Mono.just(data.get(name));
    }

    @Override
    public Flux<LocationModel> findAll() {
        return Flux.fromIterable(data.values());
    }

    @Override
    public Mono<Void> save(LocationModel location) {
        data.put(location.getName(), location);
        return Mono.empty();
    }



}