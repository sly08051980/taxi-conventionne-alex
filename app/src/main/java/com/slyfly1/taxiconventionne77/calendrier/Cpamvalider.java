package com.slyfly1.taxiconventionne77.calendrier;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.slyfly1.taxiconventionne77.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Cpamvalider extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {
    private ContentValues cv;
    private SQLiteDatabase DB;
    Cursor cur;
    int amount;


    private String TYPE;
    private RecyclerView recyclerView;
    private TextView monthTV, yearTV,test,espece,cheque,cpam,total,nbrclient;
    private CpamEventAdapter mAdapter,mAdapter1;
    private SQLiteDatabase mDatabase,mDatabase1;
    String startDate, endDate;
    private String[] months = new String[]{"JAN", "FEV", "MAR", "AVR", "MAI", "JUI",
            "JUI", "AOU", "SEP", "OCT", "NOV", "DEC"};
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpam);
        EventDBHelper dbHelper = new EventDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        mDatabase1=dbHelper.getWritableDatabase();
        DB = dbHelper.getWritableDatabase();
        ImageButton weeklyButton = findViewById(R.id.weekButton);
        recyclerView = findViewById(R.id.weeklyRecyclerView);
        monthTV = findViewById(R.id.monthText);
        yearTV = findViewById(R.id.yearText);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        test=findViewById(R.id.montantcb);
        espece=findViewById(R.id.montantespece);
        cheque=findViewById(R.id.montantcheque);
        cpam=findViewById(R.id.montantcpam);
        total=findViewById(R.id.total);
        nbrclient=findViewById(R.id.nbrclient);

        TYPE = getIntent().getStringExtra("Type");
        c = Calendar.getInstance();
        startDate = "2020-05-11";
        endDate = "2020-05-17";
        recycleMaker();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                updateItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

    }

    public void recycleMaker() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CpamEventAdapter(this, sqlQuery(), TYPE);
        recyclerView.setAdapter(mAdapter);



    }

    public Cursor sqlQuery() {

        String oui ="valideroui";
        String CPAM="CPAM";

        String SQLQuery = "SELECT * FROM " + EventDB.Event.TABLE_NAME +
                " WHERE " + EventDB.Event.COLUMN_CPAM + "= '" + oui+
                "' AND " + EventDB.Event.COLUMN_MODEPAYEMENT+ "= '"+ CPAM + "' ORDER BY datetime("
                + EventDB.Event.COLUMN_START + ") ASC;" ;


        Cursor cursor = mDatabase.rawQuery(SQLQuery, null);




        return cursor;

    }
    public void cb() {

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ endDate +"'AND "+ EventDB.Event.COLUMN_MODEPAYEMENT+"='"+ "CB" +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;
        test.setText(amount+""+"€");
        cur.close();
    }
    public void espece() {

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ endDate +"'AND "+ EventDB.Event.COLUMN_MODEPAYEMENT+"='"+ "Espèce" +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;
        espece.setText(amount+""+"€");
        cur.close();
    }
    public void cheque() {

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ endDate +"'AND "+ EventDB.Event.COLUMN_MODEPAYEMENT+"='"+ "Chèque" +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;
        cheque.setText(amount+""+"€");
        cur.close();
    }
    public void cpam() {

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ endDate +"'AND "+ EventDB.Event.COLUMN_MODEPAYEMENT+"='"+ "CPAM" +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;
        cpam.setText(amount+""+"€");
        cur.close();
    }

    public void total() {

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ endDate  +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;
        total.setText(amount+""+"€");
        cur.close();
    }

    public void nbrclient() {
        cur = DB.rawQuery("select count(*)  from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ endDate  +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;
        nbrclient.setText(amount+""+"");
        cur.close();
    }

  /*  public void nbrcb() {

        cur = DB.rawQuery("select count *  from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ endDate +"'AND "+ EventDB.Event.COLUMN_MODEPAYEMENT+"='"+ "CB" +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;
       nbrcb.setText(amount+"");
        cur.close();
    }*/

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        Calendar firstDay = initializeCalendar(year, month, dayOfMonth);
        Calendar lastDay = initializeCalendar(year, month, dayOfMonth);
        String text;
        if (TYPE.equals("Weekly")) {
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

            if (dayOfWeek != 1) {
                lastDay.add(Calendar.DAY_OF_MONTH, 7 - dayOfWeek + 1);
                if (dayOfWeek > 2) {
                    firstDay.add(Calendar.DAY_OF_MONTH, 2 - dayOfWeek);
                }
            }
            else {
                firstDay.add(Calendar.DAY_OF_MONTH, -6);
            }
            text = firstDay.get(Calendar.DAY_OF_MONTH) + " " + months[firstDay.get(Calendar.MONTH)]
                    + "-" + lastDay.get(Calendar.DAY_OF_MONTH) + " " + months[lastDay.get(Calendar.MONTH)];
            //intervalTV.setText(text);
            monthTV.setText(months[firstDay.get(Calendar.MONTH)]);
            if (firstDay.get(Calendar.MONTH) != lastDay.get(Calendar.MONTH))
                yearTV.setText(Integer.toString(firstDay.get(Calendar.DAY_OF_MONTH)) + "-" +
                        Integer.toString(lastDay.get(Calendar.DAY_OF_MONTH)) + " " +
                        months[lastDay.get(Calendar.MONTH)]);
            else
                yearTV.setText(Integer.toString(firstDay.get(Calendar.DAY_OF_MONTH)) + "-" +
                        Integer.toString(lastDay.get(Calendar.DAY_OF_MONTH)));
            lastDay.add(Calendar.DAY_OF_MONTH, 1);
        }
        else { // Monthly
            firstDay.set(Calendar.DAY_OF_MONTH, firstDay.getActualMinimum(Calendar.DAY_OF_MONTH));
            lastDay.set(Calendar.DAY_OF_MONTH, lastDay.getActualMaximum(Calendar.DAY_OF_MONTH));
            monthTV.setText(months[firstDay.get(Calendar.MONTH)]);
            yearTV.setText(Integer.toString(firstDay.get(Calendar.YEAR)));
        }
        startDate = df.format(firstDay.getTime());
        endDate = df.format(lastDay.getTime());
        recycleMaker();
        cb();
        espece();
        cheque();
        cpam();
        total();
        nbrclient();

    }

    public Calendar initializeCalendar(int year, int month, int dayOfMonth) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return c;
    }

    /*@Override
    public void onResume() {
        super.onResume();
        mAdapter = new CpamEventAdapter(this, sqlQuery(), TYPE);
        recyclerView.setAdapter(mAdapter);

    }*/

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
                Intent mainIntent = new Intent(Cpamvalider.this, MainActivitycalendrier.class);
                startActivity(mainIntent);
                return true;
            case R.id.weekly:
                if (TYPE.equals("Monthly")) {
                    Intent weeklyIntent = new Intent(Cpamvalider.this, Cpam.class);
                    weeklyIntent.putExtra("Type", "Weekly");
                    startActivity(weeklyIntent);
                }
                return true;

            case R.id.monthly:
                if (TYPE.equals(("Weekly"))) {
                    Intent monthlyIntent = new Intent(Cpamvalider.this, Cpam.class);
                    monthlyIntent.putExtra("Type", "Monthly");
                    startActivity(monthlyIntent);
                }
                return true;
            case R.id.settings:
                Intent settingsIntent = new Intent(Cpamvalider.this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.graph:
                Intent graphIntent= new Intent (Cpamvalider.this,Graphique.class);
                startActivity(graphIntent);
                return true;
            case R.id.avalider:
                Intent cam1Intent= new Intent (Cpamvalider.this, Cpam.class);
                startActivity(cam1Intent);
                return true;
            case R.id.validerfinal:
                Intent validerIntent= new Intent (Cpamvalider.this, Cpamvalider.class);
                startActivity(validerIntent);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
    private void updateItem(long id){
        cv = new ContentValues();
        String eventName="Oui";
        cv.put(EventDB.Event.COLUMN_CPAM, eventName);
        mDatabase.update(EventDB.Event.TABLE_NAME, cv, EventDB.Event.COLUMN_ID + "=" + id,
                null);

        mAdapter.swapCursor(sqlQuery());



    }


}
