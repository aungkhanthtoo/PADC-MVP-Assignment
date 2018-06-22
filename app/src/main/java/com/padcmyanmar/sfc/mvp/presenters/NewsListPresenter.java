package com.padcmyanmar.sfc.mvp.presenters;

import android.util.Log;

import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.mvp.views.NewsListView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class NewsListPresenter extends BasePresenter<NewsListView>
        implements NewsItemDelegate {

    private CompositeDisposable compositeDisposable;

    private PublishSubject<List<NewsVO>> mSubject;

    public NewsListPresenter(NewsListView mView) {
        super(mView);
        mSubject = PublishSubject.create();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mView.showLoading();
        NewsModel.getInstance().startLoadingMMNews(mSubject);
        mSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public void onTapComment() {

    }

    @Override
    public void onTapSendTo() {

    }

    @Override
    public void onTapFavorite() {

    }

    @Override
    public void onTapStatistics() {

    }

    @Override
    public void onTapNews(String id) {
        mView.launchNewsDetailScreen(id);
    }

    private Observer<List<NewsVO>> getObserver(){
        return new Observer<List<NewsVO>>() {

            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<NewsVO> newsVOS) {
                mView.hideLoading();
                mView.displayNewsList(newsVOS);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("NewsListActivity", "onError: " + e.getMessage());
                mView.hideLoading();
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("NewsListActivity", "onComplete: ");
            }
        };
    }
}
