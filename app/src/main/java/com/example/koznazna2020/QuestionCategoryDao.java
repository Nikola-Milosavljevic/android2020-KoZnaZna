package com.example.koznazna2020;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionCategoryDao {

    @Insert
    void insertQuestionCategory(QuestionCategory... categories);

    @Update
    void updateQuestionCategory(QuestionCategory... categories);

    @Delete
    void deleteQuestionCategory(QuestionCategory... categories);

    @Query("DELETE FROM question_categories WHERE category_name = :name")
    void deleteQuestionCategory(String name);

    @Query("SELECT * FROM question_categories")
    List<QuestionCategory> getAllQuestionCategories();

}
