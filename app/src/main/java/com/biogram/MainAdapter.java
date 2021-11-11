package com.biogram;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


    public class MainAdapter extends RecyclerView.Adapter<com.biogram.MainAdapter.ViewHolder> {

        ArrayList<String> mContacts;
        ArrayList<String> nam;

        public MainAdapter(ArrayList<String> mContacts, ArrayList<String> nam) {
            this.mContacts = mContacts;
            this.nam = nam;
        }

        @NonNull
        @Override
        public com.biogram.MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull com.biogram.MainAdapter.ViewHolder holder, int position) {
            holder.mName.setText(mContacts.get(position));
            holder.mN.setText(nam.get(position));
        }

        @Override
        public int getItemCount() {
            return mContacts.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mName,mN;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mName = itemView.findViewById(R.id.name);
                mN = itemView.findViewById(R.id.m);
            }
        }

}
