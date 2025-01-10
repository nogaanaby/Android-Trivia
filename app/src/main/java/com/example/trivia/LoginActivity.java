// File: app/src/main/java/com/example/trivia/LoginActivity.java

package com.example.trivia;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);


        //dummy create user:
        //dbHelper.addUser("root", "1234");

        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (validateLogin(username, password)) {
                    Cursor cursor = dbHelper.getUser(username);
                    if (cursor != null && cursor.moveToFirst()) {
                        int userIdColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_ID);
                        if (userIdColumnIndex != -1) {
                            int userId = cursor.getInt(userIdColumnIndex);
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("USER_ID", userId);
                            startActivity(intent);
                            finish();
                        }
                        cursor.close();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        Cursor cursor = dbHelper.getUser(username);
        if (cursor != null && cursor.moveToFirst()) {
            int passwordColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD);
            if (passwordColumnIndex != -1) {
                String storedPassword = cursor.getString(passwordColumnIndex);
                cursor.close();
                return password.equals(storedPassword);
            }
            cursor.close();
        }
        return false;
    }
}

/*
adb devices

adb -s emulator-5554 shell
run-as com.example.trivia
cd databases
sqlite3 trivia.db
.tables
SELECT * FROM users;



adb shell /data/local/tmp/sqlite3 /data/data/com.example.trivia/databases/trivia.db
*/
