package com.nowait.deque.RetrofitLink;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by user on 31.5.2018..
 */

public class RetrofitPrimary
{
    private static final String ROOT_URL = "http://katera.news/";

    static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }


    /**
     * Get API Service
     *
     * @return API Service
     */
    public static PrimarniAPI getApiPrimarni() {
        return getRetrofitInstance().create(PrimarniAPI.class);
    }
}
