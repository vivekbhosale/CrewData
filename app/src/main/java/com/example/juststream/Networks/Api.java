package com.example.juststream.Networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static String BASE_URL = "https://api.spacexdata.com/v4/";

    public  static Api_Interface getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Api_Interface.class);
    }
}
