package com.biogram;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recyclerViewHolder extends RecyclerView.ViewHolder {
    TextView name,num;

    public recyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.textView);
        num=itemView.findViewById(R.id.textView);
    }
}
