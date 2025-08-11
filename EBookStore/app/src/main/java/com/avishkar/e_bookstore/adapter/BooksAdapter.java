package com.avishkar.e_bookstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avishkar.e_bookstore.HomeActivity;
import com.avishkar.e_bookstore.R;
import com.avishkar.e_bookstore.activities.EditBookActivity;
import com.avishkar.e_bookstore.holders.BooksHolder;
import com.avishkar.e_bookstore.model.BooksModel;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksHolder> {


    Context context;
    ArrayList<BooksModel> booksList;

    public BooksAdapter(Context context, ArrayList<BooksModel> booksList) {
        this.context = context;
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public BooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.books_row_layout,parent,false);
//        BooksHolder holder = new BooksHolder(view);
        return new BooksHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksHolder holder, int index) {
        final BooksModel model = booksList.get(index);

        holder.tvId.setText(""+model.getId());
        holder.tvBookName.setText(model.getBookName());
        holder.tvAuthorName.setText(model.getAuthorName());
        holder.tvBookPrice.setText("₹ "+model.getPrice()+".00"); //₹ 299.00
        holder.tvAuthorContact.setText(model.getAuthorContact());

        //click on item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to edit activity
                Intent i = new Intent(context,EditBookActivity.class);
                i.putExtra("id",model.getId());
                i.putExtra("book_name",model.getBookName());
                i.putExtra("book_author",model.getAuthorName());
                i.putExtra("price",model.getPrice());
                i.putExtra("contact",model.getAuthorContact());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
