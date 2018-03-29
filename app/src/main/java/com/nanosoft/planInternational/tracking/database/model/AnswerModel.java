package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by NanoSoft on 5/13/2017.
 */

public class AnswerModel {

    private int id;
    private int survey_question_id;
    private String answer;
    private String created_by;
    private String modified_by;


    public AnswerModel(int id, int survey_question_id, String answer, String created_by, String modified_by) {
        this.id = id;
        this.survey_question_id = survey_question_id;
        this.answer = answer;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public int getId() {
        return id;
    }

    public int getSurvey_question_id() {
        return survey_question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getModified_by() {
        return modified_by;
    }
}
