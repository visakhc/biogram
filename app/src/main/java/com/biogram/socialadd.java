package com.biogram;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class socialadd extends AppCompatActivity {
    private  DatabaseReference root;

    LinearLayout wa, ig, snap, fb, yt, tw, li, tk;
    ImageView i1,i2,i3,i4,i5,i6,i7,i8;
    EditText ed1,ed2, ed3, ed4, ed5, ed6, ed7, ed8;
    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8;
    String phonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socialadd);

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app");
        root= db.getReference().child("root");


        SharedPreferences sh = getSharedPreferences("biogram",MODE_PRIVATE);
        phonenum = sh.getString("name", "");
        phonenum="8075922523";
if(phonenum.isEmpty()) {
    //finish();
}


        //all editTexts
        ed1 = findViewById(R.id.e1);
        ed2=findViewById(R.id.e2);
        ed3=findViewById(R.id.e3);
        ed4=findViewById(R.id.e4);
        ed5=findViewById(R.id.e5);
        ed6=findViewById(R.id.e6);
        ed7=findViewById(R.id.e7);
        ed8=findViewById(R.id.e8);

        //all buttons
        bt1 = findViewById(R.id.b1);
        bt2 = findViewById(R.id.b2);
        bt3 = findViewById(R.id.b3);
        bt4 = findViewById(R.id.b4);
        bt5 = findViewById(R.id.b5);
        bt6 = findViewById(R.id.b6);
        bt7 = findViewById(R.id.b7);
        bt8 = findViewById(R.id.b8);

        //all imageviews
        i1 = findViewById(R.id.i1);
        i2 = findViewById(R.id.i2);
        i3 = findViewById(R.id.i3);
        i4 = findViewById(R.id.i4);
        i5 = findViewById(R.id.i5);
        i6 = findViewById(R.id.i6);
        i7 = findViewById(R.id.i7);
        i8 = findViewById(R.id.i8);


//all layers
        wa = findViewById(R.id.watspp);
        ig = findViewById(R.id.ig);
        fb = findViewById(R.id.fb);
        yt = findViewById(R.id.yt);
        tw = findViewById(R.id.tw);
        li = findViewById(R.id.li);
        tk = findViewById(R.id.tk);
        snap = findViewById(R.id.snap);


//layer inflations
        wa.setOnClickListener(v -> waclass());
        ig.setOnClickListener(v -> igclass());
        snap.setOnClickListener(v -> snapclass());
        li.setOnClickListener(v -> liclass());
        yt.setOnClickListener(v -> ytclass());
        tw.setOnClickListener(v -> twclass());
        tk.setOnClickListener(v -> tkclass());
        fb.setOnClickListener(v -> fbclass());

//all save button functions
        bt1.setOnClickListener(v -> sv1());
        bt2.setOnClickListener(v -> sv2());
        bt3.setOnClickListener(v -> sv3());
        bt4.setOnClickListener(v -> sv4());
        bt5.setOnClickListener(v -> sv5());
        bt6.setOnClickListener(v -> sv6());
        bt7.setOnClickListener(v -> sv7());
        bt8.setOnClickListener(v -> sv8());






//all buttons to save pops up here

        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed1.length()!=0)
                    bt1.setVisibility(View.VISIBLE);
                else
                    bt1.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ed2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed2.length()!=0)
                    bt2.setVisibility(View.VISIBLE);
                else
                    bt2.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ed3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed3.length()!=0)
                    bt3.setVisibility(View.VISIBLE);
                else
                    bt3.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ed4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed4.length()!=0)
                    bt4.setVisibility(View.VISIBLE);
                else
                    bt4.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ed5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed5.length()!=0)
                    bt5.setVisibility(View.VISIBLE);
                else
                    bt5.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ed6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed6.length()!=0)
                    bt6.setVisibility(View.VISIBLE);
                else
                    bt6.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ed6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed6.length()!=0)
                    bt6.setVisibility(View.VISIBLE);
                else
                    bt6.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ed7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed7.length()!=0)
                    bt7.setVisibility(View.VISIBLE);
                else
                    bt7.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ed8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed8.length()!=0)
                    bt8.setVisibility(View.VISIBLE);
                else
                    bt8.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }







    private void sv1() {

        String s=ed1.getText().toString();
      //  s=s.replace("+91","");

        root.child("users").child(phonenum).child("whatsapp").setValue(s);
        root.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();
                bt1.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();


            }
        });
    }



    private void sv2() {
        String s=ed2.getText().toString();
        root.child("users").child(phonenum).child("instagram").setValue(s);
        root.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();
                bt2.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();

            }
        });
    }





    private void sv3() {
        String s=ed3.getText().toString();
        root.child("users").child(phonenum).child("facebook").setValue(s);
        root.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();
                bt3.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void sv4() {
        String s=ed4.getText().toString();
        root.child("users").child(phonenum).child("snapchat").setValue(s);
        root.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();
                bt4.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void sv5() {
        String s=ed5.getText().toString();
        root.child("users").child(phonenum).child("youtube").setValue(s);
        root.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();
                bt5.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();

            }
        });
    }



    private void sv6() {
        String s=ed6.getText().toString();
        root.child("users").child(phonenum).child("linkedin").setValue(s);
        root.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();
                bt6.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();

            }
        });
    }



    private void sv7() {
        String s=ed7.getText().toString();
        root.child("users").child(phonenum).child("twitter").setValue(s);
        root.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();
                bt7.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();

            }
        });
    }



    private void sv8() {
        String s=ed8.getText().toString();
        root.child("users").child(phonenum).child("tiktok").setValue(s);
        root.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();
                bt8.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();

            }
        });
    }





    private void tkclass() {
        i8.setVisibility(View.GONE);
        ed8.setVisibility(View.VISIBLE);
        bt8.setVisibility(View.GONE);
    }
    private void fbclass() {
        i3.setVisibility(View.GONE);
        ed3.setVisibility(View.VISIBLE);
        bt3.setVisibility(View.GONE);
    }

    private void liclass() {
        i6.setVisibility(View.GONE);
        ed6.setVisibility(View.VISIBLE);
        bt6.setVisibility(View.GONE);
    }

    private void ytclass() {
        i5.setVisibility(View.GONE);
        ed5.setVisibility(View.VISIBLE);
        bt5.setVisibility(View.GONE);
    }

    private void twclass() {
        i7.setVisibility(View.GONE);
        ed7.setVisibility(View.VISIBLE);
        bt7.setVisibility(View.GONE);
    }

    private void waclass() {
        i1.setVisibility(View.GONE);
        ed1.setVisibility(View.VISIBLE);
        bt1.setVisibility(View.GONE);
    }

    private void igclass() {
        i2.setVisibility(View.GONE);
        ed2.setVisibility(View.VISIBLE);
        bt2.setVisibility(View.GONE);
    }

    private void snapclass() {
        i4.setVisibility(View.GONE);
        ed4.setVisibility(View.VISIBLE);
        bt4.setVisibility(View.GONE);
    }

}
