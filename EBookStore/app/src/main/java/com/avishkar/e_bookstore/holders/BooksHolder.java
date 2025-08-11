package com.avishkar.e_bookstore.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avishkar.e_bookstore.R;

public class BooksHolder extends RecyclerView.ViewHolder {


     public TextView tvId, tvBookName,tvAuthorName, tvBookPrice, tvAuthorContact;

    public BooksHolder(@NonNull View itemView) {
        super(itemView);

        tvId = itemView.findViewById(R.id.tvId);
        tvBookName = itemView.findViewById(R.id.tvBookName);
        tvAuthorName = itemView.findViewById(R.id.tvAuthorName);
        tvBookPrice = itemView.findViewById(R.id.tvBookPrice);
        tvAuthorContact = itemView.findViewById(R.id.tvAuthorContact);
    }
}
