package com.example.myfirstapplogin;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.myfirstapplogin.user.User;

public class ProfileActivity extends AppCompatActivity {


    public static String EXTRA_USER_KEY = "EXTRA_USER_KEY";
    public static int REQUESTCODE_GET_PHOTO = 77;

    private Bundle extraBundle;

    private AppCompatImageView myImage;

    private TextView tEmail;
    private TextView tPassword;


    private View.OnClickListener onClickListenerImage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openGallery();
        }
    };

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUESTCODE_GET_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(REQUESTCODE_GET_PHOTO == requestCode
            && resultCode == Activity.RESULT_OK
            && data != null) {
            Uri img = data.getData();
            myImage.setImageURI(img);
        } else super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_profile);
        
        tEmail = findViewById(R.id.textViewChangeEmail);
        tPassword = findViewById(R.id.textViewChangePassword);
        myImage = findViewById(R.id.imageView);

        extraBundle = getIntent().getExtras();

        User user = (User) extraBundle.get(EXTRA_USER_KEY);

        assert user != null;
        tEmail.setText(user.getEmail());

        tPassword.setText(user.getPassword());


        myImage.setOnClickListener(onClickListenerImage);
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
