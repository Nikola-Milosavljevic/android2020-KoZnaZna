package com.example.koznazna2020;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {QuestionCategory.class, Question.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract QuestionCategoryDao questionCategoryDao();
    public abstract QuestionDao questionDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "who-knows-knows.db").allowMainThreadQueries().build();
        }
        return  INSTANCE;
    }

}
