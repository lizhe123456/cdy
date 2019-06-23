package com.whmnrc.cdy.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.blankj.utilcode.util.TimeUtils;
import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class SystemActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_time)
    TextView tvTime;

    public static void start(Context context) {
        Intent starter = new Intent(context, SystemActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_system;
    }

    @Override
    protected void initViewData() {
        tvTitle.setText("关于我们");


        loadDate();

    }

    private void loadDate() {

    }


    @OnClick({R.id.iv_back, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.tv_confirm:
                //设置时间
                showSelectDateDialog();
                break;
        }
    }

    private void showSelectDateDialog() {
        new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Intent intent=new Intent();
                intent.setAction("ACTION_UPDATE_TIME");
                intent.putExtra("cmd", TimeUtils.date2String(date,new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")));
                sendBroadcast(intent,null);
            }
        }).build().show();
    }
}
