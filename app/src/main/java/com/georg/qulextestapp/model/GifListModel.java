package com.georg.qulextestapp.model;

import com.georg.qulextestapp.model.rest.RestApi;
import com.georg.qulextestapp.model.rest.RetrofitServiceFactory;
import com.georg.qulextestapp.model.rest.pojo.Model;

import java.util.List;

import rx.Observable;

/**
 * Created by georg on 23.09.2016.
 */
public class GifListModel implements GifModel {

    @Override
    public Observable<List<String>> requestGifList(String request) {

        RestApi weatherService = RetrofitServiceFactory.getInstance();
        Observable<Model> modelObservable;

        if (request == null){
            modelObservable = weatherService.getTrendGifs();
        }
        else {
            modelObservable = weatherService.getRequestGifs(request);
        }

        return modelObservable
                .flatMap(model -> Observable.from(model.data))
                .map(datum -> datum.images.fixedHeightDownsampled.url)
                .filter(url -> url != null)
                .take(25)
                .toList();
    }

    @Override
    public Observable<List<String>> requestTrendGifList() {
        return requestGifList(null);
    }
}
