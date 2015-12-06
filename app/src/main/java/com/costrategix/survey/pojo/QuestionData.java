package com.costrategix.survey.pojo;

public class QuestionData {
    private int id;
    private String questionText;
    private int answerTypeId;

    public QuestionData() {

    }

    public QuestionData(final String questionText) {
        this.questionText = questionText;
    }

    public QuestionData(final String questionText, final int answerTypeId) {
        this.questionText = questionText;
        this.answerTypeId = answerTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getAnswerTypeId() {
        return answerTypeId;
    }

    public void setAnswerTypeId(int answerTypeId) {
        this.answerTypeId = answerTypeId;
    }
}
