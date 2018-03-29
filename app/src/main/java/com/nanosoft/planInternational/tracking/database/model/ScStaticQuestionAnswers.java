package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by NanoSoft on 5/13/2017.
 */

public class ScStaticQuestionAnswers {

    private int id;
    private int static_question_id;
    private int sc_id;
    private String date;
    private String answer;


    public ScStaticQuestionAnswers(int id, int static_question_id, int sc_id, String date, String answer) {
        this.id = id;
        this.static_question_id = static_question_id;
        this.sc_id = sc_id;
        this.date = date;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public int getStatic_question_id() {
        return static_question_id;
    }

    public int getSc_id() {
        return sc_id;
    }

    public String getDate() {
        return date;
    }

    public String getAnswer() {
        return answer;
    }
}
