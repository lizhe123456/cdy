package com.whmnrc.cdy.gpio;

/**
 * Created by lizhe on 2019/5/29.
 */
public class GPIOControl {

    static {
        System.loadLibrary("GPIOControl");
    }

    public final static native int exportGpio(int gpio);
    public final static native int setGpioDirection(int gpio, int direction);
    public final static native int readGpioStatus(int gpio);
    public final static native int writeGpioStatus(int gpio, int value);
    public final static native int unexportGpio(int gpio);

}
