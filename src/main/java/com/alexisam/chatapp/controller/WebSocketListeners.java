package com.alexisam.chatapp.controller;

import com.alexisam.chatapp.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Component
public class WebSocketListeners {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketListeners.class); // Imprime registros en la consola

    @Autowired // relaciona dependencias dentro de Spring

    private SimpMessageSendingOperations messagingTemplate;

    @EventListener// Entiendo que es para controlar la sala del chat

    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Conectado");
    } // cuando tengamos un nuevo usuario conectado al chat nos aparecera este mensaje de conectado

    @EventListener

    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event)
    {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null){
            logger.info("Desconectado: " + username);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        } // Aqui se coge el nombre del usuario que se desconecta y se envia a todos los usuarios que estan conectados
        // el nombre del usuario que se desconecta y el mensaje desconectado.
    }
}

