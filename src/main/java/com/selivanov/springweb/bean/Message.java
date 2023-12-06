package com.selivanov.springweb.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Configuration
////@PropertySource("application.properties")
//public class Message {
//    //  @Value("${message.text}")
//    private String text;
//    private User user;
//
//    @Autowired
//    public Message(String text, User user) {
//        this.text = text;
//        this.user = user;
//    }
//
//    public Message() {
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    @Override
//    public String toString() {
//        return "text='" + text + '\'' +
//                ", user=" + user;
//    }
//}
