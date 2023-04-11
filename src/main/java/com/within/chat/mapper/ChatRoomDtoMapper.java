package com.within.chat.mapper;

import com.within.chat.domain.ChatMessage;
import com.within.chat.domain.ChatRoom;
import com.within.chat.dto.ChatMessageDto;
import com.within.chat.dto.ChatRoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChatRoomDtoMapper {
    ChatRoomDtoMapper INSTANCE = Mappers.getMapper(ChatRoomDtoMapper.class);

    ChatRoomDto.Response toResponse(ChatRoom chatRoom);
    ChatRoom toEntity(ChatRoomDto.Create chatRoomCreateDto);
    ChatRoom toEntityByName(String name);
}
