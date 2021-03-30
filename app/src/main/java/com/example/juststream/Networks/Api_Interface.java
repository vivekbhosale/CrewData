package com.example.juststream.Networks;

import com.example.juststream.Room.Crew;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api_Interface {
    @GET("crew")
    Call<List<Crew>> getdata();
}
