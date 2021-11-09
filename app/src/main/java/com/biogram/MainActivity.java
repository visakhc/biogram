package com.biogram;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference root;

    LinearLayout wa,ig,snap,fb,yt,tw,li,tk;
    CardView map;
    TextView email,phone,name;
    String ph,id,k;
    String lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app");
        root= db.getReference();


        wa = findViewById(R.id.watspp);
        ig = findViewById(R.id.ig);
        fb = findViewById(R.id.fb);
        yt = findViewById(R.id.yt);
        tw = findViewById(R.id.tw);
        li = findViewById(R.id.li);
        tk = findViewById(R.id.tk);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        name=findViewById(R.id.username);
        snap = findViewById(R.id.snap);
        map = findViewById(R.id.map);

        Intent intent =getIntent();
        ph=intent.getStringExtra("phone");
        k=intent.getStringExtra("from");

        if(k == "search") {
                 id = ph;
            }
        else {
            SharedPreferences sh = getSharedPreferences("biogram",MODE_PRIVATE);
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

namemailnumset();

    }

    private void namemailnumset() {

        root.child("users").child(id).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nm= Objects.requireNonNull(snapshot.getValue()).toString();
                if(nm.isEmpty()){
                    Toast.makeText(getApplicationContext(),"no name 4 u....",Toast.LENGTH_SHORT).show();
                    return;
                }
               name.setText(nm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });


        root.child("users").child(id).child("contact").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String num= Objects.requireNonNull(snapshot.getValue()).toString();
                if(num.isEmpty()){
                    Toast.makeText(getApplicationContext(),"nothing here...",Toast.LENGTH_SHORT).show();
                    return;
                }
                phone.setText(num);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });


        root.child("users").child(id).child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String num= Objects.requireNonNull(snapshot.getValue()).toString();
                if(num.isEmpty()){
                    Toast.makeText(getApplicationContext(),"nothing here...",Toast.LENGTH_SHORT).show();
                    return;
                }
                email.setText(num);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });
    }


    private void tkclass() {


        root.child("users").child(id).child("tiktok").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String tiktokid= Objects.requireNonNull(snapshot.getValue()).toString();
                if(tiktokid.isEmpty()){
                    Toast.makeText(getApplicationContext(),"eternal darkness here",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = "https://www.tiktok.com/@"+tiktokid;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });
    }

  private void phoneclass() {

      root.child("users").child(id).child("contact").addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              String num= Objects.requireNonNull(snapshot.getValue()).toString();
              if(num.isEmpty()){
                  Toast.makeText(getApplicationContext(),"nothing here...",Toast.LENGTH_SHORT).show();
                  return;
              }
              phone.setText(num);
              Intent intent = new Intent(Intent.ACTION_DIAL);
              intent.setData(Uri.parse("tel:"+num));
              startActivity(intent);
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {
              Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
          }

      });

    }

    private void mailclass() {

        root.child("users").child(id).child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String address = Objects.requireNonNull(snapshot.getValue()).toString();
                if(address.isEmpty()){
                    Toast.makeText(getApplicationContext(),"homeless",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:"+address));
                startActivity(Intent.createChooser(emailIntent, "Hello "));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }
    private void fbclass() {

        root.child("users").child(id).child("facebook").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fbid= Objects.requireNonNull(snapshot.getValue()).toString();
                if(fbid.isEmpty()){
                    Toast.makeText(getApplicationContext(),"woah...founf darkmatter........., but where's fb",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = "https://www.facebook.com/"+fbid;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }
    private void liclass() {

        root.child("users").child(id).child("linkedin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String url =  Objects.requireNonNull(snapshot.getValue()).toString();
                if(url.isEmpty()){
                    Toast.makeText(getApplicationContext(),"full of emptyness...",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }


    private void ytclass() {

        root.child("users").child(id).child("youtube").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String url = Objects.requireNonNull(snapshot.getValue()).toString();
                if(url.isEmpty()){
                    Toast.makeText(getApplicationContext(),"no youtube 4 u",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }


    private void twclass() {

        root.child("users").child(id).child("twitter").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String usrid= Objects.requireNonNull(snapshot.getValue()).toString();
                if(usrid.isEmpty()){
                    Toast.makeText(getApplicationContext(),"twitttterrrrr....nooo",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = "https://twitter.com/"+usrid;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void waclass() {

        root.child("users").child(id).child("whatsapp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String number= Objects.requireNonNull(snapshot.getValue()).toString();
                if(number.isEmpty()){
                    Toast.makeText(getApplicationContext(),"emptyness 4 u....",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = "https://api.whatsapp.com/send?phone=+91"+number;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void igclass()
    {

        root.child("users").child(id).child("instagram").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String instaid= Objects.requireNonNull(snapshot.getValue()).toString();
                if(instaid.isEmpty()){
                    Toast.makeText(getApplicationContext(),"full of nothing....  :)",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = "https://www.instagram.com/"+instaid;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }
    private void snapclass()
    {

        root.child("users").child(id).child("snapchat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String snapid= Objects.requireNonNull(snapshot.getValue()).toString();
                if(snapid.isEmpty()){
                    Toast.makeText(getApplicationContext(),"woow,...such an empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = "https://www.snapchat.com/add/"+snapid;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });

    }
   private void mapclass()
    {

        root.child("users").child(id).child("longitude").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lon= Objects.requireNonNull(snapshot.getValue()).toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });
        root.child("users").child(id).child("latitude").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lat= Objects.requireNonNull(snapshot.getValue()).toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
            }

        });
        if(lat.isEmpty() || lon.isEmpty()){
            Toast.makeText(getApplicationContext(),"no map 4 u today....duhh",Toast.LENGTH_SHORT).show();
            return;
        }
        String url = "https://www.google.com/maps/search/?api=1&query="+lon+"%2C"+lat;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}