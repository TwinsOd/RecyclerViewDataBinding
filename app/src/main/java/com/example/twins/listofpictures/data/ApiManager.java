package com.example.twins.listofpictures.data;

import android.support.annotation.NonNull;

import com.example.twins.listofpictures.model.ListImageModel;
import rx.Observable;

/**
 * Created by Twins on 26.07.2016.
 */

public class ApiManager {
    private static final int RETRY_COUNT_FOR_REQUEST = 0;

    private ApiManager() {}
    @NonNull
    public static Observable<ListImageModel> getListImage() {
        return ApiFactory.getApiService()
                .setQuery("")
                .retry(RETRY_COUNT_FOR_REQUEST)
                .map(response -> response);
    }
}
