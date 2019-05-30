package com.whmnrc.cdy.bluetooth;

import android.content.Context;

/**
 * Created by lizhe on 2019/5/30.
 */
public interface Connect {

    //搜索附近设备
    void searchDevices();

    //连接
    void connect();

    //关闭
    void close();

    //配对
    void pair();

    //连接状态
    void connectState();

    //绑定
    void bind();

    void init(Context context);

}
