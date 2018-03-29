package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by NanoSoft on 5/13/2017.
 */

public class ScStaticQuestionTable {

        /*ScStaticQuestionTable*/

    public static final String TABLE_NAME_SC_STATIC_QUESTION = "table_sc_static_questions";

    public static final String COL_SC_STATIC_QUESTION_TABLE_ID = "id";
    public static final String COL_SC_STATIC_QUESTION_SERIAL_NO = "question_serial_no";
    public static final String COL_SC_STATIC_QUESTION_NAME = "question_name";

    public static final String CREATE_TABLE_SC_STATIC_QUESTION  = " CREATE TABLE " + TABLE_NAME_SC_STATIC_QUESTION + " ( " +
            COL_SC_STATIC_QUESTION_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_SC_STATIC_QUESTION_SERIAL_NO + " TEXT," +
            COL_SC_STATIC_QUESTION_NAME + " TEXT)";
}
