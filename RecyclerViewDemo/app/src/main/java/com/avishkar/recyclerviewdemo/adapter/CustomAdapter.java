package com.avishkar.recyclerviewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avishkar.recyclerviewdemo.R;
import com.avishkar.recyclerviewdemo.models.UserModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    Context context;
    ArrayList<UserModel> arrayList;
    public CustomAdapter(Context context, ArrayList<UserModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.users_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final UserModel model = arrayList.get(position);
            holder.tvName.setText(model.getName());
            holder.tvRollNo.setText(""+arrayList.get(position).getRollNo());
        }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvRollNo,tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRollNo = itemView.findViewById(R.id.tvRollNo);
            tvName = itemView.findViewById(R.id.tvUserName);
        }
    }

}
