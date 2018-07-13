package com.example.tornado.tabsmaterialdesign.interfase;

import com.example.tornado.tabsmaterialdesign.model.ModelMovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tornado on 7/13/2018.
 */

public interface ApiInterface {
    @GET("volley_array.json")
    Call<List<ModelMovies>> getMovie();
}
