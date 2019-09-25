package com.app.AndroidNews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.app.AndroidNews.retrofitConfig.GetJsonAll;
import com.app.AndroidNews.retrofitConfig.RetrofitConfigToJson;
import com.app.AndroidNews.retrofitJson.News;
import com.app.AndroidNews.retrofitJson.NewsList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    GetJsonAll getJsonAll;
    List<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsList = new ArrayList<>();
        getJsonAll = RetrofitConfigToJson.getResponses();

        getJsonAll.getNewsList("id", "b41aca1ed89d4ce7883195bb434ff2fa").enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                Log.d("berhasil", response + "");
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("gagal", t + "");
            }
        });
    }
}
