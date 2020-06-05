package com.example.myfirstapplogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfirstapplogin.user.User;
import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;

public class AuthFragment extends Fragment {

    private EditText etEmail;
    private EditText etPassword;

    private Button mEnter;
    private Button mRegister;
    private SharedPreferenceHelper sharedPreferenceHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ac_auto, container, false);

        sharedPreferenceHelper = new SharedPreferenceHelper(getActivity());


        etEmail = view.findViewById(R.id.etLogin);
        etPassword = view.findViewById(R.id.etPassword);

        mEnter = view.findViewById(R.id.buttonEnter);
        mRegister = view.findViewById(R.id.buttonRegister);

        mEnter.setOnClickListener(mOnEnterClickListener);
        mRegister.setOnClickListener(mOnRegisterClickListener);

        return view;
    }

    private View.OnClickListener mOnEnterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean checkEnter = false;
            if(checkLogin() && checkPassword()) {

                    List<User> users = sharedPreferenceHelper.getUsers();
                    for(User u : users) {
                        if(u.getEmail().equalsIgnoreCase(etEmail.getText().toString())
                        && u.getPassword().equals(etPassword.getText().toString())) {
                            Intent startPorfileIntent = new Intent(getActivity(), ProfileActivity.class);
                            startPorfileIntent.putExtra(ProfileActivity.EXTRA_USER_KEY,
                                    new User(etEmail.getText().toString(), etPassword.getText().toString()));
                            startActivity(startPorfileIntent);
                            checkEnter = true;
                            Objects.requireNonNull(getActivity()).finish();
                            break;
                        }
                    }

                    if(!checkEnter) showMessage(R.string.error_Login_Password);
                    else showMessage(R.string.red_succcessfylly);

            } else {
                showMessage(R.string.messageError);
            }
        }
    };



    static AuthFragment newInstance() {

        Bundle args = new Bundle();

        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private View.OnClickListener mOnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager;
            fragmentManager = getFragmentManager();
            assert fragmentManager != null;
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new RegistrationFragment())
                    .addToBackStack(null)
                    .commit();
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
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }

}
