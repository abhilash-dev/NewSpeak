package com.example.abhi.newspeak.NyTimes;

/**
 * Created by abhil on 31-08-2016.
 */
public class Topic {
    private String title;
    private String imgURL;
    private String jsonURL;

    public String getJsonURL() {
        return jsonURL;
    }

    public void setJsonURL(String jsonURL) {
        this.jsonURL = jsonURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
