package com.within.chat.domain;

import com.within.chat.dto.ChatMessageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ChatMessage {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private String roomId;

    private String sender;

    private String message;

    public enum MessageType {
        ENTER("입장"),
        TALK("채팅중");
        public final String label;
        MessageType(String label) { this.label = label; }
    }
}
