package com.slyfly1.taxiconventionne77.calendrier;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.slyfly1.taxiconventionne77.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Calendar;


import static com.slyfly1.taxiconventionne77.calendrier.SettingsActivity.DarkMode;
import static com.slyfly1.taxiconventionne77.calendrier.SettingsActivity.MyPreferences;


public class MainActivitycalendrier extends AppCompatActivity {


    private static final String SAMPLE_DB_NAME = "calendar";
    private static final String SAMPLE_TABLE_NAME = "Info";

    private CalendarView mCalendarView;
    private Calendar c;
    private Toolbar toolbar;
    private TextView monthTV, yearTV,infotext,montantcb;
    private String[] months = new String[]{"JAN", "FEV", "MAR", "AVR", "MAI", "JUI",
                                            "JUI", "AOU", "SEP", "OCT", "NOV", "DEC"};
    private Button todayButton,buttonexport,buttonimport;
    private static final String LOG_TAG = "ExternalStorageDemo";
    private static final int REQUEST_ID_READ_PERMISSION = 100;
    private static final int REQUEST_ID_WRITE_PERMISSION = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maincalendrier);

        SharedPreferences sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(DarkMode, false)) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
infotext=findViewById(R.id.textView8);
        mCalendarView = findViewById(R.id.calendarView);
        monthTV = findViewById(R.id.monthText);
        yearTV = findViewById(R.id.yearText);
        todayButton = findViewById(R.id.todayButton);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        c = Calendar.getInstance();
        monthTV.setText(months[c.get(Calendar.MONTH)]);
        yearTV.setText(Integer.toString(c.get(Calendar.YEAR)));
        buttonexport=findViewById(R.id.buttonExport);
buttonimport=findViewById(R.id.buttonImport);


        if (!haveBatteryPermission())
            haveBatteryPermission();

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month,
                                            int day) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, day);
                monthTV.setText(months[c.get(Calendar.MONTH)]);
                yearTV.setText(Integer.toString(c.get(Calendar.YEAR)));
                String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

                Intent eventListIntent = new Intent(MainActivitycalendrier.this, EventListActivity.class);
                eventListIntent.putExtra("DAY", day);
                eventListIntent.putExtra("MONTH", month);
                eventListIntent.putExtra("YEAR", year);
                eventListIntent.putExtra("DAY_NAME", currentDateString.split(",")[0]);
                startActivity(eventListIntent);
            }
        });

buttonimport.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent buttonimpo = new Intent(MainActivitycalendrier.this, importtest.class);
        startActivity(buttonimpo);
    }
});

        buttonexport.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) { //main code begins here
                askPermissionAndWriteFile();

            }
        });

        todayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.setDate(c.getTimeInMillis());
                c = Calendar.getInstance();
                monthTV.setText(months[c.get(Calendar.MONTH)]);
                yearTV.setText(Integer.toString(c.get(Calendar.YEAR)));
            }
        });


    }

    public boolean haveBatteryPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS}, 1);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(DarkMode, false)) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
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
            case R.id.weekly:
                Intent weeklyIntent = new Intent(MainActivitycalendrier.this, WeeklyListActivity.class);
                weeklyIntent.putExtra("Type", "Weekly");
                startActivity(weeklyIntent);
                return true;
            case R.id.monthly:
                Intent monthlyIntent = new Intent(MainActivitycalendrier.this, WeeklyListActivity.class);
                monthlyIntent.putExtra("Type", "Monthly");
                startActivity(monthlyIntent);
                return true;
            case R.id.settings:
                Intent settingsIntent = new Intent(MainActivitycalendrier.this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.graph:
                Intent graphIntent= new Intent (MainActivitycalendrier.this,Graphique.class);
                startActivity(graphIntent);
                return true;
            case R.id.avalider:
                Intent cam1Intent= new Intent (MainActivitycalendrier.this, Cpam.class);
                startActivity(cam1Intent);
                return true;
            case R.id.validerfinal:
                Intent validerIntent= new Intent (MainActivitycalendrier.this, Cpamvalider.class);
                startActivity(validerIntent);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }




    private void askPermissionAndWriteFile() {
        boolean canWrite = this.askPermission(REQUEST_ID_WRITE_PERMISSION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(!canWrite)  {
            Toast.makeText(getApplicationContext(),
                    "You do not allow this app to write files.", Toast.LENGTH_LONG).show();
            return;
        }
        //
        this.writeFile();
    }

    private boolean askPermission(int requestId, String permissionName) {


        Log.i(LOG_TAG, "Ask for Permission: " + permissionName);
        Log.i(LOG_TAG, "Build.VERSION.SDK_INT: " + Build.VERSION.SDK_INT);

        if (Build.VERSION.SDK_INT >= 23) {

            // Check if we have permission
            int permission = ActivityCompat.checkSelfPermission(this, permissionName);

            Log.i(LOG_TAG, "permission: " + permission);
            Log.i(LOG_TAG, "PackageManager.PERMISSION_GRANTED: " + PackageManager.PERMISSION_GRANTED);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                this.requestPermissions(
                        new String[]{permissionName},
                        requestId
                );
                return false;
            }
        }
        return true;
    }


    // As soon as the user decides, allows or doesn't allow.
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        // Note: If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0) {
            switch (requestCode) {
                case REQUEST_ID_READ_PERMISSION: {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        readFile();
                    }
                }
                case REQUEST_ID_WRITE_PERMISSION: {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        writeFile();
                    }
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Permission Cancelled!", Toast.LENGTH_SHORT).show();
        }
    }

    // IMPORTANT!!
    public File getAppExternalFilesDir()  {
        if (Build.VERSION.SDK_INT >= 29) {
            // /storage/emulated/0/Android/data/org.o7planning.externalstoragedemo/files
            return this.getExternalFilesDir(null);
        } else {
            // @Deprecated in API 29.
            // /storage/emulated/0
            return Environment.getExternalStorageDirectory();
        }
    }

    private void writeFile() {
        EventDBHelper dbHelper = new EventDBHelper(MainActivitycalendrier.this);
        SQLiteDatabase sqldb = dbHelper.getReadableDatabase(); //My Database class
        Cursor c = null;
        try {
            c = sqldb.rawQuery("select * from EVENTS", null);
            int rowcount = 0;
            int colcount = 0;

            File sdCardDir = this.getAppExternalFilesDir( );
            Boolean canWrite = sdCardDir.canWrite();
            Log.i(LOG_TAG, "Can write: " + sdCardDir.getAbsolutePath()+" : " + canWrite);

            // ==> /storage/emulated/0/note.txt  (API < 29)
            // ==> /storage/emulated/0/Android/data/org.o7planning.externalstoragedemo/files/note.txt (API >=29)
            String path = sdCardDir.getAbsolutePath() + "/" + "MyBackUp.csv";
            String filename = "MyBackUp.csv";
            // the name of the file to export with
            File saveFile = new File(sdCardDir, filename);
            FileWriter fw = new FileWriter(saveFile);

            BufferedWriter bw = new BufferedWriter(fw);
            rowcount = c.getCount();
            colcount = c.getColumnCount();
            if (rowcount > 0) {
                c.moveToFirst();

                for (int i = 0; i < colcount; i++) {
                    if (i != colcount - 1) {

                        bw.write(c.getColumnName(i) + ",");

                    } else {

                        bw.write(c.getColumnName(i));

                    }
                }
                bw.newLine();

                for (int i = 0; i < rowcount; i++) {
                    c.moveToPosition(i);

                    for (int j = 0; j < colcount; j++) {
                        if (j != colcount - 1)
                            bw.write(c.getString(j) + ",");
                        else
                            bw.write(c.getString(j));
                    }
                    bw.newLine();
                }
                bw.flush();
                infotext.setText("Exported Successfully.");
                Toast.makeText(MainActivitycalendrier.this, "Accepter", Toast.LENGTH_SHORT).show();
                sendEmail();
            }
        } catch (Exception ex) {
            if (sqldb.isOpen()) {
                sqldb.close();
                infotext.setText(ex.getMessage().toString());
                Toast.makeText(MainActivitycalendrier.this, "Refuser", Toast.LENGTH_SHORT).show();
            }

        } finally {

        }
    }

    private void readFile() {

        File extStore = this.getAppExternalFilesDir();

        // ==> /storage/emulated/0/note.txt  (API < 29)
        // ==> /storage/emulated/0/Android/data/org.o7planning.externalstoragedemo/note.txt (API >=29)
        String path = extStore.getAbsolutePath() + "/" + "sisi";
        Log.i(LOG_TAG, "Read file: " + path);

        String s = "";
        String fileContent = "";
        try {
            File myFile = new File(path);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));

            while ((s = myReader.readLine()) != null) {
                fileContent += s + "\n";
            }
            myReader.close();


        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Read Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(LOG_TAG, "Read Error: " + e.getMessage());
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), fileContent, Toast.LENGTH_LONG).show();
    }



    protected void sendEmail() {
        Log.i("Send email", "");

        String[] TO = {"regnier.sylvain1980@gmail.com"};
        String[] CC = {"regnier.sylvain@yahoo.fr"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        File sdCardDir = this.getAppExternalFilesDir( );

        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(sdCardDir.getAbsolutePath()+"/"  + "MyBackUp.csv"));

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "test");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "test1");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivitycalendrier.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }



}
