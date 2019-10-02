package com.app.AndroidNews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.app.AndroidNews.adapter.MyAdapter;
import com.app.AndroidNews.fragmentku.FragmentAlert;
import com.app.AndroidNews.fragmentku.FragmentDashboard;
import com.app.AndroidNews.fragmentku.FragmentHome;
import com.app.AndroidNews.retrofitConfig.GetJsonAll;
import com.app.AndroidNews.retrofitConfig.RetrofitConfigToJson;
import com.app.AndroidNews.retrofitJson.News;
import com.app.AndroidNews.retrofitJson.NewsList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fragmentclick(new FragmentHome());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int userId = menuItem.getItemId();
        Fragment fragment = null;

        switch (userId){

            case R.id.home:
                fragment = new FragmentHome();
                break;

            case R.id.dashboard:
                fragment = new FragmentDashboard();
                break;

            case  R.id.alert:
                fragment = new FragmentAlert();
                break;

        }

        return fragmentclick(fragment);
    }

    private Boolean fragmentclick(Fragment fragment){

        if (fragment != null){

            getSupportFragmentManager().beginTransaction().replace(R.id.ganti, fragment).commit();

        }

        return false;

    }
}
