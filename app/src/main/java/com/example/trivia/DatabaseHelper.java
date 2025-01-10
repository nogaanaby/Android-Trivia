package com.example.trivia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "trivia.db";
    private static final int DATABASE_VERSION = 1;

    //users table
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    // Questions table
    public static final String TABLE_QUESTIONS = "questions";
    public static final String COLUMN_QUESTION_ID = "_id";
    public static final String COLUMN_QUESTION_TEXT = "question_text";
    public static final String COLUMN_OPTION1 = "option1";
    public static final String COLUMN_OPTION2 = "option2";
    public static final String COLUMN_OPTION3 = "option3";
    public static final String COLUMN_OPTION4 = "option4";
    public static final String COLUMN_CORRECT_ANSWER = "correct_answer";

    // Scores table
    public static final String TABLE_SCORES = "scores";
    public static final String COLUMN_SCORE_ID = "_id";
    public static final String COLUMN_USER_ID_FK = "user_id";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_DATE = "date";

    private static final String TABLE_CREATE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT);";

    private static final String TABLE_CREATE_QUESTIONS =
            "CREATE TABLE " + TABLE_QUESTIONS + " (" +
                    COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_QUESTION_TEXT + " TEXT, " +
                    COLUMN_OPTION1 + " TEXT, " +
                    COLUMN_OPTION2 + " TEXT, " +
                    COLUMN_OPTION3 + " TEXT, " +
                    COLUMN_OPTION4 + " TEXT, " +
                    COLUMN_CORRECT_ANSWER + " INTEGER);";

    private static final String TABLE_CREATE_SCORES =
            "CREATE TABLE " + TABLE_SCORES + " (" +
                    COLUMN_SCORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_ID_FK + " INTEGER, " +
                    COLUMN_SCORE + " INTEGER, " +
                    COLUMN_DATE + " TEXT, " +
                    "FOREIGN KEY(" + COLUMN_USER_ID_FK + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_QUESTIONS);
        db.execSQL(TABLE_CREATE_SCORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        onCreate(db);
    }

    // Add new user
    public void addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public Cursor getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_USERS, null, COLUMN_USERNAME + "=?", new String[]{username}, null, null, null);
    }

    // Add new question
    public void addQuestion(String questionText, String option1, String option2, String option3, String option4, int correctAnswer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION_TEXT, questionText);
        values.put(COLUMN_OPTION1, option1);
        values.put(COLUMN_OPTION2, option2);
        values.put(COLUMN_OPTION3, option3);
        values.put(COLUMN_OPTION4, option4);
        values.put(COLUMN_CORRECT_ANSWER, correctAnswer);
        db.insert(TABLE_QUESTIONS, null, values);
        db.close();
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_QUESTIONS, null);


        if (cursor != null && cursor.moveToFirst()) {
            int questionTextColumnIndex = cursor.getColumnIndex(COLUMN_QUESTION_TEXT);
            int option1= cursor.getColumnIndex(COLUMN_OPTION1);
            int option2= cursor.getColumnIndex(COLUMN_OPTION2);
            int option3= cursor.getColumnIndex(COLUMN_OPTION3);
            int option4= cursor.getColumnIndex(COLUMN_OPTION4);
            int correctAnswer = cursor.getColumnIndex(COLUMN_CORRECT_ANSWER);
            if(questionTextColumnIndex != -1 && option1 != -1 && option2 != -1 && option3 != -1 && option4 != -1 && correctAnswer != -1) {
                do {
                    String questionText = cursor.getString(questionTextColumnIndex);
                    String[] options = new String[]{
                            cursor.getString(option1),
                            cursor.getString(option2),
                            cursor.getString(option3),
                            cursor.getString(option4)
                    };
                    int correctAnswerIndex = cursor.getInt(correctAnswer);
                    questionList.add(new Question(questionText, options, correctAnswerIndex));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return questionList;
    }

    public void addScore(int userId, int score, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID_FK, userId);
        values.put(COLUMN_SCORE, score);
        values.put(COLUMN_DATE, date);
        db.insert(TABLE_SCORES, null, values);
        db.close();
    }

    public Cursor getLastScore(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_SCORES, null, COLUMN_USER_ID_FK + "=?", new String[]{String.valueOf(userId)}, null, null, COLUMN_DATE + " DESC", "1");
    }
}