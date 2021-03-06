package com.biogram;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchPage extends AppCompatActivity {
String phonenum;
DatabaseReference root;
RecyclerView mResultList;
EditText mSearchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app");
        root = mFirebaseDatabase.getReference();

        mSearchField = findViewById(R.id.search_field);
        mResultList = findViewById(R.id.result_list);

        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void firebaseUserSearch(String searchText) {
        Query query;
        //Toast.makeText(SearchActivity.this, "Started Search", Toast.LENGTH_LONG).show();
        if(searchText.isEmpty()){
            query = root.child("invalidating!query!thisway,becauseidonnowhatelsetodo:)");
        }else {
            query = root.child("root").child("users").orderByChild("id").startAt(searchText).endAt(searchText + "\uf8ff");
        }

        FirebaseRecyclerOptions<Users> options = new FirebaseRecyclerOptions.Builder<Users>().setQuery(query, Users.class).build();
        FirebaseRecyclerAdapter<Users, SearchPage.UsersViewHolder> adapter = new FirebaseRecyclerAdapter<Users, SearchPage.UsersViewHolder>(options) {
            @Override
            public SearchPage.UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_layout, parent, false);
                return new SearchPage.UsersViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(SearchPage.UsersViewHolder holder, int position, Users model) {
                holder.setDetails(getApplicationContext(),model.getName(),model.getId());
            }
        };

        adapter.startListening();
        mResultList.setAdapter(adapter);

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
                    Intent i = new Intent(SearchPage.this,profile.class);
                    i.putExtra("phone",n);
                    i.putExtra("from","search");
                    startActivity(i);


                }
            });
        }
        public void setDetails(Context ctx, String userName,String userNum){
            TextView user_name = mView.findViewById(R.id.name);
            TextView user_num = mView.findViewById(R.id.num);

            //  ImageView user_image = mView.findViewById(R.id.profile_image);

            user_name.setText(userName);
            user_num.setText(userNum);
            // Glide.with(ctx).load(userImage).placeholder(R.drawable.person_outline).into(user_image);
        }

    }

}