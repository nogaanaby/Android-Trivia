package com.example.trivia;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DatabaseHelper(this);
        userId = getIntent().getIntExtra("USER_ID", -1);
        if(userId == -1) {
            throw new IllegalArgumentException("USER_ID not passed in intent");
        }
        int lastScore = dbHelper.getLastScore(userId);
        TextView last_scoreTextView = findViewById(R.id.last_score);
        if(lastScore==-1){
            last_scoreTextView.setText("No previous score");
        }else {
            last_scoreTextView.setText("Last Score: " + lastScore);
        }
        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.putExtra("USER_ID", userId);
                startActivity(intent);
            }
        });
    }
}