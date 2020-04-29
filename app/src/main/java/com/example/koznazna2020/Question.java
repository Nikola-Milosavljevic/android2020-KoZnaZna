package com.example.koznazna2020;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "questions", foreignKeys = @ForeignKey(entity = QuestionCategory.class,
                                                            parentColumns = "id",
                                                            childColumns = "category_id"))
public class Question {

    @ColumnInfo(name = "question_id")
    @PrimaryKey(autoGenerate = true)
    public Integer questionId;

    @ColumnInfo(name = "category_id")
    public Integer categoryId;

    public String question;

    public ArrayList<String> answers;

    @ColumnInfo(name = "correct_ans_id")
    public Integer correctAnsId;

    public Question(Integer categoryId, String question, ArrayList<String> answers, Integer correctAnsId) {
        this.categoryId = categoryId;
        this.question = question;
        this.answers = answers;
        this.correctAnsId = correctAnsId;
    }
}
