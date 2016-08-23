package com.example.abhi.newspeak.NyTimes.TopStories.Technology;

/**
 * Created by abhil on 23-08-2016.
 */
public class News {
    private String title;
    private String description;
    private String fullNewsUrl;
    private String imgUrl;
    private String imgCaption;

    public String getImgCaption() {
        return imgCaption;
    }

    public void setImgCaption(String imgCaption) {
        this.imgCaption = imgCaption;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullNewsUrl() {
        return fullNewsUrl;
    }

    public void setFullNewsUrl(String fullNewsUrl) {
        this.fullNewsUrl = fullNewsUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
