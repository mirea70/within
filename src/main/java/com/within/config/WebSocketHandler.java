//package com.within.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.within.chat.domain.ChatRoom;
//import com.within.chat.dto.ChatMessageDto;
//import com.within.chat.service.ChatService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class WebSocketHandler extends TextWebSocketHandler {
//    private final ObjectMapper objectMapper;
//    private final ChatService chatService;
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        log.info("{}", payload);
//        ChatMessageDto chatMessageDto = objectMapper.readValue(payload, ChatMessageDto.class);
//        ChatRoom chatRoom = chatService.findRoomById(chatMessageDto.getRoomId());
//        chatRoom.handlerActions(session, chatMessageDto, chatService);
//    }
//}
