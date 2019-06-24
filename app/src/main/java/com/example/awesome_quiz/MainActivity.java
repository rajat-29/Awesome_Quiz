package com.example.awesome_quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";
    public static final String EXTRA_SUBJECT = "extraSubject";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighScore;
    private int highscore;
    private Spinner spinnerDifficulty;
    private Spinner spinnerSubject;

    Button buttonStartQuiz;
    Button addNewQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHighScore = findViewById(R.id.text_view_highscore);
        spinnerDifficulty = findViewById(R.id.spinner_difficulty);
        spinnerSubject = findViewById(R.id.spinner_subject);

        String[] difficultyLevels = Question.getAllDifficultyLevels();
        String[] subjectLevels = Question.getAllSubjects();

        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);

        ArrayAdapter<String> adapterSubject = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,subjectLevels);
        adapterSubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(adapterSubject);


        loadHighScore();

        buttonStartQuiz = findViewById(R.id.button_start_quiz);
        addNewQuestions = findViewById(R.id.addNewQuestions);

        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startQuiz();
            }
        });

        addNewQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestions();
            }
        });
    }

    private void startQuiz()
    {
        String difficulty = spinnerDifficulty.getSelectedItem().toString();
        String subject = spinnerSubject.getSelectedItem().toString();
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        intent.putExtra(EXTRA_SUBJECT, subject);
        startActivityForResult(intent,REQUEST_CODE_QUIZ);
    }

    private void addQuestions()
    {
        Intent intent = new Intent(MainActivity.this, addNewQuestions.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_QUIZ)
        {
            if(resultCode == RESULT_OK)
            {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if(score > highscore)
                {
                    updateHighScore(score);
                }
            }
        }
    }

    private  void loadHighScore()
    {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighScore.setText("Highscore: " + highscore);

    }

    private void updateHighScore(int newScore)
    {
        highscore = newScore;
        textViewHighScore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();
    }
}
