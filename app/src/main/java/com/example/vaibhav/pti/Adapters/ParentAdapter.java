package com.example.vaibhav.pti.Adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.pti.ModelClasses.Parent_model;
import com.example.vaibhav.pti.R;

import java.util.ArrayList;


public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.MyViewHolder> {

    private FragmentActivity activity;
    private ArrayList<Parent_model> arrayList = new ArrayList<>();

    public ParentAdapter(FragmentActivity activity, ArrayList<Parent_model> arrayList) {
        this.activity = activity;
        this.arrayList=arrayList;
    }

    @Override
    public ParentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.parentdetail,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ParentAdapter.MyViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getFname());
        holder.address.setText(arrayList.get(position).getAddress());
        holder.email.setText(arrayList.get(position).getEmail());
        holder.phone.setText(arrayList.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,address,phone;

        MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.par_name);
            email=itemView.findViewById(R.id.par_email);
            address=itemView.findViewById(R.id.par_address);
            phone=itemView.findViewById(R.id.par_phone);
        }
    }
}
