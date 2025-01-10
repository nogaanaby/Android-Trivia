package com.example.trivia;
import java.util.Arrays;
import java.util.List;
import com.example.trivia.Question;
public class QuestionsData {
    private static DatabaseHelper dbHelper;
    public static void addQuestions(){
        dbHelper.addQuestion("Which Hogwarts house is Harry Potter in?", "Hufflepuff", "Slytherin", "Ravenclaw", "Gryffindor", 3);
        dbHelper.addQuestion("In which year did Harry face the Triwizard Tournament?", "1st year", "3rd year", "4th year", "5th year", 2);
        dbHelper.addQuestion("How many children are in the Weasley family?", "5", "7", "6", "4", 1);
        dbHelper.addQuestion("What is Harry Potter's favorite subject at Hogwarts?", "Potions", "Defense Against the Dark Arts", "Charms", "Transfiguration", 1);
        dbHelper.addQuestion("What is the name of the magical prison in the Harry Potter series?", "Azkaban", "Nurmengard", "Shrieking Shack", "Malfoy Manor", 0);
        dbHelper.addQuestion("Who is the main antagonist in the Harry Potter series?", "Bellatrix Lestrange", "Lucius Malfoy", "Lord Voldemort", "Peter Pettigrew", 2);
        dbHelper.addQuestion("What is the name of Harry's pet owl?", "Fawkes", "Hedwig", "Pigwidgeon", "Errol", 1);
        dbHelper.addQuestion("What are the names of Harry Potter's parents?", "James and Lily", "Arthur and Molly", "Percy and Peggy", "Fred and George", 0);
        dbHelper.addQuestion("Who was the head of Gryffindor House at Hogwarts?", "Albus Dumbledore", "Severus Snape", "Filius Flitwick", "Minerva McGonagall", 3);
        dbHelper.addQuestion("What is the name of Harry's broomstick?", "Firebolt", "Nimbus 2000", "Cleansweep 7", "Shooting Star", 0);
    }

    public static List<Question> getQuestions() {
        return Arrays.asList(
                new Question("Which Hogwarts house is Harry Potter in?", new String[]{"Hufflepuff", "Slytherin", "Ravenclaw", "Gryffindor"}, 3),
                new Question("In which year did Harry face the Triwizard Tournament?", new String[]{"1st year", "3rd year", "4th year", "5th year"}, 2),
                new Question("How many children are in the Weasley family?", new String[]{"5", "7", "6", "4"}, 1),
                new Question("What is Harry Potter's favorite subject at Hogwarts?", new String[]{"Potions", "Defense Against the Dark Arts", "Charms", "Transfiguration"}, 1),
                new Question("What is the name of the magical prison in the Harry Potter series?", new String[]{"Azkaban", "Nurmengard", "Shrieking Shack", "Malfoy Manor"}, 0),
                new Question("Who is the main antagonist in the Harry Potter series?", new String[]{"Bellatrix Lestrange", "Lucius Malfoy", "Lord Voldemort", "Peter Pettigrew"}, 2),
                new Question("What is the name of Harry's pet owl?", new String[]{"Fawkes", "Hedwig", "Pigwidgeon", "Errol"}, 1),
                new Question("What are the names of Harry Potter's parents?", new String[]{"James and Lily", "Arthur and Molly", "Percy and Peggy", "Fred and George"}, 0),
                new Question("Who was the head of Gryffindor House at Hogwarts?", new String[]{"Albus Dumbledore", "Severus Snape", "Filius Flitwick", "Minerva McGonagall"}, 3),
                new Question("What is the name of Harry's broomstick?", new String[]{"Firebolt", "Nimbus 2000", "Cleansweep 7", "Shooting Star"}, 0)
        );
    }
}
