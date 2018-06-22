package com.padcmyanmar.sfc.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;

import butterknife.BindView;

public class TopNewsViewHolder extends BaseViewHolder<NewsVO> {

    @BindView(R.id.tv_top_news_brief_news)
    TextView tvBriefNews;

    @BindView(R.id.iv_top_news_item_image)
    ImageView ivNews;

    @BindView(R.id.tv_top_news_publication)
    TextView tvPublication;

    @BindView(R.id.tv_top_news_published_date)
    TextView tvPublishedDate;

    private NewsItemDelegate mDelegate;

    private NewsVO mCurrent;

    public TopNewsViewHolder(View itemView, NewsItemDelegate delegate) {
        super(itemView);
        mDelegate = delegate;
    }

    @Override
    public void setData(NewsVO data) {
        mCurrent = data;
        if (!data.getImages().isEmpty()) {
            Glide.with(ivNews.getContext())
                    .load(data.getImages().get(0))
                    .into(ivNews);
        }
        tvBriefNews.setText(data.getBrief());
        tvPublication.setText(data.getPublication().getTitle());
        tvPublishedDate.setText(data.getPostedDate());
    }

    @Override
    public void onClick(View v) {
        mDelegate.onTapNews(mCurrent.getNewsId());
    }

}
