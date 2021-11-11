package com.biogram;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class search extends AppCompatActivity implements MainAdapter.OnEachListener {
    Button b;
    EditText t;
ArrayList<String> nam = new ArrayList<>();
ArrayList<String> mContacts;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter<MainAdapter.ViewHolder> mAdapter;
  public   String phonenum,m;
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference root = db.getReference();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_search);

        b = findViewById(R.id.button);
        t = findViewById(R.id.searchphone);
        mRecyclerView=findViewById(R.id.recycler_view);
        mContacts=new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        SharedPreferences sh = getSharedPreferences("biogram",MODE_PRIVATE);
        phonenum = sh.getString("phone", "");

        b.setOnClickListener(v -> {
/*
            Intent intent = new Intent(search.this, MainActivity.class);
            intent.putExtra("phone",t.getText().toString());
            intent.putExtra("from","search"); //add the other to normal socialadd view
            startActivity(intent);

*/

        });

       // getcontactlist();

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
                        // serch(number);
                        String finalNumber = number;
                        root.child("root").child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                //String v = Objects.requireNonNull(snapshot.getValue()).toString();
                                if (snapshot.hasChild(finalNumber) && !finalNumber.equals(phonenum)) {
                                    mContacts.add(finalNumber);
                                    nam.add(name);
                                    root.child("root").child("test").child("users").child(phonenum).child("friends").child(finalNumber).setValue(finalNumber);
                                  //  mAdapter = new MainAdapter(mContacts,nam,search.this);
                                    mRecyclerView.setLayoutManager(mLayoutManager);
                                    mRecyclerView.setAdapter(mAdapter);
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
        String s=mContacts.get(position);
        // add intent to the profile act here
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();
    }

}



