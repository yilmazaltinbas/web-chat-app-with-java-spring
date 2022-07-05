package com.bitirmeprojesi.chatapplication;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    } // /ws endpoint'ine registry oluyoruz. Mesajı yollayacağımız ve dinleyeceğimiz endpointimiz /ws.
    // Ve bunları SockJs ile yapacağımız için .withSockJS uzantımızı da tanımladık.

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); //ön ek tanımladığımız kısım. @MessageMapping anatasyonu kullanılırken ihtiyacımız olacağı için tanımladık./app/topic
        registry.enableSimpleBroker("/topic");// mesajı gönderdiğimiz yer /ws path'i.   Mesajı aldığımız yer /topic path'i
    }
}
