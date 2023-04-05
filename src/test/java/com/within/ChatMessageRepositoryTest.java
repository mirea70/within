package com.within;

import com.within.chat.domain.ChatMessage;
import com.within.chat.domain.ChatMessageRepository;
import com.within.chat.domain.ChatRoom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ChatMessageRepositoryTest {
    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Test
    @DisplayName("채팅 메시지 삽입 테스트")
    public void createChatMessageTest() {
        // given
        String expectId = "1";
        String expectMessage = "테스트 메시지1";
        ChatMessage chatMessage = ChatMessage.builder()
                .id(expectId)
                .roomId("c16b725a-c2a1-40ca-a9b0-f5d0f16a5c08")
                .messageType(ChatMessage.MessageType.ENTER)
                .sender("사용자1")
                .message(expectMessage)
                .build();
        // when
        ChatMessage savedChatMessage = chatMessageRepository.insert(chatMessage);
        // then
        assertEquals(expectMessage, savedChatMessage.getMessage());
        System.out.println("expectMessage = " + expectMessage);
        System.out.println("savedChatMessage.getMessage() = " + savedChatMessage.getMessage());
        assertEquals(expectId, savedChatMessage.getId());
    }
    @Test
    @DisplayName("채팅 메시지 조회 테스트")
    public void findChatMessageTest() {
        // given
        String expectId = "2";
        String expectMessage = "테스트 메시지2";
        ChatMessage chatMessage = ChatMessage.builder()
                .id(expectId)
                .roomId("c16b725a-c2a1-40ca-a9b0-f5d0f16a5c08")
                .messageType(ChatMessage.MessageType.ENTER)
                .sender("사용자2")
                .message(expectMessage)
                .build();
        chatMessageRepository.save(chatMessage);
        // when
        ChatMessage findChatMessage = chatMessageRepository.findById(expectId).orElseThrow();
        // then
        assertEquals(expectMessage, findChatMessage.getMessage());
        System.out.println("expectMessage = " + expectMessage);
        System.out.println("findChatMessage.getMessage() = " + findChatMessage.getMessage());
        assertEquals(expectId, findChatMessage.getId());
    }
    @Test
    @DisplayName("채팅 메시지 수정 테스트")
    public void updateChatMessageTest() {
        // given
        String originalId = "2";
        ChatMessage findChatMessage = chatMessageRepository.findById(originalId).orElseThrow();
        String originalMessage = findChatMessage.getMessage();
        ChatMessage.MessageType originalMessageType = findChatMessage.getMessageType();
        String originalRoomId = findChatMessage.getRoomId();
        String originalSender = findChatMessage.getSender();
        // when
        String changeMessage = "수정메시지";
        ChatMessage updateChatMessage = ChatMessage.builder()
                .id(originalId)
                .messageType(originalMessageType)
                .message(changeMessage)
                .roomId(originalRoomId)
                .sender(originalSender)
                .build();
        ChatMessage changedChatMessage = chatMessageRepository.save(updateChatMessage);
        // then
        assertEquals(originalId, changedChatMessage.getId());
        assertEquals(originalRoomId, changedChatMessage.getRoomId());
        assertEquals(originalMessageType, changedChatMessage.getMessageType());
        assertEquals(originalSender, changedChatMessage.getSender());
        assertNotEquals(originalMessage, changedChatMessage.getMessage());
        assertEquals(changeMessage, changedChatMessage.getMessage());
        System.out.println("changeMessage = " + changeMessage);
        System.out.println("changedChatMessage.getMessage() = " + changedChatMessage.getMessage());
    }

    @Test
    @DisplayName("채팅 메시지 삭제 테스트")
    public void deleteChatMessageTest() {
        // given
        String expectId = "1";
        ChatMessage findChatMessage = chatMessageRepository.findById(expectId).orElseThrow();
        System.out.println("findChatMessage.getId() = " + findChatMessage.getId());
        // when
        chatMessageRepository.delete(findChatMessage);
        // then
        assertFalse(chatMessageRepository.existsById(expectId));
    }

}
