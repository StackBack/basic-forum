package com.forum.domain;

public enum Color {
    RED, BLUE, BLACK, WHITE, GREEN;

    @Override
    public String toString() {
        return "Color_" + name();
    }
}