package com.example.vaibhav.pti.Adapters;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibhav.pti.DashboardClasses.Attendance;
import com.example.vaibhav.pti.DashboardClasses.Parent;
import com.example.vaibhav.pti.R;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private String [] title;
    private int[] image;
    private FragmentActivity activity ;
    public HomeAdapter(String[] title, int[] image, FragmentActivity activity) {
        this.title=title;
        this.image=image;
        this.activity=activity;
    }

    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.homecust,parent,false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(HomeAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.imageView.setImageResource(image[position]);
        holder.textView.setText(title[position]);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0){
                    Intent intent = new Intent(activity, Attendance.class);
                    activity.startActivity(intent);

                }
                else if(position==1){

                }
                else if(position==2){

                }
                else if(position==3){
                    Intent intent=new Intent(activity, Parent.class);
                    activity.startActivity(intent);

                }
                else if(position==4){

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return image.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView cardView;

        MyViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageview);
            textView=itemView.findViewById(R.id.textview);
            cardView=itemView.findViewById(R.id.cardview);
        }
    }
}
