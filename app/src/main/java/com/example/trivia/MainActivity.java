package com.example.trivia;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import com.example.trivia.Question;
import com.example.trivia.QuestionsData;
import com.example.trivia.ScoreBoard;

public class MainActivity extends AppCompatActivity {

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private TextView questionTextView;
    private RadioGroup optionsGroup;
    private RadioButton option1, option2, option3, option4;
    private Button submitButton;

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

        questionList = QuestionsData.getQuestions();

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
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questionList.size()) {
            displayQuestion();
        } else {
            Intent intent = new Intent(MainActivity.this, ScoreBoard.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("TOTAL_QUESTIONS", questionList.size());
            startActivity(intent);
            finish();
        }
    }
}