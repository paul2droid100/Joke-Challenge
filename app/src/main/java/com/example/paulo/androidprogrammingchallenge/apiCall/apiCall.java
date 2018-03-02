package com.example.paulo.androidprogrammingchallenge.apiCall;

import com.example.paulo.androidprogrammingchallenge.Model.JokeBig;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by paulo on 02/03/2018.
 */

public interface apiCall {


    @GET("/jokes/")
    Call<JokeBig> getJokes();

    //Selecting 1 random Joke
    @GET("/jokes/random/1")
    Call<JokeBig> getRandomJokes();


   //Changing the nameof the main Character in the Joke
    @GET("/jokes/random/1/?&amp;")
    Call<JokeBig> getRandomJokesMainCharacter(@Query("firstName") String fname,@Query("lastName") String lname);

//    @GET("/jokes/random/1?firstName=John&amp;lastName=Doe")
//    Call<JokeBig> getRandomJokesMainCharacter(@Query("firstName") String fname,@Query("lastName") String lname);

    //Selecting batches of 20 jokes
    @GET("/jokes/random/20")
    Call<JokeBig> getRandomJ20okes();
}
