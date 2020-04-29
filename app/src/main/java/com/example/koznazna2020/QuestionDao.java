package com.example.koznazna2020;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void insertQuestion(Question... questions);

    @Update
    void updateQuestion(Question... questions);

    @Delete
    void deleteQuestion(Question... questions);

    @Query("SELECT * FROM questions WHERE category_id = :id")
    List<Question> getAllQuestionsFromCategory(int id);

}
