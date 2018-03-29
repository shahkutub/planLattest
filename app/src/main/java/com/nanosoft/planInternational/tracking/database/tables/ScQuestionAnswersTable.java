package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by NanoSoft on 5/13/2017.
 */

public class ScQuestionAnswersTable {

    /*ScQuestionAnswersTable*/

    public static final String TABLE_NAME_SC_STATIC_QUESTION_ANSWER = "table_sc_static_question_answers";

    public static final String COL_SC_STATIC_QUESTION_ANSWER_TABLE_ID = "id";
    public static final String COL_SC_STATIC_QUESTION_ANSWER_QUESTION_ID = "static_question_id";
    public static final String COL_SC_STATIC_QUESTION_ANSWER_SC_ID = "sc_id";
    public static final String COL_SC_STATIC_QUESTION_ANSWER_DATE = "date";
    public static final String COL_SC_STATIC_QUESTION_ANSWER_ANSWER = "answer";


    public static final String CREATE_TABLE_SC_STATIC_QUESTION_ANSWER = " CREATE TABLE " + TABLE_NAME_SC_STATIC_QUESTION_ANSWER + " ( " +
            COL_SC_STATIC_QUESTION_ANSWER_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_SC_STATIC_QUESTION_ANSWER_QUESTION_ID + " text," +
            COL_SC_STATIC_QUESTION_ANSWER_SC_ID + " INTEGER," +
            COL_SC_STATIC_QUESTION_ANSWER_DATE + " TEXT," +
            COL_SC_STATIC_QUESTION_ANSWER_ANSWER + " TEXT)";


}
