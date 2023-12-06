package com.selivanov.springweb.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
//@PropertySource("application.properties")
public class User {
  //  @Value(("${user.name}"))
    private  String name;
  //  @Value("${user.age}")
    private  Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public User(){}
    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}