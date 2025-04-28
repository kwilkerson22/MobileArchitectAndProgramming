package com.example.kennethwilkersonproject2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

// DatabaseHelper class for managing SQLite database operations
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name and table constants
    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "users";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PASSWORD";

    // Constructor to initialize the database
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // Called when database is first created
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)");
    }

    // Called when the database version is upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to add new user to the database
    public boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase(); // Open database
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username); // Add username to ContentValues
        contentValues.put(COL_3, password); // Add password to ContentValues
        long result = db.insert(TABLE_NAME, null, contentValues); // Insert into database
        return result != -1; // Return true for successful insert
    }

    // Method to check if user with username/password already exists
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase(); // Open database
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE USERNAME=? AND PASSWORD=?", new String[]{username, password});
        boolean exists = (cursor.getCount() > 0); // Check for any matching records
        cursor.close(); // Close cursor to prevent memory leak
        return exists;
    }
}
