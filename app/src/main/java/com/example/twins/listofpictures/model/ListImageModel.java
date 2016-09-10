package com.example.twins.listofpictures.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Twins on 08.09.2016.
 */



public class ListImageModel {

    @JsonProperty("ImageURL")
    private ArrayList<ImageModel> imageModelList = new ArrayList<ImageModel>();

    public ArrayList<ImageModel> getImageModelListL() {
        return imageModelList;
    }
}
