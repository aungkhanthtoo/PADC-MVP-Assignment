package com.padcmyanmar.sfc;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.network.MMNewsAPI;

import java.util.concurrent.TimeUnit;

import io.reactivex.subjects.PublishSubject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aung on 11/4/17.
 */

public class SFCNewsApp extends Application {

    public static final String LOG_TAG = "SFCNewsApp";

    private static SFCNewsApp appContext;

    public static SFCNewsApp getAppContext() {
        return appContext;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        //NewsModel.getInstance().startLoadingMMNews();
    }

}
