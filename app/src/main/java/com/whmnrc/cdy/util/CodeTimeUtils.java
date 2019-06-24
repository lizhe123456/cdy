package com.whmnrc.cdy.util;

import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.TextView;


/**
 * @author yjyvi
 * @data 2018/5/10.
 */

public class CodeTimeUtils {

    private static CountDownTimer mCountDownTimer;

    /**
     * 时间倒计时
     */
    public static void countDown(final EditText textView, final long time, final Listener listener) {
        mCountDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setEnabled(false);
                textView.setFocusable(false);
                textView.setText(TimeUtils.format(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                textView.setEnabled(true);
                textView.setFocusable(true);
                mCountDownTimer.cancel();
                if (listener != null){
                    listener.complete();
                }
            }
        };
        mCountDownTimer.start();
    }

    //
    public static void countDown(final TextView textView, final String desc, final long time, final Listener listener) {
        mCountDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setEnabled(false);
                textView.setText(String.format(desc+"\n %s",TimeUtils.format(millisUntilFinished)));
            }

            @Override
            public void onFinish() {
                textView.setEnabled(true);
                mCountDownTimer.cancel();
                if (listener != null){
                    listener.complete();
                }
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

    public interface Listener {
        void complete();
    }


}
