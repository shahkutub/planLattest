package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by NanoSoft on 5/13/2017.
 */

public class FamilyQuestionTitlesTable {

     /*FamilyQuestionTitlesTable */

    public static final String TABLE_NAME_FAMILY_QUESTION_TITLES = "table_static_family_question_titles";

    public static final String COL_FAMILY_QUESTION_TITLES_TABLE_ID = "id";
    public static final String COL_FAMILY_QUESTION_SERIAL_NO = "family_question_serial_no";
    public static final String COL_FAMILY_QUESTION_TITLE = "family_question_title";


    public static final String CREATE_TABLE_FAMILY_QUESTION_TITLES = " CREATE TABLE " + TABLE_NAME_FAMILY_QUESTION_TITLES + " ( " +
            COL_FAMILY_QUESTION_TITLES_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_FAMILY_QUESTION_SERIAL_NO + " TEXT," +
            COL_FAMILY_QUESTION_TITLE + " TEXT)";


}
