package com.example.paulo.androidprogrammingchallenge.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.paulo.androidprogrammingchallenge.Model.Jokes;
import com.example.paulo.androidprogrammingchallenge.R;

import java.util.ArrayList;

/**
 * Created by paulo on 02/03/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.RecyclerViewHolderr>{

    //private ProgressBar spinner;
    private Context mContext;
    private ArrayList<Jokes> mJokesList;


    public CustomAdapter(Context context, ArrayList<Jokes> mJokesList, ProgressBar spinner) {

        this.mContext = context;
        this.mJokesList = mJokesList;
       // this.spinner = spinner;

    }


    public class RecyclerViewHolderr extends RecyclerView.ViewHolder{

        public TextView mTextView;


        public RecyclerViewHolderr(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.item_textView);

        }
    }


    @Override
    public RecyclerViewHolderr onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_items, parent, false);
        RecyclerViewHolderr recyclerViewHolderr;

        recyclerViewHolderr = new RecyclerViewHolderr(v);
        return recyclerViewHolderr;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderr holder, int position) {
        holder.mTextView.setText(mJokesList.get(position).getJoke());


//        if(holder.getLayoutPosition() == 19){
//            spinner.setVisibility(View.VISIBLE);
//            spinner.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                    NeverEndingList obj = new NeverEndingList();
//
//                    spinner.setVisibility(View.GONE);
//                    obj.submit();
//
//                }
//            }, 2000L);
//        }
    }



    @Override
    public int getItemCount() {
        return mJokesList.size();
    }
}
