package com.whmnrc.cdy.util;

import com.whmnrc.cdy.base.App;
import com.whmnrc.cdy.bean.RadonBean;
import com.whmnrc.cdy.db.RadonBeanDao;

import java.util.List;

public class RadonPageUtils {

    public static List<RadonBean> getTwentyRec(int offset){
        RadonBeanDao dao = App.getInstance().getDaoSession().getRadonBeanDao();
        List<RadonBean> listMsg = dao.queryBuilder()
                .offset(offset * 20).limit(20).list();
        return listMsg;
    }

}
