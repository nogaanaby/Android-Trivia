// File: app/src/main/java/com/example/trivia/ScoreBoard.java

package com.example.trivia;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;

public class ScoreBoard extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_board);

        dbHelper = new DatabaseHelper(this);


        // get the user
        userId = getIntent().getIntExtra("USER_ID", -1);

        TextView scoreTextView = findViewById(R.id.score_text);
        Button retryButton = findViewById(R.id.retry_button);
        Button homeButton = findViewById(R.id.home_button);

//        int score = getIntent().getIntExtra("SCORE", 0);
        int score = dbHelper.getLastScore(userId);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);


        scoreTextView.setText("Your score: " + score + "/" + totalQuestions);


        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreBoard.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreBoard.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}