package com.example.instagram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;

import com.parse.ParseUser;

import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class signup_login extends AppCompatActivity {

    EditText edtusernamesignup, edtpasswordsignup, edtusernamelogin, edtpasswordlogin;
    Button btnsignup, btnlogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup_login);

        edtusernamesignup = findViewById(R.id.edtnewusersignupusername);
        edtpasswordsignup = findViewById(R.id.edtnewusersignuppassword);
        edtusernamelogin = findViewById(R.id.olduserusername);
        edtpasswordlogin= findViewById(R.id.olduserpassword);

        btnsignup = findViewById(R.id.btnsignup);
        btnlogin = findViewById(R.id.btnloginnewuser);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("Tag", "Signup button is tapped now");

                final ParseUser appuser = new ParseUser();
                appuser.setUsername(edtusernamesignup.getText().toString());
                appuser.setPassword(edtpasswordsignup.getText().toString());

                appuser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            FancyToast.makeText(signup_login.this,
                                            appuser.get("username") + " has successfully registered",
                                    FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

                            Intent intent = new Intent(signup_login.this,WelcomeActivity.class);
                            startActivity(intent);

                        }
                        else {
                            FancyToast.makeText(signup_login.this, e.getMessage(),
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("Tag", "Login button is tapped now");

                ParseUser.logInInBackground(edtusernamelogin.getText().toString(), edtpasswordlogin.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {

                                if(user != null && e == null){



                                    FancyToast.makeText(signup_login.this,user.get("username")
                                                    + " has logged in successfully",
                                            FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

                                    Intent intent = new Intent(signup_login.this,WelcomeActivity.class);
                                    startActivity(intent);

                                }
                                else{
                                    FancyToast.makeText(signup_login.this, e.getMessage(),
                                            FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                                }
                            }
                        });

            }
        });

    }
}
