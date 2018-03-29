package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by NanoSoft on 5/13/2017.
 */

public class FamilyQuestionAnswersTable {


     /*FamilyQuestionAnswersTable*/

    public static final String TABLE_NAME_FAMILY_QUESTION_ANSWER = "table_static_family_question_answers";

    public static final String COL_FAMILY_QUESTION_ANSWER_TABLE_ID = "id";
    public static final String COL_FAMILY_QUESTION_ANSWER_SC_ID = "sc_id";
    public static final String COL_FAMILY_QUESTION_ANSWER_3_2 = "q_3_2";
    public static final String COL_FAMILY_QUESTION_ANSWER_3_4 = "q_3_4";
    public static final String COL_FAMILY_QUESTION_ANSWER_3_5 = "q_3_5";
    public static final String COL_FAMILY_QUESTION_ANSWER_3_6 = "q_3_6";
    public static final String COL_FAMILY_QUESTION_ANSWER_3_7 = "q_3_7";
    public static final String COL_FAMILY_QUESTION_ANSWER_3_8 = "q_3_8";
    public static final String COL_FAMILY_QUESTION_ANSWER_3_9 = "q_3_9";


    public static final String CREATE_TABLE_FAMILY_QUESTION_ANSWER  = " CREATE TABLE " + TABLE_NAME_FAMILY_QUESTION_ANSWER + " ( " +
            COL_FAMILY_QUESTION_ANSWER_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_FAMILY_QUESTION_ANSWER_SC_ID + " INTEGER," +
            COL_FAMILY_QUESTION_ANSWER_3_2 + " TEXT," +
            COL_FAMILY_QUESTION_ANSWER_3_4 + " TEXT," +
            COL_FAMILY_QUESTION_ANSWER_3_5 + " TEXT," +
            COL_FAMILY_QUESTION_ANSWER_3_6 + " TEXT," +
            COL_FAMILY_QUESTION_ANSWER_3_7 + " TEXT," +
            COL_FAMILY_QUESTION_ANSWER_3_8 + " TEXT," +
            COL_FAMILY_QUESTION_ANSWER_3_9 + " TEXT, created_at text, updated_at text)";


}
