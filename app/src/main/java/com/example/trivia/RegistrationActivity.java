//package com.example.trivia;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class RegistrationActivity extends AppCompatActivity {
//
//    private DatabaseHelper dbHelper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_registration);
//
//        dbHelper = new DatabaseHelper(this);
//
//        // Insert test data into the database
//        dbHelper.addUser("testuser", "testpassword");
//
//        EditText usernameEditText = findViewById(R.id.username);
//        EditText passwordEditText = findViewById(R.id.password);
//        Button registerButton = findViewById(R.id.register_button);
//
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = usernameEditText.getText().toString();
//                String password = passwordEditText.getText().toString();
//
//                if (username.isEmpty() || password.isEmpty()) {
//                    Toast.makeText(RegistrationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//                } else {
//                    dbHelper.addUser(username, password);
//                    Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//            }
//        });
//    }
//}