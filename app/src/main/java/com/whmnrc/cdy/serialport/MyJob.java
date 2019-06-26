package com.whmnrc.cdy.serialport;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

public class MyJob extends Job {

    private byte[] data;
    private String tag;

    public MyJob(long time, String tag, byte[] data) {
        super(new Params(1)
                        .addTags(tag)
                        .setDelayMs(time));
        this.tag = tag;
        this.data = data;
    }

    @Override
    public void onAdded() {
        //任务加入队列并被保存在硬盘上，定义此时要处理的逻辑；
    }

    @Override
    public void onRun() throws Throwable {
        //任务开始执执行，在此定义任务的主题逻辑，当执行完毕后，任务将被从任务队列中删除；
//        SerialUtil.getInstance().write(data);
        Log.e("MyJob.class", tag + "执行成功");
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        //任务取消的时候要执行的逻辑；
        Log.e("MyJob.class", tag + "执行失败");

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        /*
         * 当onRun()方法中抛出异常时，就会调用该函数，
         *该函数返回Job类在执行发生异常时的应对策略，
         *是重新执行还是取消，或者是一定时间之后再尝试。
         */

        //RetryConstraint的自带策略，立刻重新尝试执行策略，
        // 直到执行成功或者尝试次数达到最大（18次）；
        // return RetryConstraint.RETRY;

        //RetryConstraint的自带策略，取消当前任务的执行；
        return RetryConstraint.CANCEL;

        //定期延迟尝试执行任务，如果任务执行失败，
        // 下次执行的延迟时间会以指数形式增长,最大尝试次数为20次；
//        return null;
    }
}
