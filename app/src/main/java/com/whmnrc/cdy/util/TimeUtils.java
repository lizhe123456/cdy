package com.whmnrc.cdy.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimeUtils {

    public static String sfmSet(long date){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String hms = formatter.format(date);
        return hms;
    }

    private static String getString(long t){
        String m="";
        if(t>0){
            if(t<10){
                m="0"+t;
            }else{
                m=t+"";
            }
        }else{
            m="00";
        }
        return m;
    }

    /**
     *
     * @param t 毫秒
     * @return
     * @author Peter（张春玲）
     */
    public static String format(long t){
        if(t<60000){
            return (t % 60000 )/1000+"秒";
        }else if((t>=60000)&&(t<3600000)){
            return getString((t % 3600000)/60000)+":"+getString((t % 60000 )/1000);
        }else {
            return getString(t / 3600000)+":"+getString((t % 3600000)/60000)+":"+getString((t % 60000 )/1000);
        }
    }

}
