package com.biogram;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    DatabaseReference root;
    Button search,home,profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app");
        root = db.getReference();
search=findViewById(R.id.search);
    home=findViewById(R.id.homepage);
        profile=findViewById(R.id.profile);

        search.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this,SearchPage.class);
            startActivity(i);
        });
        home.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this,HomePage.class);
            startActivity(i);
        });
        profile.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this,profile.class);
            startActivity(i);
        });


    }
}