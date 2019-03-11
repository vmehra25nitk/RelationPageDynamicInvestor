package com.example.vedantmehra.relationpagedynamicinvestor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextClassification;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<String[]> arrayList;

    public MyRecyclerAdapter(Context context, ArrayList<String[]> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = (View)LayoutInflater.from(context).inflate(R.layout.recyclerview_row, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        TextView t1, t2;
        t1 = viewHolder.view.findViewById(R.id.listName);
        t2 = viewHolder.view.findViewById(R.id.listEmail);
        t1.setText(arrayList.get(i)[0]);
        t2.setText(arrayList.get(i)[1]);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
