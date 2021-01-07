package com.forum.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Color {
    RED("RED"),
    BLUE("BLUE"),
    BLACK("BLACK"),
    WHITE("WHITE"),
    GREEN("GREEN");

    private final String value;

    private Color(String value){
        this.value = value;
    }

    @JsonValue
    final String value() {
        return this.value;
    }
}