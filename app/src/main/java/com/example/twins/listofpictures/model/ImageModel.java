package com.example.twins.listofpictures.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Twins on 08.09.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageModel {

    @JsonProperty("url")
    private String url;

    @JsonProperty("text")
    private String text;

    private Boolean color = false;

    ImageModel(){}

    public ImageModel(String url, String text){
        this.url = url;
        this.text = text;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Boolean getColor() {
        return color;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }
}
