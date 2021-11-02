package com.example.bflp_rahmakasturi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class regisActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        mAuth = FirebaseAuth.getInstance();
        TextView login = findViewById(R.id.tvlogin);
        EditText etnama = findViewById(R.id.etnama);
        EditText etusername = findViewById(R.id.etusername);
        EditText etpassword = findViewById(R.id.etpassword);
        Button registrasi = findViewById(R.id.btndaftar);
        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nama = etnama.getText().toString().trim();
                    String username = etusername.getText().toString().trim();
                    String password = etpassword.getText().toString().trim();
                    Log.i("TAG", "onClick: "+username+" "+password);
                    mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(nama, username);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.i("TAG", "Pendaftaran berhasil");
                                            Toast.makeText(regisActivity.this, "Pendaftaran berhasil", Toast.LENGTH_LONG).show();
                                        } else {
                                            Log.i("TAG", "Pendaftaran gagal");
                                            Log.i("TAG", task.getException().getMessage());
                                            Toast.makeText(regisActivity.this, "Pendaftaran gagal", Toast.LENGTH_LONG).show();
                                        }
                                    }


                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("TAG", "onFailure: "+e.getMessage());
                                    }
                                });
                            } else {
                                Log.i("TAG asd", "Pendaftaran gagal");
                                Log.i("TAG", task.getException().getMessage());
                                Toast.makeText(regisActivity.this, "Pendaftaran gagal", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(regisActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    Log.e("TAG", "onClick: ", e);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(regisActivity.this,loginActivity.class);
                startActivity(i);
            }
        });
    }
}