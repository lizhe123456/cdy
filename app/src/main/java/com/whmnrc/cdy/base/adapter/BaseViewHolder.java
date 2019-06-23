package com.whmnrc.cdy.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * author：lizhe
 * time： 2017-08-25
 *  https://github.com/yat3s
 */

public class BaseViewHolder extends RecyclerView.ViewHolder{

    private final SparseArray<View> mViews;

    private Context mContext;

    public BaseViewHolder(View itemView, Context context) {
        super(itemView);
        mViews = new SparseArray<>();
        mContext = context;
    }


    /**
     * 通过findViewById获取view
     * @param id
     * @param <V>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <V extends View> V getView(int id){
        View view = mViews.get(id);
        if (view == null){
            view = itemView.findViewById(id);
            mViews.put(id,view);
        }
        return (V) view;
    }



    public BaseViewHolder setText(int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    /**
     * 传入view可配合ButterKnife使用
     * @param view
     * @param value
     * @param <V>
     * @return
     */
    public <V extends TextView>BaseViewHolder setText(V view, CharSequence value){
        view.setText(value);
        return this;
    }

    public BaseViewHolder setImageURI(int viewId, Uri uri) {
        ImageView view = getView(viewId);
        view.setImageURI(uri);
        return this;
    }

    public <V extends ImageView>BaseViewHolder setImageURI(V view, Uri uri) {
        view.setImageURI(uri);
        return this;
    }

    public BaseViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColor));
        return this;
    }

    public <V extends TextView>BaseViewHolder setTextColor(V view, int textColor) {
        view.setTextColor(mContext.getResources().getColor(textColor));
        return this;
    }

    public BaseViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    public <V extends TextView>BaseViewHolder setTextColorRes(V view, int textColorRes) {
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    public BaseViewHolder setImageResource(int viewId, int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }
    public <V extends ImageView>BaseViewHolder setImageResource(V view, int imageResId) {
        view.setImageResource(imageResId);
        return this;
    }

    public BaseViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public <V extends View>BaseViewHolder setBackgroundColor(V view, int color) {
        view.setBackgroundColor(color);
        return this;
    }

    public BaseViewHolder setBackgroundResource(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public <V extends View>BaseViewHolder setBackgroundResource(V view, int backgroundRes) {
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public BaseViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public  <V extends View>BaseViewHolder setVisible(V view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public BaseViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public <V extends TextView>BaseViewHolder setTypeface(Typeface typeface, V... views) {
        for (V view : views) {
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public BaseViewHolder setLastItemXian(int resId,int position,int size){
        View view = getView(resId);
        if (view != null) {
            if (size - 1 == position) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
        return this;
    }

    public <V extends View>BaseViewHolder setOnClickListener(V view, View.OnClickListener listener) {
        view.setOnClickListener(listener);
        return this;
    }

    public BaseViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public <V extends View>BaseViewHolder setOnTouchListener(V view, View.OnTouchListener listener) {
        view.setOnTouchListener(listener);
        return this;
    }

    public BaseViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public <V extends View>BaseViewHolder setOnLongClickListener(V view, View.OnLongClickListener listener) {
        view.setOnLongClickListener(listener);
        return this;
    }

    public BaseViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public <V extends View>BaseViewHolder setTag(V view, Object tag) {
        view.setTag(tag);
        return this;
    }

    public BaseViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public BaseViewHolder startActivity(Class activity, Bundle bundle) {
        Intent intent = new Intent(mContext, activity);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
        return this;
    }

    public BaseViewHolder startActivity(Class activity) {
        startActivity(activity, null);
        return this;
    }


}
