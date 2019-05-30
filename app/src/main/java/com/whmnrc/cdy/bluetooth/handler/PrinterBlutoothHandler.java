package com.whmnrc.cdy.bluetooth.handler;

import com.whmnrc.cdy.bean.PrintData;
import com.whmnrc.cdy.bluetooth.BluetoothConnect;

/**
 * Created by lizhe on 2019/5/30.
 * 蓝牙打印机功能
 */
public class PrinterBlutoothHandler extends BluetoothConnect {


    /**
     * 打印数据
     * @param printData
     */
    public void print(PrintData printData){
        printData.print();
    }



}
