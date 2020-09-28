package com.example.quectionandanswer;

public class Question {
    String name;
    String question;


    public Question(String name,String question) {
        this.name=name;
        this.question = question;


    }
    public Question() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
