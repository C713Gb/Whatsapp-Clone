package com.example.whatsappclone.UserProfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.whatsappclone.Models.User;
import com.example.whatsappclone.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        AppBarLayout appBarLayout = findViewById(R.id.appBarEditProfile);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarEditProfile);
        Toolbar toolbar = findViewById(R.id.editProfileNameToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final ImageView profile= findViewById(R.id.editProfileImage);
        final ImageView edit = findViewById(R.id.editImage);
        final TextView phone = findViewById(R.id.editProfilePhoneNumber);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (Math.abs(i) == appBarLayout.getTotalScrollRange()) {
                    // Collapsed
                    edit.setVisibility(View.VISIBLE);
                } else if (i == 0) {
                    // Expanded
                    edit.setVisibility(View.VISIBLE);

                } else {
                    // Somewhere in between

                }
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, EditActivity2.class));
            }
        });

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                phone.setText(user.getPhone());
                getSupportActionBar().setTitle(user.getUsername());

                if (user.getImageURI().equals("default")) {
                    profile.setImageResource(R.drawable.minon_hitman);
                } else {
                    Glide
                            .with(getApplicationContext())
                            .load(user.getImageURI())
                            .into(profile);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}