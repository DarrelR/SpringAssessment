package com.spring.assessment.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by Darrel Rayen on 2/10/19.
 */
public enum OrderType {

    BOX("box"), ITEM("item");
    String text;

    OrderType(String text) {
        this.text = text;
    }

    @JsonCreator
    public static OrderType fromString(String string) {
        if ("box".equalsIgnoreCase(string)) {
            return BOX;
        } else if ("item".equalsIgnoreCase(string)) {
            return ITEM;
        } else {
            throw new IllegalArgumentException(string + " has no corresponding value");
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
