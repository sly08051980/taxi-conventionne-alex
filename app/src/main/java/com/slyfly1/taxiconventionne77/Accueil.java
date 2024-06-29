package com.slyfly1.taxiconventionne77;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Arrays;

public class Accueil extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = db.collection("users");
    private NoteAdapter adapter;
    SharedPreferences sharedPreferences1;

    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_NAME="name";
    private static final String KEY_SURNAME="surname";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil_main);
        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        sharedPreferences1=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name =sharedPreferences1.getString(KEY_NAME,null);
        String surnom1=sharedPreferences1.getString(KEY_SURNAME,null);

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Accueil.this, NewNoteActivity.class));
            }
        });
        setUpRecyclerView();
    }
    private void setUpRecyclerView() {
        sharedPreferences1=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name =sharedPreferences1.getString(KEY_NAME,null);
        String surnom1=sharedPreferences1.getString(KEY_SURNAME,null);

        Query query=usersRef
                .whereIn("chauffeurs", Arrays.asList(name,""))
                .whereEqualTo("valider","")
                .whereNotEqualTo("refuser",name);





        FirestoreRecyclerOptions<Note1> options = new FirestoreRecyclerOptions.Builder<Note1>()
                .setQuery(query, Note1.class)
                .build();
        adapter = new NoteAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Note1 note = documentSnapshot.toObject(Note1.class);
                String id = documentSnapshot.getId();
                String path = documentSnapshot.getReference().getPath();
                Toast.makeText(Accueil.this,
                        "Position: " + position + " ID: " + id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DetailClick.class);
                intent.putExtra("name",id);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}