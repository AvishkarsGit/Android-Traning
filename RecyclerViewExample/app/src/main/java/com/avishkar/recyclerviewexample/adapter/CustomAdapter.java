package com.avishkar.recyclerviewexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avishkar.recyclerviewexample.R;
import com.avishkar.recyclerviewexample.models.UserModel;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<UserModel> arrayList;

    public CustomAdapter(Context context, ArrayList<UserModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.users_row_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int index) {
        holder.tvRollNo.setText(""+arrayList.get(index).getRollNo());
        holder.tvName.setText(arrayList.get(index).getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size(); //
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRollNo,tvName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRollNo = itemView.findViewById(R.id.tvRollNo);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }

}
