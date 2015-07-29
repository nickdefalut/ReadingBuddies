package com.example.lxia.readingbuddies;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

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
        initUIL();
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

    public void initUIL(){

        File cacheDir = StorageUtils.getOwnCacheDirectory(this, "ReadingBuddies/cache/image");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 *1024 *1024))
                .memoryCacheSize(2*1024*1024)
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheSize(100)
                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000))
                .build();

        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.getInstance().clearMemoryCache();
    }
}
