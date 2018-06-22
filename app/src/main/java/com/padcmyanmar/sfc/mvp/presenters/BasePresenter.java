package com.padcmyanmar.sfc.mvp.presenters;

import com.padcmyanmar.sfc.mvp.views.BaseView;

public abstract class BasePresenter<V extends BaseView> {

    V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
    }

    public void onCreate() {
    }

    public void onStart() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public void onDestroy() {
    }
}
