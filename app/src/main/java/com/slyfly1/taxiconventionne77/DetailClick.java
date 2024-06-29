package com.slyfly1.taxiconventionne77;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.slyfly1.taxiconventionne77.calendrier.EventDBHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.firebase.firestore.FieldPath.documentId;

public class DetailClick extends AppCompatActivity {
EventDBHelper eventDBHelper;


    private TextView idtextView;
    TextView nom1;
    TextView prenom1;
    TextView telephone1;
    TextView email1;
    TextView date1;
    TextView heure1;
    TextView personne1;
    TextView adressedepart1;
    TextView codepostaldepart1;
    TextView villedepart1;
    TextView adressearriver1;
    TextView codepostalarriver1;
    TextView villearriver1;
    TextView valider1;
    TextView refuser1;
    TextView validernom1;
    TextView testdate1;

    TextView textViewminutes;
    TextView textViewheure;

    SharedPreferences sharedPreferences1;

    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_NAME="name";
    private static final String KEY_SURNAME="surname";

    private static final String TAG = "DetailClick";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_VALIDER = "valider";

    private TextView textViewData;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = db.collection("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_click);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        idtextView = findViewById(R.id.idtest);
        idtextView.setText(name);

validernom1=findViewById(R.id.validernom);
        textViewData = findViewById(R.id.text_view_data);
        nom1 = findViewById(R.id.nom);
        prenom1 = findViewById(R.id.prenom);
        telephone1 = findViewById(R.id.telephone);
        email1 = findViewById(R.id.email);
        date1 = findViewById(R.id.date);
        heure1 = findViewById(R.id.heure);
        personne1 = findViewById(R.id.personne);
        adressedepart1 = findViewById(R.id.adressedepart);
        codepostaldepart1 = findViewById(R.id.codepostaldepart);
        villedepart1 = findViewById(R.id.villedepart);
        adressearriver1 = findViewById(R.id.adressearriver);
        codepostalarriver1 = findViewById(R.id.codepostalarriver);
        villearriver1 = findViewById(R.id.villearriver);
        valider1 = findViewById(R.id.valider);
        refuser1 = findViewById(R.id.refuser);
        testdate1=findViewById(R.id.testdate);

        textViewheure=findViewById(R.id.textViewheure);
        textViewminutes=findViewById(R.id.textViewminute);


        eventDBHelper = new EventDBHelper(this);


        sharedPreferences1=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String validernomchiant=sharedPreferences1.getString(KEY_NAME,null);
        validernom1.setText(validernomchiant);
        if(name != null ){
            validernom1.setText(validernomchiant);

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        usersRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                String test = idtextView.getText().toString();
                usersRef.whereEqualTo(documentId(), test)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Note1 note = documentSnapshot.toObject(Note1.class);
                                    note.setDocumentId(documentSnapshot.getId());
                                    String documentId = note.getDocumentId();
                                    String nom = note.getNom();
                                    String prenom = note.getPrenom();
                                    String telephone = note.getTelephone();
                                    String email = note.getEmail();
                                    String date = note.getDate();
                                    String heure = note.getHeure();
                                    String personne = note.getPersonne();
                                    String adressedepart = note.getAdresse();
                                    String codepostaldepart = note.getCodepostal();
                                    String villedepart = note.getVille();
                                    String adressearriver = note.getAdresse2();
                                    String codepostalarriver = note.getCodepostal2();
                                    String villearriver = note.getVille2();
                                    String valider = note.getValider();
                                    String refuser = note.getRefuser();


                                    nom1.setText(nom);
                                    prenom1.setText(prenom);
                                    telephone1.setText(telephone);
                                    email1.setText(email);
                                    date1.setText(date);
                                    heure1.setText(heure);
                                    personne1.setText(personne);
                                    adressedepart1.setText(adressedepart);
                                    codepostaldepart1.setText(codepostaldepart);
                                    villedepart1.setText(villedepart);
                                    adressearriver1.setText(adressearriver);
                                    codepostalarriver1.setText(codepostalarriver);
                                    villearriver1.setText(villearriver);
                                    valider1.setText(valider);
                                    refuser1.setText(refuser);

                                    String date12=date1.getText().toString();

                                    String oldString1 = date12;
                                    String newString1 = oldString1.replace("/", "-");
                                    String datetest =newString1;


                                    String date987 = datetest;
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
                                    Date testDate = null;
                                    try {
                                        testDate = sdf.parse(date987);
                                    }catch(Exception ex){
                                        ex.printStackTrace();
                                    }
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                                    String newFormat = formatter.format(testDate);


                                    testdate1.setText(newFormat);

                                    String decoupeminute=heure1.getText().toString();
                                    String str =decoupeminute;
                                    String res = "";
                                   String resheure="";
                                    res = str.substring(3, 5);
                                    resheure=str.substring(0,2);

                                    int a ;
                                    int b;
                                    a= Integer.parseInt(res);
                                    b=2;
                                   int somme=a+b;
                                   String somme2;
                                    somme2=Integer.toString(somme);
                                    if (somme<10){
                                        textViewheure.setText(res);
                                        textViewminutes.setText(resheure+":0"+somme2);
                                    }else{
                                        textViewheure.setText(res);
                                        textViewminutes.setText(resheure+":"+somme2);
                                    }





                                }

                            }
                        });

            }

        });


    }

    public void loadNotes(View v) {

        String test = idtextView.getText().toString();
        usersRef.whereEqualTo(documentId(), test)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Note1 note = documentSnapshot.toObject(Note1.class);
                            note.setDocumentId(documentSnapshot.getId());
                            String documentId = note.getDocumentId();
                            String nom = note.getNom();
                            String prenom = note.getPrenom();
                            String telephone = note.getTelephone();
                            String email = note.getEmail();
                            String date = note.getDate();
                            String heure = note.getHeure();
                            String personne = note.getPersonne();
                            String adressedepart = note.getAdresse();
                            String codepostaldepart = note.getCodepostal();
                            String villedepart = note.getVille();
                            String adressearriver = note.getAdresse2();
                            String codepostalarriver = note.getCodepostal2();
                            String villearriver = note.getVille2();

                            data += "ID: " + documentId
                                    + "\nTitle: " + nom + "\nDescription: " + prenom
                                    + "\nPriority: " + telephone + "\n\n";
                            nom1.setText(nom);
                            prenom1.setText(prenom);
                            telephone1.setText(telephone);
                            email1.setText(email);
                            date1.setText(date);
                            heure1.setText(heure);
                            personne1.setText(personne);
                            adressedepart1.setText(adressedepart);
                            codepostaldepart1.setText(codepostaldepart);
                            villedepart1.setText(villedepart);
                            adressearriver1.setText(adressearriver);
                            codepostalarriver1.setText(codepostalarriver);
                            villearriver1.setText(villearriver);

                        }
                        textViewData.setText(data);
                    }
                });

    }

    public void valider(View v) {

        String decoupeminute=heure1.getText().toString();
        String str =decoupeminute;
        String res = "";
        String resheure="";
        res = str.substring(3, 5);
        resheure=str.substring(0,2);

        int a ;
        int b;
        a= Integer.parseInt(res);
        b=2;
        int somme=a+b;
        String somme2;
        somme2=Integer.toString(somme);
        if (somme<10){
            textViewheure.setText(res);
            textViewminutes.setText(resheure+":0"+somme2);
        }else{
            textViewheure.setText(res);
            textViewminutes.setText(resheure+":"+somme2);
        }

        String adresse= adressedepart1.getText().toString()+" "+codepostaldepart1.getText().toString()+" "+villedepart1.getText().toString();
String arriver = adressearriver1.getText().toString()+" "+codepostalarriver1.getText().toString()+" "+villedepart1.getText().toString();
String heurejours=testdate1.getText().toString()+" "+heure1.getText().toString();
String arriverjours=testdate1.getText().toString()+" "+textViewminutes.getText().toString();
        String nomTXT = nom1.getText().toString();
        String heuredepartjourTXT=heurejours;
        String heurearriverjoursTXT=arriverjours;
        String serinoTXT="-1";
        String seritypeTXT="null";
        String arriverTXT = arriver;
        String prenomTXT = prenom1.getText().toString();
        String telephoneTXT = "null";
        String montantTXT="";
        String adresseTXT = adresse;
        String linkTXT="null";
        String cpamTXT="Non";
        String modepayementTXT="";
        String noteTXT=telephone1.getText().toString();

        Boolean checkinsertdata = eventDBHelper.insertuserdata(nomTXT,heuredepartjourTXT,heurearriverjoursTXT,serinoTXT,
                seritypeTXT,adresseTXT,linkTXT,arriverTXT, prenomTXT,cpamTXT, telephoneTXT,montantTXT,modepayementTXT,noteTXT);
        if(checkinsertdata==true){


            Toast.makeText(DetailClick.this,  "Nouveau client Ajouté", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(DetailClick.this, "Non ajouté", Toast.LENGTH_SHORT).show();
        }


        String test = idtextView.getText().toString();
        DocumentReference washingtonRef = db.collection("users").document(test);
String nom1234;
nom1234 = sharedPreferences1.getString(KEY_NAME,null);




        washingtonRef
                .update("valider",nom1234 )
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Valider");

                        try {
                            SmsManager smgr = SmsManager.getDefault();
                            smgr.sendTextMessage(telephone1.getText().toString(), null, "Bonjour Mr/Mme " + nom1.getText().toString() + "\n" + "Votre demande de taxi est validé " + "\n" + "Je serais présent le " + date1.getText().toString() + "à" + heure1.getText().toString() + "\n" + "Cordialement " + valider1.getText().toString(), null, null);
                            Toast.makeText(DetailClick.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(DetailClick.this, "SMS non envoyé il faut ajouter l autorisation dans les parametres", Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new Intent(getApplicationContext(), Accueil.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });

    }

    public void refuser(View v) {

        String nom1234;
        nom1234 = sharedPreferences1.getString(KEY_NAME,null);

        if (refuser1.getText().toString().equals("")) {
            String surnom;
            surnom=sharedPreferences1.getString(KEY_NAME,null);
            String test = idtextView.getText().toString();
            DocumentReference washingtonRef = db.collection("users").document(test);


            washingtonRef
                    .update("refuser", nom1234)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "Valider");

                            Intent intent = new Intent(getApplicationContext(), Accueil.class);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error updating document", e);
                        }
                    });


        } else {
            try {
                SmsManager smgr = SmsManager.getDefault();
                smgr.sendTextMessage(telephone1.getText().toString(), null, "Bonjour " + nom1.getText().toString() + "\n" + "Nos chauffeurs étant déjà pris sur la date indiqué nous ne pouvons donnés suites" + "\n" + "En espérant vous revoir" + "\n" + "L'équipe Taxi Conventionnés 77", null, null);
                Toast.makeText(DetailClick.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(DetailClick.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void appelertelephone(View v) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Intent appel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+telephone1.getText().toString()));
            startActivity(appel);

            return;
        }

    }

}