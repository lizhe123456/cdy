package com.whmnrc.cdy.bluetooth;

/**
 * Created by lizhe on 2019/5/30.
 * 蓝牙信息实体
 */
public class BluetoothReadEvent {

    public int bytes;
    public byte[] buffer;
    public String message;

    public BluetoothReadEvent(int bytes, byte[] buffer) {
        this.buffer = buffer;
        this.bytes = bytes;
        this.message = new String(buffer, 0, bytes);
    }

}
