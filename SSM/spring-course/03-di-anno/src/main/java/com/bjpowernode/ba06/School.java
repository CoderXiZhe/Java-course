package com.bjpowernode.ba06;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mySchool")
public class School {
    @Value("江苏大学")
    private String name;
    @Value("镇江")
    private String address;

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
