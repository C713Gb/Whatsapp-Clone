package com.example.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.whatsappclone.Adapters.PagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.viewPager);
        final TabLayout tabLayout = findViewById(R.id.tabLayout);
        floatingActionButton = findViewById(R.id.Fab);

        setUpHomeViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = tabLayout.getSelectedTabPosition();
                switch (pos){
                    case 0:
                        startActivity(new Intent(MainActivity.this, SelectContactActivity.class));
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "Status", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Call", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.contact_icon));
                        break;
                    case 1:
                        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.camera_icon));
                        break;
                    case 2:
                        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.call_icon));
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void setUpHomeViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new ChatFragment(), "CHATS");
        adapter.addFragment(new StatusFragment(), "STATUS");
        adapter.addFragment(new CallFragment(), "CALLS");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_right_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        mAuth.signOut();
        finish();
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}