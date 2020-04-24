package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class WelcomeActivity extends AppCompatActivity {

    private TextView txtWelcome;
    private Button btnlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtWelcome = findViewById(R.id.txtwelcome);
        btnlogout = findViewById(R.id.btnlogout);

        txtWelcome.setText("Welcome "+ ParseUser.getCurrentUser().get("username"));

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(WelcomeActivity.this,signup_login.class);
//                startActivity(intent);

                    ParseUser.logOut();
                    finish();

//                Toast.makeText(WelcomeActivity.this, "User session has been logged out",
//                        Toast.LENGTH_SHORT).show();
//
//                FancyToast.makeText(WelcomeActivity.this,
//                        ParseUser.getCurrentUser().get("username").toString() +
//                        " has successfully logged out of the session",
//                       FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

            }
        });

    }
}
