package com.example.user.lets.Utility;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * Created by Shayan on 05-Mar-17.
 */

public class ApiClient {
    private static String baseUrl="13.75.45.68";
    private static int port = 3000;

    private static String getRecommendedEndpoint= "getRecommended";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //String should contain firebase id of user
    public static Call getRecommended(String id)
    {
        HttpUrl httpUrl= new HttpUrl.Builder()
                .scheme("http")
                .host(baseUrl)
                .port(port)
                .addPathSegment(getRecommendedEndpoint)
                .addQueryParameter("id", id)
                .build();
        Request request= new Request.Builder()
                .get()
                .url(httpUrl)
                .build();
        return OkHttpSingleton.getOkHttpInstance().getOkHttpClient().newCall(request);
    }
}
