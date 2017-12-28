package com.maldiny.spring.boot.websocket;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }
    
    @Scheduled(fixedRate = 10)
    public void reportCurrentTime() {
    	this.template.convertAndSend("/time",  new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()));
    }
}