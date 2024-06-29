package com.slyfly1.taxiconventionne77;


import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.slyfly1.taxiconventionne77.calendrier.Cpam;

import com.slyfly1.taxiconventionne77.calendrier.Cpamvalider;
import com.slyfly1.taxiconventionne77.calendrier.Graphique;
import com.slyfly1.taxiconventionne77.calendrier.MainActivitycalendrier;
import com.slyfly1.taxiconventionne77.calendrier.WeeklyListActivity;
import com.slyfly1.taxiconventionne77.enregistrementclient.AjoutClient;
import com.slyfly1.taxiconventionne77.login.ProfileActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences1;

    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_NAME="name";
    private static final String KEY_SURNAME="surname";
    private static final String TAG = "MainActivity";
    private static final String KEY_NOM = "nom";
    private static final String KEY_PRENOM= "prenom";
    private static final String KEY_EMAIL= "email";
    private static final String KEY_ADRESSE= "adresse";
    private static final String KEY_VILLE= "ville";
    private static final String KEY_CODEPOSTAL= "codepostal";
    private static final String KEY_ADRESSE2= "adresse2";
    private static final String KEY_VILLE2= "ville2";
    private static final String KEY_CODEPOSTAL2= "codepostal2";
    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextEmail;
    private TextView textViewData;
    Button logout;

    private Toolbar toolbar;

    TextView textbon,surnom;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = db.collection("users");
    private DocumentReference noteRef = db.document("Users/My First Note");
//debut notif
ProgressBar progressbar;
    WebView webview;
    //fin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextEmail=findViewById(R.id.edit_text_email);
        textViewData = findViewById(R.id.text_view_data);
        textbon=findViewById(R.id.txtbonjour);
        surnom=findViewById(R.id.surnom);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


Intent intentBackgtoundService = new Intent(this,FirebasePushNotificationClass.class);
startService(intentBackgtoundService);
logout=findViewById(R.id.btnLogout);

        sharedPreferences1=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name =sharedPreferences1.getString(KEY_NAME,null);
        String surnom1=sharedPreferences1.getString(KEY_SURNAME,null);



        if(name != null ){
            textbon.setText("Bonjour "+name);
            surnom.setText(surnom1);

        }

logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intentMyAccount = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intentMyAccount);
    }
});
//debut notification
        Log.d("TOken ",""+ FirebaseInstanceId.getInstance().getToken());
        FirebaseMessaging.getInstance().subscribeToTopic("allDevices");
        //now Let's see server code
        webview=findViewById(R.id.webview);
        progressbar=findViewById(R.id.progressbar);
        webview.getSettings().setJavaScriptEnabled(true);
        //its launching in browser let's fixed it
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }
        });

        //its fixed let's add progressbar
        webview.loadUrl("https://amazon.in/");


        //now on back press it exist instead of back page let's fixed it
        clearPreviousNotification();
        //fin
    }

    //debut notif
    private void clearPreviousNotification() {
        int notificationId=getIntent().getIntExtra("notificationId",0);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(notificationId);
    }


    @Override
    public void onBackPressed() {


    }
    //fin
    @Override
    protected void onStart() {
        super.onStart();
        usersRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }
                String data = "";
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Note note = documentSnapshot.toObject(Note.class);
                    note.setDocumentId(documentSnapshot.getId());
                    String documentId = note.getDocumentId();
                    String nom = note.getNom();
                    String prenom = note.getPrenom();
                    String email = note.getEmail();
                    String adresse=note.getAdresse();
                    String ville=note.getVille();
                    String codepostal=note.getCodepostal();
                    String adresse2=note.getAdresse2();
                    String ville2=note.getVille2();
                    String codepostal2=note.getCodepostal2();
                    data +=  "\nNom: " + nom + " Prenom: " + prenom + "\nEmail: " + email + "\nAdresse: " + adresse + "\nCode Postal: " + codepostal + "\nVille: " + ville + "\nAdresse2: " + adresse2 + "\nCode Postal2: " + codepostal2 +"\nVille2: " + ville2 +"\n\n";
                }
                textViewData.setText(data);
            }
        });
    }
    public void addNote(View v) {
        String nom = editTextTitle.getText().toString();
        String prenom = editTextDescription.getText().toString();
        String email =editTextEmail.getText().toString();
        Note note = new Note(nom, prenom,email,email,email,email,email,email,email);
        usersRef.add(note);
    }

  public void calendrier(View v){
      Intent calendrier = new Intent(getApplicationContext(), MainActivitycalendrier.class);
      startActivity(calendrier);
  }

    public void loadNotes(View v) {

      //  usersRef.get()
        //        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
        //            @Override
         //           public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
         //               String data = "";
         //               for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
          //                  Note note = documentSnapshot.toObject(Note.class);
           //                 note.setDocumentId(documentSnapshot.getId());
            //                String documentId = note.getDocumentId();
             //               String nom = note.getNom();
             //               String prenom = note.getPrenom();
              //              String email = note.getEmail();
               //             String adresse=note.getAdresse();
               //             String ville=note.getVille();
                //            String codepostal=note.getCodepostal();
                 //           String adresse2=note.getAdresse2();
                 //           String ville2=note.getVille2();
                  //          String codepostal2=note.getCodepostal2();
                 //           data += "\nNom: " + nom + " Prenom: " + prenom + "\nEmail: " + email + "\nAdresse: " + adresse + "\nCode Postal: " + codepostal + "\nVille: " + ville + "\nAdresse2: " + adresse2 + "\nCode Postal2: " + codepostal2 +"\nVille2: " + ville2 +"\n\n";
                 //       }
                  //      textViewData.setText(data);
                //    }
               // });

        Intent intent = new Intent(getApplicationContext(),test.class);
        startActivity(intent);
    }


    public void testNotes(View view) {
        Intent intentMyAccount = new Intent(getApplicationContext(), list.class);
        startActivity(intentMyAccount);
    }
    public void ajouterClient(View view) {
        Intent intentMyAccount = new Intent(getApplicationContext(), AjoutClient.class);
        startActivity(intentMyAccount);
    }
    public void RecycleView(View view) {
        Intent intentMyAccount = new Intent(getApplicationContext(), Accueil.class);
        startActivity(intentMyAccount);
    }   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {

            case R.id.calendar:
                Intent calendarIntent=new Intent(MainActivity.this,MainActivitycalendrier.class);
                startActivity(calendarIntent);
                return true;
            case R.id.weekly:
                Intent weeklyIntent = new Intent(MainActivity.this, WeeklyListActivity.class);
                weeklyIntent.putExtra("Type", "Weekly");
                startActivity(weeklyIntent);
                return true;
            case R.id.monthly:
                Intent monthlyIntent = new Intent(MainActivity.this, WeeklyListActivity.class);
                monthlyIntent.putExtra("Type", "Monthly");
                startActivity(monthlyIntent);
                return true;
        /*    case R.id.settings:
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true; */
            case R.id.graph:
                Intent graphIntent= new Intent (MainActivity.this, Graphique.class);
                startActivity(graphIntent);
                return true;
            case R.id.avalider:
                Intent cam1Intent= new Intent (MainActivity.this, Cpam.class);
                startActivity(cam1Intent);
                return true;
            case R.id.validerfinal:
                Intent validerIntent= new Intent (MainActivity.this, Cpamvalider.class);
                startActivity(validerIntent);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }




}