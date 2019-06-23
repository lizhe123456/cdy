package com.whmnrc.cdy.ui.adapter;

import android.content.Context;

import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.adapter.BaseAdapter;
import com.whmnrc.cdy.base.adapter.BaseViewHolder;
import com.whmnrc.cdy.bean.MenuBean;

public class MenuAdapter extends BaseAdapter<MenuBean> {

    public MenuAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindDataToItemView(BaseViewHolder holder, MenuBean item, int position) {
        holder.setImageResource(R.id.iv_img,item.getImg()).setText(R.id.tv_title,item.getTitle());
    }

    @Override
    protected int getItemViewLayoutId(int position, MenuBean item) {
        return R.layout.item_menu;
    }
}
