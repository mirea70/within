package com.within;

import com.within.chat.domain.ChatRoom;
import com.within.chat.domain.ChatRoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

//@SpringJUnitConfig(MongoTestConfiguration.class)
//@EnableAutoConfiguration
//@DataMongoTest
@SpringBootTest
public class ChatRoomRepositoryTest {
    @Autowired
//    @Qualifier("testMongoTemplate")
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
}
