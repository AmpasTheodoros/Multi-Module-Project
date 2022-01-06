package com.example.service.person;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class PersonSearch {
    private String maker;

    public PersonSearch(){}
    public PersonSearch(String maker) {
        this.maker = maker;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }
}
