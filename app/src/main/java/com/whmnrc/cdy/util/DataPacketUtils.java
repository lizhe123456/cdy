package com.whmnrc.cdy.util;

/**
 * 凭借包
 */
public class DataPacketUtils {

    private static byte[] packetHandle(byte[] data){


        return data;
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
