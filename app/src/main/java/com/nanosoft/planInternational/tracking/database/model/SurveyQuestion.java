package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by NanoSoft on 4/16/2017.
 */

public class SurveyQuestion {

    private int surveyQuestionTableId;
    private String crated_by;
    private String modified_by;
    private int surveyEntryId;
    private String questionType;
    private String questionName;
    private int serialNo;
    private int otherAnswerOption;
    private int answerRequired;
    private String reqtext;
    private int validateAnswerFormat;
    private int characters;
    private int scale;
    private int ageRange;
    private String is_primary_question;

    public SurveyQuestion(int surveyQuestionTableId, String crated_by, String modified_by, int surveyEntryId, String questionType, String questionName, int serialNo, int otherAnswerOption, int answerRequired, String reqtext,
                          int validateAnswerFormat, int characters, int scale, int ageRange, String is_primary_question) {
        this.surveyQuestionTableId = surveyQuestionTableId;
        this.crated_by = crated_by;
        this.modified_by = modified_by;
        this.surveyEntryId = surveyEntryId;
        this.questionType = questionType;
        this.questionName = questionName;
        this.serialNo = serialNo;
        this.otherAnswerOption = otherAnswerOption;
        this.answerRequired = answerRequired;
        this.reqtext = reqtext;
        this.validateAnswerFormat = validateAnswerFormat;
        this.characters = characters;
        this.scale = scale;
        this.ageRange = ageRange;
        this.is_primary_question = is_primary_question;
    }

    public SurveyQuestion(String crated_by, String modified_by, int surveyEntryId, String questionType, String questionName, int serialNo, int otherAnswerOption, int answerRequired, String reqtext, int validateAnswerFormat,
                          int characters, int scale, int ageRange, String is_primary_question) {
        this.crated_by = crated_by;
        this.modified_by = modified_by;
        this.surveyEntryId = surveyEntryId;
        this.questionType = questionType;
        this.questionName = questionName;
        this.serialNo = serialNo;
        this.otherAnswerOption = otherAnswerOption;
        this.answerRequired = answerRequired;
        this.reqtext = reqtext;
        this.validateAnswerFormat = validateAnswerFormat;
        this.characters = characters;
        this.scale = scale;
        this.ageRange = ageRange;
        this.is_primary_question = is_primary_question;
    }

    public String getIs_primary_question() {
        return is_primary_question;
    }

    public int getScale() {
        return scale;
    }

    public String getCrated_by() {
        return crated_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public int getSurveyQuestionTableId() {
        return surveyQuestionTableId;
    }

    public int getSurveyEntryId() {
        return surveyEntryId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getQuestionName() {
        return questionName;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public int getOtherAnswerOption() {
        return otherAnswerOption;
    }

    public int getAnswerRequired() {
        return answerRequired;
    }

    public String getReqtext() {
        return reqtext;
    }

    public int getValidateAnswerFormat() {
        return validateAnswerFormat;
    }

    public int getCharacters() {
        return characters;
    }


    public int getAgeRange() {
        return ageRange;
    }
}
