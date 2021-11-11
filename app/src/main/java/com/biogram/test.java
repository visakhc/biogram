package com.biogram;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class test extends AppCompatActivity {
    private DatabaseReference mUserDatabase;
ArrayList<String> mContacts;
RecyclerView mRecyclerView;
RecyclerView.LayoutManager mLayoutManager;
RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app");
        mUserDatabase = db.getReference().child("root");


mRecyclerView=findViewById(R.id.recyclerview);
mContacts=new ArrayList<>();
mContacts.add("ds");
        mContacts.add("dfcvs");
        mContacts.add("dggys");
        mContacts.add("dhh");
        mContacts.add("dshhh");
        mContacts.add("ds");
        mContacts.add("d4s");


    }
}

