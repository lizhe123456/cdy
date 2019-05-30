package com.whmnrc.cdy.bluetooth;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;

/**
 * Created by lizhe on 2019/5/30.
 * 蓝牙连接,蓝牙操作继承这个类
 */
public class BluetoothConnect implements Connect{

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothManager mBluetoothManager;
    private Context context;

    @Override
    public void searchDevices() {
        if (BluetoothUtil.isOpen(mBluetoothAdapter)){
            BluetoothUtil.searchDevices(mBluetoothAdapter);
        }
    }

    @Override
    public void connect() {

    }

    @Override
    public void close() {
        BluetoothUtil.cancelDiscovery(mBluetoothAdapter);
    }

    @Override
    public void pair() {

    }

    @Override
    public void connectState() {

    }

    @Override
    public void bind() {

    }

    @Override
    public void init(Context context) {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

}
