package com.whmnrc.cdy.bean;

import com.whmnrc.cdy.gpio.MeasureType;

/**
 * 测量配置
 */
public class MeasureConfig {

    //密封时间
    private long m1;

    //抽气时间
    private long c2;

    //测量时间
    private long c3;

    //排气时间
    private long p4;

    //间隔时间
    private long j5;

    //系数
    private double x6;

    //本底
    private double b7;

    private MeasureType measureType;

    public MeasureConfig(MeasureType measureType) {
        this.measureType = measureType;
    }

    public long getM1() {
        return m1;
    }

    public void setM1(long m1) {
        this.m1 = getSS(m1);
    }

    public long getC2() {
        return c2;
    }

    public void setC2(long c2) {
        this.c2 = getSS(c2);
    }

    public long getC3() {
        return c3;
    }

    public void setC3(long c3) {
        this.c3 = getSS(c3);
    }

    public long getP4() {
        return p4;
    }

    public void setP4(long p4) {
        this.p4 = getSS(p4);
    }

    public long getJ5() {
        return j5;
    }

    public void setJ5(long j5) {
        this.j5 = getSS(j5);
    }

    public double getX6() {
        return x6;
    }

    public void setX6(double x6) {
        this.x6 = x6;
    }

    public double getB7() {
        return b7;
    }

    public void setB7(double b7) {
        this.b7 = b7;
    }

    public MeasureType getMeasureType() {
        return measureType;
    }

    public void setMeasureType(MeasureType measureType) {
        this.measureType = measureType;
    }

    private long getSS(long mm) {
        return mm * 60 * 1000;
    }
}
