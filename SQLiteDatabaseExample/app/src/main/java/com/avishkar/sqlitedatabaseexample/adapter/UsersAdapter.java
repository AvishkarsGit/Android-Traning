package com.avishkar.sqlitedatabaseexample.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avishkar.sqlitedatabaseexample.R;
import com.avishkar.sqlitedatabaseexample.model.UsersModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    Context context;

    ArrayList<UsersModel> arrayList;

    public UsersAdapter(Context context,ArrayList<UsersModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.users_data_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int index) {
        final UsersModel model = arrayList.get(index);
        holder.tvId.setText(""+model.getId());
        holder.tvName.setText(model.getName());
        holder.tvEmail.setText(model.getEmail());
        holder.tvPhone.setText(model.getPhone());
        try {
            Picasso.get().load(Uri.parse(model.getImg())).into(holder.imageView);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvId,tvName,tvEmail,tvPhone;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            imageView = itemView.findViewById(R.id.iv);
        }
    }
}
