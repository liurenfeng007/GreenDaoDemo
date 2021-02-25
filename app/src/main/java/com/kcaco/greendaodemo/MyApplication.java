package com.kcaco.greendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.kcaco.greendaodemo.gen.DaoMaster;
import com.kcaco.greendaodemo.gen.DaoSession;

public class MyApplication extends Application {
    private static DaoSession mDaoSession;
    public static MyApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        initGreenDao();
    }

    public static MyApplication getInstances() {
        return instances;
    }

    private void initGreenDao() {
        // 创建数据库cool_weather
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "cool_weather");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}
