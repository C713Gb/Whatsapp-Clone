package com.example.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    private static int WELCOME_TIMEOUT = 4000;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null && !mAuth.getCurrentUser().toString().contains("com.google.firebase.auth.internal.zzp")) {
            Toast.makeText(this, mAuth.getCurrentUser().toString(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(StartActivity.this, MainActivity.class));
            finish();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mAuth.getCurrentUser() != null) {
                    Intent welcome = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(welcome);
                    finish();
                } else {
                    Intent welcome = new Intent(StartActivity.this, RegisterActivity.class);
                    startActivity(welcome);
                    finish();
                }


            }
        }, WELCOME_TIMEOUT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }
}