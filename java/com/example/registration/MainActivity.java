package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{


    ImageView imgGallery;
    EditText email,password,confirmpassword;
    Button signupbtn;
   private final int GALLERY_REQ_CODE=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.textView6);
        password = findViewById(R.id.confirm7);
        confirmpassword =findViewById(R.id.confirm8);
        signupbtn = findViewById(R.id.button);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(email.getText().toString()))
                {
                    email.setError("email shouldnt be empty");
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString()))
                {
                    password.setError("password shouldnt be empty");
                    return;
                }
                if(TextUtils.isEmpty(confirmpassword.getText().toString()))
                {
                    confirmpassword.setError("confirm password");
                    if(!password.equals(confirmpassword))
                    {
                        Toast.makeText(MainActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


        imgGallery = findViewById(R.id.imagess);
        imgGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,GALLERY_REQ_CODE);


            }
        });

        Spinner spinner = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.branch, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Location, android.R.layout.simple_spinner_item);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);


        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == GALLERY_REQ_CODE) {
                imgGallery.setImageURI(data.getData());
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


