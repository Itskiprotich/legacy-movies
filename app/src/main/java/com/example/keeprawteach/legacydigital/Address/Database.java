package com.example.keeprawteach.legacydigital.Address;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Car_Parking.db";
    // User table name
    private static final String TABLE_USER = "users";
    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_FIRST_NAME = "user_first_name";
    private static final String COLUMN_USER_LAST_NAME = "user_last_name";
    private static final String COLUMN_USER_PHONE = "user_phone";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_CAR = "user_car";
    private static final String COLUMN_USER_TYPE = "user_type";
    private static final String COLUMN_USER_PROFILE = "user_profile";


    // User table name
    private static final String TABLE_IP = "ipaddress";
    // User Table Columns names
    private static final String COLUMN_IP_ID = "id";
    private static final String COLUMN_IP_NUMBER = "ip_number";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USER + "(" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_first_name TEXT," +
                "user_last_name TEXT," +
                "user_phone TEXT," +
                "user_email TEXT," +
                "user_password TEXT," +
                "user_car TEXT," +
                "user_type TEXT," +
                "user_profile BLOB)");

        db.execSQL("CREATE TABLE " + TABLE_IP + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ip_number TEXT)");


        String ip = "192.168.43.159";

        ContentValues values = new ContentValues();

        values.put(COLUMN_IP_NUMBER, ip);

        db.insert(TABLE_IP, null, values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IP);

        onCreate(db);
    }

    public boolean addUser(String firstname, String lastname, String phonenumber, String emailadress, String password, String car, String type) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_FIRST_NAME, firstname);

        values.put(COLUMN_USER_LAST_NAME, lastname);

        values.put(COLUMN_USER_PHONE, phonenumber);

        values.put(COLUMN_USER_EMAIL, emailadress);

        values.put(COLUMN_USER_PASSWORD, password);

        values.put(COLUMN_USER_CAR, car);

        values.put(COLUMN_USER_TYPE, type);

        long bool = db.insert(TABLE_USER, null, values);

        if (bool == -1) {

            return false;

        }

        return true;
    }


    public String CheckUser(String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String em = cursor.getString(4);

                String pa = cursor.getString(5);

                if (email.equalsIgnoreCase(em)) {

                    if (password.equals(pa)) {

                        return "Success";

                    }

                    return "Incorrect Credentials";
                }
            }
            return "User does not Exist";
        }
        return "No data Found";

    }

    public Cursor check(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM users WHERE user_id=" + id + "", null);

        return res;

    }

    public Cursor checkIp(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM ipaddress WHERE id=" + id + "", null);

        return res;

    }

    public boolean updateIp(String name, String id) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_IP_NUMBER, name);

        long bool = database.update(TABLE_IP, contentValues, "" + COLUMN_IP_ID + " =?", new String[]{id});

        if (bool == -1) {

            return false;

        }

        return true;
    }


    public String CheckPin(String pin, String email) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String em = cursor.getString(4);

                String pa = cursor.getString(6);

                if (email.equalsIgnoreCase(em)) {

                    if (pin.equals(pa)) {

                        return "Success";

                    }

                    return "Incorrect Pin";
                }
            }

            return "User does not Exist";
        }

        return "No data Found";
    }

    public Cursor searchEmail() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);

        return cursor;
    }

    public boolean changePass(String email, String pass) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USER_PASSWORD, pass);

        long bool = database.update(TABLE_USER, contentValues, "" + COLUMN_USER_EMAIL + " =?", new String[]{email});

        if (bool == -1) {

            return false;

        }

        return true;
    }

    public boolean updateProfile(byte[] byteArray, String id) {
        return false;
    }

    public boolean logout() {

        SQLiteDatabase database = this.getWritableDatabase();

        long bool = database.delete(TABLE_USER,null,null);

        if (bool == -1) {

            return false;

        }
        return true;
    }

    public boolean updateProfele(byte[] byteArray, String id) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_PROFILE, byteArray);
        long bool = database.update(TABLE_USER, contentValues, "" + COLUMN_USER_ID + " =?", new String[]{id});
        if (bool == -1) {
            return false;

        }
        return true;
    }

    public boolean updateCar(String a, String b, String id) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_CAR, a);
        long bool = database.update(TABLE_USER, contentValues, "" + COLUMN_USER_ID + " =?", new String[]{id});
        if (bool == -1) {
            return false;

        }
        return true;
    }
}

