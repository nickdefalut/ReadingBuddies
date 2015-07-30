package com.example.lxia.readingbuddies;

/**
 * Created by lxia on 7/27/2015.
 */
public class Contact {

    int id;
    String name;

    public Contact(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
