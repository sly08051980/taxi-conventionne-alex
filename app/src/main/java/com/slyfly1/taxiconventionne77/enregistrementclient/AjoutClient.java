package com.slyfly1.taxiconventionne77.enregistrementclient;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.slyfly1.taxiconventionne77.R;


import com.slyfly1.taxiconventionne77.calendrier.Cpam;
import com.slyfly1.taxiconventionne77.calendrier.Cpamvalider;
import com.slyfly1.taxiconventionne77.calendrier.Graphique;

import com.slyfly1.taxiconventionne77.calendrier.MainActivitycalendrier;
import com.slyfly1.taxiconventionne77.calendrier.WeeklyListActivity;

public class AjoutClient extends AppCompatActivity {
    EditText nom, prenom, telephone,adresse,arriver,rechercher,recherchertelephone;
    Button insert, update, delete, view,effacer;
    DBClientHelper DB;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutclient);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        telephone = findViewById(R.id.telephone);
        adresse = findViewById(R.id.adresse);
        arriver = findViewById(R.id.arriver);
rechercher=findViewById(R.id.recherche);
recherchertelephone=findViewById(R.id.recherchetelephone);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
effacer=findViewById(R.id.effacer);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBClientHelper(this);

        effacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rechercher.setText("");
                recherchertelephone.setText("");
                nom.setText("");
                prenom.setText("");
                telephone.setText("");
                adresse.setText("");
                arriver.setText("");

            }
        });


        rechercher.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
               String a;

               a=rechercher.getText().toString();


                Cursor res = DB.recherchedata(a);


                while(res.moveToNext()){
nom.setText(res.getString(0));
                    prenom.setText(res.getString(1));
                    telephone.setText(res.getString(2));
                    adresse.setText(res.getString(3));
                    arriver.setText(res.getString(4));

                }

            }
        });


        recherchertelephone.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s1) {
            }

            public void beforeTextChanged(CharSequence s1, int start,
                                          int count, int after) {

            }

            public void onTextChanged(CharSequence s1, int start,
                                      int before, int count) {
                String b;

                b=recherchertelephone.getText().toString();


                Cursor res = DB.recherchetelephonedata(b);


                while(res.moveToNext()){
                    nom.setText(res.getString(0));
                    prenom.setText(res.getString(1));
telephone.setText(res.getString(2));
                    adresse.setText(res.getString(3));
                    arriver.setText(res.getString(4));

                }

            }
        });



        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomTXT = nom.getText().toString();
                String prenomTXT = prenom.getText().toString();
                String telephoneTXT = telephone.getText().toString();
                String adresseTXT = adresse.getText().toString();
                String arriverTXT = arriver.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nomTXT, prenomTXT, telephoneTXT,adresseTXT,arriverTXT);
                if(checkinsertdata==true){


                    Toast.makeText(AjoutClient.this,  "Nouveau client Ajouté", Toast.LENGTH_SHORT).show();
                    nom.setText("");
                    prenom.setText("");
                    telephone.setText("");
                    adresse.setText("");
                    arriver.setText("");
                }
else{
                    Toast.makeText(AjoutClient.this, "Non ajouté", Toast.LENGTH_SHORT).show();
            }     }     });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomTXT = nom.getText().toString();
                String prenomTXT = prenom.getText().toString();
                String telephoneTXT = telephone.getText().toString();
                String adresseTXT = adresse.getText().toString();
                String arriverTXT = arriver.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nomTXT, prenomTXT, telephoneTXT,adresseTXT,arriverTXT);
                if(checkupdatedata==true){


                    Toast.makeText(AjoutClient.this, "Ajouter", Toast.LENGTH_SHORT).show();
                nom.setText("");
                prenom.setText("");
                telephone.setText("");
                adresse.setText("");
                arriver.setText("");
                } else{
                    Toast.makeText(AjoutClient.this, "Non Ajouté", Toast.LENGTH_SHORT).show();
            }     }    });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomTXT = nom.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nomTXT);
                if(checkudeletedata==true)
                    Toast.makeText(AjoutClient.this, "Supprimer", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AjoutClient.this, "Erreur non supprimé", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(AjoutClient.this, "Aucun enregistrement", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Nom :"+res.getString(0)+"\n");
                    buffer.append("Prenom :"+res.getString(1)+"\n");
                    buffer.append("Téléphone :"+res.getString(2)+"\n");
                    buffer.append("Adresse :"+res.getString(3)+"\n");
                    buffer.append("Arriver :"+res.getString(4)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(AjoutClient.this);
                builder.setCancelable(true);
                builder.setTitle("Liste client");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {

            case R.id.calendar:
                Intent calendarIntent=new Intent(AjoutClient.this, MainActivitycalendrier.class);
                startActivity(calendarIntent);
                return true;
            case R.id.weekly:
                Intent weeklyIntent = new Intent(AjoutClient.this, WeeklyListActivity.class);
                weeklyIntent.putExtra("Type", "Weekly");
                startActivity(weeklyIntent);
                return true;
            case R.id.monthly:
                Intent monthlyIntent = new Intent(AjoutClient.this, WeeklyListActivity.class);
                monthlyIntent.putExtra("Type", "Monthly");
                startActivity(monthlyIntent);
                return true;
         /*   case R.id.settings:
                Intent settingsIntent = new Intent(AjoutClient.this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;*/
            case R.id.graph:
                Intent graphIntent= new Intent (AjoutClient.this, Graphique.class);
                startActivity(graphIntent);
                return true;
            case R.id.avalider:
                Intent cam1Intent= new Intent (AjoutClient.this, Cpam.class);
                startActivity(cam1Intent);
                return true;
            case R.id.validerfinal:
                Intent validerIntent= new Intent (AjoutClient.this, Cpamvalider.class);
                startActivity(validerIntent);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }


}
