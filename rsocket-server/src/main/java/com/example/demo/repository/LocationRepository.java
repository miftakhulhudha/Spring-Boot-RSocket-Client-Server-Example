package com.example.demo.repository;

import com.example.demo.model.LocationModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LocationRepository {

    Mono<LocationModel> findByName(String name);

    Flux<LocationModel> findAll();

    Mono<Void> save(LocationModel location);

}