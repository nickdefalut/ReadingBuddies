package com.example.lxia.readingbuddies;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by v-lesh on 7/28/2015.
 */
public class MyApplication extends Application{

    private static MyApplication instance;
    private RequestQueue mVolleyQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static MyApplication getInstance() {
        return instance;
    }

    public RequestQueue getVolleyQueue(){

        if (mVolleyQueue == null){

            mVolleyQueue = Volley.newRequestQueue(this);
        }

        return mVolleyQueue;
    }
}
