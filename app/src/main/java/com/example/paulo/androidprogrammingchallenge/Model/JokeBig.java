package com.example.paulo.androidprogrammingchallenge.Model;

import java.util.ArrayList;

/**
 * Created by paulo on 02/03/2018.
 */

public class JokeBig {

    String type;
    ArrayList<Jokes> value;

    public String getType() {
        return type;
    }

    public ArrayList<Jokes> getValue() {
        return value;
    }

    public JokeBig(String type, ArrayList<Jokes> value) {
        this.type = type;
        this.value = value;
    }

}
