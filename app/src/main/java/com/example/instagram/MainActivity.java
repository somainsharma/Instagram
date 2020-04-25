package com.example.instagram;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtemail,edtusername,edtpassword;
    private Button btnsingup, btnlogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Sign up");

        edtemail = findViewById(R.id.edtemailsignup);
        edtusername = findViewById(R.id.edtusernamesignup);
        edtpassword = findViewById(R.id.edtpasswordsignup);

//        edtpassword.setOnKeyListener(new View.OnKeyListener(){
//
//            public Boolean onKey(View view, int Keycode , KeyEvent event){
//
//                if((Keycode == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN)){
//                    onClick(btnsingup);
//                }
//                return false;
//            }
//
//
//        });

        edtpassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnsingup);

                }
                return false;
            }
        });

        btnsingup = findViewById(R.id.singupbutton);
        btnlogin = findViewById(R.id.loginbutton);

        btnsingup.setOnClickListener(MainActivity.this);
        btnlogin.setOnClickListener(MainActivity.this);

        if(ParseUser.getCurrentUser() != null){

          //  ParseUser.getCurrentUser().logOut();
            transitionofActivity();

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.singupbutton:

                if(edtusername.getText().toString().equals("") ||
                        edtpassword.getText().toString().equals("") ||
                        edtemail.getText().toString().equals(""))
                {
                    FancyToast.makeText(MainActivity.this,"Email, username, password is required ",
                            Toast.LENGTH_SHORT, FancyToast.WARNING,
                            true).show();
                }
                else
                    {
                    final ParseUser appuser = new ParseUser();
                    appuser.setEmail(edtemail.getText().toString());
                    appuser.setUsername(edtusername.getText().toString());
                    appuser.setPassword(edtpassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Signing in the user: " + edtusername.getText());
                    progressDialog.show();

                    appuser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {

                                //Also try appuser.getusername in the message section of the toast

                                FancyToast.makeText(MainActivity.this, appuser.get("username") +
                                                " has signed up successfully", Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                                        true).show();
                                transitionofActivity();

                            } else {

                                FancyToast.makeText(MainActivity.this, "An error occurred" + "\n"
                                                + e.getMessage(), Toast.LENGTH_LONG,
                                        FancyToast.ERROR, true).show();

                            }

                            progressDialog.dismiss();

                        }

                    });
                }

             break;

            case R.id.loginbutton:

//                Log.i("TAG","btn login is working now ");


                Intent intent = new Intent(MainActivity.this,loginActivity.class);
                 startActivity(intent);

             break;
        }
    }

    public void emptylayoutapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void transitionofActivity(){
        Intent intent = new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}








