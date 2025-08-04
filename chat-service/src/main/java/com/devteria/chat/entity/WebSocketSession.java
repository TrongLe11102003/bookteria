package com.devteria.chat.entity;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "web_socket_session")
public class WebSocketSession {
    @MongoId
    String id;

    String socketSessionId;

    String userId;

    Instant createdAt;
}
