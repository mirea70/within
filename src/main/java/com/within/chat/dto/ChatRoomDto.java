package com.within.chat.dto;

import com.within.account.domain.Account;
import lombok.*;

import java.util.Set;

public class ChatRoomDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Create {
        private String id;
        private String name;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String id;
        private String name;
        private Set<Account> accounts;
    }
}
