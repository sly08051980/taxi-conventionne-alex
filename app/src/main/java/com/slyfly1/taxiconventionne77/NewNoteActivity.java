package com.slyfly1.taxiconventionne77;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.slyfly1.taxiconventionne77.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewNoteActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextVille;
 private EditText editTextEmail;
    private EditText editTextAdresse;
    private EditText editTextCodepostal;
    private EditText editTextAdresse2;
     private EditText editTextVille2;
    private EditText editTextCodepostal2;
    private EditText editTextTelephone;
    private EditText editTextPersonne;
    private EditText editTextChauffers;
    private EditText editTextDate;
    private EditText editTextHeure;
    private EditText editTextValider;
    private EditText editTextRefuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextVille=findViewById(R.id.edit_text_ville);
        editTextEmail  = findViewById(R.id.edit_text_email);
        editTextAdresse =findViewById(R.id.edit_text_adresse);
        editTextCodepostal =findViewById(R.id.edit_text_codepostal);
        editTextAdresse2 =findViewById(R.id.edit_text_adresse2);
        editTextVille2=findViewById(R.id.edit_text_ville2);
        editTextCodepostal2=findViewById(R.id.edit_text_codepostal2);
        editTextTelephone=findViewById(R.id.edit_text_telephone);
        editTextPersonne=findViewById(R.id.edit_text_personne);
        editTextChauffers=findViewById(R.id.edit_text_chauffeurs);
        editTextDate=findViewById(R.id.edit_text_date);
        editTextHeure=findViewById(R.id.edit_text_heure);
        editTextValider=findViewById(R.id.edit_text_valider);
        editTextValider=findViewById(R.id.edit_text_valider);
        editTextRefuser=findViewById(R.id.edit_text_refuser);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void saveNote() {
        String nom = editTextTitle.getText().toString();
        String prenom = editTextDescription.getText().toString();
        String ville =editTextVille.getText().toString();
        String email = editTextEmail.getText().toString();
        String adresse = editTextAdresse.getText().toString();
        String codepostal = editTextCodepostal.getText().toString();
        String adresse2= editTextAdresse2.getText().toString();
        String ville2 =editTextVille2.getText().toString();
        String codepostal2 = editTextCodepostal2.getText().toString();
        String telephone = editTextTelephone.getText().toString();
        String personne = editTextPersonne.getText().toString();
        String chauffeurs = editTextChauffers.getText().toString();
        String date=editTextDate.getText().toString();
        String heure=editTextHeure.getText().toString();
        String valider=editTextValider.getText().toString();
        String refuser=editTextRefuser.getText().toString();
        if (nom.trim().isEmpty() || prenom.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        CollectionReference usersRef = FirebaseFirestore.getInstance()
                .collection("users");
        usersRef.add(new Note1(nom, prenom,email,adresse,codepostal,ville,adresse2,codepostal2,ville2,telephone,personne,chauffeurs,date,heure,valider,refuser));
        Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();
        finish();
    }
}