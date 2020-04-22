package com.example.koznazna2020;

public class Question {

    public Integer questionId;

    public Integer categoryId;

    public String question;

    public String[] answers = new String[4];
    public Integer correctAnsId;

    public Question(Integer categoryId, String question, String[] answers, Integer correctAnsId) {
        this.categoryId = categoryId;
        this.question = question;
        this.answers = answers;
        this.correctAnsId = correctAnsId;
    }
}
