package com.websocket.chatting_server.config.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //메시지 브로커와 관련된 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub"); //메시지 브로커가 /sub이 들어가는 구독자들에게 메시지를 전달해준다
        config.setApplicationDestinationPrefixes("/pub"); //클라이언트가 서버에게 /pub 을 붙이고 메시지를 전달할 주소
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //클라이언트가 서버에 접속할 Endpoint를설정한다.엔드포인트는 여러개 추가가능하다 .
        //client에서 Websocket대신 향상된 SockJS로 접속하려면 .withSockJS.()를 붙여준다.
        registry.addEndpoint("/ws-stomp").setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
