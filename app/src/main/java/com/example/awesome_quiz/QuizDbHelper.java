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

        //Programming Questions

        Question q11 = new Question("Who is the father of C Language","Dennis Ritchie","Bjarne Stroustrup","James A. Gosling",1, Question.DIFFICULTY_EASY, Question.SUBJECT_1);
        addQuestions(q11);

        Question q12 = new Question("C Language developed at ?","Sun MicroSystems","Cambridge University","AT &T's Bell Lab",3, Question.DIFFICULTY_EASY, Question.SUBJECT_1);
        addQuestions(q12);

        Question q13 = new Question("C programs are converted into machine language with help of ?","Os","Complier","Editor",2, Question.DIFFICULTY_MEDIUM, Question.SUBJECT_1);
        addQuestions(q13);

        Question q14 = new Question("C was primarily developed as ?","System Programming Language","General Purpose Language","Data Processing Language",1, Question.DIFFICULTY_MEDIUM, Question.SUBJECT_1);
        addQuestions(q14);

        Question q15 = new Question("Standard ANSI C recognizes number of keywords?","64","32","36",2, Question.DIFFICULTY_HARD, Question.SUBJECT_1);
        addQuestions(q15);

        Question q16 = new Question("For 16-bit compliler range for integer constant is ?","-32768 to 32767","-32768 to 32167","-32768 to 32867",1, Question.DIFFICULTY_HARD, Question.SUBJECT_1);
        addQuestions(q16);

        // Geography Questions

        Question q21 = new Question("Which of the following rivers does not flow into the Arabian Sea?","Tungabhadra","Mandovi","Narmada",1, Question.DIFFICULTY_EASY, Question.SUBJECT_2);
        addQuestions(q21);

        Question q22 = new Question("Which of the following is the highest peak of Satpura Range?","Mahendragiri","Pachmarhi","Dhupgarh",3, Question.DIFFICULTY_EASY, Question.SUBJECT_2);
        addQuestions(q22);

        Question q23 = new Question("The lacustrine deposits of Kashmir called ‘Karewas’ are known for :","Terrace farming","Saffron Cultivation","Jhum Cultivation",2, Question.DIFFICULTY_MEDIUM, Question.SUBJECT_2);
        addQuestions(q23);

        Question q24 = new Question("The famous hill-station ‘Kodaikanal’ lies in :","Palani hills","Javadi hills","Cardamom hills",1, Question.DIFFICULTY_MEDIUM, Question.SUBJECT_2);
        addQuestions(q24);

        Question q25 = new Question("Asia’s largest tulip garden is located in which state?","Sikkim","Jammu & Kashmir","Assam",2, Question.DIFFICULTY_HARD, Question.SUBJECT_2);
        addQuestions(q25);

        Question q26 = new Question("Barak valley in Assam is famous for which among the following?","Tea Cultivation","Cottage Industries","Bamboo Industry",1, Question.DIFFICULTY_HARD, Question.SUBJECT_2);
        addQuestions(q26);

        // Maths Questions

        Question q31 = new Question(" The average of first 50 natural numbers is ?","25.5","50.0","20.5",1, Question.DIFFICULTY_EASY, Question.SUBJECT_3);
        addQuestions(q31);

        Question q32 = new Question("The number of 3-digit numbers divisible by 6 is ?","149","151","150",3, Question.DIFFICULTY_EASY, Question.SUBJECT_3);
        addQuestions(q32);

        Question q33 = new Question("The period of | sin (3x) | is ","2π / 3 ","π / 3 ","π / 4 ",2, Question.DIFFICULTY_MEDIUM, Question.SUBJECT_3);
        addQuestions(q33);

        Question q34 = new Question("If f(x) is an odd function, then | f(x) | is ","an even function ","an odd function ","even and odd",1, Question.DIFFICULTY_MEDIUM, Question.SUBJECT_3);
        addQuestions(q34);

        Question q35 = new Question("If Log 4 (x) = 12, then log 2 (x / 4) is equal to ","12","22","32",2, Question.DIFFICULTY_HARD, Question.SUBJECT_3);
        addQuestions(q35);

        Question q36 = new Question("If Logx (1 / 8) = - 3 / 2, then x is equal to ","4","8","9",1, Question.DIFFICULTY_HARD, Question.SUBJECT_3);
        addQuestions(q36);

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
