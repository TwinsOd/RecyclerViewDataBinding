package com.example.twins.listofpictures.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Twins on 08.09.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageModel implements Parcelable {

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

    public ImageModel(Parcel in) {
        url = in.readString();
        text = in.readString();
        color = in.readSparseBooleanArray().get(0);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        sparseBooleanArray.put(0, color);
        dest.writeString(url);
        dest.writeString(text);
    }

    public static final Parcelable.Creator<ImageModel> CREATOR = new Parcelable.Creator<ImageModel>() {
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };
}
