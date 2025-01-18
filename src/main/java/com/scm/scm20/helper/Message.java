package com.scm.scm20.helper;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String content;
    @Enumerated(EnumType.STRING)
    private MessageType type;
}
