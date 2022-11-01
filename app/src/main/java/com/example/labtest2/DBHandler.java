package com.example.labtest2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {


    private static final String DB_NAME = "contactsDB" ;
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "contact";

    //varialbes for each column
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String PHONE_COL = "phone";
    private static final String EMAIL_COL = "email";
    private static final String ADDRESS_COL = "address";

    //constructor for database handler
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_NAME + "("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + PHONE_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + ADDRESS_COL + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    public void addContact(String name,String phone,String email,String address) {

        //creating a variable for database and calling writable method for  writing data in to database.
        SQLiteDatabase db = this.getWritableDatabase();

        //creating a variable for content values.
        ContentValues values = new ContentValues();

        //passing all values along with its key and value pair
        values.put(NAME_COL, name);
        values.put(PHONE_COL, phone);
        values.put(EMAIL_COL, email);
        values.put(ADDRESS_COL, address);

        //passing content values to table.
        db.insert(TABLE_NAME, null, values);

        db.close();

    }

    public Cursor viewContacts(){
        SQLiteDatabase db = this.getWritableDatabase();
       // db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        Cursor res = db.rawQuery("select * from " +TABLE_NAME,null);
        return res;
    }

    public Cursor getContact(int id1){
        SQLiteDatabase db = this.getWritableDatabase();
        String id = Integer.toString(id1+1);
        Cursor res = db.rawQuery("select * from " +TABLE_NAME+ " where id = '" + id + "'",null);
      //  Cursor res = db.rawQuery("select * from " +TABLE_NAME+ " where id = '1'",null);
        return res;
    }

    public Cursor getContactbyName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        //String id = Integer.toString(id1+1);
        Cursor res = db.rawQuery("select * from " +TABLE_NAME+ " where name = '" + name + "'",null);
        //  Cursor res = db.rawQuery("select * from " +TABLE_NAME+ " where id = '1'",null);
        return res;
    }

    public boolean deleteContact(int id1){
        SQLiteDatabase db = this.getWritableDatabase();
        String id = Integer.toString(id1+1);

         db.delete(TABLE_NAME,"ID = ?",new String[]{ id });
        //  Cursor res = db.rawQuery("select * from " +TABLE_NAME+ " where id = '1'",null);
        return true;
    }

    public boolean updateContact(int id1,String name,String phone,String email,String address){
        SQLiteDatabase db = this.getWritableDatabase();
        String id = Integer.toString(id1+1);
        ContentValues values = new ContentValues();
      //  values.put(ID_COL,id);
        values.put(NAME_COL,name);
        values.put(PHONE_COL,phone);
        values.put(EMAIL_COL,email);
        values.put(ADDRESS_COL,address);
        db.update(TABLE_NAME, values,"ID = ?",new String[] { id });
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
