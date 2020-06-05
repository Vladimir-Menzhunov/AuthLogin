package com.example.myfirstapplogin;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myfirstapplogin.user.User;

import java.util.Objects;

public class RegistrationFragment extends Fragment {
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPasswordAgain;

    private Button bReg;

    private SharedPreferenceHelper sharedPreferenceHelper;

    private View.OnClickListener onClickListenerReg = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isValid()) {
                User user = new User(
                        etEmail.getText().toString(),
                        etPassword.getText().toString()
                );

                if (sharedPreferenceHelper.addUser(user)) {
                    showMessage(R.string.red_succcessfylly);
                    assert getFragmentManager() != null;
                    getFragmentManager().popBackStack();
                } else {
                    showMessage(R.string.reg_error);
                }


            }
        }
    };

    private boolean isValid() {

        return emailIsValid() && passwordIsValid();
    }

    private boolean emailIsValid() {
        return !TextUtils.isEmpty(etEmail.getText())
                && Patterns.EMAIL_ADDRESS.matcher(etEmail.getText()).matches();
    }

    private boolean passwordIsValid() {
        return !TextUtils.isEmpty(etPassword.getText())
                && !TextUtils.isEmpty(etPasswordAgain.getText())
                && etPassword.getText().toString().equals(etPasswordAgain.getText().toString());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ac_reg, container, false);

        sharedPreferenceHelper = new SharedPreferenceHelper(getActivity());

        etEmail = view.findViewById(R.id.etEmailReg);
        etPassword = view.findViewById(R.id.etPasswordReg);
        etPasswordAgain = view.findViewById(R.id.etPasswordAgainReg);

        bReg = view.findViewById(R.id.bReg);

        bReg.setOnClickListener(onClickListenerReg);

        return view;
    }

    private void showMessage(@StringRes int string){
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }



}
