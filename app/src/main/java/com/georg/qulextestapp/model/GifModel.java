package com.georg.qulextestapp.model;

import java.util.List;

import rx.Observable;

/**
 * Created by georg on 23.09.2016.
 */
public interface GifModel extends MVPModel {

    Observable<List<String>> requestGifList(String request);

    Observable<List<String>> requestTrendGifList();

}
