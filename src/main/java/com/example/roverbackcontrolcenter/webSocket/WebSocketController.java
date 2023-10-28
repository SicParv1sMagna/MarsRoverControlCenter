package com.example.roverbackcontrolcenter.webSocket;

import org.springframework.stereotype.Controller;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Controller
public class WebSocketController {

    /*@MessageMapping("/chat") // Обработка сообщений, отправленных на "/app/chat"
    @SendTo("/topic/messages") // Отправка сообщений на "/topic/messages"
    public ChatMessage send(ChatMessage message) {
        return new ChatMessage(message.getSender(), message.getContent());
    }*/
}
