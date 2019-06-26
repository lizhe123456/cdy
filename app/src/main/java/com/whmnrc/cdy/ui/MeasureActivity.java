package com.whmnrc.cdy.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.BaseActivity;
import com.whmnrc.cdy.bean.MeasureConfig;
import com.whmnrc.cdy.gpio.GPIOConstant;
import com.whmnrc.cdy.gpio.GPIOSender;
import com.whmnrc.cdy.gpio.MeasureType;
import com.whmnrc.cdy.util.AndroidBug5497Workaround;
import com.whmnrc.cdy.util.CodeTimeUtils;
import com.whmnrc.cdy.util.ToastUtil;
import com.whmnrc.cdy.widget.AlertUtils;

import butterknife.BindView;
import butterknife.OnClick;
import kr.pe.burt.android.lib.androidoperationqueue.AndroidOperation;
import kr.pe.burt.android.lib.androidoperationqueue.AndroidOperationQueue;
import kr.pe.burt.android.lib.androidoperationqueue.Operation;

public class MeasureActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_la2)
    LinearLayout llLa2;
    @BindView(R.id.v_d_2)
    View vD2;
    @BindView(R.id.ll_la3)
    LinearLayout llLa3;
    @BindView(R.id.v_d_3)
    View vD3;
    @BindView(R.id.ll_la4)
    LinearLayout llLa4;
    @BindView(R.id.v_d_4)
    View vD4;
    @BindView(R.id.ll_la5)
    LinearLayout llLa5;
    @BindView(R.id.v_d_5)
    View vD5;
    @BindView(R.id.ll_la6)
    LinearLayout llLa6;
    @BindView(R.id.v_d_6)
    View vD6;
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
    @BindView(R.id.et_m1)
    EditText etM1;
    @BindView(R.id.et_c2)
    EditText etC2;
    @BindView(R.id.et_c3)
    EditText etC3;
    @BindView(R.id.et_p4)
    EditText etP4;
    @BindView(R.id.et_j5)
    EditText etJ5;
    @BindView(R.id.et_x6)
    EditText etX6;
    @BindView(R.id.et_b7)
    EditText etB7;
    @BindView(R.id.tv_measure_time)
    TextView mTvMeasureTime;

    private MeasureType mMeasureType;

    private boolean isMeasure;

    private MeasureConfig mMeasureConfig;
    private AndroidOperationQueue mAndroidOperationQueue = new AndroidOperationQueue("Measure");

    public static void start(Context context, MeasureType measureType) {
        Intent starter = new Intent(context, MeasureActivity.class);
        starter.putExtra("measureType", measureType);
        ActivityUtils.startActivity(starter,0,0);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_measure;
    }

    @Override
    protected void initViewData() {
        AndroidBug5497Workaround.assistActivity(this);
        tvTitle.setText("测量参数设置");
        mMeasureType = (MeasureType) getIntent().getSerializableExtra("measureType");
        mMeasureConfig = new MeasureConfig(mMeasureType);
        initUi();
    }

    private void initUi() {
        switch (mMeasureType) {
            case AIR:
                tvName.setText("空气氡");
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
                vD4.setVisibility(View.VISIBLE);
                vD5.setVisibility(View.GONE);
                vD6.setVisibility(View.VISIBLE);

                break;
            case SOIL:
                tvName.setText("土壤氡");
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
                vD4.setVisibility(View.VISIBLE);
                vD5.setVisibility(View.GONE);
                vD6.setVisibility(View.VISIBLE);
                break;
            case WATER:
                tvName.setText("水中氡");
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
                vD4.setVisibility(View.VISIBLE);
                vD5.setVisibility(View.GONE);
                vD6.setVisibility(View.VISIBLE);
                break;
            case BACKGROUND:
                tvName.setText("本底测试");
                llLa1.setVisibility(View.GONE);
                llLa2.setVisibility(View.GONE);
                llLa3.setVisibility(View.VISIBLE);
                llLa4.setVisibility(View.GONE);
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
                tvName.setText("氡析出率");

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
                tvName.setText("连续测量");

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
                if (isMeasure) {
                    AlertUtils.showInterruptOperationDialog(this, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            interruptOperation();
                            MeasureActivity.this.finish();
                        }
                    });
                } else {
                    this.finish();
                }
                break;
            case R.id.tv_confirm:
                if (!isMeasure) {
                    inputVerification();
                }
                break;
        }
    }

    private void stopMeasure() {
        CodeTimeUtils.cancelTimer();
        mAndroidOperationQueue.stop();
    }

    private void startMeasure() {
        isMeasure = true;
        llConfig.setVisibility(View.GONE);
        llMeasure.setVisibility(View.VISIBLE);

        if (mMeasureConfig.getMeasureType() == MeasureType.AIR) {

            mAndroidOperationQueue.addOperation(new Operation() {
                @Override
                public void run(AndroidOperationQueue queue, Bundle bundle) {
                    AndroidOperation.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            implementC2(getSS(mMeasureConfig.getC2()), new CodeTimeUtils.Listener() {
                                @Override
                                public void complete() {
                                    mAndroidOperationQueue.addOperation(new Operation() {
                                        @Override
                                        public void run(AndroidOperationQueue queue, Bundle bundle) {
                                            AndroidOperation.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    implementC3(getSS(mMeasureConfig.getC3()), new CodeTimeUtils.Listener() {
                                                        @Override
                                                        public void complete() {
                                                            mAndroidOperationQueue.addOperation(new Operation() {
                                                                @Override
                                                                public void run(AndroidOperationQueue queue, Bundle bundle) {
                                                                    AndroidOperation.runOnUiThread(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            implementP4(getSS(mMeasureConfig.getP4()), new CodeTimeUtils.Listener() {
                                                                                @Override
                                                                                public void complete() {
                                                                                    mAndroidOperationQueue.addOperation(new Operation() {
                                                                                        @Override
                                                                                        public void run(AndroidOperationQueue queue, Bundle bundle) {
                                                                                            //测量完成，开始计算
                                                                                            AndroidOperation.runOnUiThread(new Runnable() {
                                                                                                @Override
                                                                                                public void run() {
                                                                                                    mTvMeasureTime.setText("测试完成");
                                                                                                }

                                                                                            });

                                                                                        }
                                                                                    });
                                                                                }
                                                                            });
                                                                        }

                                                                    });
                                                                }
                                                            });
                                                        }
                                                    });
                                                }

                                            });
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });


            mAndroidOperationQueue.start();

//            TQueue.queue("s")
//                    .onUI(new Doable() {
//                        @Override
//                        public void doing(TQueue queue, Bundle args) {
//
//                        }
//                    })
//                    .onUIDelayed(getSS(mMeasureConfig.getC2()), new Doable() {
//                        @Override
//                        public void doing(TQueue queue, Bundle args) {
//                            implementC3(getSS(mMeasureConfig.getC3()));
//                        }
//                    }).rest(getSS(mMeasureConfig.getC3()))
//                    .onUI(new Doable() {
//                        @Override
//                        public void doing(TQueue queue, Bundle args) {
//                            implementP4(getSS(mMeasureConfig.getP4()));
//                        }
//                    }).rest(getSS(mMeasureConfig.getP4()))
//                    .onUI(new Doable() {
//                        @Override
//                        public void doing(TQueue queue, Bundle args) {
//                            //测量完成，开始计算
//                            mTvMeasureTime.setText("测试完成");
//
//                        }
//                    })
//                    .go(new Ceased() {
//                        @Override
//                        public void doing(Throwable error, Bundle args) {
//                            //执行出错
//                            if (mTvMeasureTime != null) {
//                                mTvMeasureTime.setText("执行出错");
//                            }
//                        }
//                    });
        }


    }

    private long getSS(long mm) {
        return mm * 60 * 1000;
    }

    //执行密封任务
    private void implementM1(long time) {

    }

    //执行抽气任务
    private void implementC2(long time, final CodeTimeUtils.Listener listener) {
        GPIOSender.write(GPIOConstant.PG0, GPIOConstant.GPIO_VALUE_HIGH);
        CodeTimeUtils.countDown(mTvMeasureTime, "抽气中..", time, new CodeTimeUtils.Listener() {
            @Override
            public void complete() {
                GPIOSender.write(GPIOConstant.PG0, GPIOConstant.GPIO_VALUE_LOW);
                if (listener != null){
                    listener.complete();
                }
            }
        });
    }

    //执行测量任务
    private void implementC3(long time, final CodeTimeUtils.Listener listener) {
        GPIOSender.write(GPIOConstant.PG1, GPIOConstant.GPIO_VALUE_HIGH);
        CodeTimeUtils.countDown(mTvMeasureTime, "测量中..", time, new CodeTimeUtils.Listener() {
            @Override
            public void complete() {
                GPIOSender.write(GPIOConstant.PG1, GPIOConstant.GPIO_VALUE_LOW);
                if (listener != null){
                    listener.complete();
                }
            }
        });
    }

    //执行排气任务
    private void implementP4(long time,final CodeTimeUtils.Listener listener) {
        GPIOSender.write(GPIOConstant.PG0, GPIOConstant.GPIO_VALUE_HIGH);
        CodeTimeUtils.countDown(mTvMeasureTime, "排气中..", time, new CodeTimeUtils.Listener() {
            @Override
            public void complete() {
                GPIOSender.write(GPIOConstant.PG0, GPIOConstant.GPIO_VALUE_LOW);
                if (listener != null){
                    listener.complete();
                }
            }
        });
    }

    //执行连续任务
    private void implementContinuity() {

//        implementC2();
//        implementC3();
    }

    //执行本底任务
    private void implementBackground(long time) {

    }


    //中断测量
    private void interruptOperation() {
        stopMeasure();
    }

    private void inputVerification() {
        switch (mMeasureType) {
            case AIR:

                if (TextUtils.isEmpty(etC2.getText().toString())) {
                    ToastUtil.showToast(this, etC2.getHint().toString());
                    return;
                }
                mMeasureConfig.setC2(Long.parseLong(etC2.getText().toString()));

                if (TextUtils.isEmpty(etC3.getText().toString())) {
                    ToastUtil.showToast(this, etC3.getHint().toString());
                    return;
                }
                mMeasureConfig.setC3(Long.parseLong(etC3.getText().toString()));

                if (TextUtils.isEmpty(etP4.getText().toString())) {
                    ToastUtil.showToast(this, etP4.getHint().toString());
                    return;
                }
                mMeasureConfig.setP4(Long.parseLong(etP4.getText().toString()));

                if (TextUtils.isEmpty(etX6.getText().toString())) {
                    ToastUtil.showToast(this, etX6.getHint().toString());
                    return;
                }
                mMeasureConfig.setX6(Long.parseLong(etX6.getText().toString()));

                if (TextUtils.isEmpty(etB7.getText().toString())) {
                    ToastUtil.showToast(this, etB7.getHint().toString());
                    return;
                }
                mMeasureConfig.setB7(Long.parseLong(etB7.getText().toString()));

                break;
            case SOIL:

                if (TextUtils.isEmpty(etC2.getText().toString())) {
                    ToastUtil.showToast(this, etC2.getHint().toString());
                    return;
                }
                mMeasureConfig.setC2(Long.parseLong(etC2.getText().toString()));

                if (TextUtils.isEmpty(etC3.getText().toString())) {
                    ToastUtil.showToast(this, etC3.getHint().toString());
                    return;
                }
                mMeasureConfig.setC3(Long.parseLong(etC3.getText().toString()));

                if (TextUtils.isEmpty(etP4.getText().toString())) {
                    ToastUtil.showToast(this, etP4.getHint().toString());
                    return;
                }
                mMeasureConfig.setP4(Long.parseLong(etP4.getText().toString()));


                if (TextUtils.isEmpty(etX6.getText().toString())) {
                    ToastUtil.showToast(this, etX6.getHint().toString());
                    return;
                }
                mMeasureConfig.setX6(Long.parseLong(etX6.getText().toString()));

                if (TextUtils.isEmpty(etB7.getText().toString())) {
                    ToastUtil.showToast(this, etB7.getHint().toString());
                    return;
                }
                mMeasureConfig.setB7(Long.parseLong(etB7.getText().toString()));

                break;
            case WATER:
                if (TextUtils.isEmpty(etC2.getText().toString())) {
                    ToastUtil.showToast(this, etC2.getHint().toString());
                    return;
                }
                mMeasureConfig.setC2(Long.parseLong(etC2.getText().toString()));

                if (TextUtils.isEmpty(etC3.getText().toString())) {
                    ToastUtil.showToast(this, etC3.getHint().toString());
                    return;
                }
                mMeasureConfig.setC3(Long.parseLong(etC3.getText().toString()));

                if (TextUtils.isEmpty(etP4.getText().toString())) {
                    ToastUtil.showToast(this, etP4.getHint().toString());
                    return;
                }
                mMeasureConfig.setP4(Long.parseLong(etP4.getText().toString()));

                if (TextUtils.isEmpty(etX6.getText().toString())) {
                    ToastUtil.showToast(this, etX6.getHint().toString());
                    return;
                }
                mMeasureConfig.setX6(Long.parseLong(etX6.getText().toString()));

                if (TextUtils.isEmpty(etB7.getText().toString())) {
                    ToastUtil.showToast(this, etB7.getHint().toString());
                    return;
                }
                mMeasureConfig.setB7(Long.parseLong(etB7.getText().toString()));

                break;
            case BACKGROUND:
                if (TextUtils.isEmpty(etP4.getText().toString())) {
                    ToastUtil.showToast(this, etP4.getHint().toString());
                    return;
                }
                mMeasureConfig.setP4(Long.parseLong(etP4.getText().toString()));

                break;
            case RADON_EXHALATION_RATE:
                if (TextUtils.isEmpty(etM1.getText().toString())) {
                    ToastUtil.showToast(this, etM1.getHint().toString());
                    return;
                }
                mMeasureConfig.setM1(Long.parseLong(etM1.getText().toString()));

                if (TextUtils.isEmpty(etC2.getText().toString())) {
                    ToastUtil.showToast(this, etC2.getHint().toString());
                    return;
                }
                mMeasureConfig.setC2(Long.parseLong(etC2.getText().toString()));

                if (TextUtils.isEmpty(etC3.getText().toString())) {
                    ToastUtil.showToast(this, etC3.getHint().toString());
                    return;
                }
                mMeasureConfig.setC3(Long.parseLong(etC3.getText().toString()));

                if (TextUtils.isEmpty(etP4.getText().toString())) {
                    ToastUtil.showToast(this, etP4.getHint().toString());
                    return;
                }
                mMeasureConfig.setP4(Long.parseLong(etP4.getText().toString()));

                if (TextUtils.isEmpty(etX6.getText().toString())) {
                    ToastUtil.showToast(this, etX6.getHint().toString());
                    return;
                }
                mMeasureConfig.setX6(Long.parseLong(etX6.getText().toString()));

                if (TextUtils.isEmpty(etB7.getText().toString())) {
                    ToastUtil.showToast(this, etB7.getHint().toString());
                    return;
                }
                mMeasureConfig.setB7(Long.parseLong(etB7.getText().toString()));

                break;
            case CONTINUOUS_MEASUREMENT:
                if (TextUtils.isEmpty(etC2.getText().toString())) {
                    ToastUtil.showToast(this, etC2.getHint().toString());
                    return;
                }
                mMeasureConfig.setC2(Long.parseLong(etC2.getText().toString()));

                if (TextUtils.isEmpty(etC3.getText().toString())) {
                    ToastUtil.showToast(this, etC3.getHint().toString());
                    return;
                }
                mMeasureConfig.setC3(Long.parseLong(etC3.getText().toString()));

                if (TextUtils.isEmpty(etJ5.getText().toString())) {
                    ToastUtil.showToast(this, etJ5.getHint().toString());
                    return;
                }
                mMeasureConfig.setJ5(Long.parseLong(etJ5.getText().toString()));

                if (TextUtils.isEmpty(etX6.getText().toString())) {
                    ToastUtil.showToast(this, etX6.getHint().toString());
                    return;
                }
                mMeasureConfig.setX6(Long.parseLong(etX6.getText().toString()));

                if (TextUtils.isEmpty(etB7.getText().toString())) {
                    ToastUtil.showToast(this, etB7.getHint().toString());
                    return;
                }
                mMeasureConfig.setB7(Long.parseLong(etB7.getText().toString()));
                break;
        }


        startMeasure();

    }

}
