package com.example.myfirstapplogin.user;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String password;
    private boolean isAuth;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        isAuth = false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }
}
