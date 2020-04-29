package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;

@Configuration
public class RsocketConfig {

    // @Bean
    // public RSocket rSocket() {
    //     return RSocketFactory
    //         .connect()
    //         .mimeType(MimeTypeUtils.APPLICATION_JSON_VALUE, MimeTypeUtils.APPLICATION_JSON_VALUE)
    //         .frameDecoder(PayloadDecoder.ZERO_COPY)
    //         .transport(TcpClientTransport.create(7000))
    //         .start()
    //         .block();
    // }

    // @Bean
    // public RSocketRequester rSocketRequesster(RSocketStrategies rSocketStrategies) {
    //     return RSocketRequester.wrap(rSocket(), MimeTypeUtils.APPLICATION_JSON, MimeTypeUtils.APPLICATION_JSON, rSocketStrategies);
    // }

    @Autowired
    private RSocketMessageHandler messageHandler;

    @Bean
    public RSocketRequester rSocketRequesster(RSocketRequester.Builder builder) {
        String clientId = "admin";
        return builder
            .rsocketFactory(factory -> factory.acceptor(messageHandler.responder()))
            .setupData(clientId)
            .connectTcp("localhost", 7000).block();
    }

}