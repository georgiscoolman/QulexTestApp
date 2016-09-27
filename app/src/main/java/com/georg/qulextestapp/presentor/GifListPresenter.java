package com.georg.qulextestapp.presentor;

import android.os.Looper;
import android.util.Log;

import com.georg.qulextestapp.model.GifListModel;
import com.georg.qulextestapp.view.GifListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by georg on 23.09.2016.
 */
public class GifListPresenter extends BasePresenter<GifListView> {

    private static final String VIEW_LIST_SUBSCRIBER = "ViewListSubscriber";
    private static String lastRequest = null;
    private GifListModel model;

    @Inject
    public GifListPresenter(GifListModel model) {
        this.model = model;
    }

    @Override
    public void attachView(GifListView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        if (mSubscriptions.hasSubscriptions() && !mSubscriptions.isUnsubscribed()) {
            mMvpView.stopUpdate();
        }
        super.detachView();
    }

    public void showTrendGifList(){
        checkViewAttached();
        lastRequest = null;
        Observable<List<String>> listObservable =  model
                .requestTrendGifList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Subscription subscription = listObservable.subscribe(getViewListSubscriber());
        mSubscriptions.add(subscription);
    }

    public void showRequestGifList(String request){
        checkViewAttached();
        lastRequest = request;
        Observable<List<String>> listObservable =  model
                .requestGifList(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Subscription subscription = listObservable.subscribe(getViewListSubscriber());
        mSubscriptions.add(subscription);
    }

    public void refresh(){
        if (lastRequest == null){
            showTrendGifList();
        }
        else {
            showRequestGifList(lastRequest);
        }
    }

    private Subscriber<List<String>> getViewListSubscriber(){

        Subscriber<List<String>> subscriber = new Subscriber<List<String>>() {

            @Override
            public void onStart() {
                Log.d(VIEW_LIST_SUBSCRIBER, "onStart" + (Looper.myLooper() == Looper.getMainLooper() ? " on UI" : " on OUT"));
                mMvpView.startUpdate();
            }

            @Override
            public void onCompleted() {
                Log.d(VIEW_LIST_SUBSCRIBER, "onCompleted" + (Looper.myLooper() == Looper.getMainLooper() ? " on UI" : " on OUT"));
                mMvpView.stopUpdate();
            }

            @Override
            public void onError(Throwable throwable) {
                mMvpView.stopUpdate();
                if (throwable!=null) {
                    Log.d(VIEW_LIST_SUBSCRIBER, "onError " + throwable.toString() + (Looper.myLooper() == Looper.getMainLooper() ? " on UI" : " on OUT"));
                    mMvpView.showMessage(throwable.getClass().getName() ,throwable.getMessage());
                }
            }

            @Override
            public void onNext(List<String> strings) {
                Log.d(VIEW_LIST_SUBSCRIBER, "onNext list size " + strings.size() + (Looper.myLooper() == Looper.getMainLooper() ? " on UI" : " on OUT"));
                mMvpView.setGifItemList((ArrayList<String>) strings);
            }
        };

        return subscriber;
    }
}
