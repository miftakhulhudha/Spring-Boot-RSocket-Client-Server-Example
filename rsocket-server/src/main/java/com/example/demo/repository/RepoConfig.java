package com.example.demo.repository;

import com.example.demo.repository.impl.InMemoryClientRepository;
import com.example.demo.repository.impl.InMemoryLocationRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfig {

    @Bean
    public ClientRepository clientRepository() {
        return new InMemoryClientRepository();
    }

    @Bean
    public LocationRepository locationRepository() {
        return new InMemoryLocationRepository();
    }

}