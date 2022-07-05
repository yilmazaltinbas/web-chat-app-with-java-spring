package com.bitirmeprojesi.chatapplication;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage") // 14. kod satırında ki işlemimizde ki dataları maplemek için kullanıyoruz.
    @SendTo("/topic/public") // burada ise messagemapping'in nereye sendto olacağını hangi path e gideceği değerini yazdık.
    public ChatMessagePojo sendMessage(@Payload ChatMessagePojo chatMessagePojo) {
        return chatMessagePojo;
    } // gelen request payload anatasyonuyla payload'a yani body'ye dataları biriktiriyoruz.

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessagePojo addUser(@Payload ChatMessagePojo chatMessagePojo, SimpMessageHeaderAccessor headerAccessor) {

// Web soket oturumunda kullanıcı adı ekle
        headerAccessor.getSessionAttributes().put("username", chatMessagePojo.getSender());
        return chatMessagePojo;
    }
}
