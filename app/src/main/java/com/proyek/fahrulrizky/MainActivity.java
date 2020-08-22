package com.proyek.fahrulrizky;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.proyek.fahrulrizky.adapter.PresensiModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    SPManager spManager;
    Button btnPresensi, btnList, btnLogout;
    SQLiteDBHelper dbHelper;
    boolean insertData;
    SimpleDateFormat formatDate;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        clickListener();
    }

    private void initialization(){
        getSupportActionBar().setTitle("Presensi");
        dbHelper = new SQLiteDBHelper(this);
        spManager = new SPManager(this);
        btnPresensi = findViewById(R.id.btnPresensi);
        btnList = findViewById(R.id.btnList);
        btnLogout = findViewById(R.id.btnLogout);
        formatDate = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss");
    }

    private void logoutDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle    ("Keluar");
        builder.setMessage("Apakah Anda Yakin untuk Keluar ?");
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                spManager.saveSPBoolean(spManager.SP_IS_SIGNED, false);
                spManager.removeSP();
                startActivity(new Intent(MainActivity.this, Login.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void clickListener(){
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dbHelper.getCount()==0){
                    Toast.makeText(getApplicationContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(MainActivity.this, ListActivity.class));
                }
            }
        });

        btnPresensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = new Date();
                int counter = dbHelper.getCount();
                if (counter==0){
                    insertData = dbHelper.addData(new PresensiModel(1,spManager.getSpName(), formatDate.format(date)));
                }else {
                    insertData = dbHelper.addData(new PresensiModel(counter+1,spManager.getSpName(), formatDate.format(date)));
                }
                Toast.makeText(getApplicationContext(), "Berhasil Presensi", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }else if(item.getItemId()== R.id.profile){
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        }else{
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}