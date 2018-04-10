package com.example.vaibhav.pti.Adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.pti.ModelClasses.Parent_model;
import com.example.vaibhav.pti.R;


public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.MyViewHolder> {

    private FragmentActivity activity;
    private Parent_model p=new Parent_model();
    public ParentAdapter(FragmentActivity activity) {
        this.activity=activity;
    }

    @Override
    public ParentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.parentdetail,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ParentAdapter.MyViewHolder holder, int position) {
        holder.name.setText(p.getFname());
        holder.address.setText(p.getAddress());
        holder.email.setText(p.getAddress());
        holder.phone.setText(p.getPhone());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,address,phone;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.par_name);
            email=itemView.findViewById(R.id.par_email);
            address=itemView.findViewById(R.id.par_address);
            phone=itemView.findViewById(R.id.par_phone);
        }
    }
}
