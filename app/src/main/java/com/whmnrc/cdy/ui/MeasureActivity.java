package com.whmnrc.cdy.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.BaseActivity;
import com.whmnrc.cdy.gpio.MeasureType;
import butterknife.BindView;
import butterknife.OnClick;

public class MeasureActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_mf_time)
    EditText tvMfTime;
    @BindView(R.id.tv_cq_time)
    EditText tvCqTime;
    @BindView(R.id.ll_la2)
    LinearLayout llLa2;
    @BindView(R.id.v_d_2)
    View vD2;
    @BindView(R.id.tv_cl_time)
    EditText tvClTime;
    @BindView(R.id.ll_la3)
    LinearLayout llLa3;
    @BindView(R.id.v_d_3)
    View vD3;
    @BindView(R.id.tv_pq_time)
    EditText tvPqTime;
    @BindView(R.id.ll_la4)
    LinearLayout llLa4;
    @BindView(R.id.v_d_4)
    View vD4;
    @BindView(R.id.tv_jg_time)
    EditText tvJgTime;
    @BindView(R.id.ll_la5)
    LinearLayout llLa5;
    @BindView(R.id.v_d_5)
    View vD5;
    @BindView(R.id.tv_xishu)
    EditText tvXishu;
    @BindView(R.id.ll_la6)
    LinearLayout llLa6;
    @BindView(R.id.v_d_6)
    View vD6;
    @BindView(R.id.tv_bg)
    EditText tvBg;
    @BindView(R.id.ll_la7)
    LinearLayout llLa7;
    @BindView(R.id.ll_config)
    LinearLayout llConfig;
    @BindView(R.id.ll_measure)
    LinearLayout llMeasure;
    @BindView(R.id.ll_la1)
    LinearLayout llLa1;
    @BindView(R.id.v_d1)
    View vD1;

    private MeasureType mMeasureType;

    private boolean isMeasure;

    public static void start(Context context, MeasureType measureType) {
        Intent starter = new Intent(context, MeasureActivity.class);
        starter.putExtra("measureType", measureType);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_measure;
    }

    @Override
    protected void initViewData() {
        tvTitle.setText("测量参数设置");
        mMeasureType = (MeasureType) getIntent().getSerializableExtra("measureType");

        initUi();
    }

    private void initUi() {
        switch (mMeasureType) {
            case AIR:
                llLa1.setVisibility(View.GONE);
                llLa2.setVisibility(View.VISIBLE);
                llLa3.setVisibility(View.VISIBLE);
                llLa4.setVisibility(View.VISIBLE);
                llLa5.setVisibility(View.GONE);
                llLa6.setVisibility(View.VISIBLE);
                llLa7.setVisibility(View.VISIBLE);

                vD1.setVisibility(View.GONE);
                vD2.setVisibility(View.VISIBLE);
                vD3.setVisibility(View.VISIBLE);
                vD4.setVisibility(View.GONE);
                vD5.setVisibility(View.VISIBLE);
                vD6.setVisibility(View.VISIBLE);

                break;
            case SOIL:
                llLa1.setVisibility(View.GONE);
                llLa2.setVisibility(View.VISIBLE);
                llLa3.setVisibility(View.VISIBLE);
                llLa4.setVisibility(View.GONE);
                llLa5.setVisibility(View.VISIBLE);
                llLa6.setVisibility(View.VISIBLE);
                llLa7.setVisibility(View.VISIBLE);

                vD1.setVisibility(View.GONE);
                vD2.setVisibility(View.VISIBLE);
                vD3.setVisibility(View.VISIBLE);
                vD4.setVisibility(View.GONE);
                vD5.setVisibility(View.VISIBLE);
                vD6.setVisibility(View.VISIBLE);
                break;
            case WATER:
                llLa1.setVisibility(View.GONE);
                llLa2.setVisibility(View.VISIBLE);
                llLa3.setVisibility(View.VISIBLE);
                llLa4.setVisibility(View.VISIBLE);
                llLa5.setVisibility(View.GONE);
                llLa6.setVisibility(View.VISIBLE);
                llLa7.setVisibility(View.VISIBLE);

                vD1.setVisibility(View.GONE);
                vD2.setVisibility(View.VISIBLE);
                vD3.setVisibility(View.VISIBLE);
                vD4.setVisibility(View.GONE);
                vD5.setVisibility(View.VISIBLE);
                vD6.setVisibility(View.VISIBLE);
                break;
            case BACKGROUND:
                llLa1.setVisibility(View.GONE);
                llLa2.setVisibility(View.GONE);
                llLa3.setVisibility(View.GONE);
                llLa4.setVisibility(View.VISIBLE);
                llLa5.setVisibility(View.GONE);
                llLa6.setVisibility(View.GONE);
                llLa7.setVisibility(View.GONE);

                vD1.setVisibility(View.GONE);
                vD2.setVisibility(View.GONE);
                vD3.setVisibility(View.GONE);
                vD4.setVisibility(View.GONE);
                vD5.setVisibility(View.GONE);
                vD6.setVisibility(View.GONE);
                break;
            case RADON_EXHALATION_RATE:
                llLa1.setVisibility(View.VISIBLE);
                llLa2.setVisibility(View.VISIBLE);
                llLa3.setVisibility(View.VISIBLE);
                llLa4.setVisibility(View.VISIBLE);
                llLa5.setVisibility(View.GONE);
                llLa6.setVisibility(View.VISIBLE);
                llLa7.setVisibility(View.VISIBLE);

                vD1.setVisibility(View.VISIBLE);
                vD2.setVisibility(View.VISIBLE);
                vD3.setVisibility(View.VISIBLE);
                vD4.setVisibility(View.VISIBLE);
                vD5.setVisibility(View.GONE);
                vD6.setVisibility(View.VISIBLE);
                break;
            case CONTINUOUS_MEASUREMENT:
                llLa1.setVisibility(View.GONE);
                llLa2.setVisibility(View.VISIBLE);
                llLa3.setVisibility(View.VISIBLE);
                llLa4.setVisibility(View.GONE);
                llLa5.setVisibility(View.VISIBLE);
                llLa6.setVisibility(View.VISIBLE);
                llLa7.setVisibility(View.VISIBLE);

                vD1.setVisibility(View.GONE);
                vD2.setVisibility(View.VISIBLE);
                vD3.setVisibility(View.VISIBLE);
                vD4.setVisibility(View.GONE);
                vD5.setVisibility(View.VISIBLE);
                vD6.setVisibility(View.VISIBLE);
                break;
        }

    }


    @OnClick({R.id.iv_back, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.tv_confirm:
                inputVerification();
                break;
        }
    }

    private void inputVerification() {
        switch (mMeasureType) {
            case AIR:
                llLa1.setVisibility(View.GONE);
                llLa2.setVisibility(View.VISIBLE);
                llLa3.setVisibility(View.VISIBLE);
                llLa4.setVisibility(View.VISIBLE);
                llLa5.setVisibility(View.GONE);
                llLa6.setVisibility(View.VISIBLE);
                llLa7.setVisibility(View.VISIBLE);

                break;
            case SOIL:
                llLa1.setVisibility(View.GONE);
                llLa2.setVisibility(View.VISIBLE);
                llLa3.setVisibility(View.VISIBLE);
                llLa4.setVisibility(View.GONE);
                llLa5.setVisibility(View.VISIBLE);
                llLa6.setVisibility(View.VISIBLE);
                llLa7.setVisibility(View.VISIBLE);
                break;
            case WATER:
                llLa1.setVisibility(View.GONE);
                llLa2.setVisibility(View.VISIBLE);
                llLa3.setVisibility(View.VISIBLE);
                llLa4.setVisibility(View.VISIBLE);
                llLa5.setVisibility(View.GONE);
                llLa6.setVisibility(View.VISIBLE);
                llLa7.setVisibility(View.VISIBLE);
                break;
            case BACKGROUND:
                llLa1.setVisibility(View.GONE);
                llLa2.setVisibility(View.GONE);
                llLa3.setVisibility(View.GONE);
                llLa4.setVisibility(View.VISIBLE);
                llLa5.setVisibility(View.GONE);
                llLa6.setVisibility(View.GONE);
                llLa7.setVisibility(View.GONE);
                break;
            case RADON_EXHALATION_RATE:
                llLa1.setVisibility(View.VISIBLE);
                llLa2.setVisibility(View.VISIBLE);
                llLa3.setVisibility(View.VISIBLE);
                llLa4.setVisibility(View.VISIBLE);
                llLa5.setVisibility(View.GONE);
                llLa6.setVisibility(View.VISIBLE);
                llLa7.setVisibility(View.VISIBLE);
                break;
            case CONTINUOUS_MEASUREMENT:
                llLa1.setVisibility(View.GONE);
                llLa2.setVisibility(View.VISIBLE);
                llLa3.setVisibility(View.VISIBLE);
                llLa4.setVisibility(View.GONE);
                llLa5.setVisibility(View.VISIBLE);
                llLa6.setVisibility(View.VISIBLE);
                llLa7.setVisibility(View.VISIBLE);
                break;
        }

        isMeasure = true;
        llConfig.setVisibility(View.GONE);
        llMeasure.setVisibility(View.VISIBLE);


    }

}
