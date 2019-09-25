package com.app.AndroidNews.retrofitConfig;

import com.app.AndroidNews.retrofitJson.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetJsonAll {

    @GET("v2/top-headlines")
    Call<NewsList> getNewsListCategory(

            @Query("country")
            String country,

            @Query("category")
            String category,

            @Query("apikey")
            String apikey

    );

    @GET("v2/top-headlines")
    Call<NewsList> getNewsList(

            @Query("country")
            String country,

            @Query("apikey")
            String apikey

    );
}
