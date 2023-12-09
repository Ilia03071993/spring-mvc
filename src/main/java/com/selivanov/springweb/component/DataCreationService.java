package com.selivanov.springweb.component;

import com.selivanov.springweb.bean.Message;
import com.selivanov.springweb.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class DataCreationService {
    @Value("${user.name}")
    private String name;
    @Value("${user.age}")
    private Integer age;
    @Value("${message.text}")
    private String text;

    public User createUser() {
        return new User(name, age);
    }

    public Message createMessage() {
        return new Message(text, createUser());
    }
}