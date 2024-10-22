//package com.example.adminapp;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import android.database.sqlite.SQLiteDatabase;
//
//public class MainActivity extends AppCompatActivity {
//    private DatabaseHelper dbHelper;
//    private EditText usernameEditText, passwordEditText;
//    private Button saveButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Initialize the DatabaseHelper
//        dbHelper = new DatabaseHelper(this);
//        usernameEditText = findViewById(R.id.username);
//        passwordEditText = findViewById(R.id.password);
//        saveButton = findViewById(R.id.save_button);
//
//        saveButton.setOnClickListener(v -> saveCredentials());
//    }
//
//    private void saveCredentials() {
//        String username = usernameEditText.getText().toString();
//        String password = passwordEditText.getText().toString();
//
//        if (username.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Open the database without encryption
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        // Insert the credentials into the database
//        db.execSQL("INSERT INTO " + DatabaseHelper.TABLE_NAME + " ("
//                + DatabaseHelper.COLUMN_USERNAME + ", "
//                + DatabaseHelper.COLUMN_PASSWORD + ") VALUES ('"
//                + username + "', '" + password + "');");
//
//        Toast.makeText(this, "Credentials saved", Toast.LENGTH_SHORT).show();
//        db.close();
//    }
//}
package com.example.adminapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText usernameEditText, passwordEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the DatabaseHelper
        dbHelper = DatabaseHelper.getInstance(this);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(v -> saveCredentials());
    }

    private void saveCredentials() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Open the database without encryption
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Insert the credentials into the database
        db.execSQL("INSERT INTO " + DatabaseHelper.TABLE_NAME + " (" +
                        DatabaseHelper.COLUMN_USERNAME + ", " +
                        DatabaseHelper.COLUMN_PASSWORD + ") VALUES (?, ?);",
                new Object[]{username, password});

        Toast.makeText(this, "Credentials saved", Toast.LENGTH_SHORT).show();
        db.close();
    }
}
