package com.example.instagram;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("jQBL4WnnHIDmMIRW2Hrseu5YHIqEiVlReMdFb1xx")
                // if defined
                .clientKey("CCcugMpzomoxr8XB2mjSMLd0CdptPdVfj1IRPoY9")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
