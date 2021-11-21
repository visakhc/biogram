package com.biogram;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class signup extends AppCompatActivity {
    CircleImageView SELECT_PHOTO;
    TextInputEditText name,email;
    TextView DOB;
    Button button3,save;
    ImageView img;
    String na,em;
    int SELECT_PICTURE = 200;
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://biogram-63868-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference root=db.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        SELECT_PHOTO=findViewById(R.id.SELECT_PHOTO);
        img=findViewById(R.id.imageView);


        name=findViewById(R.id.nam);
        email=findViewById(R.id.mail);

        DOB=findViewById(R.id.DOB);
        button3=findViewById(R.id.button3);




        MaterialDatePicker datePicker=MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getSupportFragmentManager(),"Material_Date_Picker");
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        DOB.setText(datePicker.getHeaderText());
                    }
                });
            }
        });

        SELECT_PHOTO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();

            }
        });

    }

    private void imageChooser() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, SELECT_PICTURE);

        //startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    img.setImageURI(selectedImageUri);

                }
            }
        }
    }

}