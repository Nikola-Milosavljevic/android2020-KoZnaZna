package com.example.koznazna2020;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface QuestionCategoryDao {

    @Insert
    public void insertQuestionCategory(QuestionCategory... categories);

    @Update
    public void updateQuestionCategory(QuestionCategory... categories);

    @Delete
    public void deleteQuestionCategory(QuestionCategory... categories);

    @Query("DELETE FROM question_categories WHERE category_name = :name")
    public void deleteQuestionCategory(String name);

    @Query("SELECT * FROM question_categories")
    public List<QuestionCategory> getAllQuestionCategories();

}
