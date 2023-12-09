package com.selivanov.springweb.bean;

public class Message {
    private String text;
    private User user;

    public Message(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "text='" + text + '\'' +
                ", user=" + user;
    }
}