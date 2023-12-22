package com.selivanov.springweb.model;

public enum Aggregation {
    AVG("avg".toLowerCase()), MIN("min".toLowerCase()), MAX("max".toLowerCase()), SUM("sum".toLowerCase());

    Aggregation(String avg) {

    }
}