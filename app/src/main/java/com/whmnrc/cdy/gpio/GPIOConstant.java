package com.whmnrc.cdy.gpio;

import java.text.SimpleDateFormat;

public class GPIOConstant {

    public final static SimpleDateFormat sDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //输入输出
    public final static int GPIO_DIRECTION_IN = 0;
    public final static int GPIO_DIRECTION_OUT = 1;
    //高低电频
    public final static int GPIO_VALUE_LOW = 0;
    public final static int GPIO_VALUE_HIGH = 1;


    //1 输入，窗口比较器上输出，高电平有效
    public final static int PH8 = 232;
    //2 输入，窗口比较器下输出，高电平有效
    public final static int PH9  = 233;
    //3  输出，清除，上边沿触发，清除后CNVSTH，CNVSTL变成0
    public final static int PG8 = 200;
    //6  抽气泵电源开关
    public final static int PG0 = 192;
    //5 高压发生有信号处理电源开关
    public final static int PG1 = 193;


    public final static int PG2 = 194;
    public final static int PG3 = 195;
    public final static int PG4 = 196;
    public final static int PG5 = 197;
    public final static int PG12 = 204;
    public final static int PG13 = 205;







}
