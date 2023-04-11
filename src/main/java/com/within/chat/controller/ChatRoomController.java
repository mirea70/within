package com.within.chat.controller;

import com.within.chat.domain.ChatRoom;
import com.within.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatService chatService;

    @PostMapping
    public ChatRoom chatRoom(@RequestParam String name) {
        return chatService.creatRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRooms() {
        return chatService.findAllRooms();
    }
}
