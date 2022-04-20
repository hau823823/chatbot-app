package com.example.apptest2;

public class Favoriteitem {
    private String uId;
    private String pId;
    private String pName;
    private String pIntroduce;
    private String pTel;
    private String pAddress;
    private String pTime;
    private String pIMG;

    public Favoriteitem(String uId, String pId, String pName, String pIntroduce, String pTel, String pAddress, String pTime, String pIMG) {

        this.uId = uId;
        this.pId = pId;
        this.pName = pName;
        this.pIntroduce = pIntroduce;
        this.pTel = pTel;
        this.pAddress = pAddress;
        this.pTime = pTime;
        this.pIMG = pIMG;

    }

    public String getuId() {
        return uId;
    }

    public String getpId() {
        return pId;
    }

    public String getpName() {
        return pName;
    }

    public String getpIntroduce() {
        return pIntroduce;
    }

    public String getpTel() {
        return pTel;
    }

    public String getpAddress() {
        return pAddress;
    }

    public String getpTime() {
        return pTime;
    }

    public String getpIMG() {
        return pIMG;
    }
}