package com.example.paulo.androidprogrammingchallenge;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.paulo.androidprogrammingchallenge.Data.Text_Input_Joke;
import com.example.paulo.androidprogrammingchallenge.Model.JokeBig;
import com.example.paulo.androidprogrammingchallenge.Model.Jokes;
import com.example.paulo.androidprogrammingchallenge.apiCall.apiCall;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NeverEndingList extends AppCompatActivity {

    RecyclerView mRecyclerView;
    CustomAdapter mAdapter;
    ArrayList<Jokes> mJokesList = new ArrayList<>();
    private EndlessRecyclerViewScrollListener scrollListener;
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_never_ending_list);
        submit();
        mRecyclerView = findViewById(R.id.recyclerView);
        spinner = findViewById(R.id.progressBar1);

        mAdapter = new CustomAdapter(this, mJokesList, spinner);
        spinner.setVisibility(View.GONE);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                submit();
            }
        };
        mRecyclerView.addOnScrollListener(scrollListener);
    }



    public void submit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.icndb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCall ap = retrofit.create(apiCall.class);

        Call<JokeBig> jokers = ap.getRandomJ20okes();
        jokers.enqueue(new Callback<JokeBig>() {

            @Override
            public void onResponse(Call<JokeBig> call, Response<JokeBig> response) {
                if (response.isSuccessful()) {
                    JokeBig body = response.body();
                    mJokesList.addAll(body.getValue());
                    mRecyclerView.setAdapter(mAdapter);

                } else {
                    printMessage("Unsuccessful Response");
                }
            }

            @Override
            public void onFailure(Call<JokeBig> call, Throwable throwable) {
                printMessage(throwable.getMessage());
            }
        });
    }
    private void printMessage(String message) {
        System.out.println(message);
    }

}
