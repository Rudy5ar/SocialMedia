package com.socialmedia.socialmediaclone.config;

import com.socialmedia.socialmediaclone.services.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    JwtService jwtService;

    public ChatWebSocketHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Authentication auth = (Authentication) session.getAttributes().get("auth");
        if (auth == null) {
            session.close();
            return;
        }
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        for (WebSocketSession s : sessions) {
            if (s.isOpen() && !s.equals(session)) {
                try {
                    s.sendMessage(new TextMessage(payload));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
