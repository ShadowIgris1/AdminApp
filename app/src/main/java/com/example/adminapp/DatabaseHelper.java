//package com.example.adminapp;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.os.Environment;
//
//import java.io.File;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//    private static final String DATABASE_NAME = "unencrypted_credentials.db";
//    private static final int DATABASE_VERSION = 1;
//    public static final String TABLE_NAME = "users";
//    public static final String COLUMN_USERNAME = "username";
//    public static final String COLUMN_PASSWORD = "password";
//    private static String DATABASE_PATH;
//
//    // SQL statement to create the user table
//    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
//            COLUMN_USERNAME + " TEXT, " +
//            COLUMN_PASSWORD + " TEXT);";
//
//    public DatabaseHelper(Context context) {
//        super(context, getDatabasePath(context), null, DATABASE_VERSION);
//    }
//
//    // Method to set the database path in the Downloads directory
//    private static String getDatabasePath(Context context) {
//        // Get the Downloads directory path
//        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//        if (!downloadsDir.exists()) {
//            downloadsDir.mkdirs(); // Create directory if not exists
//        }
//
//        // Create the full path for the database file in the Downloads folder
//        File databaseFile = new File(downloadsDir, DATABASE_NAME);
//        DATABASE_PATH = databaseFile.getAbsolutePath();
//        return DATABASE_PATH;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // Create the users table
//        db.execSQL(DATABASE_CREATE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }
//
//    // Method to add a user (for testing)
//    public void addUser(String username, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String insertQuery = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_USERNAME + ", " + COLUMN_PASSWORD + ") VALUES (?, ?)";
//        db.execSQL(insertQuery, new Object[]{username, password});
//        db.close(); // Ensure the database is closed after operations
//    }
//}


package com.example.adminapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "unencrypted_credentials.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    private static DatabaseHelper instance;
    private static String DATABASE_PATH;

    // SQL statement to create the user table
    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            COLUMN_USERNAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT);";

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, getDatabasePath(context), null, DATABASE_VERSION);
    }

    // Method to set the database path in the Downloads directory
    private static String getDatabasePath(Context context) {
        // Get the Downloads directory path
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (!downloadsDir.exists()) {
            downloadsDir.mkdirs(); // Create directory if not exists
        }

        // Create the full path for the database file in the Downloads folder
        File databaseFile = new File(downloadsDir, DATABASE_NAME);
        DATABASE_PATH = databaseFile.getAbsolutePath();

        // Log the database path for debugging
        Log.d("DatabaseHelper", "Database Path: " + DATABASE_PATH);

        return DATABASE_PATH;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the users table
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to add a user (for testing)
    public void addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_USERNAME + ", " + COLUMN_PASSWORD + ") VALUES (?, ?)";
        db.execSQL(insertQuery, new Object[]{username, password});
        db.close(); // Ensure the database is closed after operations
    }
}
