package com.slyfly1.taxiconventionne77.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.slyfly1.taxiconventionne77.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
TextView userEmail;
Button userLogout;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
TextView nom;
    SharedPreferences sharedPreferences1;

    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_NAME="name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);
    userEmail=findViewById(R.id.tvUserEmail);
    userLogout=findViewById(R.id.btnLogout);

            nom=findViewById(R.id.nom);


        sharedPreferences1=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name =sharedPreferences1.getString(KEY_NAME,null);
        if(name != null){
            nom.setText(name);

        }

    firebaseAuth= FirebaseAuth.getInstance();
    firebaseUser=firebaseAuth.getCurrentUser();

    userEmail.setText(firebaseUser.getEmail());

   userLogout.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
       FirebaseAuth.getInstance().signOut();
       Intent intent= new Intent(ProfileActivity.this,LoginActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
           SharedPreferences.Editor editor = sharedPreferences1.edit();
           editor.clear();
           editor.commit();
           Toast.makeText(ProfileActivity.this,"Logout", Toast.LENGTH_SHORT).show();
           finish();
      startActivity(intent);

       }
   });

    }
}
