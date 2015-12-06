package com.costrategix.survey.pojo;

public class AnswerData {

    private int id;
    private int questionId;
    private String answer;
    private int sortOder;

    public AnswerData() {

    }

    public AnswerData(final int questionId, final int sortOder, final String answer) {
        this.answer = answer;
        this.questionId = questionId;
        this.sortOder = sortOder;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getSortOder() {
        return sortOder;
    }

    public void setSortOder(int sortOder) {
        this.sortOder = sortOder;
    }
}
