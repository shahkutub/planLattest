package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by NanoSoft on 5/13/2017.
 */

public class FamilyQuestionTitles {

    private int id;
    private String family_question_serial_no;
    private String family_question_title;

    public FamilyQuestionTitles(int id, String family_question_serial_no, String family_question_title) {
        this.id = id;
        this.family_question_serial_no = family_question_serial_no;
        this.family_question_title = family_question_title;
    }

    public int getId() {
        return id;
    }

    public String getFamily_question_serial_no() {
        return family_question_serial_no;
    }

    public String getFamily_question_title() {
        return family_question_title;
    }
}
