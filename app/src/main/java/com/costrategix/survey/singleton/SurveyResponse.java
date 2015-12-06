package com.costrategix.survey.singleton;

import java.util.ArrayList;
import java.util.List;

public class SurveyResponse {

    private static SurveyResponse mInstance;

    private String userName;

    private String gender;

    private List<String> featuresList = new ArrayList<>();

    private String country;

    private String source;


    private SurveyResponse() {
        mInstance = this;
    }


    public static synchronized SurveyResponse getInstance() {
        if (null == mInstance) {
            new SurveyResponse();
        }
        return mInstance;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFeaturesList(List<String> featuresList) {
        this.featuresList = featuresList;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void addFeature(String s) {
        featuresList.add(s);
    }

    public void removeFeature(String s) {
        featuresList.remove(s);
    }

    public List<String> getFeaturesList() {
        return featuresList;
    }

    public String getUserName() {
        return userName;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getSource() {
        return source;
    }

    public void clear() {
        mInstance = null;
        featuresList = new ArrayList<>();
    }
}
