package com.proyek.fahrulrizky;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {
    EditText etEmail,etPassword;
    com.google.android.material.textfield.TextInputLayout lytEmail,lytPassword;
    Button btnLogin;

    SPManager spManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialization();
        spManager = new SPManager(this);
        if (spManager.getSpIsSigned()){
            startActivity(new Intent(this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        buttonHandler();
    }

    private void initialization(){
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        lytEmail = (com.google.android.material.textfield.TextInputLayout) findViewById(R.id.lytEmail);
        lytPassword = (com.google.android.material.textfield.TextInputLayout) findViewById(R.id.lytPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    private void buttonHandler(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    auth();
                }
            }
        });
    }

    private boolean validate(){
        boolean valid = false;
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
            valid = false;
            lytEmail.setError("Masukkan email yang Valid");
        } else {
            valid = true;
            lytEmail.setError(null);
        }

        if (etPassword.getText().toString().isEmpty()) {
            valid = false;
            lytPassword.setError("Masukkan Kata sandi yang Valid");
        } else {
            if (etPassword.length() > 2) {
                valid = true;
                lytPassword.setError(null);
            } else {
                valid = false;
                lytPassword.setError("Kata sandi terlalu pendek");
            }
        }
        return valid;
    }

    private void auth(){
        if (etEmail.getText().toString().equalsIgnoreCase("fahrul@gmail.com")&&etPassword.getText().toString().equalsIgnoreCase("123456")){
            spManager.saveSPBoolean(spManager.SP_IS_SIGNED, true);
            spManager.saveSPString(spManager.SP_NAME, "M FAHRUL RIZKY RIDWAN");
            spManager.saveSPString(spManager.SP_EMAIL, "fahrul@gmail.com");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }else {
            Toast.makeText(this,"Email / Kata Sandi Salah",Toast.LENGTH_SHORT).show();
        }


    }

}
