package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/20/2017.
 */

public class SurveyLogic {

    private int id;
    private int survey_question_choice_id;
    private int survey_question_id;

    public SurveyLogic(int id, int survey_question_choice_id, int survey_question_id) {
        this.id = id;
        this.survey_question_choice_id = survey_question_choice_id;
        this.survey_question_id = survey_question_id;
    }

    public SurveyLogic(int survey_question_choice_id, int survey_question_id) {
        this.survey_question_choice_id = survey_question_choice_id;
        this.survey_question_id = survey_question_id;
    }

    public int getId() {
        return id;
    }

    public int getSurvey_question_choice_id() {
        return survey_question_choice_id;
    }

    public int getSurvey_question_id() {
        return survey_question_id;
    }
}
