package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnsignup,btnlogin;
    private EditText edtemaillogin, edtpasswordlogin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnsignup = findViewById(R.id.btnsignuponloginpage);
        btnlogin = findViewById(R.id.btnloginonloginpage);

        edtemaillogin = findViewById(R.id.edtloginemailonloginpage);
        edtpasswordlogin = findViewById(R.id.edtpasswordonloginpage);

        btnsignup.setOnClickListener(loginActivity.this);
        btnlogin.setOnClickListener(loginActivity.this);

        if(ParseUser.getCurrentUser() != null){

            ParseUser.logOut();

        }



    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.btnloginonloginpage:

                if(edtemaillogin.getText().toString().equals("") || edtpasswordlogin.getText().toString().equals("")){
                    FancyToast.makeText(loginActivity.this,"Email and password is required",
                            Toast.LENGTH_SHORT, FancyToast.WARNING,
                            true).show();

                }
                else{

                    ParseUser.logInInBackground(edtemaillogin.getText().toString(),
                            edtpasswordlogin.getText().toString(), new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if(user != null && e == null){
                                        FancyToast.makeText(loginActivity.this, user.getUsername()
                                                        + " has successfully logged in ", Toast.LENGTH_LONG,
                                                FancyToast.INFO, true).show();

                                    }else{

                                        FancyToast.makeText(loginActivity.this, "An error occurred" +"\n"
                                                        + e.getMessage(), Toast.LENGTH_LONG,
                                                FancyToast.ERROR, true).show();
                                    }
                                }
                            });

                }
                break;


            case R.id.btnsignuponloginpage:

                Intent intent = new Intent(loginActivity.this,MainActivity.class);
                startActivity(intent);

                break;
        }
    }
    public void lauoutistappedonloginpagenow(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
