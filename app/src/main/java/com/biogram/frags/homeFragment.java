package com.biogram.frags;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.biogram.MainAdapter;
import com.biogram.R;
import com.biogram.profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class homeFragment extends Fragment implements MainAdapter.OnEachListener {
    View view;
    private RecyclerView mResultList;
    private DatabaseReference root;
    String phonenum;
    ArrayList<String> frndname =new ArrayList<>();
    ArrayList<String> frndnum =new ArrayList<>();

    SwipeRefreshLayout swipe;

    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter<MainAdapter.ViewHolder> mAdapter;

    FirebaseDatabase mFirebaseDatabase =
            FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);


        SharedPreferences sh = requireActivity().getSharedPreferences("biogram", MODE_PRIVATE);

        phonenum = sh.getString("phone", "");
        root = mFirebaseDatabase.getReference();

        mResultList = view.findViewById(R.id.recycler_v);
        mResultList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        friends();
        swipe=view.findViewById(R.id.refreshlayout);
        swipe.setOnRefreshListener(() -> {
          //  refresh();
            Thread thread=new Thread(runnable);
            thread.start();
            swipe.post(this::friends);
            swipe.setRefreshing(false);
        });

        swipe.setColorSchemeResources(android.R.color.holo_blue_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);



        return view;

    }


Runnable runnable=new Runnable() {
    @Override
    public void run() {

        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        Cursor cursor = requireActivity().getApplicationContext().getContentResolver().query(uri, null, null, null, sort);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Uri uriphone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";
                Cursor phonecursor = requireActivity().getContentResolver().query(uriphone, null, selection, new String[]{id}, null);
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

                                Toast.makeText(getContext(), "No contacts are on biogram", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                phonecursor.close();
            }
        }
        cursor.close();
    }
};

        public void friends() {
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
                                if(frndname.contains(friends)){}
                                else {
                                    frndname.add(friends);
                                    }
                                if(frndname.contains(num)){}
                                else {
                                    frndnum.add(num);
                                }
                                mAdapter=new MainAdapter(frndname,frndnum,homeFragment.this);
                                mResultList.setLayoutManager(mLayoutManager);
                                mResultList.setAdapter(mAdapter);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getContext(), "error on func friends", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

      @Override
        public void OnEachClick(int position) {
            //intent to profile mainactivity here
            String num=frndnum.get(position);
            Intent i = new Intent(getContext(), profile.class);
            i.putExtra("phone",num);
            i.putExtra("from","search");
            startActivity(i);

        }
}


