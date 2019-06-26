package com.whmnrc.cdy.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.whmnrc.cdy.bean.MeasureConfig;
import com.whmnrc.cdy.queue.Doable;
import com.whmnrc.cdy.queue.TQueue;
import com.whmnrc.cdy.util.CodeTimeUtils;
import com.whmnrc.cdy.util.DataPacketUtils;
import com.whmnrc.cdy.util.SerialUtil;

public class MeasurePlug {

    private TextView mTvMeasureTime;
    private boolean isContinuity = true;

    public MeasurePlug(TextView mTvMeasureTime) {
        this.mTvMeasureTime = mTvMeasureTime;
    }

    //发送数据请求后，长时间无返回处理
    public void overtTimeHandle(){

    }

    //执行密封任务
    public void implementM1(long time,final CodeTimeUtils.Listener listener) {
        CodeTimeUtils.countDown(mTvMeasureTime, "抽气中..", time, new CodeTimeUtils.Listener() {
            @Override
            public void complete() {
                if (listener != null){
                    listener.complete();
                }
            }
        });
    }

    //执行抽气任务
    public void implementC2(long time, final CodeTimeUtils.Listener listener) {
//        GPIOSender.write(GPIOConstant.PG0, GPIOConstant.GPIO_VALUE_HIGH);
        SerialUtil.getInstance().write(DataPacketUtils.getPUMP(true));
        CodeTimeUtils.countDown(mTvMeasureTime, "抽气中..", time, new CodeTimeUtils.Listener() {
            @Override
            public void complete() {
//                GPIOSender.write(GPIOConstant.PG0, GPIOConstant.GPIO_VALUE_LOW);
                if (listener != null){
                    SerialUtil.getInstance().write(DataPacketUtils.getPUMP(false));
                    listener.complete();
                }
            }
        });
    }

    //执行测量任务
    public void implementC3(long time, final CodeTimeUtils.Listener listener) {
        SerialUtil.getInstance().write(DataPacketUtils.getANALOGSW(true));
        CodeTimeUtils.countDown(mTvMeasureTime, "测量中..", time, new CodeTimeUtils.Listener() {
            @Override
            public void complete() {
                SerialUtil.getInstance().write(DataPacketUtils.getANALOGSW(false));
                if (listener != null){
                    listener.complete();
                }
            }
        });
    }

    //执行排气任务
    public void implementP4(long time,final CodeTimeUtils.Listener listener) {
        SerialUtil.getInstance().write(DataPacketUtils.getPUMP(true));
        CodeTimeUtils.countDown(mTvMeasureTime, "排气中..", time, new CodeTimeUtils.Listener() {
            @Override
            public void complete() {
                SerialUtil.getInstance().write(DataPacketUtils.getPUMP(false));
                if (listener != null){
                    listener.complete();
                }
            }
        });
    }

    public void ordinaryMeasure(final MeasureConfig measureConfig, final OnMeasureListener onMeasureListener){
        implementC2(measureConfig.getC2(), new CodeTimeUtils.Listener() {
            @Override
            public void complete() {
                implementC3(measureConfig.getC3(), new CodeTimeUtils.Listener() {
                    @Override
                    public void complete() {
                        implementP4(measureConfig.getP4(), new CodeTimeUtils.Listener() {
                            @Override
                            public void complete() {
                                if (onMeasureListener != null){
                                    onMeasureListener.complete();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    public void miOrdinaryMeasure(final MeasureConfig measureConfig, final OnMeasureListener onMeasureListener){

        implementM1(measureConfig.getM1(), new CodeTimeUtils.Listener() {
            @Override
            public void complete() {
                implementC2(measureConfig.getC2(), new CodeTimeUtils.Listener() {
                    @Override
                    public void complete() {
                        implementC3(measureConfig.getC3(), new CodeTimeUtils.Listener() {
                            @Override
                            public void complete() {
                                implementP4(measureConfig.getP4(), new CodeTimeUtils.Listener() {
                                    @Override
                                    public void complete() {
                                        if (onMeasureListener != null){
                                            onMeasureListener.complete();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });


    }

    //执行连续任务
    public void implementContinuity(final long time, final MeasureConfig measureConfig) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (isContinuity){
                    TQueue.queue().onUI(new Doable() {
                        @Override
                        public void doing(TQueue queue, Bundle args) {
                            ordinaryMeasure(measureConfig, new OnMeasureListener() {
                                @Override
                                public void complete() {
                                    try {
                                        Thread.sleep(time);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void error() {
                                    isContinuity = false;
                                }
                            });
                        }
                    });

                }
            }
        }.start();
    }

    public void stopContinuity(){
        isContinuity = false;
    }

    //执行本底任务
    public void implementBackground(long time, final CodeTimeUtils.Listener listener) {
        CodeTimeUtils.countDown(mTvMeasureTime, "测量中..", time, new CodeTimeUtils.Listener() {
            @Override
            public void complete() {
                if (listener != null){
                    listener.complete();
                }
            }
        });
    }

    public void stopMeasure() {
        CodeTimeUtils.cancelTimer();
        //关闭所有仪器
        SerialUtil.getInstance().write(DataPacketUtils.getPUMP(false));

        SerialUtil.getInstance().write(DataPacketUtils.getANALOGSW(false));
    }

    public interface OnMeasureListener{
        void complete();

        void error();
    }


}
