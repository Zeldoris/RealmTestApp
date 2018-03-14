package com.example.dell.firstapp;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import adapters.MoviesAdapter;
import api.client.ApiClient;
import api.service.ApiService;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import model.JsonDataModel;
import model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private TextView tv_info;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization
        init();
    }

    @Override
    public void onRefresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                initMovies();
                refreshLayout.setRefreshing(false);
            }
        }, 1500);
    }

    private void init()
    {
        tv_info = findViewById(R.id.textView_info);
        refreshLayout = findViewById(R.id.swipe_refresh_layout);
        recyclerView = findViewById(R.id.recyclerView_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        refreshLayout.setOnRefreshListener(this);

        realm = Realm.getDefaultInstance();

        // On récupère les movie par http
        initMovies();
    }

    private void initMovies() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<JsonDataModel> call = apiService.getMovies();
        call.enqueue(new Callback<JsonDataModel>() {
            @Override
            public void onResponse(Call<JsonDataModel> call, Response<JsonDataModel> response) {
                JsonDataModel dataModel = response.body();
                if (dataModel != null && dataModel.getStatus() == 200) {
                    if (dataModel.getMovies() != null && dataModel.getMovies().size() > 0) {
                        refreshLayout.setVisibility(View.VISIBLE);
                        adapter = new MoviesAdapter(MainActivity.this, dataModel.getMovies());
                        recyclerView.setAdapter(adapter);
                        writeLocalDb(dataModel.getMovies());
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonDataModel> call, Throwable t) {
                Log.e("Failed", (t.getMessage() != null) ? t.getMessage() : "onFailure");
                RealmResults<Movie> movies = readLocalDb();
                if (movies.isLoaded() && !movies.isEmpty()) {
                    RealmList<Movie> m = new RealmList<>();
                    m.addAll(movies);
                    refreshLayout.setVisibility(View.VISIBLE);
                    adapter = new MoviesAdapter(MainActivity.this, m);
                    recyclerView.setAdapter(adapter);
                } else {
                    refreshLayout.setVisibility(View.GONE);
                    tv_info.setText("No movies in database...");
                    tv_info.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private RealmResults<Movie> readLocalDb() {
        return realm.where(Movie.class).findAll();
    }

    private void writeLocalDb(final RealmList<Movie> movies)
    {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Movie m : movies) {
                    Movie movie = realm.createObject(Movie.class);
                    movie.setMovie(m);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
