package com.safewalk.dispatch_webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.safewalk.dispatch_webapp.websocket.LocationWebSocketHandler;

@Configuration
@EnableWebSocket
public class WsConfig implements WebSocketConfigurer {

    @NonNull
    private final LocationWebSocketHandler locationWebSocketHandler;

    public WsConfig(@NonNull LocationWebSocketHandler locationWebSocketHandler) {
        this.locationWebSocketHandler = locationWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        registry.addHandler(locationWebSocketHandler, "/ws")
        .setAllowedOrigins("*");
    }

}
