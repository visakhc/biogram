package com.biogram;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class login extends AppCompatActivity {

    //if otp code fail, use this to resend
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private static final String TAG="MAIN_TAG";
    private FirebaseAuth firebaseAuth;
    public DatabaseReference root;
    //progress bar dialog
    private ProgressDialog pd;


    public String phone;

    EditText ed1,ed2;
    Button bt1, bt2,bt3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       FirebaseDatabase db = FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app");
    root = db.getReference();

        //all editTexts
        ed1 = findViewById(R.id.phonenum);
        ed2=findViewById(R.id.otp);


        //all buttons
        bt1 = findViewById(R.id.verify);
        bt2 = findViewById(R.id.submit);
        bt3 = findViewById(R.id.resend);

        // phone=ed1.getText().toString();


        // authentic section
        firebaseAuth= FirebaseAuth.getInstance();

        pd = new ProgressDialog(this);
        pd.setTitle("please wait...");
        pd.setCanceledOnTouchOutside(false);


        SharedPreferences sh = getSharedPreferences("biogram",MODE_PRIVATE);
        String s1 = sh.getString("phone", "");
        Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();



        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                pd.dismiss();
                Toast.makeText(login.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent(verificationId, forceResendingToken);
                Log.d(TAG, "onCodeSent: "+verificationId);

                mVerificationId=verificationId;
                forceResendingToken = token;
                pd.dismiss();
                ed1.setVisibility(View.GONE);
                bt1.setVisibility(View.GONE);
                ed2.setVisibility(View.VISIBLE);
                bt2.setVisibility(View.VISIBLE);
                bt3.setVisibility(View.VISIBLE);

                Toast.makeText(login.this,"verification code send.....",Toast.LENGTH_SHORT).show();

            }
        };








//all save button functions
        bt1.setOnClickListener(v -> sv1());
        bt2.setOnClickListener(v -> sv2());
        bt3.setOnClickListener(v -> sv3());





//all buttons to save pops up here



        ed2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed2.length()!=0)
                    bt2.setVisibility(View.VISIBLE);
                else
                    bt2.setVisibility(View.INVISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    private void sv1() {

        phone=ed1.getText().toString();
        if(phone.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_LONG).show();
        }
        else{
            startPhoneNumberVerification(phone);
        }
    }


    private void sv2() {
        String code=ed2.getText().toString();
        if(code.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_LONG).show();
        }
        else{
            verifyPhoneNumberWithCode(mVerificationId, code);
        }
    }


    private void sv3() {
        phone=ed1.getText().toString();
        if(phone.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_LONG).show();
        }
        else{
            resendVerificationCode(phone,forceResendingToken);
        }
    }



    private void startPhoneNumberVerification(String phone) {
        pd.setMessage("verifying phone number");
        pd.show();
        PhoneAuthOptions options=
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber("+91"+phone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private void resendVerificationCode(String phone, PhoneAuthProvider.ForceResendingToken token) {
        pd.setMessage("resending code");
        pd.show();
        PhoneAuthOptions options=
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .setForceResendingToken(token)
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        pd.setMessage("verifying code");
        pd.show();
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        pd.setMessage("Logging in");
        pd.show();
        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(authResult -> {
                    // after successful signin
                    pd.dismiss();
                    String phone= Objects.requireNonNull(firebaseAuth.getCurrentUser()).getPhoneNumber();
                    assert phone != null;
                    phone=phone.replace("+91","");

                    //saving user to apps memry after succesfell login
                    SharedPreferences sharedPreferences = getSharedPreferences("biogram",MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("phone",phone);
                    myEdit.apply();

                    String finalPhone = phone;
                    root.child("root").child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(finalPhone)) {
                                //add the intent to the homepage here
                                Toast.makeText(login.this,"user aldeary exist",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                root.child("root").child("users").child(finalPhone).child("id").setValue(finalPhone);
                                //add the intent to the add users name,age,shit here
                                Toast.makeText(login.this,"Logged in as"+finalPhone,Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(login.this,"db error",Toast.LENGTH_LONG).show();
                        }
                    });
                })
                .addOnFailureListener(e -> {
                    //on login failure
                    pd.dismiss();
                    Toast.makeText(login.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                });
    }
}
