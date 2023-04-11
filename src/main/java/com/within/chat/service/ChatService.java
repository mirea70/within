package com.within.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.within.chat.domain.ChatRoom;
import com.within.chat.domain.ChatRoomRepository;
import com.within.chat.mapper.ChatRoomDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> findAllRooms() {
        return chatRoomRepository.findAll(Sort.by("createdAt").descending());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow(
                () -> new IllegalArgumentException("해당 채팅방은 존재하지 않습니다."));
    }

    public ChatRoom creatRoom(String name) {
        return ChatRoomDtoMapper.INSTANCE.toEntityByName(name);
    }

//    public <T> void sendMessage(WebSocketSession session, T message) {
//        try {
//            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
}
