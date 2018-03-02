package com.example.paulo.androidprogrammingchallenge.Model;

import java.util.ArrayList;

/**
 * Created by paulo on 02/03/2018.
 */

public class Jokes {


    int id;
    String joke;
    ArrayList<String> categories;

    public Jokes(int id, String joke, ArrayList<String> categories) {
        this.id = id;
        this.joke = joke;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public String getJoke() {
        return joke;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }


}
