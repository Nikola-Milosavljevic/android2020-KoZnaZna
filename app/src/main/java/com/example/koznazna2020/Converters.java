package com.example.koznazna2020;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Collections;

public class Converters {

    @TypeConverter
    public static ArrayList<String> fromAnswerString(String string) {
        ArrayList<String> result = new ArrayList<>();
        String[] res = string.split("\n");
        Collections.addAll(result, res);
        return result;
    }

    @TypeConverter
    public static String fromAnswerList(ArrayList<String> answers) {
        if (answers.isEmpty())
            return "";
        StringBuilder result = new StringBuilder(answers.get(0));
        for (int i = 1; i < answers.size(); i++) {
            result.append("\n").append(answers.get(i));
        }
        return result.toString();
    }

}
