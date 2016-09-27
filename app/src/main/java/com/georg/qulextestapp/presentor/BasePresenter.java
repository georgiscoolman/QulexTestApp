package com.georg.qulextestapp.presentor;

import com.georg.qulextestapp.view.MVPView;

import rx.subscriptions.CompositeSubscription;

public class BasePresenter<T extends MVPView> implements Presenter<T> {

    protected final CompositeSubscription mSubscriptions = new CompositeSubscription();
    protected T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
        mSubscriptions.clear();
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

}
