package com.example.koznazna2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private static final int QUESTION_NUM = 5;

    private String username;
    private int points = 0;

    private Button button;
    private Spinner spinner;
    private TextView textViewQuestion;
    private ListView listView;

    private ArrayList<Question> questions;
    private int curr_question_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        if (intent != null) {
            TextView user = findViewById(R.id.text_view_username);
            username = intent.getStringExtra("USERNAME");
            user.setText(username);
        }

        spinner = findViewById(R.id.spinner_choose_category);
        ArrayList<QuestionCategory> categories
                = (ArrayList<QuestionCategory>) AppDatabase.getInstance(this).questionCategoryDao().getAllQuestionCategories();

        ArrayAdapter<QuestionCategory> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showBestScoreForCategory(((QuestionCategory) parent.getAdapter().getItem(position)).categoryName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button = findViewById(R.id.button_start_questions);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuestions();
            }
        });

        textViewQuestion = findViewById(R.id.text_view_curr_question);

        listView = findViewById(R.id.list_view_curr_answers);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == questions.get(curr_question_id).correctAnsId) {
                    points++;
                    Toast.makeText(parent.getContext(), "TACNO!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(parent.getContext(), "NETACNO!", Toast.LENGTH_SHORT).show();
                }
                curr_question_id++;
                setCurrQuestion();
            }

        });



    }

    private void setCurrQuestion() {

        if (curr_question_id == questions.size() || curr_question_id == QUESTION_NUM) {
            textViewQuestion.setText("");
            listView.setAdapter(null);
            endGame();
            return;
        }

        Question question = questions.get(curr_question_id);

        textViewQuestion.setText(question.question);
        ArrayList<String> answers = new ArrayList<>();
        answers.add(question.answers.get(0));
        answers.add(question.answers.get(1));
        answers.add(question.answers.get(2));
        answers.add(question.answers.get(3));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, answers);
        listView.setAdapter(adapter);

    }

    private void endGame() {
        Toast.makeText(this, "Points: " + points, Toast.LENGTH_LONG).show();

        saveScore();
        addGameToLogFile();

        button.setClickable(true);
    }

    private void addGameToLogFile() {
    }

    // SharedPreferences (begin)

    private void saveScore() {
        // default hash mapa na nivou aplikacije
        //SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        // hocemo za svaku kategoriju da pamtimo najbolji skor za svakog korisnika (dakle za svaku kategoriju nova sharedpreferences)
        QuestionCategory category = (QuestionCategory) spinner.getSelectedItem();
        SharedPreferences preferences = getSharedPreferences(getString(R.string.app_name) + "." + category.categoryName, Context.MODE_PRIVATE);

        int oldMaxScore = preferences.getInt(username, -1);
        if (oldMaxScore == -1 || oldMaxScore < points) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(username, points);
            editor.apply();
        }
    }

    private void showBestScoreForCategory(String categoryName) {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.app_name) + "." + categoryName, MODE_PRIVATE);
        int best_score = preferences.getInt(username, -1);
        if (best_score == -1) {
            Toast.makeText(this, "Niste igrali kategoriju " + categoryName, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Najbolji skor u " + categoryName + " je " + best_score, Toast.LENGTH_LONG).show();
        }
    }

    // SharedPreferences (end)

    private void startQuestions() {
        QuestionCategory category = (QuestionCategory) spinner.getSelectedItem();
        questions = (ArrayList<Question>) AppDatabase.getInstance(this).questionDao().getAllQuestionsFromCategory(category.id);

        Log.i("INFO", "" + questions.size());
        Toast.makeText(this, "" + questions.size(), Toast.LENGTH_LONG).show();

        points = 0;
        curr_question_id = 0;
        button.setClickable(false);
        setCurrQuestion();
    }
}
