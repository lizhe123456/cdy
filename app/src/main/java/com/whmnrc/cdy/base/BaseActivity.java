package com.whmnrc.cdy.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.whmnrc.cdy.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lizhe on 2019/5/30.
 */
public abstract class BaseActivity extends FragmentActivity {

    protected Activity mActivity;
    protected View mView;
    private Unbinder mUnbinder;


    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mView = LayoutInflater.from(this).inflate(setLayoutId(), null);
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(mView);
        mUnbinder = ButterKnife.bind(mActivity);
        setSUp();
        hidpN();
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


    private void setSUp() {
        Intent intent = new Intent();
        intent.setAction("ACTION_STATUSBAR_DROPDOWN");
        intent.putExtra("cmd", "hide");
        sendBroadcast(intent, null);
    }

    private void hidpN() {
        Intent intent = new Intent();
        intent.setAction("ACTION_SHOW_NAVBAR");
        intent.putExtra("cmd", "hide");
        sendBroadcast(intent, null);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    protected void showProgress(String msg) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        mProgressDialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        mProgressDialog.setCanceledOnTouchOutside(true);// 设置在点击Dialog外是否取消Dialog进度条
        // 设置提示的title的图标，默认是没有的，如果没有设置title的话只设置Icon是不会显示图标的
//        mProgressDialog.setTitle("提示");
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    protected void hodeProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

}
