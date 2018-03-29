package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by NanoSoft on 5/13/2017.
 */

public class AnswerTable {


    /*AnswerTable*/
    public static final String TABLE_NAME_ANSWER = "answer_table";

    public static final String COL_ANSWER_TABLE_ID = "id";
    public static final String COL_ANSWER_SURVEY_QUESTION_ID = "survey_question_id";
    public static final String COL_ANSWER_ANSWER = "answer";
    public static final String COL_ANSWER_CREATED_BY = "created_by";
    public static final String COL_ANSWER_MODIFIED_BY = "modified_by";


    public static final String CREATE_TABLE_ANSWER = " CREATE TABLE " + TABLE_NAME_ANSWER + " ( " +
            COL_ANSWER_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_ANSWER_SURVEY_QUESTION_ID + " INTEGER, sc_number TEXT," +
            COL_ANSWER_ANSWER + " TEXT," +
            COL_ANSWER_CREATED_BY + " TEXT," +
            COL_ANSWER_MODIFIED_BY + " TEXT)";






}
