package com.slyfly1.taxiconventionne77.login;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.slyfly1.taxiconventionne77.MainActivity;
import com.slyfly1.taxiconventionne77.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
   static final int REQUEST_CODE=123;

    SharedPreferences sharedPreferences1;
    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_NAME="name";
    private static final String KEY_SURNAME="surname";
    Toolbar toolbar;
    ProgressBar progressBar;
    EditText editTextnom;
            EditText userEmail,userPassword;
    Button userLogin;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        editTextnom=findViewById(R.id.edittextnom);
toolbar=findViewById(R.id.toolbar2);
progressBar=findViewById(R.id.progressBar);
userEmail=findViewById(R.id.etUserEmail);
userPassword=findViewById(R.id.etUserPassword);
userLogin=findViewById(R.id.btnUserLogin);

toolbar.setTitle("Login");

        userEmail.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s1) {
            }

            public void beforeTextChanged(CharSequence s1, int start,
                                          int count, int after) {

            }

            public void onTextChanged(CharSequence s1, int start,
                                      int before, int count) {
                String email;
                email=userEmail.getText().toString();
    if (email.equals("taxicyril@free.fr")){
        editTextnom.setText("Cyril");
    }
                if (email.equals("taxialex@free.fr")){
                    editTextnom.setText("Alexandre");
                }


            }
        });

        sharedPreferences1=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String name = sharedPreferences1.getString(KEY_NAME,null);

firebaseAuth= FirebaseAuth.getInstance();

userLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String editnom;
        editnom = editTextnom.getText().toString();
String surnom;
surnom=userEmail.getText().toString();
String alex = "taxialex@free.fr";
String cyril="taxicyril@free.fr";



if (surnom.equals(alex)){



progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(),userPassword.getText()
.toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
     progressBar.setVisibility(View.GONE);
        if(task.isSuccessful()){
            SharedPreferences.Editor editor =sharedPreferences1.edit();
            editor.putString(KEY_NAME,editTextnom.getText().toString());
            editor.putString(KEY_SURNAME,"Cyril");
            editor.apply();

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }else{
            Toast.makeText(LoginActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
});
    }else if (surnom.equals(cyril)){



    progressBar.setVisibility(View.VISIBLE);
    firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(),userPassword.getText()
            .toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            progressBar.setVisibility(View.GONE);
            if(task.isSuccessful()){
                SharedPreferences.Editor editor =sharedPreferences1.edit();
                editor.putString(KEY_NAME,editTextnom.getText().toString());
                editor.putString(KEY_SURNAME,"Alexandre");
                editor.apply();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }else{
                Toast.makeText(LoginActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    });
}
    }

});
      if (ContextCompat.checkSelfPermission(LoginActivity.this,Manifest.permission.SEND_SMS)+
              ContextCompat.checkSelfPermission(LoginActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)+
              ContextCompat.checkSelfPermission(LoginActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)+
              ContextCompat.checkSelfPermission(LoginActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)+
              ContextCompat.checkSelfPermission(LoginActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!=
      PackageManager.PERMISSION_GRANTED)
          if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,Manifest.permission.SEND_SMS)||
                  ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)||
                  ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)||
                  ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)||
                  ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)){
              AlertDialog.Builder builder=new AlertDialog.Builder(
                      LoginActivity.this
              );
              builder.setTitle("Choisi tes permissions");
              builder.setMessage("SMS,ECRITURE,LIRE");
              builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      ActivityCompat.requestPermissions(
                         LoginActivity.this,
                         new String[]{
                              Manifest.permission.SEND_SMS,
Manifest.permission.READ_EXTERNAL_STORAGE,
                                 Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                 Manifest.permission.ACCESS_COARSE_LOCATION,
                                 Manifest.permission.ACCESS_FINE_LOCATION

                         },
                              REQUEST_CODE
                      );
                  }
              });
              builder.setNegativeButton("Annuler",null);
              AlertDialog alertDialog=builder.create();
              alertDialog.show();

        }else{
              ActivityCompat.requestPermissions(
                      LoginActivity.this,
                      new String[]{
                              Manifest.permission.SEND_SMS,
Manifest.permission.READ_EXTERNAL_STORAGE,
                              Manifest.permission.WRITE_EXTERNAL_STORAGE,
                              Manifest.permission.ACCESS_FINE_LOCATION,
                              Manifest.permission.ACCESS_COARSE_LOCATION
                      },
                      REQUEST_CODE
              );
          }else{
             Toast.makeText(getApplicationContext(),"Permission deja valider",Toast.LENGTH_SHORT).show();

      }
    }

}
