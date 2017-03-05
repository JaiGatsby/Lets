package com.example.user.lets.Utility;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Shayan on 05-Mar-17.
 */

public class OkHttpSingleton {
    private static OkHttpSingleton okHttpSingleton;
    private OkHttpClient okHttpClient;

    private OkHttpSingleton(){

        okHttpClient= new OkHttpClient.Builder()
                .readTimeout(300, TimeUnit.SECONDS)
                .build();

    }

    public static OkHttpSingleton getOkHttpInstance() {
        if(okHttpSingleton==null) {

            return new OkHttpSingleton();
        }
        return okHttpSingleton;
    }

    public OkHttpClient getOkHttpClient()
    {
        return okHttpClient;
    }
}
