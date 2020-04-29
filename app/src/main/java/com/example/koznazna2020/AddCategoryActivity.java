package com.example.koznazna2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        findViewById(R.id.button_new_category).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCategoryToDatabase();
            }
        });
    }

    private void addCategoryToDatabase() {

        String name = ((EditText) findViewById(R.id.edit_text_new_category)).getText().toString();

        QuestionCategory questionCategory = new QuestionCategory(name);
        AppDatabase.getInstance(this).questionCategoryDao().insertQuestionCategory(questionCategory);

        finish();
    }
}
