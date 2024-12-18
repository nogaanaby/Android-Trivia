package com.example.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_board);

        TextView scoreTextView = findViewById(R.id.score_text);
        Button retryButton = findViewById(R.id.retry_button);
        Button homeButton = findViewById(R.id.home_button);

        int score = getIntent().getIntExtra("SCORE", 0);
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