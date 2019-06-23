package com.whmnrc.cdy.util;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * @author yjyvi
 * @data 2018/5/10.
 */

public class CodeTimeUtils {

    private static CountDownTimer mCountDownTimer;
    /**
     * 订单支付剩余时间
     */
    public static long ORDER_PAY_TIME = 1800_000L;
    public static long resultTime;

    /**
     * 验证码倒计时
     */
    public static void countDown(final TextView textView) {
        mCountDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setEnabled(false);
                textView.setText(String.format("重新发送%ss", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                textView.setEnabled(true);
                mCountDownTimer.cancel();
            }
        };
        mCountDownTimer.start();
    }

    /**
     * 验证码倒计时
     */
    public static void countDown(final TextView textView, final String text) {
        mCountDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setEnabled(false);
                textView.setText(String.format(text+"(%ss"+")", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                textView.setEnabled(true);
                textView.setText("重新发送");
                mCountDownTimer.cancel();
            }
        };
        mCountDownTimer.start();
    }

    public static void startTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.start();
        }
    }

    public static void cancelTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    public interface PayOrderTimeListener {
        void payField();
    }


}
