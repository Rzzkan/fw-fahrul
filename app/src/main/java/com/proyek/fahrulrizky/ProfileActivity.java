package com.proyek.fahrulrizky;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {
    com.mikhaellopez.circularimageview.CircularImageView imgProfile;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initaialization();
    }

    private void initaialization(){
        getSupportActionBar().setTitle("Profil");
        imgProfile =  findViewById(R.id.imgProfile);
        Glide.with(this)
                .load("")
                .into(imgProfile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}