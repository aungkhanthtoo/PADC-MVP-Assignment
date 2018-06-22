package com.padcmyanmar.sfc.mvp.presenters;

import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.mvp.views.NewsDetailView;

public class NewsDetailPresenter extends BasePresenter<NewsDetailView> {

    private static final String TAG = "NewsDetailPresenter";

    public NewsDetailPresenter(NewsDetailView mView) {
        super(mView);
    }

    public void onLoadNewsDetails(String newsId) {
        final NewsVO currentNew = NewsModel.getInstance().getNewsById(newsId);
        if (currentNew == null) {
            mView.showErrorMsg(" Error, Such NewsId is not found.");
        }else{
            mView.showNewsDetail(currentNew);
        }
    }

}
