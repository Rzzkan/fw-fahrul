package com.proyek.fahrulrizky;

import android.content.Context;
import android.content.SharedPreferences;

public class SPManager {
    public static final String SP_LOGIN_APP = "spLoginApp";
    public static final String SP_NAME = "spName";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_IS_SIGNED = "spSignedIn";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SPManager(Context context){
        sp = context.getSharedPreferences(SP_LOGIN_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.apply();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.apply();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.apply();
    }

    public void removeSP(){
        spEditor.remove(SP_EMAIL);
        spEditor.remove(SP_NAME);
        spEditor.apply();
    }

    public String getSpName(){
        return sp.getString(SP_NAME, "User");
    }

    public String getSpEmail(){
        return sp.getString(SP_EMAIL, "User");
    }


    public Boolean getSpIsSigned(){
        return sp.getBoolean(SP_IS_SIGNED, false);
    }

}
