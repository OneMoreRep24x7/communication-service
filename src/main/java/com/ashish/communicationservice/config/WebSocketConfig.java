package com.ashish.communicationservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        System.out.println("configureMessageBroker");
        config.enableSimpleBroker("/topic"); // Enables a simple in-memory message broker
        config.setApplicationDestinationPrefixes("/app"); // Defines the prefix to use when mapping message-handling methods
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        System.out.println("registerStompEndpoints");
        registry.addEndpoint("/ws");
        registry.addEndpoint("/ws").withSockJS(); // Registers the "/ws" endpoint, enabling SockJS fallback options
        registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:4200").withSockJS();
    }
}
