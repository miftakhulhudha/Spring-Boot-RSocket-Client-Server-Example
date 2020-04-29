package com.example.demo.controller;

import com.example.demo.model.LocationModel;
import com.example.demo.model.LocationRequest;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationClientController {

    @Autowired
    private RSocketRequester rSocketRequester;

    @GetMapping(value = "/all")
    public Publisher<LocationModel> getAllLocation() {
        return rSocketRequester
            .route("allLocation", "")
            .retrieveFlux(LocationModel.class);
    }

    @GetMapping(value = "/allStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<LocationModel> getStreamAllLocation() {
        return rSocketRequester
            .route("allLocation", "")
            .retrieveFlux(LocationModel.class);
    }

    @GetMapping(value = "/my/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<LocationModel> getStreamMyLocation(@PathVariable("name") String name) {
        return rSocketRequester
            .route("curentLocation", "")
            .data(new LocationRequest(name))
            .retrieveFlux(LocationModel.class);
    }

    @PostMapping("/new")
    public Publisher<Void> save(@RequestBody LocationModel location) {
        return rSocketRequester
            .route("newLocation", "")
            .data(location)
            .send();
    }

    @GetMapping("/newLoc")
    public Publisher<Void> saveLoc(@RequestParam("name") String name, @RequestParam("x") double x, @RequestParam("y") double y) {
        return rSocketRequester
            .route("newLocation", "")
            .data(new LocationModel(name, x, y))
            .send();
    }

}