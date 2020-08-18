package com.example.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, phone, otp;
    private Button submit, verify;
    private String str_phone = null, str_name = null, str_otp = null, codeSent = null;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

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
        str_otp = otp.getText().toString();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, str_otp);

        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void enterdetails() {
        boolean validate = validatedata();
        if (validate) {
            sendVerificationCode();
        }
    }

    private void sendVerificationCode() {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                str_phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            Toast.makeText(RegisterActivity.this, "Verification completed!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            Toast.makeText(RegisterActivity.this, "Verification code sent to mobile number!", Toast.LENGTH_SHORT).show();
            codeSent = s;
        }
    };

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