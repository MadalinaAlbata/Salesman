package com.example.iss;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String database_name = "iss.db";
    public static final String TABLE_NAME ="utilizatori_table";
    public static final String tablename = "PRODUS";
    public static final String col0 = "ID";
    public static final String col1 = "NUME_PRODUS";
    public static final String col2 = "PRET_PRODUS";
    public static final String col3 = "CANT_PRODUS";
    public static final String col4="DESCRIERE_PRODUS";

    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";
    public static final String COL_4 ="usertype";
    public static final String COL_5 ="CNP";
    public static final String COL_6 ="nume";
    public static final String COL_7 ="adresa";
    public static final String COL_8 ="telefon";



    public DataBaseHelper(Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + tablename + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NUME_PRODUS TEXT,PRET_PRODUS TEXT,CANT_PRODUS TEXT,DESCRIERE_PRODUS TEXT)");
        db.execSQL("CREATE TABLE "+TABLE_NAME+"  (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT, usertype TEXT , CNP TEXT , nume TEXT , adresa TEXT , telefon TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tablename);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);

    }


    public ArrayList<Produs> getAllProduse() {
        ArrayList<Produs> produs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rez = db.rawQuery("select NUME_PRODUS,PRET_PRODUS,CANT_PRODUS ,DESCRIERE_PRODUS from " + tablename, null);
        while (rez.moveToNext()) {
            String nume_produs = rez.getString(0);
            String pret_produs = rez.getString(1);
            String cant_produs = rez.getString(2);
            String descriere=rez.getString(3);
            int c=Integer.parseInt(cant_produs);
            float pret=Float.parseFloat(pret_produs);
            Produs p = new Produs(nume_produs, pret, c,descriere);
            produs.add(p);
        }
        return produs;
    }

    public boolean insertProdus(String nume,String pret,String cant,String descriere) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new  ContentValues();
        cv.put(col1, nume);
        cv.put(col2, pret);
        cv.put(col3,cant);
        cv.put(col4,descriere);
        long result=database.insert( tablename, null, cv );
        if(result==-1)
            return false;
        else return true;
    }

    public boolean updateCantitate(String nume,String pret,String cantitate){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
     //   cv.put(col1,nume);
       // cv.put(col2,pret);
        cv.put(col3,cantitate);
        String[] vector=new String[]{nume};
        long result=db.update("PRODUS",cv,"NUME_PRODUS = ? ",vector);
        if(result==-1)
            return false;
        else return true;
    }

    public Cursor getNume(String nume){
        SQLiteDatabase db=this.getWritableDatabase();
        String[] vector=new String[]{nume};
        Cursor cursor=db.rawQuery("select * FROM "+ tablename + " where NUME_PRODUS = ?",vector);
        return cursor;
    }

    public ArrayList<Produs> getProdusewhereCantitate(){
        ArrayList<Produs> produs = new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor rez=db.rawQuery("select * from "+ tablename + " where CANT_PRODUS = ?",new String[]{"0"});
        while (rez.moveToNext()) {
            String nume_produs = rez.getString(0);
            String pret_produs = rez.getString(1);
            String cant_produs = rez.getString(2);
            String descriere=rez.getString(3);
            int c=Integer.parseInt(cant_produs);
            float pret=Float.parseFloat(pret_produs);

            Produs p = new Produs(nume_produs, pret, c,descriere);
            produs.add(p);
    }
    return produs;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tablename, "ID = ?", new String[]{id});
    }
    public String getId(String nume,String pret,String cantitate,String descr){
        int nr=0;

        SQLiteDatabase dB=this.getReadableDatabase();
        String[] v=new String[]{nume};
        Cursor c= dB.rawQuery("select ID FROM "+ tablename +" where NUME_PRODUS=? ",v);
       if(c.getCount()>0)
       {
           c.moveToNext();
           nr=c.getInt(0);
       }       c.close();
        return nr+"";
    }

    public boolean updateData(String nume, String pret, String cant, String d) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, pret);
        contentValues.put(col3, cant);
        contentValues.put(col4,d);
        String[] vector = new String[]{nume};
        long result = db.update("PRODUS", contentValues, "NUME_PRODUS = ?", vector);
        if (result == -1)
            return false;
        else return true;
    }

    public long addUser(String user, String password, String usertype,String cnp, String adresa, String nume, String telefon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        contentValues.put("usertype",usertype);
        contentValues.put("CNP",cnp);
        contentValues.put("adresa",adresa);
        contentValues.put("nume",nume);
        contentValues.put("telefon",telefon);
        long res = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser(String username, String password,String usertype){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?" + " and " + COL_4 + "=?";
        String[] selectionArgs = { username, password ,usertype};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }



}
