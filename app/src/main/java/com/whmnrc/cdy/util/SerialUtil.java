package com.whmnrc.cdy.util;

import android.util.Log;
import com.kongqw.serialportlibrary.Device;
import com.kongqw.serialportlibrary.SerialPortFinder;
import com.kongqw.serialportlibrary.SerialPortManager;
import com.kongqw.serialportlibrary.listener.OnOpenSerialPortListener;
import com.kongqw.serialportlibrary.listener.OnSerialPortDataListener;
import java.io.File;
import java.util.ArrayList;

public class SerialUtil {

    private SerialPortManager mSerialPortManager = new SerialPortManager();
    private static SerialUtil instance = null;

    // 此处使用单例模式
    public static SerialUtil getInstance() {
        if (instance == null) {
            synchronized (SerialUtil.class) {
                if (instance == null) {
                    instance = new SerialUtil();
//                    instance.mSerialPortManager.setOnSerialPortDataListener(instance.onSerialPortDataListener);
                    instance.mSerialPortManager.setOnOpenSerialPortListener(instance.onOpenSerialPortListener);
                }
            }
        }
        return instance;
    }

    public void setListener(OnSerialPortDataListener onSerialPortDataListener) {
        instance.mSerialPortManager.setOnSerialPortDataListener(onSerialPortDataListener);
    }


    private OnSerialPortDataListener onSerialPortDataListener = new OnSerialPortDataListener() {
        @Override
        public void onDataReceived(byte[] bytes) {
            Log.d("sop","收到了数据。");
        }

        @Override
        public void onDataSent(byte[] bytes) {
            Log.d("sop","发送了数据。");
        }
    };

    private OnOpenSerialPortListener onOpenSerialPortListener = new OnOpenSerialPortListener() {
        @Override
        public void onSuccess(File device) {
            Log.d("sop", "链接" + device.getName() + "成功");
        }

        @Override
        public void onFail(File device, Status status) {
            Log.d("sop", "链接" + device.getName() + "失败");
        }
    };


    /**
     * 链接串口
     *
     * @return
     */
    public boolean connect() {
        return connect("ttysWK1");
    }

    /**
     * 链接串口
     *
     * @param PortName 设备节点名称
     * @return
     */
    public boolean connect(String PortName) {
        return mSerialPortManager.openSerialPort(new File("dev/" + PortName), 9600);
    }

    /**
     * 写入数据
     *
     * @param val
     */
    public void write(String val) {
        mSerialPortManager.sendBytes(val.getBytes());
    }

    public void write(byte[] val) {
        mSerialPortManager.sendBytes(val);
    }

    public void close() {
        mSerialPortManager.closeSerialPort();
    }

    public void trySerialTest() {
        SerialPortFinder serialPortFinder = new SerialPortFinder();
        SerialPortManager mSerialPortManager;
        ArrayList<Device> devices = serialPortFinder.getDevices();
        Log.d("sop", "获取到节点数量为：" + devices.size());
        Device device = null;
        mSerialPortManager = new SerialPortManager();

        mSerialPortManager.setOnSerialPortDataListener(new OnSerialPortDataListener() {
            @Override
            public void onDataReceived(byte[] bytes) {
                Log.d("sop", "收到数据");
            }

            @Override
            public void onDataSent(byte[] bytes) {
            }
        });

        mSerialPortManager.setOnOpenSerialPortListener(new OnOpenSerialPortListener() {
            @Override
            public void onSuccess(File device) {
                Log.d("sop", "串口链接成功，节点为：");
                Log.d("name  ", device.getName());//打印节点ID
            }

            @Override
            public void onFail(File device, Status status) {
                Log.d("sop", "串口链接失败，节点为：");
                Log.d("sop", device.getName() + status);
            }
        });

        for (Device dev : devices
        ) {
            boolean openSerialPort = mSerialPortManager.openSerialPort(dev.getFile(), 9600);
            if (openSerialPort) {
                mSerialPortManager.sendBytes("send something".getBytes());

                try {
                    Thread.sleep(3000);//给点时间用来接收
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d("stop", "结束测试");
        mSerialPortManager.closeSerialPort();
    }

    public interface OnSerialListener{

        void onS();

    }


}
