package com.example.koznazna2020;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface QuestionDao {

    @Insert
    public void insertQuestion(Question... questions);

    @Update
    public void updateQuestion(Question... questions);

    @Delete
    public void deleteQuestion(Question... questions);

    @Query("SELECT * FROM questions WHERE category_id = :id")
    public List<Question> getAllQuestionsFromCategory(int id);

}
