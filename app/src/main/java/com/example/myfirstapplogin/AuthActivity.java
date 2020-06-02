package com.example.myfirstapplogin;

import androidx.fragment.app.Fragment;

import com.example.myfirstapplogin.AuthFragment;

public class AuthActivity extends SingleFragment {
    @Override
    protected Fragment getFragment() {
        return AuthFragment.newInstance();
    }
}
