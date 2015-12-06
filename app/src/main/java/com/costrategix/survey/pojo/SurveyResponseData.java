package com.costrategix.survey.pojo;

public class SurveyResponseData {

    private int id;
    private int surveyId;
    private String name;
    private String gender;
    private String features;
    private String country;
    private String source;


    public SurveyResponseData() {

    }

    public SurveyResponseData( final int surveyId, final String name,
                              final String gender, final String features, final String country,
                              final String source) {
        this.surveyId = surveyId;
        this.name = name;
        this.gender = gender;
        this.features = features;
        this.country = country;
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
