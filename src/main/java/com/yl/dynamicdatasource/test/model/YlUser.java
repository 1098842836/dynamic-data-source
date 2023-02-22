package com.yl.dynamicdatasource.test.model;

import java.io.Serializable;

public class YlUser implements Serializable {
    private String id;

    private String name;

    private Integer age;

    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public YlUser(String id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
}