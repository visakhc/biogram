package com.biogram.frags;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.biogram.R;
import com.biogram.profile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import soup.neumorphism.NeumorphButton;


public class searchFragment extends Fragment {

    NeumorphButton nb;
    View view;
    String phonenum;
    DatabaseReference root;
    RecyclerView mResultList;
    EditText mSearchField;
    String searchText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflates the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);

      FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app");
     root = mFirebaseDatabase.getReference();

        mSearchField = view.findViewById(R.id.search_field);
        mResultList = view.findViewById(R.id.result_list);

        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(getContext()));

        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               searchText = mSearchField.getText().toString();

       firebaseUserSearch(searchText);
       Thread thread=new Thread(runnable);
       thread.start();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        return view;
    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
//commit a git before runnable TODO
        }
    };

    public void firebaseUserSearch(String searchText) {

    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public UsersViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String pos= String.valueOf(getBindingAdapterPosition());
                    TextView user_name = mView.findViewById(R.id.name);

                    String n=user_name.getText().toString();
                 //intent to mainActivity here
                     Intent i = new Intent(getContext(), profile.class);
                    i.putExtra("phone",n);
                    i.putExtra("from","search");
                   startActivity(i);


                }
            });
        }
        public void setDetails(Context ctx, String userName, String userNum){
            TextView user_name = mView.findViewById(R.id.name);
            TextView user_num = mView.findViewById(R.id.num);

            //  ImageView user_image = mView.findViewById(R.id.profile_image);

            user_name.setText(userName);
            user_num.setText(userNum);
            // Glide.with(ctx).load(userImage).placeholder(R.drawable.person_outline).into(user_image);
        }

    }
}