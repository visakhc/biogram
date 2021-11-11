package com.biogram;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SearchActivity  extends AppCompatActivity {
    private EditText mSearchField;
    private RecyclerView mResultList;
    private DatabaseReference root;
    String phonenum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app");
        root = mFirebaseDatabase.getReference();

        mSearchField = findViewById(R.id.search_field);
        mResultList = findViewById(R.id.result_list);

        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sh = getSharedPreferences("biogram",MODE_PRIVATE);
        phonenum = sh.getString("phone", "");


      //  whilenoinput();


        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = mSearchField.getText().toString();
                 if(searchText.isEmpty()){
                     firebaseUserSearch("");
                 }
                else {
                     firebaseUserSearch(searchText);
                }
            }
        });

    }

    private void whilenoinput() {
     //   ArrayList<String> arr=new ArrayList<String>();
     //   RecyclerView recyclerView = findViewById(R.id.recycleview);

        root.child("root").child("users").child(phonenum).child("friends")
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot lastDataSnapshot = null;
                Iterable<DataSnapshot>  iterable= snapshot.getChildren();
                    for (DataSnapshot dataSnapshot : iterable) {
                         lastDataSnapshot = dataSnapshot;
                         String friends = Objects.requireNonNull(lastDataSnapshot.getValue()).toString();
                         //  arr.add(friends);
                    //friends has all the values of friends
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

             }
        });
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // myAdapter adapter = new myAdapter(this, arr);
      //  recyclerView.setAdapter(adapter);
    }

    public void firebaseUserSearch(String searchText) {

       // Toast.makeText(SearchActivity.this, "Started Search", Toast.LENGTH_LONG).show();

        Query query = root.child("root").child("users").orderByChild("id").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerOptions<Users> options = new FirebaseRecyclerOptions.Builder<Users>().setQuery(query, Users.class).build();

        FirebaseRecyclerAdapter<Users, UsersViewHolder> adapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(options)
        {
            @Override
            public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_layout, parent, false);
                return new UsersViewHolder(view);
            }

            @Override
            protected void onBindViewHolder( UsersViewHolder holder, int position, Users model) {
                holder.setDetails(getApplicationContext(), model.getName(), model.getImageurl());
            }
        };

        adapter.startListening();
        mResultList.setAdapter(adapter);

    }



    // View Holder Class

    public static class UsersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public UsersViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setDetails(Context ctx, String userName,String userImage){
            TextView user_name = mView.findViewById(R.id.name);
            ImageView user_image = mView.findViewById(R.id.profile_image);

            user_name.setText(userName);
            Glide.with(ctx).load(userImage).placeholder(R.drawable.person_outline).into(user_image);
        }

    }

}