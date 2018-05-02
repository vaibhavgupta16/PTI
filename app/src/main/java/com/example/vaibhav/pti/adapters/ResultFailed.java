package com.example.vaibhav.pti.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibhav.pti.R;

public class ResultFailed extends RecyclerView.Adapter<ResultFailed.MyViewHolder> {
    Context context;
    private int image;

    public ResultFailed(int image, Context context) {
        this.image = image;
        this.context = context;
        //Log.e("Image", "" + image);
    }

    @Override
    public ResultFailed.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.resultfailed, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (image == 2131165302) {
            holder.txt.setText(R.string.no_notice);
            holder.img.setBackgroundResource(image);
        } else {
            holder.txt.setText(R.string.attennotmarked);
            holder.img.setBackgroundResource(image);
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txtres);
            img = itemView.findViewById(R.id.imgres);
        }
    }
}
