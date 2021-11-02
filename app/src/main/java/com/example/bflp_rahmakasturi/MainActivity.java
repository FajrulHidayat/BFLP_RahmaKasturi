package com.example.bflp_rahmakasturi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout btnSch = findViewById(R.id.btnsch);
        LinearLayout btnInfo = findViewById(R.id.btninfo);
        LinearLayout btnMap = findViewById(R.id.btnmap);
        Button btnlogin = findViewById(R.id.btnlogin);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, loginActivity.class);
                startActivity(i);
                                        }
        });
        btnSch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,JadwalActivity.class);
                startActivity(i);
            }
        });
        btnInfo.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,InfoActivity.class);
            startActivity(i);
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,PetaActivity.class);
                startActivity(i);
            }
        });

    }
}