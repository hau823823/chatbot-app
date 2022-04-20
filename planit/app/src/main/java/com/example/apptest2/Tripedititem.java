package com.example.apptest2;

public class Tripedititem {

    private String sId;
    private String schname;
    private String place;

    public Tripedititem(String sId, String schname, String place) {

        this.sId = sId;
        this.schname = schname;
        this.place = place;

    }

    public String getsId() {
        return sId;
    }

    public String getschname() {
        return schname;
    }

    public String getplace() {
        return place;
    }
}
