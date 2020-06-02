package com.example.myfirstapplogin;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.myfirstapplogin.user.User;

public class ProfileActivity extends AppCompatActivity {


    public static String EXTRA_USER_KEY = "EXTRA_USER_KEY";

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

        User user = (User) extraBundle.get(EXTRA_USER_KEY);

        assert user != null;
        tEmail.setText(user.getEmail());

        tPassword.setText(user.getPassword());


        image.setOnClickListener(onClickListenerImage);
    }
}
