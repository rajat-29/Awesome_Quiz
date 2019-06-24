package com.example.awesome_quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.awesome_quiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    public static  int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_SUBJECTS + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable()
    {
        Question q1 = new Question("A is correct","A","B","C",1, Question.DIFFICULTY_EASY, Question.SUBJECT_1);
        addQuestions(q1);

        Question q2 = new Question("B is correct","A","B","C",2, Question.DIFFICULTY_MEDIUM, Question.SUBJECT_2);
        addQuestions(q2);

        Question q3 = new Question("C is correct","A","B","C",3, Question.DIFFICULTY_MEDIUM, Question.SUBJECT_2);
        addQuestions(q3);

        Question q4 = new Question("C is correct","A","B","C",3, Question.DIFFICULTY_HARD, Question.SUBJECT_3);
        addQuestions(q4);

        Question q5 = new Question("B is correct","A","B","C",2, Question.DIFFICULTY_HARD, Question.SUBJECT_3);
        addQuestions(q5);

        Question q6 = new Question("A is correct","A","B","C",1, Question.DIFFICULTY_HARD, Question.SUBJECT_3);
        addQuestions(q6);
    }

    public void addNewQuestion(String questionvalue,String option1value,String option2value,String option3value,int correctvalue,String difficultyvalue,String subjectvalue)
    {
        db = getWritableDatabase();
        Question q1 = new Question(questionvalue,option1value,option2value,option3value,correctvalue, difficultyvalue, subjectvalue);
        DATABASE_VERSION++;
        addQuestions(q1);
        System.out.println("data ayaya ji");
    }

    // add ques to database
    private void addQuestions(Question question)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_SUBJECTS, question.getSubjects());


        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    // retirve question from database
    public ArrayList<Question> getAllQuestions()
    {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+ QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst())
        {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_SUBJECTS)));
                questionList.add(question);
            } while (c.moveToNext());

        }
        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(String difficulty,String subjects)
    {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_DIFFICULTY + " = ? " +
                " AND " + QuestionsTable.COLUMN_SUBJECTS + " = ? ";

        String[] selectionArgs =  new String[]{difficulty,subjects};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(c.moveToFirst())
        {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_SUBJECTS)));
                questionList.add(question);
            } while (c.moveToNext());

        }
        c.close();
        return questionList;
    }


}
