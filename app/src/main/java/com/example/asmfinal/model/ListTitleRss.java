package com.example.asmfinal.model;

public class ListTitleRss {
    private String title, link;
    private int image;

    public ListTitleRss() {
    }

    public ListTitleRss(String title, String link, int image) {
        this.title = title;
        this.link = link;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
