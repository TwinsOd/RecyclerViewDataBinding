package com.example.twins.listofpictures.data;

import com.example.twins.listofpictures.model.ListImageModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface ApiService {

    @FormUrlEncoded
    @POST("list_img.json")
    Observable<ListImageModel> setQuery(@Field("") String string);

}
