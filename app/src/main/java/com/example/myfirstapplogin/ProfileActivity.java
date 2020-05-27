package com.example.myfirstapplogin;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class ProfileActivity extends AppCompatActivity {


    public static String EXTRA_EMAIL = "EXTRA_EMAIL";
    public static String EXTRA_PASSWORD = "EXTRA_PASSWORD";

    private Bundle extraBundle;

    private AppCompatImageView image;

    private TextView tEmail;
    private TextView tPassword;


    private View.OnClickListener onClickListenerImage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //todo
        }
    };


    @Override   
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_profile);
        
        tEmail = findViewById(R.id.textViewChangeEmail);
        tPassword = findViewById(R.id.textViewChangePassword);
        image = findViewById(R.id.imageView);

        extraBundle = getIntent().getExtras();

        tEmail.setText(extraBundle.getString(EXTRA_EMAIL));
        tPassword.setText(extraBundle.getString(EXTRA_PASSWORD));


        image.setOnClickListener(onClickListenerImage);
    }
}
