package com.example.instagram;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    private TextView txt1, txt2,txt3;
    private TextView fromserver ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView2);
        txt3 = findViewById(R.id.textView3);

        fromserver = findViewById(R.id.textView4);

        fromserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("FA9DHr4Bkw", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(e==null && object != null){
                            fromserver.setText(object.get("punch_speed") + " " );
                        }else{
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



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

