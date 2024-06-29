package com.slyfly1.taxiconventionne77.calendrier;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.slyfly1.taxiconventionne77.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EventListActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private EventAdapter mAdapter;
    private int DAY, MONTH, YEAR;
    private String DAY_NAME, currentDate;
    private SQLiteDatabase DB;
    Cursor cur;
    int amount;
    TextView montantcb,montantcheque,montantespece,montantcpam,total,nbrclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        EventDBHelper dbHelper = new EventDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        DB = dbHelper.getWritableDatabase();
        DAY = getIntent().getIntExtra("DAY", 1);
        YEAR = getIntent().getIntExtra("YEAR", 1970);
        MONTH = getIntent().getIntExtra("MONTH", 0);
        DAY_NAME = getIntent().getStringExtra("DAY_NAME");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, YEAR);
        c.set(Calendar.MONTH, MONTH);
        c.set(Calendar.DAY_OF_MONTH, DAY);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = df.format(c.getTime());
montantcb=findViewById(R.id.montantcb);
montantcheque=findViewById(R.id.montantcheque);
montantcpam=findViewById(R.id.montantcpam);
montantespece=findViewById(R.id.montantespece);
total=findViewById(R.id.total);
nbrclient=findViewById(R.id.nbrclient);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EventAdapter(this, getAllItems(), "Time");
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton addButton = findViewById(R.id.floatingButton);
        TextView dayNumberTV = findViewById(R.id.day_number);
        TextView dayNameTV = findViewById(R.id.day_name);

        dayNumberTV.setText(Integer.toString(DAY));
        dayNameTV.setText(DAY_NAME);

        cb();
        cpam();
        cheque();
        espece();
        total();
        nbrclient();

        // new event
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eventListIntent = new Intent(EventListActivity.this, EditEventActivity.class);
                eventListIntent.putExtra("DAY", DAY);
                eventListIntent.putExtra("MONTH", MONTH);
                eventListIntent.putExtra("YEAR", YEAR);
                eventListIntent.putExtra("DAY_NAME", DAY_NAME);
                startActivity(eventListIntent);
            }
        });

    }

    private Cursor getAllItems() {
        String SQLQuery = "SELECT * FROM " + EventDB.Event.TABLE_NAME +
                " WHERE " + EventDB.Event.COLUMN_START + " GLOB '" + currentDate + "*'"
                + " ORDER BY datetime(" + EventDB.Event.COLUMN_START + ") ASC;" ;

        return mDatabase.rawQuery(SQLQuery, null);
    }

    public void cb() {


        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "
                + EventDB.Event.TABLE_NAME +" WHERE "+EventDB.Event.COLUMN_START+ " GLOB '" + currentDate + "*'"+
               "AND "+ EventDB.Event.COLUMN_MODEPAYEMENT+"='"+ "CB" +"';", null);



        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;
        montantcb.setText(amount+""+"€");
        cur.close();
    }

    public void espece() {


        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "
                + EventDB.Event.TABLE_NAME +" WHERE "+EventDB.Event.COLUMN_START+ " GLOB '" + currentDate + "*'"+
                "AND "+ EventDB.Event.COLUMN_MODEPAYEMENT+"='"+ "Espèce" +"';", null);



        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;
        montantespece.setText(amount+""+"€");
        cur.close();
    }

    public void cheque() {


        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "
                + EventDB.Event.TABLE_NAME +" WHERE "+EventDB.Event.COLUMN_START+ " GLOB '" + currentDate + "*'"+
                "AND "+ EventDB.Event.COLUMN_MODEPAYEMENT+"='"+ "Chèque" +"';", null);



        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;
        montantcheque.setText(amount+""+"€");
        cur.close();
    }

    public void cpam() {


        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "
                + EventDB.Event.TABLE_NAME +" WHERE "+EventDB.Event.COLUMN_START+ " GLOB '" + currentDate + "*'"+
                "AND "+ EventDB.Event.COLUMN_MODEPAYEMENT+"='"+ "CPAM" +"';", null);



        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;
        montantcpam.setText(amount+""+"€");
        cur.close();
    }

    public void total() {


        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "
                + EventDB.Event.TABLE_NAME +" WHERE "+EventDB.Event.COLUMN_START+ " GLOB '" + currentDate + "*'"+
                ";", null);



        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;
        total.setText(amount+""+"€");
        cur.close();
    }

    public void nbrclient() {
        cur = DB.rawQuery("select count(*)  from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                + " GLOB '" + currentDate + "*'"+";", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;
        nbrclient.setText(amount+""+"");
        cur.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EventAdapter(this, getAllItems(), "Time");
        recyclerView.setAdapter(mAdapter);


    }

}
