package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by NanoSoft on 4/16/2017.
 */

public class SurveyQuestionOptionModel {

    private int surveyQuestionOptionTableId;
    private String surveyQuestionOptionTableName;
    private String surveyQuestionOptionTableValue;
    private int surveyQuestionOptionTableStatus;
    private String crated_by;
    private String modified_by;

    public SurveyQuestionOptionModel(int surveyQuestionOptionTableId, String surveyQuestionOptionTableName, String surveyQuestionOptionTableValue, int surveyQuestionOptionTableStatus) {
        this.surveyQuestionOptionTableId = surveyQuestionOptionTableId;
        this.surveyQuestionOptionTableName = surveyQuestionOptionTableName;
        this.surveyQuestionOptionTableValue = surveyQuestionOptionTableValue;
        this.surveyQuestionOptionTableStatus = surveyQuestionOptionTableStatus;
    }

    public SurveyQuestionOptionModel(String surveyQuestionOptionTableName, String surveyQuestionOptionTableValue, int surveyQuestionOptionTableStatus) {
        this.surveyQuestionOptionTableName = surveyQuestionOptionTableName;
        this.surveyQuestionOptionTableValue = surveyQuestionOptionTableValue;
        this.surveyQuestionOptionTableStatus = surveyQuestionOptionTableStatus;
    }

    public int getSurveyQuestionOptionTableId() {
        return surveyQuestionOptionTableId;
    }

    public String getSurveyQuestionOptionTableName() {
        return surveyQuestionOptionTableName;
    }

    public String getSurveyQuestionOptionTableValue() {
        return surveyQuestionOptionTableValue;
    }

    public int getSurveyQuestionOptionTableStatus() {
        return surveyQuestionOptionTableStatus;
    }
}
