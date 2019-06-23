package com.whmnrc.cdy.ui;

import android.content.Context;
import android.content.Intent;

import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.BaseActivity;
import com.whmnrc.cdy.gpio.MeasureType;

public class MeasureActivity extends BaseActivity {

    private MeasureType mMeasureType;

    public static void start(Context context, MeasureType measureType) {
        Intent starter = new Intent(context, MeasureActivity.class);
        starter.putExtra("measureType",measureType);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_measure;
    }

    @Override
    protected void initViewData() {
        mMeasureType = (MeasureType) getIntent().getSerializableExtra("measureType");

        initUi();
    }

    private void initUi() {


    }
}
