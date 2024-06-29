package com.slyfly1.taxiconventionne77.calendrier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


public class EventDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "calendar.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "EVENTS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_START = "START_DATE";
    public static final String COLUMN_END = "END_DATE";
    public static final String COLUMN_SERI = "SERI_NO";
    public static final String COLUMN_SERI_TYPE = "SERI_TYPE";
    public static final String COLUMN_LOCATION = "LOCATION";
    public static final String COLUMN_LOCATION_LINK = "LOCATION_LINK";
    public static final String COLUMN_ARRIVER="ARRIVER";

    public static final String COLUMN_PRENOM="PRENOM";
    public static final String COLUMN_CPAM="CPAM";
    public static final String COLUMN_TELEPHONE="TELEPHONE";
    public static final String COLUMN_MONTANT="MONTANT";
    public static final String COLUMN_MODEPAYEMENT="MODEPAYEMENT";
    public static final String COLUMN_CONFIRMERCPAM="CONFIRMERCPAM";
    public static final String COLUMN_NOTE = "NOTE";
    public String dbPath;

    public EventDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.dbPath = context.getDatabasePath(DATABASE_NAME).getAbsolutePath();


        Log.e("DB Path", dbPath);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" + EventDB.Event.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EventDB.Event.COLUMN_NAME + " TEXT NOT NULL, " +
                EventDB.Event.COLUMN_START + " TEXT NOT NULL, " +
                EventDB.Event.COLUMN_END + " TEXT NOT NULL, " +
                EventDB.Event.COLUMN_SERI + " INTEGER DEFAULT -1, " +
                EventDB.Event.COLUMN_SERI_TYPE + " TEXT, " +
                EventDB.Event.COLUMN_LOCATION + " TEXT, " +
                EventDB.Event.COLUMN_LOCATION_LINK + " TEXT, " +
                EventDB.Event.COLUMN_ARRIVER + " TEXT, " +
                EventDB.Event.COLUMN_PRENOM + " TEXT, " +
                EventDB.Event.COLUMN_CPAM + " TEXT, " +
                EventDB.Event.COLUMN_TELEPHONE + " TEXT, " +
                EventDB.Event.COLUMN_MONTANT + " TEXT, " +
                EventDB.Event.COLUMN_MODEPAYEMENT + " TEXT, " +
                EventDB.Event.COLUMN_NOTE + " TEXT," +
                EventDB.Event.COLUMN_CONFIRMERCPAM + "TEXT"+");";

        final String SQL_CREATE_REMINDER_TABLE = "CREATE TABLE " + EventDB.Event.REMINDER_TABLE_NAME +
                "(" + EventDB.Event.REMINDER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EventDB.Event.REMINDER_COLUMN_EID + " INTEGER NOT NULL, " +
                EventDB.Event.REMINDER_COLUMN_DATE + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_EVENT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_REMINDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EventDB.Event.REMINDER_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getAllProducts() {

        ArrayList<HashMap<String, String>> productList;
        productList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {
//Id, Company,Name,Price
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("a", cursor.getString(0));
                map.put("b", cursor.getString(1));
                map.put("c", cursor.getString(2));
                map.put("d", cursor.getString(3));
                map.put("e", cursor.getString(4));
                map.put("f", cursor.getString(5));
                map.put("g", cursor.getString(6));
                map.put("h", cursor.getString(7));
                map.put("i", cursor.getString(8));
                map.put("j", cursor.getString(9));
                map.put("k", cursor.getString(10));
                map.put("l", cursor.getString(11));
                map.put("m", cursor.getString(12));
                map.put("n", cursor.getString(13));
                map.put("o", cursor.getString(14));
                productList.add(map);
                Log.e("dataofList", cursor.getString(0) + "," + cursor.getString(1) + "," + cursor.getString(2)+ "," + cursor.getString(3)+ "," + cursor.getString(4)+ "," + cursor.getString(5)+ "," + cursor.getString(6)+ "," + cursor.getString(7)+ "," + cursor.getString(8)+ "," + cursor.getString(9)+ "," + cursor.getString(10)+ "," + cursor.getString(11)+ "," + cursor.getString(12)+ "," + cursor.getString(13)+ "," + cursor.getString(14));
            } while (cursor.moveToNext());
        }
        return productList;

    }
    public Boolean insertuserdata(String nom, String heuredepart, String heurearriver,String serino,String seritype,String depart,String locationlink,String arriver,String prenom,
                                  String cpam,String telephone,String montant,String modepayement,String note)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", nom);
        contentValues.put("START_DATE", heuredepart);
        contentValues.put("END_DATE", heurearriver);
        contentValues.put("SERI_NO",serino);
        contentValues.put("SERI_TYPE",seritype);
        contentValues.put("LOCATION", depart);
        contentValues.put("LOCATION_LINK", locationlink);
        contentValues.put("ARRIVER", arriver);
        contentValues.put("PRENOM",prenom);
        contentValues.put("CPAM",cpam);
        contentValues.put("TELEPHONE", telephone);
        contentValues.put("MONTANT", montant);
        contentValues.put("MODEPAYEMENT",modepayement);
        contentValues.put("NOTE",note);

        long result=sqLiteDatabase.insert("EVENTS", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


}
