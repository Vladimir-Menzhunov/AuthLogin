package com.example.myfirstapplogin;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;

    private Button mEnter;
    private Button mRegister;

    private View.OnClickListener mOnEnterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(checkLogin() && checkPassword()) {
                Intent  startPorfileIntent = new Intent(MainActivity.this, ProfileActivity.class);

                startPorfileIntent.putExtra(ProfileActivity.EXTRA_EMAIL, etEmail.getText().toString());
                startPorfileIntent.putExtra(ProfileActivity.EXTRA_PASSWORD, etPassword.getText().toString());

                startActivity(startPorfileIntent);
            } else {
             showMessage(R.string.messageError);
            }
        }
    };

    private View.OnClickListener mOnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //todo
        }
    };

    private boolean checkLogin() {
        return !TextUtils.isEmpty(etEmail.getText())
                && Patterns.EMAIL_ADDRESS.matcher(etEmail.getText()).matches();
    }

    private boolean checkPassword() {
        return !TextUtils.isEmpty(etPassword.getText());
    }

    private void showMessage(@StringRes int string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_auto);

        etEmail = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);

        mEnter = findViewById(R.id.buttonEnter);
        mRegister = findViewById(R.id.buttonRegister);

        mEnter.setOnClickListener(mOnEnterClickListener);
        mRegister.setOnClickListener(mOnRegisterClickListener);

    }
}
