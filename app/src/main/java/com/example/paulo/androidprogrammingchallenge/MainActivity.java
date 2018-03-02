package com.example.paulo.androidprogrammingchallenge;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.paulo.androidprogrammingchallenge.Data.Text_Input_Joke;
import com.example.paulo.androidprogrammingchallenge.Model.JokeBig;
import com.example.paulo.androidprogrammingchallenge.apiCall.apiCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button mRandom;
    Button mTextInput;
    Button mNever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRandom = findViewById(R.id.random_jk);
        mTextInput = findViewById(R.id.text_input);
        mNever = findViewById(R.id.never_list);


    }


    public void Sever(View v){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.icndb.com") //website: http://api.icndb.com/jokes/jokenumber
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCall ap = retrofit.create(apiCall.class);

        switch(v.getId()) {
            case R.id.random_jk:
                Call<JokeBig> jokers = ap.getRandomJokes();
                jokers.enqueue(new Callback<JokeBig>() {

                    @Override
                    public void onResponse(Call<JokeBig> call, Response<JokeBig> response) {
                        if (response.isSuccessful()) {

                            JokeBig body = response.body();
                            String mRandomJoke = body.getValue().get(0).getJoke();

                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                            mBuilder.setTitle("CONNECTION");
                            mBuilder.setMessage(mRandomJoke);
                            mBuilder.setCancelable(false);
                            mBuilder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            mBuilder.show();

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
    }

    private void printMessage(String message) {
        System.out.println(message);
    }


    public void Text_Input_Button(View view){
        Intent intent = new Intent(this, Text_Input_Joke.class);
        startActivity(intent);
    }

    public void Never_Ending_Button(View view){
        Intent intent = new Intent(this, NeverEndingList.class);
        startActivity(intent);
    }
}
