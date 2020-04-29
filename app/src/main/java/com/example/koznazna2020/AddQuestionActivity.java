package com.example.koznazna2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        Spinner spinner = findViewById(R.id.spinner_category);

        ArrayList<QuestionCategory> categories
                = (ArrayList<QuestionCategory>) AppDatabase.getInstance(this).questionCategoryDao().getAllQuestionCategories();

        ArrayAdapter<QuestionCategory> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        spinner.setAdapter(adapter);

        findViewById(R.id.button_new_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestionToDatabase();
            }
        });
    }

    private void addQuestionToDatabase() {

        String questionText = ((EditText) findViewById(R.id.edit_text_new_question)).getText().toString();

        String ans1 = ((EditText) findViewById(R.id.edit_text_ans1)).getText().toString();
        String ans2 = ((EditText) findViewById(R.id.edit_text_ans2)).getText().toString();
        String ans3 = ((EditText) findViewById(R.id.edit_text_ans3)).getText().toString();
        String ans4 = ((EditText) findViewById(R.id.edit_text_ans4)).getText().toString();
        ArrayList<String> answers = new ArrayList<>();
        answers.add(ans1);
        answers.add(ans2);
        answers.add(ans3);
        answers.add(ans4);

        Integer ans_id = Integer.valueOf(((EditText) findViewById(R.id.edit_text_correct_ans_id)).getText().toString());

        Spinner spinner = findViewById(R.id.spinner_category);
        Integer category_id = ((QuestionCategory) spinner.getSelectedItem()).id;

        Question question = new Question(category_id, questionText, answers, ans_id);

        AppDatabase.getInstance(this).questionDao().insertQuestion(question);



        finish();
    }
}
