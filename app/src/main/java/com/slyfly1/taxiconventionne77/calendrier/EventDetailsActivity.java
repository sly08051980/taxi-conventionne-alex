package com.slyfly1.taxiconventionne77.calendrier;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.slyfly1.taxiconventionne77.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EventDetailsActivity extends AppCompatActivity {

    //shared preference

    SharedPreferences sharedPreferences1;

    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_NAME="name";
    //fin

    //firebase

    private static final String TAG = "MainActivity";
    private static final String KEY_NOM = "nom";
    private static final String KEY_PRENOM= "prenom";
    private static final String KEY_ADRESSEDEPART= "adresse";
    private static final String KEY_ADRESSEARRIVER= "adresse2";
    private static final String KEY_TELEPHONE= "telephone";
    private static final String KEY_HEURE= "heure";
    private static final String KEY_DATE= "date";
    private static final String KEY_REFUSER= "refuser";
    private static final String KEY_CHAUFFEURS= "chauffeurs";
    private static final String KEY_VALIDER= "valider";
    private static final String KEY_CODEPOSTAL= "codepostal";
    private static final String KEY_CODEPOSTAL2= "codepostal2";
    private static final String KEY_EMAIL= "email";
    private static final String KEY_PERSONNE= "personne";
    private static final String KEY_VILLE= "ville";
    private static final String KEY_VILLE2= "ville2";


    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    //fin de firebase
    private TextView eventNameTV, locationTV, dateTV, reminderTV, repeatTV, noteTV,textArriver,textPrenom,textCpam,textmodedepayement,textmontant;
    private Button shareButton, editButton,btnRenvoyer;
    private ImageView imageview10;
    private SQLiteDatabase mDatabase;
    String departlat;
    private String start, end, eventName, link,arriver;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        sharedPreferences1=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        final String name =sharedPreferences1.getString(KEY_NAME,null);

        EventDBHelper dbHelper = new EventDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        eventNameTV = findViewById(R.id.eventNameTV);
        locationTV = findViewById(R.id.locationTV);
        dateTV = findViewById(R.id.dateTV);
        reminderTV = findViewById(R.id.reminderTV);
        repeatTV = findViewById(R.id.repeatTV);
        noteTV = findViewById(R.id.noteTV);
        shareButton = findViewById(R.id.shareButton);
        editButton = findViewById(R.id.editButton);
textArriver=findViewById(R.id.textArriver);
textPrenom=findViewById(R.id.textPrenom);
textCpam=findViewById(R.id.textCpam);
imageview10=findViewById(R.id.imageView10);
textmodedepayement=findViewById(R.id.textmodepayement);
textmontant=findViewById(R.id.textmontant);
btnRenvoyer=findViewById(R.id.btnrenvoyer);

        eventName = getIntent().getStringExtra("EVENT NAME");
        start = getIntent().getStringExtra("START");
        end = getIntent().getStringExtra("END");


        findRow();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editEventIntent = new Intent(EventDetailsActivity.this, EditEventActivity.class);
                editEventIntent.putExtra("EVENT NAME", eventName);
                editEventIntent.putExtra("START", start);
                editEventIntent.putExtra("END", end);
                editEventIntent.putExtra("PRENOM",textPrenom.getText().toString());
                editEventIntent.putExtra("DEPART",locationTV.getText().toString());
                editEventIntent.putExtra("ARRIVER",textArriver.getText().toString());
                editEventIntent.putExtra("CPAM",textCpam.getText().toString());
                editEventIntent.putExtra("MODEDEPAYEMENT",textmodedepayement.getText().toString());
                editEventIntent.putExtra("MONTANT",textmontant.getText().toString());
                editEventIntent.putExtra("EDIT", "true");
                startActivity(editEventIntent);
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent1 = new Intent(Intent.ACTION_SEND);
                shareIntent1.setType("text/plain");
                String text = "Event Name: "+ eventNameTV.getText().toString() +"\n" +
                        "Date: " + dateTV.getText().toString();
                if (!locationTV.getText().toString().equals(" "))
                    text = text + "\n" + "Location: " + locationTV.getText().toString() + " " + link;
                shareIntent1.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(shareIntent1);
            }
        });

        locationTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sSource =departlat;
                String sDestination = locationTV.getText().toString();

                if(sSource.equals("")&&sDestination.equals("")){
                    Toast.makeText(getApplicationContext(),"ajout",Toast.LENGTH_SHORT).show();

                }else{
                    DiplayTrack(sSource,sDestination);
                }

            }
        });
        textArriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sSource =departlat;
                String sDestination = textArriver.getText().toString();

                if(sSource.equals("")&&sDestination.equals("")){
                    Toast.makeText(getApplicationContext(),"ajout",Toast.LENGTH_SHORT).show();

                }else{
                    DiplayTrack(sSource,sDestination);
                }
            }
        });
        imageview10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(EventDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Intent appel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+noteTV.getText().toString()));
                    startActivity(appel);

                    return;
                }
            }
        });
        noteTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(EventDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Intent appel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+noteTV.getText().toString()));
                    startActivity(appel);

                    return;
                }
            }
        });


        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(
                EventDetailsActivity.this
        );
        if (ActivityCompat.checkSelfPermission(EventDetailsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(EventDetailsActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            getCurrentLocation();
        }else{
            ActivityCompat.requestPermissions(EventDetailsActivity.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
            },100);
        }
        btnRenvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String decoupeminute=dateTV.getText().toString();
                String str =decoupeminute;
                String resdate = "";
                String resheure="";
                resdate = str.substring(0,10);
                resheure=str.substring(13,18);

                String date12=resdate;

                String oldString1 = date12;
                String newString1 = oldString1.replace("-", "/");
                String datetest =newString1;

String resanne=datetest;
String resannee=resanne.substring(0,4);
String resmois=resanne.substring(5,7);
String resjours=resanne.substring(8,10);
String resdatefinal = resjours+"/"+resmois+"/"+resannee;



                String nom = eventNameTV.getText().toString();
                String prenom = textPrenom.getText().toString();
                String adresse = locationTV.getText().toString();
                String adresse2 = textArriver.getText().toString();
                String telephone = noteTV.getText().toString();
                String heure= resheure;
                String date = resdatefinal;
                String refuser = name;
                String chauffeurs="";
String valider="";
String codepostal="";
                String codepostal2="";
                String email="";
                String personne="";
                String ville="";
                String ville2="";

                Map<String, Object> note = new HashMap<>();
                note.put(KEY_NOM, nom);
                note.put(KEY_PRENOM, prenom);
                note.put(KEY_ADRESSEDEPART, adresse);
                note.put(KEY_ADRESSEARRIVER, adresse2);
                note.put(KEY_TELEPHONE, telephone);
                note.put(KEY_HEURE, heure);
                note.put(KEY_DATE, date);
                note.put(KEY_REFUSER, refuser);
                note.put(KEY_CHAUFFEURS,chauffeurs);
                note.put(KEY_VALIDER,valider);
                note.put(KEY_CODEPOSTAL, codepostal);
                note.put(KEY_CODEPOSTAL2, codepostal2);
                note.put(KEY_EMAIL, email);
                note.put(KEY_PERSONNE, personne);
                note.put(KEY_VILLE,ville);
                note.put(KEY_VILLE2,ville2);


                db.collection("users").document().set(note)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                              //  Toast.makeText(EventDetailsActivity.this, "Note saved", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(EventDetailsActivity.this,EditEventActivity.class);

                                String oui = "Oui";
                                intent.putExtra("Oui",oui);
                                startActivity(intent);



                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EventDetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, e.toString());
                            }
                        });




            }
        });
    }

    public void onResume() {
        super.onResume();
        findRow();
    }

    public void writeToTV(String note, String location, String seriType,String arriver,String prenom,String cpam,String montant,String modedepayement) {
        eventNameTV.setText(eventName);
        String text;
        if(start.split(" ")[0].equals(end.split(" ")[0]))
            text = start.split(" ")[0] + " • " + start.split(" ")[1] + " - " +
                    end.split(" ")[1];
        else
            text = start.split(" ")[0] + " • " + start.split(" ")[1] + " - " +
                    end.split(" ")[0] + " • " + end.split(" ")[1];
        dateTV.setText(text);

        if (note != null)
            noteTV.setText(note);
        else
            noteTV.setText(" ");


        if (arriver != null)
            textArriver.setText(arriver);
        else
            textArriver.setText(" ");

        if (prenom != null)
            textPrenom.setText(prenom);
        else
            textPrenom.setText(" ");

        if (cpam != null)
            textCpam.setText(cpam);
        else
            textCpam.setText(" ");

        if (montant != null)
            textmontant.setText(montant);
        else
            textmontant.setText(" ");

        if (modedepayement != null)
            textmodedepayement.setText(modedepayement);
        else
            textmodedepayement.setText(" ");

        if (location != null)
            locationTV.setText(location);
        else
            locationTV.setText(" ");

        if (seriType != null)
            repeatTV.setText(seriType);
    }

    public void findRow() {
        String SQLQuery = "SELECT * FROM " + EventDB.Event.TABLE_NAME +
                " WHERE " + EventDB.Event.COLUMN_START + "='" + start + "' AND " +
                EventDB.Event.COLUMN_END + "='" + end + "' AND " + EventDB.Event.COLUMN_NAME + "='"
                + eventName + "';";
        Cursor cursor = mDatabase.rawQuery(SQLQuery, null);
        int ID = -1, SERI = -1;
        String note = null, location = null, seriType = null,arriver=null,prenom=null,cpam=null,montant=null,modedepayement=null;
        if (cursor.moveToFirst()) {
            do {
                ID = cursor.getInt(cursor.getColumnIndex(EventDB.Event.COLUMN_ID));
                note = cursor.getString(cursor.getColumnIndex(EventDB.Event.COLUMN_NOTE));
                SERI = cursor.getInt(cursor.getColumnIndex(EventDB.Event.COLUMN_SERI));
                location = cursor.getString(cursor.getColumnIndex(EventDB.Event.COLUMN_LOCATION));
                arriver=cursor.getString(cursor.getColumnIndex(EventDB.Event.COLUMN_ARRIVER));
                prenom=cursor.getString(cursor.getColumnIndex(EventDB.Event.COLUMN_PRENOM));
                cpam=cursor.getString(cursor.getColumnIndex(EventDB.Event.COLUMN_CPAM));
                montant=cursor.getString(cursor.getColumnIndex(EventDB.Event.COLUMN_MONTANT));
                modedepayement=cursor.getString(cursor.getColumnIndex(EventDB.Event.COLUMN_MODEPAYEMENT));
                if (SERI != -1)
                    seriType = cursor.getString(cursor.getColumnIndex(EventDB.Event.COLUMN_SERI_TYPE));
                link = cursor.getString(cursor.getColumnIndex(EventDB.Event.COLUMN_LOCATION_LINK));
            } while (cursor.moveToNext());
        }
        findReminders(ID);
        writeToTV(note, location, seriType,arriver,prenom,cpam,montant,modedepayement);

    }

    public void findReminders(int ID) {
        String SQLQuery = "SELECT * FROM " + EventDB.Event.REMINDER_TABLE_NAME +
                " WHERE " + EventDB.Event.REMINDER_COLUMN_EID + "='" + ID + "';";
        Cursor cursor = mDatabase.rawQuery(SQLQuery, null);
        String dateText = "";
        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndex(EventDB.Event.REMINDER_COLUMN_DATE));
                dateText = dateText + date + "\n";
            } while (cursor.moveToNext());
            dateText.substring(0, dateText.length() - 2);
        }
        reminderTV.setText(dateText);
    }
    private void DiplayTrack(String sSource,String sDestination){
        try {
            Uri uri=Uri.parse("https://www.google.co.in/maps/dir/"+sSource+"/"+sDestination);
            Intent intent= new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Uri uri=Uri.parse("htpps://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent= new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==100 && grantResults.length>0 && (grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED)){
            getCurrentLocation();
        }else{
            Toast.makeText(getApplicationContext(),"Permission denied",Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)|| locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location!=null){

                        String lati=String.valueOf(location.getLatitude());
                        String longi=String.valueOf(location.getLongitude());

                        departlat=lati+","+longi;

                    }else {
                        LocationRequest locationRequest= new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(10000)
                                .setNumUpdates(1);

                        LocationCallback locationCallback=new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {
                                Location location1=locationResult.getLastLocation();

                                String lati=String.valueOf(location1.getLatitude());
                                String longi=String.valueOf(location1.getLongitude());

                                departlat=lati+","+longi;

                            }
                        };
                        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
                    }
                }
            });
        }else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        }
    }

}
