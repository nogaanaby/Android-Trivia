package com.example.trivia;
import java.util.Arrays;
import java.util.List;
import com.example.trivia.Question;
public class QuestionsData {
    public static List<Question> getQuestions() {
        return Arrays.asList(
                new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, 0),
                new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1),
                new Question("What is the capital of Israel?", new String[]{"Paris", "London", "Jerusalem", "Madrid"}, 2)
        );
    }
}
