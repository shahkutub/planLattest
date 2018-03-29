package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/20/2017.
 */

public class SurveyQuestionChoice {
    private int id;
    private int survey_question_id;
    private int ansNo;
    private String  answer_choice;


    public SurveyQuestionChoice(int id, int survey_question_id, String answer_choice,int ansNo) {
        this.id = id;
        this.survey_question_id = survey_question_id;
        this.answer_choice = answer_choice;
        this.ansNo=ansNo;
    }

    public SurveyQuestionChoice(int survey_question_id, String answer_choice) {
        this.survey_question_id = survey_question_id;
        this.answer_choice = answer_choice;
    }

    public int getId() {
        return id;
    }

    public int getSurvey_question_id() {
        return survey_question_id;
    }

    public String getAnswer_choice() {
        return answer_choice;
    }

    public int getAnsNo() {
        return ansNo;
    }
}
