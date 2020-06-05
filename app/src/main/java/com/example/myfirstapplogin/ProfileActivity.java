package com.example.myfirstapplogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.profile_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {
            case R.id.logout:
                startActivity(new Intent(this, AuthActivity.class));
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
