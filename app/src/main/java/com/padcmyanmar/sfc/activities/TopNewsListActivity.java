package com.padcmyanmar.sfc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.adapters.TopNewsAdapter;
import com.padcmyanmar.sfc.components.EmptyViewPod;
import com.padcmyanmar.sfc.components.SmartRecyclerView;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.mvp.presenters.NewsListPresenter;
import com.padcmyanmar.sfc.mvp.views.NewsListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopNewsListActivity extends AppCompatActivity
        implements NewsListView {

    @BindView(R.id.rvTopNews)
    SmartRecyclerView rvNews;

    @BindView(R.id.vp_empty_top_news)
    EmptyViewPod vpEmptyNews;

    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmer;

    private NewsListPresenter mPresenter;

    private TopNewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_news_list);
        ButterKnife.bind(this, this);

        mPresenter = new NewsListPresenter(this);
        mPresenter.onCreate();

        mAdapter = new TopNewsAdapter(this, mPresenter);

        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvNews.setAdapter(mAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public void displayNewsList(List<NewsVO> news) {
        rvNews.setEmptyView(vpEmptyNews);
        mAdapter.appendNewData(news);
    }

    @Override
    public void launchNewsDetailScreen(String newsId) {
        startActivity(NewsDetailsActivity.newIntent(getApplicationContext(), newsId));
    }

    @Override
    public void hideLoading() {
        mShimmer.stopShimmer();
        mShimmer.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        mShimmer.startShimmer();
    }

    @Override
    public void showErrorMsg(String msg) {
        Snackbar.make(findViewById(R.id.coordinator_layout), msg, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
        showLoading();
    }

    @Override
    protected void onPause() {
        hideLoading();
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

}
