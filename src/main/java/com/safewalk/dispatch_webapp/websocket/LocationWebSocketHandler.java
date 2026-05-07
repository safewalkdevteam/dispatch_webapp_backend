package com.safewalk.dispatch_webapp.websocket;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safewalk.dispatch_webapp.dto.TeamPingDto;
import com.safewalk.dispatch_webapp.mapper.TeamPingMapper;
import com.safewalk.dispatch_webapp.repository.TeamPingRepository;

@Component
public class LocationWebSocketHandler extends TextWebSocketHandler {
    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    private TeamPingRepository teamPingRepository;

    public LocationWebSocketHandler(TeamPingRepository teamPingRepository) {
        this.teamPingRepository = teamPingRepository;
    }

    public void sendPing(Object message, WebSocketSession session) {

        System.out.println("SENDING MESSAGE TO SESSION: " + session.getId() + " MESSAGE: " + ((TeamPingDto) message).getTeam());
        String json;
        try {
            json = new ObjectMapper().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        try {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(json));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        System.out.println("New WebSocket connection established: " + session.getId());
        teamPingRepository.findAll().forEach(teamPing -> sendPing(TeamPingMapper.mapToTeamPingDto(teamPing), session));
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        sessions.remove(session);
    }

    public void broadcast(Object message) {
        System.out.println("BROADCASTING MESSAGE");
        sessions.forEach(session -> sendPing(message, session));
    }

}
