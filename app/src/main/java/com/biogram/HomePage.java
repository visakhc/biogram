package com.biogram;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class HomePage extends AppCompatActivity implements MainAdapter.OnEachListener{
    public MaterialButton refresh;

    private RecyclerView mResultList;
    private DatabaseReference root;
    String phonenum;
    ArrayList<String> frndname =new ArrayList<>();
    ArrayList<String> frndnum =new ArrayList<>();

    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter<MainAdapter.ViewHolder> mAdapter;
    FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        SharedPreferences sh = getSharedPreferences("biogram",MODE_PRIVATE);
        phonenum = sh.getString("phone", "");
        root = mFirebaseDatabase.getReference();

        mResultList = findViewById(R.id.recycler_v);
        mResultList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        friends();


    refresh=findViewById(R.id.refresh);
    refresh.setOnClickListener(v -> refresh());



    }

    private void friends() {
        //this function fills the recycler view with numbers and names of your friends on db
        root.child("root").child("users").child(phonenum).child("friends")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DataSnapshot lastDataSnapshot;
                        Iterable<DataSnapshot>  iterable= snapshot.getChildren();
                        for (DataSnapshot dataSnapshot : iterable) {
                            lastDataSnapshot = dataSnapshot;
                            String friends = Objects.requireNonNull(lastDataSnapshot.getValue()).toString();
                            String num=lastDataSnapshot.getKey();

                            frndname.add(friends);
                            frndnum.add(num);

                            mAdapter=new MainAdapter(frndname,frndnum,HomePage.this);
                            mResultList.setLayoutManager(mLayoutManager);
                            mResultList.setAdapter(mAdapter);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "error on func friends", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void refresh() {

        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        Cursor cursor = getContentResolver().query(uri, null, null, null, sort);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Uri uriphone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";
                Cursor phonecursor = getContentResolver().query(uriphone, null, selection, new String[]{id}, null);
                if (phonecursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String number = phonecursor.getString(phonecursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    number = number.replaceAll("\\s+", "");
                    number = number.replaceAll("-", "");
                    number = number.replace("+91", "");
                    if (number.length() == 10) {
                        String finalNumber = number;
                        root.child("root").child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChild(finalNumber) && !finalNumber.equals(phonenum)) {
                                    root.child("root").child("users")
                                            .child(phonenum).child("friends").child(finalNumber).setValue(name);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(getApplicationContext(), "No contacts are on biogram", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                phonecursor.close();
            }
        }
        cursor.close();
    }

    @Override
    public void OnEachClick(int position) {
        Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
    }
}