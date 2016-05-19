package com.zombie.show.bean;

/**
 *
 */

public class Greeting {
    private String name;
    private Long id;

    public Greeting(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public Long getId() {
        return id;
    }


}
