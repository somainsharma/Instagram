package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class SocialMediaActivity extends AppCompatActivity {

    //There could be some error here as tutorial has imported some other libraries for Toolbar see video at
   // 33:45 minutes lecture89 of instagram clone

    private Toolbar toolbar;
    private ViewPager viewpager;
    private TabLayout tablayout;
    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        setTitle("Social Media App");

        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        viewpager = findViewById(R.id.viewPager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewpager.setAdapter(tabAdapter);

        tablayout = findViewById(R.id.tablayout);
        tablayout.setupWithViewPager(viewpager,true);



    }
}
