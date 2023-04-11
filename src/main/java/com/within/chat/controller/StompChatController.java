package com.within.chat.controller;

import com.within.chat.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final SimpMessagingTemplate simpMessagingTemplate; // 특정 브로커로 메시지 전달

    @MessageMapping(value = "chat/enter")
    public void enter(ChatMessageDto chatMessageDto) {
        chatMessageDto.setMessage(chatMessageDto.getSender() + "님이 채팅방에 참여하였습니다.");
        simpMessagingTemplate.convertAndSend("/sub/chat/room/" + chatMessageDto.getRoomId(), chatMessageDto);
    }
    @MessageMapping(value = "chat/message")
    public void message(ChatMessageDto chatMessageDto) {
        simpMessagingTemplate.convertAndSend("sub/chat/room/" + chatMessageDto.getRoomId(), chatMessageDto);
    }
}
