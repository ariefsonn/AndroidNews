package com.app.AndroidNews;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.AndroidNews.adapter.MyAdapter;
import com.app.AndroidNews.retrofitConfig.GetJsonAll;
import com.app.AndroidNews.retrofitConfig.RetrofitConfigToJson;
import com.app.AndroidNews.retrofitJson.News;
import com.app.AndroidNews.retrofitJson.NewsList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardDetail extends AppCompatActivity {

    RecyclerView dashboard_detail;
    String business;
    GridLayoutManager gm;
    MyAdapter adapter;

    GetJsonAll getJsonAll;
    List<News> news;
    String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_detail);

        dashboard_detail = findViewById(R.id.recycler_detail);
        gm = new GridLayoutManager(this, 1);
        dashboard_detail.setLayoutManager(gm);
        business = getIntent().getStringExtra("business");
        news = new ArrayList<>();
        adapter = new MyAdapter(this, news);

        getJsonAll = RetrofitConfigToJson.getResponses();


        switch (business){

            case "business":
                //dashboard_detail.setText(business);
                tampilkanDashboard("business");
                break;
            case "entertainment":
                //dashboard_detail.setText(business);
                tampilkanDashboard("entertainment");
                break;
            case "health":
                //dashboard_detail.setText(business);
                tampilkanDashboard("health");
                break;
            case "science":
                //dashboard_detail.setText(business);
                tampilkanDashboard("science");
                break;
            case "sports":
                //dashboard_detail.setText(business);
                tampilkanDashboard("sports");
                break;
            case "technology":
                //dashboard_detail.setText(business);
                tampilkanDashboard("technology");
                break;
            default:
                Toast.makeText(getApplicationContext(), "CATEGORY TIDAK ADA", Toast.LENGTH_LONG).show();
                break;
        }

    }

    private void tampilkanDashboard(String category){

        getJsonAll.getNewsListCategory("id", category, "b41aca1ed89d4ce7883195bb434ff2fa").enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                Log.d("berhasil", response + "");

                news = response.body().getArticles();
                title = news.get(0).getTitle();

                Log.d("titleKu", title + "");

                adapter = new MyAdapter(getApplicationContext(), news);
                dashboard_detail.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("gagal", t + "");
            }
        });

    }
}
