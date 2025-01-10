package com.example.trivia;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDateTime;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import com.example.trivia.Question;
import com.example.trivia.ScoreBoard;

public class MainActivity extends AppCompatActivity {

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int userId;

    private TextView questionTextView;
    private RadioGroup optionsGroup;
    private RadioButton option1, option2, option3, option4;
    private Button submitButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.question_text);
        optionsGroup = findViewById(R.id.options_group);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        submitButton = findViewById(R.id.submit_button);

        dbHelper = new DatabaseHelper(this);
        userId = getIntent().getIntExtra("USER_ID", -1);

        // Insert test data into the database
        dbHelper.addQuestion("What is the capital of France?", "Paris", "London", "Berlin", "Madrid", 0);


        questionList = dbHelper.getAllQuestions();
        displayQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion() {
        Question currentQuestion = questionList.get(currentQuestionIndex);

        optionsGroup.clearCheck();

        questionTextView.setText(currentQuestion.getQuestionText());
        option1.setText(currentQuestion.getOptions()[0]);
        option2.setText(currentQuestion.getOptions()[1]);
        option3.setText(currentQuestion.getOptions()[2]);
        option4.setText(currentQuestion.getOptions()[3]);
    }

    private void checkAnswer() {
        int selectedOptionIndex = optionsGroup.indexOfChild(findViewById(optionsGroup.getCheckedRadioButtonId()));
        Question currentQuestion = questionList.get(currentQuestionIndex);

        if (selectedOptionIndex == currentQuestion.getCorrectAnswerIndex()) {
            score++;
            Toast.makeText(this, "Excellent! 10 points for Gryffindor!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Magic takes time, try again!", Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questionList.size()) {
            displayQuestion();
        } else {
            Intent intent = new Intent(MainActivity.this, ScoreBoard.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("TOTAL_QUESTIONS", questionList.size());
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
            dbHelper.addScore(userId, score, LocalDateTime.now().toString());
            finish();
        }
    }
}


//
//int userId = 1; // Replace with actual user ID
//int score = 10; // Replace with actual score
//String date = "2023-10-10 10:00:00"; // Replace with actual date
//dbHelper.addScore(userId, score, date);