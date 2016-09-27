package com.georg.qulextestapp.presentor;

import com.georg.qulextestapp.view.MVPView;

public interface Presenter<V extends MVPView> {

    void attachView(V view);

    void detachView();

}
