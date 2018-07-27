package com.blogbasbas.fortraining.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.blogbasbas.fortraining.ui.LoginActivity;
import com.blogbasbas.fortraining.ui.MainActivity;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int mode = 0;

    private static final String pref_name = "crudpref";
    private static final String is_login = "islogin";
    public static final String kunci_username = "keyemail";

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    public void createSession(String email){
        editor.putBoolean(is_login, true);
        editor.putString(kunci_username, email);
        editor.commit();
    }

    public void checkLogin(){
        if (!this.is_login()){
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    private boolean is_login() {
        return pref.getBoolean(is_login, false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(pref_name, pref.getString(pref_name, null));
        user.put(kunci_username, pref.getString(kunci_username, null));
        return user;
    }

}