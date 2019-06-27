package com.whmnrc.cdy.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.DisplayMetrics;
import android.util.Log;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.blankj.utilcode.util.Utils;
import com.squareup.leakcanary.LeakCanary;
import com.whmnrc.cdy.db.DaoMaster;
import com.whmnrc.cdy.db.DaoSession;

/**
 * Created by lizhe on 2019/5/30.
 */
public class App extends Application {

    private static App instance;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private JobManager jobManager;

    public static int width;
    public static int height;
    public static float density;
    public static int densityDpi;


    public synchronized static App getInstance() {
        if (instance == null) {
            return new App();
        }
        return instance;
    }

    public JobManager getJobManager() {
        return jobManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        instance = this;
        Utils.init(this);
        setDatabase();
        configureJobManager();
        initDisplay(this);
    }

    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "radon_db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static void initDisplay(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        density = metrics.density;
        densityDpi = metrics.densityDpi;
    }

    /**
     * 配置JobMananger
     */
    private void configureJobManager() {
        Configuration configuration = new Configuration.Builder(this)
                //日志设置，便于用户查看任务队列的工作信息
                .customLogger(new CustomLogger() {
                    @Override
                    public boolean isDebugEnabled() {
                        return true;
                    }

                    @Override
                    public void d(String text, Object... args) {
                        Log.d("cdy", String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e("cdy", String.format(text, args));
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e("cdy", String.format(text, args));
                    }

                    @Override
                    public void v(String text, Object... args) {
                        Log.v("cdy", String.format(text, args));
                    }
                })
                //最少开启的线程
                .minConsumerCount(3)
                //最多开启的线程
                .maxConsumerCount(3)
                //一个Thread设置多3个任务
                .loadFactor(3)
                //设置线程在没有任务的情况下保持存活的时长，以秒为单位
                .consumerKeepAlive(120)
                .build();

        jobManager = new JobManager(configuration);
    }


    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}
