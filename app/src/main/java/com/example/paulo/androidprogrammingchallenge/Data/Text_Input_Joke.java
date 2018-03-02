package com.example.paulo.androidprogrammingchallenge.Data;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.paulo.androidprogrammingchallenge.Model.JokeBig;
import com.example.paulo.androidprogrammingchallenge.R;
import com.example.paulo.androidprogrammingchallenge.apiCall.apiCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Text_Input_Joke extends AppCompatActivity {

    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text__input__joke);

        mEditText = findViewById(R.id.edit_text);

    }


    public void submit(View view){

        String[] parts = mEditText.getText().toString().split(" ");
        String string1 = parts[0].trim();
        String string2 = parts[1].trim();

        System.out.println(string1);
        System.out.println(string2);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.icndb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCall ap = retrofit.create(apiCall.class);

        Call<JokeBig> jokers = ap.getRandomJokesMainCharacter(string1, string2);
        jokers.enqueue(new Callback<JokeBig>() {

            @Override
            public void onResponse(Call<JokeBig> call, Response<JokeBig> response) {
                if (response.isSuccessful()) {

                    JokeBig body = response.body();
                    String mRandomJoke = body.getValue().get(0).getJoke();

                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(Text_Input_Joke.this);
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
    private void printMessage(String message) {
        System.out.println(message);
    }

}
