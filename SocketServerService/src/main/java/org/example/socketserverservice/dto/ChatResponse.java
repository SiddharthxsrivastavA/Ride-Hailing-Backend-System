package org.example.socketserverservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    private String name;
    private String message;
    private String timeStamp;
}
