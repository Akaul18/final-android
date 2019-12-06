package com.example.final_android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<String> subredit = new ArrayList<>();
//    Context con;
//    String subredit;


    public RecyclerAdapter(List<String> subredit) {
        this.subredit = subredit;
//        this.con = con;
    }
//    public RecyclerAdapter(String subredit) {
//        this.subredit = subredit;
//    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.textView.setText(subredit.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), subreditActivity.class);
                i.putExtra("subredit",subredit.get(position));
                view.getContext().startActivity(i);
            }
        });

//
//                    Intent i = new Intent(v.getContext(), MainActivity.class);
////                    Intent i = new Intent();
////                    i.setAction(Intent.ACTION_SEND);
//                    i.putExtra("url",url.get(position));
//                    v.getContext().startActivity(i);
//                }
//            });
//        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.subredit_rows, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
//        return url.size();
        return subredit.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.subreditView);
        }
    }
}
