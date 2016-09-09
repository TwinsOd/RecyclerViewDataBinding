package com.example.twins.listofpictures.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Twins on 08.09.2016.
 */



public class ListImageModel {

    @JsonProperty("ImageURL")
    private List<ImageModel> imageModelList = new ArrayList<ImageModel>();

    public List<ImageModel> getImageModelListL() {
        return imageModelList;
    }
}
