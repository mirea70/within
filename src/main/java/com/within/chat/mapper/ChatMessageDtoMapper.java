package com.within.chat.mapper;

import com.within.chat.domain.ChatMessage;
import com.within.chat.dto.ChatMessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChatMessageDtoMapper {
    ChatMessageDtoMapper INSTANCE = Mappers.getMapper(ChatMessageDtoMapper.class);

    ChatMessageDto toDto(ChatMessage chatMessage);
    ChatMessage toEntity(ChatMessageDto chatMessageDto);
}
