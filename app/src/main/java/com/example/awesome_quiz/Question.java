package com.example.awesome_quiz;

// this java file will work as a bridge between our app and database

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    public static final String DIFFICULTY_EASY = "Easy";
    public static final String DIFFICULTY_MEDIUM = "Medium";
    public static final String DIFFICULTY_HARD = "Hard";

    public static final String SUBJECT_1 = "Programming";
    public static final String SUBJECT_2 = "Geography";
    public static final String SUBJECT_3 = "Math";


    private String question;
    private String option1;
    private String option2;
    private String option3;
    private int answerNr;

    private String difficulty;


    private String subjects;

    public Question() {}

    public Question(String question, String option1, String option2, String option3, int answerNr, String difficulty, String subjects) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNr = answerNr;
        this.difficulty = difficulty;
        this.subjects = subjects;
    }

    protected Question(Parcel in)
    {
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        answerNr = in.readInt();
        difficulty = in.readString();
        subjects = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeInt(answerNr);
        dest.writeString(difficulty);
        dest.writeString(subjects);
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public static String[] getAllDifficultyLevels()
    {
        return new String[]{
                DIFFICULTY_EASY,
                DIFFICULTY_MEDIUM,
                DIFFICULTY_HARD,
        };
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public static String[] getAllSubjects()
    {
        return new String[]{
                SUBJECT_1,
                SUBJECT_2,
                SUBJECT_3,
        };
    }
}
