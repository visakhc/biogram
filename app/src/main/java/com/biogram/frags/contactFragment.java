package com.biogram.frags;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.biogram.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class contactFragment extends Fragment {
    private DatabaseReference root;
    LinearLayout wa,ig,snap,fb,yt,tw,li,tk;
    CardView map;
    TextView email,phone,name;
    String ph,id,k="dummy";
    String lat,lon;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contact, container, false);

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app");
        root= db.getReference();

        wa = view.findViewById(R.id.watspp);
        ig = view.findViewById(R.id.ig);
        fb = view.findViewById(R.id.fb);
        yt = view.findViewById(R.id.yt);
        tw = view.findViewById(R.id.tw);
        li = view.findViewById(R.id.li);
        tk = view.findViewById(R.id.tk);
        email=view.findViewById(R.id.email);
        phone=view.findViewById(R.id.phone);
        name=view.findViewById(R.id.username);
        snap = view.findViewById(R.id.snap);
        map = view.findViewById(R.id.map);

        Intent intent =requireActivity().getIntent();
        ph=intent.getStringExtra("phone");
        k=intent.getStringExtra("from");

        String d="search";

        if(Objects.equals(k, d)) {
            id = ph;
        }
        else {
            SharedPreferences sh = requireActivity().getSharedPreferences("biogram",MODE_PRIVATE);
            id = sh.getString("phone", "");
        }



        wa.setOnClickListener(v -> waclass());
        ig.setOnClickListener(v -> igclass());
        snap.setOnClickListener(v -> snapclass());
        map.setOnClickListener(v -> mapclass());
        li.setOnClickListener(v -> liclass());
        yt.setOnClickListener(v -> ytclass());
        tw.setOnClickListener(v -> twclass());
        email.setOnClickListener(v -> mailclass());
        phone.setOnClickListener(v -> phoneclass());
        tk.setOnClickListener(v -> tkclass());
        fb.setOnClickListener(v -> fbclass());

//namemailnumset();
        return view;

    }

    private void namemailnumset() {

        root.child("root").child("users").child(id).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nm= Objects.requireNonNull(snapshot.getValue()).toString();
                if(nm.isEmpty()){
                    Toast.makeText(getContext(),"no name 4 u....",Toast.LENGTH_SHORT).show();
                    return;
                }
                name.setText(nm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });


        root.child("root").child("users").child(id).child("contact").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String num= Objects.requireNonNull(snapshot.getValue()).toString();
                if(num.isEmpty()){
                    Toast.makeText(getContext(),"nothing here...",Toast.LENGTH_SHORT).show();
                    return;
                }
                phone.setText(num);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });


        root.child("root").child("users").child(id).child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String num= Objects.requireNonNull(snapshot.getValue()).toString();
                if(num.isEmpty()){
                    Toast.makeText(getContext(),"nothing here...",Toast.LENGTH_SHORT).show();
                    return;
                }
                email.setText(num);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });
    }




    private void tkclass() {


        root.child("root").child("users").child(id).child("tiktok").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String tiktokid= Objects.requireNonNull(snapshot.getValue()).toString();
                if(tiktokid.isEmpty()){
                    Toast.makeText(getContext(),"eternal darkness here",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = "https://www.tiktok.com/@"+tiktokid;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void phoneclass() {

        root.child("root").child("users").child(id).child("contact").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String num= Objects.requireNonNull(snapshot.getValue()).toString();
                if(num.isEmpty()){
                    Toast.makeText(getContext(),"nothing here...",Toast.LENGTH_SHORT).show();
                    return;
                }
                phone.setText(num);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+num));
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void mailclass() {

        root.child("root").child("users").child(id).child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String address = Objects.requireNonNull(snapshot.getValue()).toString();
                if(address.isEmpty()){
                    Toast.makeText(getContext(),"homeless",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:"+address));
                startActivity(Intent.createChooser(emailIntent, "Hello "));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }
    private void fbclass() {

        root.child("root").child("users").child(id).child("facebook").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fbid= Objects.requireNonNull(snapshot.getValue()).toString();
                if(fbid.isEmpty()){
                    Toast.makeText(getContext(),"woah...founf darkmatter........., but where's fb",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = "https://www.facebook.com/"+fbid;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }
    private void liclass() {

        root.child("root").child("users").child(id).child("linkedin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String url =  Objects.requireNonNull(snapshot.getValue()).toString();
                if(url.isEmpty()){
                    Toast.makeText(getContext(),"full of emptyness...",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }


    private void ytclass() {

        root.child("root").child("users").child(id).child("youtube").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String url = Objects.requireNonNull(snapshot.getValue()).toString();
                if(url.isEmpty()){
                    Toast.makeText(getContext(),"no youtube 4 u",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }


    private void twclass() {

        root.child("root").child("users").child(id).child("twitter").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String usrid= Objects.requireNonNull(snapshot.getValue()).toString();
                if(usrid.isEmpty()){
                    Toast.makeText(getContext(),"twitttterrrrr....nooo",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = "https://twitter.com/"+usrid;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void waclass() {

        root.child("root").child("users").child(id).child("whatsapp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String number= Objects.requireNonNull(snapshot.getValue()).toString();
                if(number.isEmpty()){
                    Toast.makeText(getContext(),"emptyness 4 u....",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = "https://api.whatsapp.com/send?phone=+91"+number;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void igclass()
    {

        root.child("root").child("users").child(id).child("instagram").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String instaid= Objects.requireNonNull(snapshot.getValue()).toString();
                if(instaid.isEmpty()){
                    Toast.makeText(getContext(),"full of nothing....  :)",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = "https://www.instagram.com/"+instaid;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }
    private void snapclass()
    {

        root.child("root").child("users").child(id).child("snapchat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String snapid= Objects.requireNonNull(snapshot.getValue()).toString();
                if(snapid.isEmpty()){
                    Toast.makeText(getContext(),"woow,...such an empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = "https://www.snapchat.com/add/"+snapid;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }
    private void mapclass()
    {

        root.child("root").child("users").child(id).child("longitude").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lon= Objects.requireNonNull(snapshot.getValue()).toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });
        root.child("root").child("users").child(id).child("latitude").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lat= Objects.requireNonNull(snapshot.getValue()).toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });
        if(lat.isEmpty() || lon.isEmpty()){
            Toast.makeText(getContext(),"no map 4 u today....duhh",Toast.LENGTH_SHORT).show();
            return;
        }
        String url = "https://www.google.com/maps/search/?api=1&query="+lon+"%2C"+lat;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}