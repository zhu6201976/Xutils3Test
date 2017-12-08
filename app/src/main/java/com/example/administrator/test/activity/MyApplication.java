package com.example.administrator.test.activity;

import android.app.Application;

import org.xutils.x;

/**
 * Created by My on 2017/12/8.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
