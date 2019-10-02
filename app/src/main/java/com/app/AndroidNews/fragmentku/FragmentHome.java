package com.app.AndroidNews.fragmentku;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.AndroidNews.R;
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

public class FragmentHome extends Fragment {

    GetJsonAll getJsonAll;
    List<News> newsList;
    String title, description;

    RecyclerView recycler_view;
    MyAdapter adapter;
    GridLayoutManager glm;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        recycler_view = view.findViewById(R.id.recycler_view);
        glm = new GridLayoutManager(getContext(), 1);
        recycler_view.setLayoutManager(glm);
        newsList = new ArrayList<>();
        adapter = new MyAdapter(getContext(), newsList);
        recycler_view.setAdapter(adapter);

        getJsonAll = RetrofitConfigToJson.getResponses();

        getJsonAll.getNewsList("id", "b41aca1ed89d4ce7883195bb434ff2fa").enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                Log.d("berhasil", response + "");

                newsList = response.body().getArticles();

                title = newsList.get(0).getTitle();
                description = newsList.get(0).getDescription();
                Log.d("title berita", "Judul: " + title + " " + "Deskripsi: " + description);

                adapter = new MyAdapter(getContext(), newsList);
                recycler_view.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("gagal", t + "");
            }
        });

        return view;

    }
}
