package com.example.koznazna2020;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_categories", indices = {@Index(value = {"category_name"}, unique = true)})
public class QuestionCategory {

    public QuestionCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo(name = "category_name")
    public String categoryName;

}
