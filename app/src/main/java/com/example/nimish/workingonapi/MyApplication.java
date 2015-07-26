package com.example.nimish.workingonapi;

import android.app.Application;
import android.content.Context;

/**
 * Created by Nimish on 7/25/2015.
 */
public class MyApplication extends Application{
    public static MyApplication sInstance;


    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getInstance(){
        return sInstance;
    }


    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

}
