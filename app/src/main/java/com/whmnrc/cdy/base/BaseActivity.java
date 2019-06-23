package com.whmnrc.cdy.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lizhe on 2019/5/30.
 */
public abstract class BaseActivity extends FragmentActivity {

    protected Activity mActivity;
    protected View mView;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mView = LayoutInflater.from(this).inflate(setLayoutId(), null);
        setContentView(mView);
        mUnbinder = ButterKnife.bind(mActivity);
        initViewData();
        ActivityUtils.getActivityList().add(mActivity);
    }

    @LayoutRes
    protected abstract int setLayoutId();

    protected abstract void initViewData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.getActivityList().remove(mActivity);
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



}
