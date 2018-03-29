package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by NanoSoft on 5/13/2017.
 */

public class ScStaticQuestions {

    private int id;
    private String question_serial_no;
    private String question_name;

    public ScStaticQuestions(int id, String question_serial_no, String question_name) {
        this.id = id;
        this.question_serial_no = question_serial_no;
        this.question_name = question_name;
    }

    public int getId() {
        return id;
    }

    public String getQuestion_serial_no() {
        return question_serial_no;
    }

    public String getQuestion_name() {
        return question_name;
    }
}
