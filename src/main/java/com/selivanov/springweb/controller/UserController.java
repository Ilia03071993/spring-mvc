package com.selivanov.springweb.controller;

import com.selivanov.springweb.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
//    private final MessageService messageService;
//
//    @Autowired
//    public UserController(MessageService messageService) {
//        this.messageService = messageService;
//    }

    @ResponseBody
    @GetMapping("/user")
    public String getUserController() {
        return new User("Linda",22).toString();
    }

//    @ResponseBody
//    @GetMapping("/message")
//    public String getMessageController() {
//        return new Message().toString();
//    }

//OLD
//    @ResponseBody
//    @GetMapping("/user")
//    public String getUserController() {
//        User user1 = new User("Lui", 12);
//        User user2 = new User("Linda", 22);
//        List<User> userList = new ArrayList<>(List.of(user1,user2));
//
//        return userList.toString();
//    }
//
//    @ResponseBody
//    @GetMapping("/message")
//    public String getMessageController() {
//        List<Message> messageList = new ArrayList<>(List.of(
//                new Message("Hi from Russia", LocalDateTime.now()),
//                new Message("Hi from UK", LocalDateTime.now(ZoneId.of("America/New_York")))
//        ));
//
//        return messageList.toString();
//    }

}
