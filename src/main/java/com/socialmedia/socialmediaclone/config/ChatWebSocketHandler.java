package com.socialmedia.socialmediaclone.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialmedia.socialmediaclone.model.User;
import com.socialmedia.socialmediaclone.repository.UserRepository;
import com.socialmedia.socialmediaclone.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ChatWebSocketHandler(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String token = getTokenFromSession(session);
        if (token != null) {
            String username = jwtService.extractUsername(token);
            if (username != null) {

                var user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("No username with username"));

                if (jwtService.isTokenValid(token, user)) {
                    session.getAttributes().put("username", username);
                    sessions.add(session);
                    System.out.println("WebSocket connection established with username: " + username);
                } else {
                    session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Invalid Token"));
                }

            } else {
                session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Invalid Token"));
            }
        } else {
            session.close(CloseStatus.NOT_ACCEPTABLE.withReason("No Token Provided"));
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String username = (String) session.getAttributes().get("username");
        if (username != null) {
            JsonNode payloadNode = objectMapper.readTree(message.getPayload());
            String recipient = payloadNode.get("to").asText();
            String textMessage = payloadNode.get("message").asText();
            System.out.println("Message from username " + username + ": " + textMessage);

            for (WebSocketSession s : sessions) {
                if (s.isOpen() && s.getAttributes().get("username").equals(recipient)) {
                    s.sendMessage(new TextMessage(username + ": " + message.getPayload()));
                }
            }
        } else {
            session.close(CloseStatus.NOT_ACCEPTABLE.withReason("User not authenticated"));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        System.out.println("WebSocket connection closed with username: " + session.getAttributes().get("username"));
    }

    private String getTokenFromSession(WebSocketSession session) {
        // Extract token from query parameters
        String query = session.getUri().getQuery();
        if (query != null) {
            for (String param : query.split("&")) {
                String[] pair = param.split("=");
                if (pair.length == 2 && "token".equals(pair[0])) {
                    return pair[1];
                }
            }
        }
        return null;
    }
}
