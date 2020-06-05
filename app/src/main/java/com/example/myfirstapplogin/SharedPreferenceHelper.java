package com.example.myfirstapplogin;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.myfirstapplogin.user.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class SharedPreferenceHelper {

    private static final String NAME_SHARED_PREF = "NAME_SHARED_PREF";
    private static final String USER_KEY = "USER_KEY";

    private static final Type USER_TYPE = new TypeToken<List<User>>(){
    }.getType();

    private SharedPreferences sharedPreferences;

    private Gson gson;



    SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME_SHARED_PREF, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    List<User> getUsers() {
        List<User> users = gson.fromJson(sharedPreferences.getString(USER_KEY, ""), USER_TYPE);


        return users == null ? new ArrayList<User>() : users;
    }

    boolean addUser(User user) {
        List<User> listUser = getUsers();

        for (User u : listUser) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) return false;
        }
        listUser.add(user);
        sharedPreferences.edit().putString(USER_KEY,gson.toJson(listUser, USER_TYPE)).apply();

        return true;
    }

}
