package com.guzman.z.shane.suprtek.entity;

import java.math.BigDecimal;
import java.util.Optional;

public abstract class Person {
    private String name;

    static String tab(int depth) {
        String tabs = "";
        for (int i = 0; i < depth; i++) {
            tabs = tabs.concat("\t");
        }
        return tabs;
    }

    public Person(String name) {
        this.name = Optional.ofNullable(name).orElse("unknown name");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
