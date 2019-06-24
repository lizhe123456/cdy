package com.whmnrc.cdy.ui;

import com.blankj.utilcode.util.FragmentUtils;
import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.BaseActivity;


public class MainActivity extends BaseActivity {


    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewData() {
        FragmentUtils.add(getSupportFragmentManager(),HomeFragment.newInstance(),R.id.fl_content);
    }




}
