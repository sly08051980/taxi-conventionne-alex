package com.slyfly1.taxiconventionne77.login;

import android.app.Application;
import android.content.Intent;

import com.slyfly1.taxiconventionne77.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        if (firebaseUser !=null){


            Intent i = new Intent(Home.this, MainActivity.class);
            i.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK); startActivity (i);
        }
    }
}
