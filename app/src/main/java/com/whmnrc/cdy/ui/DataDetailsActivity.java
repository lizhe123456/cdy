package com.whmnrc.cdy.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.App;
import com.whmnrc.cdy.base.BaseActivity;
import com.whmnrc.cdy.bean.RadonBean;
import com.whmnrc.cdy.gpio.GPIOConstant;
import com.whmnrc.cdy.widget.AlertUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class DataDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_value)
    TextView tvValue;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_desc)
    TextView tvDesc;

    private RadonBean mRadonBean;


    public static void start(Context context, RadonBean radonBean) {
        Intent starter = new Intent(context, DataDetailsActivity.class);
        starter.putExtra("radonBean",radonBean);
        ActivityUtils.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_data_detail;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initViewData() {
        mRadonBean  = (RadonBean) getIntent().getSerializableExtra("radonBean");
        tvAddress.setText(mRadonBean.getAddress());
        tvDesc.setText(mRadonBean.getDesc());
        tvId.setText(mRadonBean.getId()+"");
        tvTime.setText(TimeUtils.date2String(mRadonBean.getCreateTime(), GPIOConstant.sDateString));
        tvName.setText(mRadonBean.getName());
        tvValue.setText(mRadonBean.getRadonValue()+"  Bq/m");
        tvTitle.setText("数据详情");

    }

    @OnClick({R.id.iv_back, R.id.tv_clean, R.id.tv_printing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.tv_clean:
                AlertUtils.showCleanDialog(this, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.getInstance().getDaoSession().getRadonBeanDao().delete(mRadonBean);
                    }
                });
                break;
            case R.id.tv_printing:
                //打印

                break;
        }
    }


}
