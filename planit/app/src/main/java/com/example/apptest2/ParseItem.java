package com.example.apptest2;

public class ParseItem {

    private String infobar;
    private String title;
    private String imgUrl;
    private String detailUrl;


    public ParseItem(String infobar, String title, String imgUrl, String detailUrl) {
        this.infobar = infobar;
        this.title = title;
        this.imgUrl = imgUrl;
        this.detailUrl = detailUrl;
    }

    public String getInfobar() {
        return infobar;
    }

    public void setInfobar(String infobar) {
        this.infobar = infobar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl)  {
        this.detailUrl = detailUrl;
    }
}
