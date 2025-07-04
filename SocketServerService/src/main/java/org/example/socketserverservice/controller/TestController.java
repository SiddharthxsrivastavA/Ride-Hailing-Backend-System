package org.example.socketserverservice.controller;

import org.example.socketserverservice.dto.ChatRequest;
import org.example.socketserverservice.dto.ChatResponse;
import org.example.socketserverservice.dto.TestRequest;
import org.example.socketserverservice.dto.TestResponse;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    private final SimpMessagingTemplate simpMessagingTemplate;


    public TestController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/ping")
    @SendTo("/topic/ping")
    public TestResponse pingCheck(TestRequest message) {
        System.out.println("Received message from client " + message.getData());
        return TestResponse.builder().data("Received").build();
    }


//    @Scheduled(fixedDelay = 2000)
//    public void sendPeriodicMessage() {
//        System.out.println("Executed periodic function");
//        simpMessagingTemplate.convertAndSend("/topic/scheduled", "Periodic Message sent " + System.currentTimeMillis());
//    }

    @MessageMapping("/chat/{room}")
    @SendTo("/topic/message/{room}")
    public ChatResponse chatMessage(@DestinationVariable String room, ChatRequest request) {
        return ChatResponse.builder()
                .name(request.getName())
                .message(request.getMessage())
                .timeStamp("" + System.currentTimeMillis())
                .build();
    }

    @MessageMapping("/privateChat/{room}/{userId}")
//    @SendTo("/topic/privateMessage/{room}/{userId}")
    public void privateChatMessage(@DestinationVariable String room, @DestinationVariable String userId, ChatRequest request) {
        ChatResponse response =  ChatResponse.builder()
                .name(request.getName())
                .message(request.getMessage())
                .timeStamp("" + System.currentTimeMillis())
                .build();
        simpMessagingTemplate.convertAndSendToUser(userId, "/queue/privateMessage/" + room, response);
    }
}