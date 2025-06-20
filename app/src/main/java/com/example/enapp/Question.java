package com.example.enapp;

public class Question {
    private String question, optionA, optionB, optionC, optionD, correct;

    public Question() {}  // Firestore cần constructor rỗng

    public Question(String question, String a, String b, String c, String d, String correct) {
        this.question = question;
        this.optionA = a;
        this.optionB = b;
        this.optionC = c;
        this.optionD = d;
        this.correct = correct;
    }

    // Getters
    public String getQuestion() { return question; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public String getOptionD() { return optionD; }
    public String getCorrect() { return correct; }
}

