package com.within.chat.domain;

import com.within.chat.dto.ChatMessageDto;
import com.within.chat.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.socket.WebSocketSession;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Getter
@Document
public class ChatRoom {

    @Id
    private String id;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.id = roomId;
        this.name = name;
    }

    public void handlerActions(WebSocketSession session, ChatMessageDto chatMessageDto, ChatService chatService) {
        if(chatMessageDto.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);
            chatMessageDto.setMessage(chatMessageDto.getSender() + "님이 입장했습니다");
        }
        sendMessage(chatMessageDto, chatService);
    }

    private <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
    }
}
