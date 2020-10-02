package com.example.quectionandanswer;

public class QuestionsInfo {
    private String name;
    private String question;

    //update question


    public QuestionsInfo(String question) {
        this.question = question;
    }

    public QuestionsInfo(String name, String question){
        this.name = name;
        this.question = question;
    }
    public QuestionsInfo(){
    }

    public String getName() {
        return name;
    }

    public String getQuestion() {
        return question;
    }
}
