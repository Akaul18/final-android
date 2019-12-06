package com.example.final_android;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder>{

    List<String> titles = new ArrayList<>();
    private static final String TAG = "RecyclerAdapter2";

    public RecyclerAdapter2(List<String> titles) {
        this.titles = titles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        Log.i(TAG, "onCreateViewHolder: " + count++);
        Log.d(TAG, "count");

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.subredit_titles, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//        holder.textView.setText(String.valueOf(position));
        Log.d(TAG, "onBindViewHolder: "+titles.get(position));
        holder.titles.setText(titles.get(position));

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }//represents number of rows in your recycler view

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView titles;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titles = itemView.findViewById(R.id.titles);
        }
    }
}