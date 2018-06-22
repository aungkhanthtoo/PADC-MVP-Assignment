package com.padcmyanmar.sfc.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.viewholders.TopNewsViewHolder;

public class TopNewsAdapter extends BaseRecyclerAdapter<TopNewsViewHolder, NewsVO> {

    private NewsItemDelegate itemDelegate;

    public TopNewsAdapter(Context context, NewsItemDelegate delegate) {
        super(context);
        itemDelegate = delegate;
    }

    @NonNull
    @Override
    public TopNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopNewsViewHolder(
                mLayoutInflator.inflate(R.layout.view_item_top_new, parent, false),
                itemDelegate);
    }
}
