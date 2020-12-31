package com.example.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.whatsappclone.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CallActivity extends AppCompatActivity {

    private Intent intent;
    private String userid;
    private TextView name, status;
    private ImageView endCall, startCall, callingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        name = findViewById(R.id.name_calling);
        status = findViewById(R.id.status_calling);
        endCall = findViewById(R.id.call_end_calling);
        startCall = findViewById(R.id.call_receive_calling);
        callingImage = findViewById(R.id.calling_image);


        intent = getIntent();
        userid = intent.getStringExtra("userid");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                name.setText(user.getUsername());

                if (user.getImageURI().equals("default")) {
                    callingImage.setImageResource(R.drawable.minon_hitman);
                } else {
                    Glide
                            .with(CallActivity.this)
                            .load(user.getImageURI())
                            .centerCrop()
                            .into(callingImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}