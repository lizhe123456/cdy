package com.whmnrc.cdy.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.adapter.BaseAdapter;
import com.whmnrc.cdy.base.adapter.BaseViewHolder;
import com.whmnrc.cdy.bean.RadonBean;
import com.whmnrc.cdy.ui.DataDetailsActivity;

public class RadonBeanAdapter extends BaseAdapter<RadonBean> {

    private boolean isSelect;

    public RadonBeanAdapter(Context context) {
        super(context);
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    protected void bindDataToItemView(BaseViewHolder holder, final RadonBean item, final int position) {
        holder.setText(R.id.tv_num, String.format("序号  %s", item.getId()))
                .setText(R.id.tv_time, String.format("采气时间    %s", item.getCreateTime()))
                .setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_value, String.format("%s  Bq/m", item.getRadonValue()));


        holder.setVisible(R.id.iv_select, isSelect);
        if (!isSelect){
            holder.setOnClickListener(R.id.content, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataDetailsActivity.start(getContext(),item);
                }
            });
        }
        ImageView imageView = holder.getView(R.id.iv_select);
        imageView.setSelected(item.isSelect());
        holder.setOnClickListener(R.id.iv_select, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setSelect(!item.isSelect());
                notifyItemChanged(position);
            }
        });

        holder.setOnClickListener(R.id.iv_delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataSource().remove(item);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    protected int getItemViewLayoutId(int position, RadonBean item) {
        return R.layout.item_radon_list;
    }
}
