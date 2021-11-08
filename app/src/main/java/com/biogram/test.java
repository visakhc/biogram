package com.biogram;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class test extends AppCompatActivity {


    private EditText mSearchField;
    private Button mSearchBtn;

    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mUserDatabase = FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users");
        mSearchField = findViewById(R.id.search_field);
        mSearchBtn = findViewById(R.id.search_btn);

        mResultList = findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);

        mSearchBtn.setOnClickListener(view -> {

            String searchText = mSearchField.getText().toString();

            firebaseUserSearch(searchText);

        });

    }

    private void firebaseUserSearch(String searchText) {

        Toast.makeText(test.this, "Started Search", Toast.LENGTH_LONG).show();

       // Query firebaseSearchQuery = mUsearDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");
        Query query = mUserDatabase.startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerOptions<Users> options = new FirebaseRecyclerOptions.Builder<Users>().setQuery(query, Users.class).build();
        FirebaseRecyclerAdapter<Users, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder viewHolder, int position, @NonNull Users model) {
                viewHolder.user_name.setText(model.getNamee());
                viewHolder.user_status.setText(model.getStatus());
            }

            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
                return new UsersViewHolder(v);
            }

        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }


    // View Holder Class




    }

