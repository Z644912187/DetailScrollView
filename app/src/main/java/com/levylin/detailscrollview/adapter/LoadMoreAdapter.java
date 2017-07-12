package com.levylin.detailscrollview.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.levylin.detailscrollview.R;

import java.util.List;


/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class LoadMoreAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public LoadMoreAdapter(List<String> data) {
        super(R.layout.rc_item_simple, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String comment) {
        baseViewHolder.setText(R.id.tv, comment);
    }
}
