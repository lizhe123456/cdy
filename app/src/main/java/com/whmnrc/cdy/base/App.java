package com.whmnrc.cdy.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by lizhe on 2019/5/30.
 */
public class App extends Application {

    private static App instance;

    public synchronized static App getInstance() {
        if (instance == null) {
            return new App();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(this);
    }
}
