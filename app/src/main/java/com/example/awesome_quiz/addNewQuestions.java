package com.example.awesome_quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addNewQuestions extends AppCompatActivity {

    EditText questionName;
    EditText option1;
    EditText option2;
    EditText option3;
    EditText difficulty;
    EditText subject;
    EditText correctAnswer;

    Button addNewQuestion;

    String questionvalue;
    String option1value;
    String option2value;
    String option3value;
    Integer correctvalue;
    String difficultyvalue;
    String subjectvalue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_questions);

        questionName = (EditText) findViewById(R.id.questionName);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        correctAnswer = findViewById(R.id.correctAnswer);
        difficulty = findViewById(R.id.difficulty);
        subject = findViewById(R.id.subject);

        addNewQuestion = findViewById(R.id.addNewQuestion);

        addNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                questionvalue = questionName.getText().toString().trim();
                option1value = option1.getText().toString();
                option2value = option2.getText().toString();
                option3value = option3.getText().toString();
                correctvalue = Integer.parseInt(correctAnswer.getText().toString());
                difficultyvalue = difficulty.getText().toString();
                subjectvalue = subject.getText().toString();

                addQuestiontobase();

            }
        });

    }

    public void addQuestiontobase()
    {
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        dbHelper.addNewQuestion(questionvalue,option1value,option2value,option3value,correctvalue,difficultyvalue,subjectvalue);
    }
}
