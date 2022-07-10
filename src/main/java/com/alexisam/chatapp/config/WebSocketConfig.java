package com.alexisam.chatapp.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker // Activa nuestro WebSocket server
// implementamos la interfaz WebSocketMessageBrokerConfigurer y proporcionamos implementación de algunos de sus métodos para configurar la conexión del websocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    // registramos un websocket endpoint que usaran los clientes para connectarse a nuestro websocket server
    // usamos el metodo STOMP que es un protocolo de mensajería que define el formato y las reglas para el intercambio de datos
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
        //.withSockJS() se utiliza para habilitar las opciones de fallback para los navegadores que no soportan websocket
    }

    @Override
    // Aqui configuramos un broker que va a enrutar los mensajes de un cliente a otro
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
