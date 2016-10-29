package com.CM.agendacm;

/**
 * Created by Nathan on 17-08-16.
 */
public class ItemCal{
    private String name;
    private String date;
    private String place;

    public ItemCal(String name, String date, String place) {
        this.name = name;
        this.date = date;
        this.place = place;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }



}
