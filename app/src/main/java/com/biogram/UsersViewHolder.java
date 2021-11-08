package com.biogram;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class UsersViewHolder extends RecyclerView.ViewHolder {
    TextView user_name,user_status;
    public UsersViewHolder(@NonNull View itemView) {
        super(itemView);
        user_name = itemView.findViewById(R.id.name_text);
        user_status =itemView.findViewById(R.id.status_text);

    }
}
