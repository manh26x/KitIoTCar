package com.kit.mike.controller;

import com.kit.mike.dao.AppUserDao;
import com.kit.mike.dao.TopicDao;
import com.kit.mike.entity.Topic;
import com.kit.mike.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class WebSocketController {

    @Autowired
    private TopicDao topicDao;

    @RequestMapping(value = "/topic/{topicId}", method = RequestMethod.GET)
    public String controlDevice(@PathVariable String topicId, Model model, Principal principal) {
        Long id = Long.parseLong(topicId);
        List<Topic> topicList =  this.topicDao.findTopic(principal.getName());
        Boolean canControl = false;
        for(Topic tp: topicList){
            if(tp.getId().longValue() == id.longValue())
                canControl = true;
        }


        model.addAttribute("canControl", canControl);
        model.addAttribute("topicId", topicId);
        return "controlDevicePage";
    }

    @MessageMapping("/chat.sendMessage")
    public String sendMessage(@Payload String chatMessage) {
        System.out.println("mes: " + chatMessage);
        return chatMessage;
    }


}
