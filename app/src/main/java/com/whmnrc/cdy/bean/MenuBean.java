package com.whmnrc.cdy.bean;

public class MenuBean {

    private String title;
    private int img;

    public MenuBean(String title, int img) {
        this.title = title;
        this.img = img;
    }

    public MenuBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
