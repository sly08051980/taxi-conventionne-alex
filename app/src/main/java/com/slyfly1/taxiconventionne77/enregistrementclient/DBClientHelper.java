package com.slyfly1.taxiconventionne77.enregistrementclient;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBClientHelper extends SQLiteOpenHelper {
    public DBClientHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(nom TEXT , prenom TEXT, telephone TEXT primary key,adresse Text,arriver Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String nom, String prenom, String telephone,String adresse,String arriver)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nom", nom);
        contentValues.put("prenom", prenom);
        contentValues.put("telephone", telephone);
        contentValues.put("adresse",adresse);
        contentValues.put("arriver",arriver);
        long result=DB.insert("Userdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


    public Boolean updateuserdata(String nom, String prenom, String telephone,String adresse,String arriver) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("prenom", prenom);
        contentValues.put("telephone", telephone);
        contentValues.put("adresse",adresse);
        contentValues.put("arriver",arriver);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where nom = ?", new String[]{nom});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "nom=?", new String[]{nom});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean deletedata (String nom)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where nom = ?", new String[]{nom});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "nom=?", new String[]{nom});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails ORDER BY nom ASC", null);
        return cursor;

    }

    public Cursor recherchedata(String nom){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where nom = ?", new String[]{nom});
    return cursor;
    }

    public Cursor recherchetelephonedata(String telephone){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where telephone = ?", new String[]{telephone});
        return cursor;
    }
}
