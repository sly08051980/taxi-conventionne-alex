package com.slyfly1.taxiconventionne77.calendrier;


import android.Manifest;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.slyfly1.taxiconventionne77.R;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class importtest extends ListActivity {

    TextView lbl;
    EventDBHelper controller;
    Button btnimport;
    ListView lv;
    final Context context = this;
    ListAdapter adapter;

    ArrayList<HashMap<String, String>> myList;
    public static final int requestcode = 1;
    private static final int REQUEST_ID_READ_PERMISSION = 100;
    private static final int REQUEST_ID_WRITE_PERMISSION = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importtest);
        controller = new EventDBHelper(this);
        lbl = (TextView) findViewById(R.id.txtresulttext);
        btnimport = (Button) findViewById(R.id.btnupload);
        lv = getListView();
        btnimport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.setType("text/csv");
                try {
                    startActivityForResult(fileintent, requestcode);
                } catch (ActivityNotFoundException e) {
                    lbl.setText("No activity can handle picking a file. Showing alternatives.");
                }
            }
        });

        myList = controller.getAllProducts();
        if (myList.size() != 0) {
            ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter(importtest.this, myList,
                    R.layout.lst_template, new String[]{"a", "b", "c","d","e","f","g","h","i","j","k","l","m","n","o"}, new int[]{
                    R.id.txtproductcompany, R.id.txtproductname, R.id.txtproductprice});
            setListAdapter(adapter);
            lbl.setText("");

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_ID_READ_PERMISSION);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }

        if (data == null)

            return;
        switch (requestCode) {

            case requestcode:

                String filepath = data.getData().getPath();
                Log.e("File path", filepath);

                if (filepath.contains("/root_path"))
                    filepath = filepath.replace("/root_path", "");

                Log.e("New File path", filepath);
                controller = new EventDBHelper(getApplicationContext());
                SQLiteDatabase db = controller.getWritableDatabase();



                try {

                    if (resultCode == RESULT_OK) {
                        Log.e("RESULT CODE", "OK");
                        try {
                            FileReader file = new FileReader(filepath);
                            BufferedReader buffer = new BufferedReader(file);
                            ContentValues contentValues = new ContentValues();
                            String line = "";
                            db.beginTransaction();

                            while ((line = buffer.readLine()) != null) {

                                Log.e("line", line);
                                String[] str = line.split(",", 15); // defining 3 columns with null or blank field //values acceptance

//Id, Company,Name,Price

                                String ID = str[0].toString();
                                String NAME = str[1].toString();
                                String START = str[2].toString();
                                String END = str[3].toString();
                                String SERI_NO = str[4].toString();
                                String SERI_TYPE = str[5].toString();
                                String LOCATION = str[6].toString();
                                String LOCATION_LINK = str[7].toString();
                                String ARRIVER = str[8].toString();
                                String PRENOM = str[9].toString();
                                String CPAM = str[10].toString();
                                String TELEPHONE = str[11].toString();
                                String MONTANT = str[12].toString();
                                String MODEPAYEMENT = str[13].toString();
                                String NOTE = str[14].toString();

                                contentValues.put(EventDBHelper.COLUMN_ID, ID);
                                contentValues.put(EventDBHelper.COLUMN_NAME, NAME);
                                contentValues.put(EventDBHelper.COLUMN_START, START);
                                contentValues.put(EventDBHelper.COLUMN_END, END);
                                contentValues.put(EventDBHelper.COLUMN_SERI, SERI_NO);
                                contentValues.put(EventDBHelper.COLUMN_SERI_TYPE, SERI_TYPE);
                                contentValues.put(EventDBHelper.COLUMN_LOCATION, LOCATION);
                                contentValues.put(EventDBHelper.COLUMN_LOCATION_LINK, LOCATION_LINK);
                                contentValues.put(EventDBHelper.COLUMN_ARRIVER, ARRIVER);
                                contentValues.put(EventDBHelper.COLUMN_PRENOM, PRENOM);
                                contentValues.put(EventDBHelper.COLUMN_CPAM, CPAM);
                                contentValues.put(EventDBHelper.COLUMN_TELEPHONE, TELEPHONE);
                                contentValues.put(EventDBHelper.COLUMN_MONTANT, MONTANT);
                                contentValues.put(EventDBHelper.COLUMN_MODEPAYEMENT, MODEPAYEMENT);
                                contentValues.put(EventDBHelper.COLUMN_NOTE, NOTE);

                                db.insert(EventDBHelper.TABLE_NAME, null, contentValues);
                                String db1 = "INSERT INTO PHOTO (Id, name, lat, lon, image) VALUES(NULL,?,?,?,?)";
                                lbl.setText("Successfully Updated Database.");
                                Log.e("Import", "Successfully Updated Database.");
                            }
                            db.setTransactionSuccessful();

                            db.endTransaction();

                        } catch (SQLException e) {
                            Log.e("SQLError", e.getMessage().toString());
                        } catch (IOException e) {
                            Log.e("IOException", e.getMessage().toString());

                        }
                    } else {
                        Log.e("RESULT CODE", "InValid");
                        if (db.inTransaction())

                            db.endTransaction();
                        Toast.makeText(importtest.this, "Only CSV files allowed.", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception ex) {
                    Log.e("Error", ex.getMessage().toString());
                    if (db.inTransaction())

                        db.endTransaction();

                    Toast.makeText(importtest.this, ex.getMessage(), Toast.LENGTH_LONG).show();

                }

        }

        myList = controller.getAllProducts();

        if (myList.size() != 0) {

            ListView lv = getListView();

            ListAdapter adapter = new SimpleAdapter(importtest.this, myList,

                    R.layout.lst_template, new String[]{"a", "b", "c"}, new int[]{
                    R.id.txtproductcompany, R.id.txtproductname, R.id.txtproductprice});

            setListAdapter(adapter);

            lbl.setText("Data Imported");

        }
    }

}