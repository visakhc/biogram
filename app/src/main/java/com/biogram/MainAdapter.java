package com.biogram;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


    public class MainAdapter extends RecyclerView.Adapter<com.biogram.MainAdapter.ViewHolder> {

        ArrayList<String> frndname;
        ArrayList<String> frndnum;
        OnEachListener mOnEachListener;

        public MainAdapter(ArrayList<String> frndname,ArrayList<String> frndnum, OnEachListener OnEachListener) {
            this.frndname = frndname;
            this.frndnum = frndnum;
            this.mOnEachListener=OnEachListener;
        }

        @NonNull
        @Override
        public com.biogram.MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_layout, parent, false);
            return new ViewHolder(view,mOnEachListener);
        }

        @Override
        public void onBindViewHolder(@NonNull com.biogram.MainAdapter.ViewHolder holder, int position) {
            holder.mName.setText(frndname.get(position));
            holder.mNum.setText(frndnum.get(position));

        }

        @Override
        public int getItemCount() {
            return frndname.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView mName,mNum;
            OnEachListener OnEachListener;

            public ViewHolder(@NonNull View itemView,OnEachListener OnEachListener) {
                super(itemView);
                mName = itemView.findViewById(R.id.name);
                mNum = itemView.findViewById(R.id.num);
                this.OnEachListener=OnEachListener;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {

                OnEachListener.OnEachClick(getBindingAdapterPosition());
            }
                    }
        public interface OnEachListener{
            void OnEachClick(int position);
        }

}
