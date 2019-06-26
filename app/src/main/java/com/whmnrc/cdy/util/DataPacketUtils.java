package com.whmnrc.cdy.util;

import com.whmnrc.cdy.serialport.SerialPortConstant;

/**
 * 凭借包
 *
 */
public class DataPacketUtils {


    private static byte[] packetHandle(byte[] b,boolean isRW){

        byte[] head = new byte[2];
        byte[] tail;
        head[0] = SerialPortConstant.SOH;
        head[1] = isRW ? SerialPortConstant.CMD_R : SerialPortConstant.CMD_W;
        char check = crc16Calc(b,b.length);
        tail = charToByte(check);
        byte[] data = new byte[head.length + b.length];
        System.arraycopy(head, 0, data, 0, head.length);
        System.arraycopy(b, 0, data, head.length, b.length);
        byte[] sendData = new byte[data.length + tail.length];
        System.arraycopy(data, 0, sendData, 0, data.length);
        System.arraycopy(tail, 0, sendData, data.length, tail.length);
        return sendData;
    }


    private static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }


    public static byte[] getPUMP(boolean isOpen){
        byte[] bytes = new byte[1];
        if (isOpen) {
            bytes[0] = SerialPortConstant.PUMP_OPEN;
        }else {
            bytes[0] = SerialPortConstant.PUMP_CLOSE;
        }
        return packetHandle(bytes,false);
    }

    public static byte[] getANALOGSW(boolean isOpen){
        byte[] bytes = new byte[1];
        if (isOpen) {
            bytes[0] = SerialPortConstant.ANALOGSW_OPEN;
        }else {
            bytes[0] = SerialPortConstant.ANALOGSW_CLOSE;
        }
        return packetHandle(bytes,false);
    }


    //奇偶校验
    public static char crc16Calc(byte[] data_arr, int data_len) {
        char crc16 = 0;
        int i;
        for(i =0; i < (data_len); i++) {
            crc16 = (char)(( crc16 >> 8) | (crc16 << 8));
            crc16 ^= data_arr[i]& 0xFF;
            crc16 ^= (char)(( crc16 & 0xFF) >> 4);
            crc16 ^= (char)(( crc16 << 8) << 4);
            crc16 ^= (char)((( crc16 & 0xFF) << 4) << 1);
        }
        return crc16;
    }

}
