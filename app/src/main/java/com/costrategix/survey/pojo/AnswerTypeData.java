package com.costrategix.survey.pojo;

public class AnswerTypeData {

    private int id;
    private int answerTypeId;
    private String answerType;


    public AnswerTypeData(){

    }


    public AnswerTypeData(final int answerTypeId, final String answerType){

        this.answerTypeId = answerTypeId;
        this.answerType = answerType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public int getAnswerTypeId() {
        return answerTypeId;
    }

    public void setAnswerTypeId(int answerTypeId) {
        this.answerTypeId = answerTypeId;
    }
}
