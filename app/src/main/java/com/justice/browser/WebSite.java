package com.justice.browser;

import java.io.Serializable;

public class WebSite implements Serializable {
    private String name;
    private String url;
    private int image;

    public WebSite(String name, String url, int image) {
        this.name = name;
        this.url = url;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
