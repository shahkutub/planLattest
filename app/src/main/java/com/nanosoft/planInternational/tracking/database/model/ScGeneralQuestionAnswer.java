package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 5/14/2017.
 */

public class ScGeneralQuestionAnswer {

    private int sc_id;
    private String question_serial_no;
    private String question_name;
    private String answer;
    private String date;

    private int static_question_id;
    private int static_ans_id;

    public ScGeneralQuestionAnswer(int sc_id, String question_serial_no,
                                   String question_name, String answer, String date, int static_question_id, int static_ans_id) {
        this.sc_id = sc_id;
        this.question_serial_no = question_serial_no;
        this.question_name = question_name;
        this.answer = answer;
        this.date = date;
        this.static_question_id = static_question_id;
        this.static_ans_id = static_ans_id;
    }

    public ScGeneralQuestionAnswer() {

    }

    public int getSc_id() {
        return sc_id;
    }

    public String getQuestion_serial_no() {
        return question_serial_no;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public String getAnswer() {
        return answer;
    }

    public String getDate() {
        return date;
    }

    public int getStatic_question_id() {
        return static_question_id;
    }

    public int getStatic_ans_id() {
        return static_ans_id;
    }

    public void setSc_id(int sc_id) {
        this.sc_id = sc_id;
    }

    public void setQuestion_serial_no(String question_serial_no) {
        this.question_serial_no = question_serial_no;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatic_question_id(int static_question_id) {
        this.static_question_id = static_question_id;
    }

    public void setStatic_ans_id(int static_ans_id) {
        this.static_ans_id = static_ans_id;
    }
}
