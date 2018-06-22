package com.padcmyanmar.sfc.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.events.RestApiEvents;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by aung on 11/4/17.
 */

public class NewsViewHolder extends BaseViewHolder<NewsVO> {

    @BindView(R.id.tv_brief_news)
    TextView tvBriefNews;

    @BindView(R.id.iv_news_hero_image)
    ImageView ivBriefNews;

    @BindView(R.id.tv_publication_name)
    TextView tvPublicationName;

    @BindView(R.id.iv_publication_logo)
    ImageView ivPubLogo;

    @BindView(R.id.tv_published_date)
    TextView tvPublishedDate;

    private NewsItemDelegate mDelegate;

    private NewsVO mCurrent;

    public NewsViewHolder(View itemView, NewsItemDelegate newsItemDelegate) {
        super(itemView);
        mDelegate = newsItemDelegate;
    }

    @Override
    public void setData(NewsVO data) {
        tvBriefNews.setText(data.getDetails());
        tvPublishedDate.setText(data.getPostedDate());
        tvPublicationName.setText(data.getPublication().getTitle());
        
        if (!data.getImages().isEmpty()) {
            Glide.with(ivBriefNews.getContext())
                    .load(data.getImages().get(0))
                    .into(ivBriefNews);
        }
//        }else{
//            ivNews.setVisibility(View.GONE);
//        }

        Glide.with(ivPubLogo)
                .load(data.getPublication().getLogo())
                .into(ivPubLogo);
        mCurrent = data;
    }

    @Override
    public void onClick(View v) {
        mDelegate.onTapNews(mCurrent.getNewsId());

        //EventBus.getDefault().post(new TapNewsEvent("news-id"));
        //EventBus.getDefault().post(new RestApiEvents.EmptyResponseEvent());
    }

    @OnClick(R.id.btn_comment_news)
    public void onTapComments(View view) {
        mDelegate.onTapComment();
    }
}
