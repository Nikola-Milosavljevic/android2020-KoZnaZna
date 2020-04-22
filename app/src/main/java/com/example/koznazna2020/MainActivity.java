package com.example.koznazna2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUESTION = 1;
    private static final int REQUEST_CODE_CATEGORY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button categoryButton = findViewById(R.id.button_add_category);
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCategory();
            }
        });

        Button questionButton = findViewById(R.id.button_add_question);
        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestion();
            }
        });

        Button startButton = findViewById(R.id.button_play_game);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    private void startGame() {
        String username = ((EditText) findViewById(R.id.edit_text_username)).getText().toString();
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    private void addQuestion() {
        Intent intent = new Intent(this, AddQuestionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUESTION);
    }

    private void addCategory() {
        Intent intent = new Intent(this, AddCategoryActivity.class);
        startActivityForResult(intent, REQUEST_CODE_CATEGORY);
    }
}
