package com.example.koznazna2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
    }

    private void addCategoryToDatabase() {

        QuestionCategory questionCategory = new QuestionCategory("matematika");
        AppDatabase.getInstance(this).questionCategoryDao().insertQuestionCategory(questionCategory);

    }
}
