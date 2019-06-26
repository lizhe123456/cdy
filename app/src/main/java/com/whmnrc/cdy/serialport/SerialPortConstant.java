package com.whmnrc.cdy.serialport;

public class SerialPortConstant {

    public static final byte NAK = 0x15;
    public static final byte EOT = 0x20;
    public static final byte SOH = 0x01;
    public static final byte CAN = 0x18;

    //气泵开关
    public static final byte PUMP_OPEN = 0x21;
    public static final byte PUMP_CLOSE = 0x22;

    //高压发生有信号处理电源
    public static final byte ANALOGSW_OPEN = 0x23;
    public static final byte ANALOGSW_CLOSE = 0x24;

    //获取测量值
    public static final byte GET_MEASURE_VALUE = 0x25;


}
