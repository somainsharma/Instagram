package com.example.instagram;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    private TextView txt1, txt2,txt3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView2);
        txt3 = findViewById(R.id.textView3);



    }

    public void HelloWorldIsTapped(View view){


   

        ParseObject kickboxer = new ParseObject("KickBoxer");
        kickboxer.put("kickspeed",txt1.toString());
        kickboxer.put("punchpower",txt2.toString());
        kickboxer.put("kickpower",txt3.toString());
        kickboxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Toast.makeText(MainActivity.this,"Bravo",Toast.LENGTH_SHORT).show();
            }
        });


    }

    }

