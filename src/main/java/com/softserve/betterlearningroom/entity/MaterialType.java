package com.softserve.betterlearningroom.entity;

import lombok.Getter;

@Getter
public enum MaterialType {
    TASK("TASK"), TEST("TEST"), QUESTIONS("QUESTIONS"), MATERIAL("MATERIAL");

    private String name;

    MaterialType(String name) {
        this.name = name;
    }
}
