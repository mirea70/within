package com.within.chat.dto;

import com.within.chat.domain.ChatMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {

    private ChatMessage.MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
