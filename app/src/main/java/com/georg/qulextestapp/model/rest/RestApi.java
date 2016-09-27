package com.georg.qulextestapp.model.rest;


import com.georg.qulextestapp.model.rest.pojo.Model;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RestApi {

    String URL = "http://api.giphy.com";
    String API_KEY = "dc6zaTOxFJmzC";

    @GET("/v1/gifs/trending?api_key=" + API_KEY)
    Observable<Model> getTrendGifs();

    @GET("/v1/gifs/search?api_key=" + API_KEY)
    Observable<Model> getRequestGifs(@Query("q") String cityName);

}