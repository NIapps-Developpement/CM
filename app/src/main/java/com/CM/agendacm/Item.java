package com.CM.agendacm;

/**
 * Created by Nathan on 17-08-16.
 */
public class Item {
    private String name;
    private String date;

    public Item(String name, String date) {
        this.name = name;
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
