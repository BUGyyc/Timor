package com.linkerlab.housemanager;

import android.app.Application;

import com.linkerlab.housemanager.util.Log;

/**
 * Created by x-man on 16/7/30.
 * App启动Application
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Env.setEnv(Env.ENV_DEV);//发布版本后设置不打印日志
        if(!Env.isRelease()){
            Log.setLevel(Log.LEVEL_ALL);
        }
    }
}
