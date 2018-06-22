package com.padcmyanmar.sfc.mvp.views;

import com.padcmyanmar.sfc.data.vo.NewsVO;

public interface NewsDetailView extends BaseView {

    void showNewsDetail(NewsVO news);
}
