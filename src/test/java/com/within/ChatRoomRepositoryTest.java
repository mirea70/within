package com.within;

import com.within.chat.domain.ChatRoom;
import com.within.chat.domain.ChatRoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ChatRoomRepositoryTest {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Test
    @DisplayName("채팅방 생성 테스트")
    public void createChatRoomTest() {
        // given
        String expectId = UUID.randomUUID().toString();
        String expectName = "테스트방1";
        ChatRoom chatRoom = ChatRoom.builder()
                .id(expectId)
                .name(expectName)
                .build();
        // when
        ChatRoom savedChatRoom = chatRoomRepository.insert(chatRoom);
        // then
        assertEquals(expectName, savedChatRoom.getName());
        System.out.println("savedChatRoom.getName() = " + savedChatRoom.getName());
        System.out.println("expectName = " + expectName);
        assertEquals(expectId, savedChatRoom.getId());
    }
    @Test
    @DisplayName("채팅방 조회 테스트")
    public void findChatRoomTest() {
        // given
        String expectId = UUID.randomUUID().toString();
        String expectName = "테스트방2";
        ChatRoom chatRoom = ChatRoom.builder()
                .id(expectId)
                .name(expectName)
                .build();
        ChatRoom savedChatRoom = chatRoomRepository.insert(chatRoom);
        // when
        ChatRoom findChatRoom = chatRoomRepository.findById(expectId).orElseThrow();
        // then
        assertEquals(expectName, findChatRoom.getName());
        System.out.println("findChatRoom.getName() = " + findChatRoom.getName());
        System.out.println("expectName = " + expectName);
        assertEquals(expectId, findChatRoom.getId());
    }
    @Test
    @DisplayName("채팅방 수정 테스트")
    public void updateChatRoomTest() {
        // given
        String expectId = "97228625-c1c1-4160-b24e-411a82d5a3f2";
        ChatRoom findChatRoom = chatRoomRepository.findById(expectId).orElseThrow();
        // when
        String changeName = "수정방2";
        ChatRoom updateChatRoom = ChatRoom.builder()
                .id(expectId)
                .name(changeName)
                .build();
        ChatRoom changedChatRoom = chatRoomRepository.save(updateChatRoom);
        // then
        assertEquals(expectId, changedChatRoom.getId());
        assertEquals(changeName, changedChatRoom.getName());
        System.out.println("findChatRoom.getName() = " + findChatRoom.getName());
        System.out.println("changedChatRoom.getName() = " + changedChatRoom.getName());
    }

    @Test
    @DisplayName("채팅방 삭제 테스트")
    public void deleteChatRoomTest() {
        // given
        String expectId = "29ddc86f-2758-4173-90ae-d3d2b4766164";
        ChatRoom findChatRoom = chatRoomRepository.findById(expectId).orElseThrow();
        System.out.println("findChatRoom.getId() = " + findChatRoom.getId());
        // when
        chatRoomRepository.delete(findChatRoom);
        // then
        assertFalse(chatRoomRepository.existsById(expectId));
    }
}
