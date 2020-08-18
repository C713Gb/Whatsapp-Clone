package com.example.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, phone, otp;
    private Button submit, verify;
    private String str_phone = null, str_name = null, str_otp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterdetails();
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyotp();
            }
        });

    }

    private void verifyotp() {
    }

    private void enterdetails() {
        boolean validate = validatedata();
    }

    private boolean validatedata() {
        str_name = name.getText().toString();
        str_phone = phone.getText().toString();
        return true;
    }

    private void init() {
        name = findViewById(R.id.nameTxt);
        phone = findViewById(R.id.phoneTxt);
        otp = findViewById(R.id.otpTxt);
        verify = findViewById(R.id.verifyBtn);
        submit = findViewById(R.id.submitBtn);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();

    }
}