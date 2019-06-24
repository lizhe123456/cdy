package com.whmnrc.cdy.gpio;

/**
 * 提供通用的 GPIO 口操作接口，参考开发示例，可以将指定的 GPIO 口配置为
 * 输入或者输出状态，设置输出电平的高低。
 * 在/sys/class/gpio 文件夹下，会为每个 GPIO 口生成一个文件夹，比如 PG0 对应的文件
 * 夹为 gpio192，PG1 的文件夹为 gpio193，其他 GPIO 口对应文件夹以此类推。在每个文件下有两个文件，向 direction 文件写入 in 或者 out 来配置输入还是输出，如果配置成输出，
 * 则通过向 value 文件写入 0 或者 1 来指定这个 GPIO 口输出电平的高低。
 *
 */
public class GPIOSender {

    public static void write(int gpio, int value){
        GPIOControl.exportGpio(gpio);
        GPIOControl.setGpioDirection(gpio,GPIOConstant.GPIO_DIRECTION_OUT);
        GPIOControl.writeGpioStatus(gpio,value);
        GPIOControl.unexportGpio(gpio);
    }

    public static int read(int gpio){
        GPIOControl.exportGpio(gpio);
        GPIOControl.setGpioDirection(gpio,GPIOConstant.GPIO_DIRECTION_IN);
        int statu = GPIOControl.readGpioStatus(gpio);
        GPIOControl.unexportGpio(gpio);
        return statu;
    }

}
