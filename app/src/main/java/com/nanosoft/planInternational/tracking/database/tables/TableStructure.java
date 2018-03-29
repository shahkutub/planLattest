package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by NanoSoft on 4/16/2017.
 */

public class TableStructure {

    /*VOLUNTEER*/

    public static final String TABLE_NAME_VOLUNTEER = "tbl_volunteer";

    public static final String COL_VOLUNTEER_TABLE_ID = "id";
    public static final String COL_VOLUNTEER_COMMUNITY_ID = "community_id";
    public static final String COL_VOLUNTEER_USER_ID = "user_id";
    public static final String COL_VOLUNTEER_NAME = "volunteer_name";
    public static final String COL_VOLUNTEER_DESIGNATION = "volunteer_designation";
    public static final String COL_VOLUNTEER_ADDRESS = "volunteer_address";
    public static final String COL_VOLUNTEER_MOBILE = "volunteer_mobile";
    public static final String COL_VOLUNTEER_EMAIL = "volunteer_email";
    public static final String COL_VOLUNTEER_USER_NAME = "volunteer_username";
    public static final String COL_VOLUNTEER_PASSWORD = "volunteer_password";
    public static final String COL_VOLUNTEER_CREATE_BY = "created_by";
    public static final String COL_VOLUNTEER_MODIFIED_BY = "modified_by";




    public static final String CREATE_TABLE_VOLUNTEER = " CREATE TABLE " + TABLE_NAME_VOLUNTEER + " ( " +
            COL_VOLUNTEER_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_VOLUNTEER_USER_ID + " INTEGER," +
            COL_VOLUNTEER_COMMUNITY_ID + " INTEGER," +
            COL_VOLUNTEER_NAME + " TEXT," +
            COL_VOLUNTEER_DESIGNATION + " TEXT," +
            COL_VOLUNTEER_ADDRESS + " TEXT," +
            COL_VOLUNTEER_MOBILE + " TEXT," +
            COL_VOLUNTEER_EMAIL + " TEXT," +
            COL_VOLUNTEER_USER_NAME + " TEXT," +
            COL_VOLUNTEER_PASSWORD + " TEXT," +
            COL_VOLUNTEER_CREATE_BY + " TEXT," +
            COL_VOLUNTEER_MODIFIED_BY + " TEXT)";


/*"id": 1,
          "role_id": 1,
          "name": "Kamrul  Islam Touhid",
          "email": "kamrultouhids@gmail.com",
          "password": "$2y$10$xdpCG4aB.J47gxwxxOhVvepDyFy5gpjzk0mozv1805Cl6lUx5ofta",
          "status": 1,
          "photo": "5a4b50eaa4d313bdddcfe49a1a3c6e35.png",
          "remember_token": "zHXYeRzu8fWs8Sl7iAbs3NrbRFQZ3Fz8O3nRluiepkkhwuy01VMiYHXDHPv4",
          "created_by": 2,
          "modified_by": 2,
          "user_type": 1*/

    /*USERS*/



    public static final String TABLE_NAME_USERS = "users";

    public static final String COL_USERS_TABLE_ID = "id";
    public static final String COL_USERS_ROLE_ID = "role_id";
    public static final String COL_USERS_NAME = "name";
    public static final String COL_USERS_EMAIL = "email";
    public static final String COL_USERS_PASSWORD = "password";
    public static final String COL_USERS_STATUS = "status";
    public static final String COL_USERS_PHOTO = "photo";
    public static final String COL_USERS_REMEMBER_TOKEN = "remember_token";
    public static final String COL_USERS_CREATE_BY = "created_by";
    public static final String COL_USERS_MODIFIED_BY = "modified_by";
    public static final String COL_USERS_USER_TYPE = "user_type";


    public static final String CREATE_TABLE_USERS = " CREATE TABLE " + TABLE_NAME_USERS + " ( " +
            COL_USERS_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_USERS_ROLE_ID + " INTEGER," +
            COL_USERS_NAME + " TEXT," +
            COL_USERS_EMAIL + " TEXT," +
            COL_USERS_PASSWORD + " TEXT," +
            COL_USERS_STATUS + " INTEGER," +
            COL_USERS_PHOTO + " TEXT," +
            COL_USERS_REMEMBER_TOKEN + " TEXT," +
            COL_USERS_CREATE_BY + " TEXT," +
            COL_USERS_MODIFIED_BY + " TEXT," +
            COL_USERS_USER_TYPE + " TEXT)";


    /*SurveyQuestionOption*/

    public static final String TABLE_NAME_SURVEY_QUESTION_OPTION = "survey_question_option_category";

    public static final String COL_SURVEY_QUESTION_OPTION_TABLE_ID = "id";
    public static final String COL_SURVEY_QUESTION_OPTION_TABLE_NAME = "name";
    public static final String COL_SURVEY_QUESTION_OPTION_TABLE_VALUE = "value";
    public static final String COL_SURVEY_QUESTION_OPTION_TABLE_STATUS = "status";


    public static final String CREATE_TABLE_SURVEY_QUESTION_OPTION = " CREATE TABLE " + TABLE_NAME_SURVEY_QUESTION_OPTION + " ( " +
            COL_SURVEY_QUESTION_OPTION_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_SURVEY_QUESTION_OPTION_TABLE_NAME + " TEXT," +
            COL_SURVEY_QUESTION_OPTION_TABLE_VALUE + " TEXT," +
            COL_SURVEY_QUESTION_OPTION_TABLE_STATUS + " INTEGER)";



    /*TargetMigration*/

    public static final String TABLE_NAME_TARGET_GROUP = "tbl_target_group";

    public static final String COL_TARGET_GROUP_TABLE_ID = "id";
    public static final String COL_TARGET_GROUP_NAME = "group_name";
    public static final String  COL_TARGET_GROUP_CRAETED_BY = "created_by";
    public static final String  COL_TARGET_GROUP_MODIFIED_BY = "modified_by";


    public static final String CREATE_TABLE_TARGET_GROUP = " CREATE TABLE " + TABLE_NAME_TARGET_GROUP + " ( " +
            COL_TARGET_GROUP_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_TARGET_GROUP_NAME + " TEXT," +
            COL_TARGET_GROUP_CRAETED_BY + " TEXT," +
            COL_TARGET_GROUP_MODIFIED_BY + " TEXT)";

/*"        id": 3,
          "survey_entry_id": 1,
          "questionType": "2",
          "questionName": "df",
          "serial_no": 1,
          "otherAnswerOption": 0,
          "answerRequired": 0,
          "reqtext": "",
          "validateAnswerFormat": 0,
          "characters": 0,
          "scale": 0,
          "ageRange": 1,
          "created_by": 1,
          "modified_by": 1*/

  /*SURVEY_QUESTION*/

    public static final String TABLE_NAME_SURVEY_QUESTION = "survey_question";

    public static final String COL_SURVEY_QUESTION_TABLE_ID = "id";
    public static final String COL_SURVEY_QUESTION_CRAETED_BY = "created_by";
    public static final String  COL_SURVEY_QUESTION_MODIFIED_BY = "modified_by";
    public static final String COL_SURVEY_QUESTION_ENTRY_ID = "survey_entry_id";
    public static final String COL_SURVEY_QUESTION_TYPE = "questionType";
    public static final String COL_SURVEY_QUESTION_NAME = "questionName";
    public static final String COL_SURVEY_QUESTION_SERIAL_NO = "serial_no";
    public static final String COL_SURVEY_QUESTION_OTHER_ANSWER_OPTION = "otherAnswerOption";
    public static final String COL_SURVEY_QUESTION_ANSWER_REQUIRED = "answerRequired";
    public static final String COL_SURVEY_QUESTION_REQ_TEXT = "reqtext";
    public static final String COL_SURVEY_QUESTION_VALIDATION_ANSWER_FORMAT = "validateAnswerFormat";
    public static final String COL_SURVEY_QUESTION_CHARACTERS = "characters";
    public static final String COL_SURVEY_QUESTION_SCALE = "scale";
    public static final String COL_SURVEY_QUESTION_AGE_RANGE = "ageRange";
    public static final String COL_SURVEY_QUESTION_TABLE_IS_PRIMARY_QUESTION = "is_primary_question";


    public static final String CREATE_TABLE_SURVEY_QUESTION = " CREATE TABLE " + TABLE_NAME_SURVEY_QUESTION + " ( " +
            COL_SURVEY_QUESTION_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_SURVEY_QUESTION_ENTRY_ID + " INTEGER," +
            COL_SURVEY_QUESTION_TYPE + " TEXT," +
            COL_SURVEY_QUESTION_NAME + " TEXT," +
            COL_SURVEY_QUESTION_SERIAL_NO + " INTEGER," +
            COL_SURVEY_QUESTION_OTHER_ANSWER_OPTION + " INTEGER," +
            COL_SURVEY_QUESTION_ANSWER_REQUIRED + " INTEGER," +
            COL_SURVEY_QUESTION_REQ_TEXT + " TEXT," +
            COL_SURVEY_QUESTION_VALIDATION_ANSWER_FORMAT + " INTEGER," +
            COL_SURVEY_QUESTION_CHARACTERS + " INTEGER," +
            COL_SURVEY_QUESTION_SCALE + " TEXT," +
            COL_SURVEY_QUESTION_AGE_RANGE + " INTEGER," +
            COL_SURVEY_QUESTION_CRAETED_BY + " TEXT," +
            COL_SURVEY_QUESTION_MODIFIED_BY + " TEXT," +
            COL_SURVEY_QUESTION_TABLE_IS_PRIMARY_QUESTION + " TEXT)";


    /*SurveyEntry*/

    public static final String TABLE_NAME_SURVEY_ENTRY = "survey_entry";

    public static final String COL_TARGET_SURVEY_ENTRY_TABLE_ID = "id";
    public static final String COL_TARGET_SURVEY_ENTRY_CATEGORY_ID = "survey_category_id";
    public static final String COL_TARGET_SURVEY_ENTRY_NAME = "name";
    public static final String  COL_TARGET_SURVEY_CRAETED_BY = "created_by";
    public static final String  COL_TARGET_SURVEY_MODIFIED_BY = "modified_by";


    public static final String CREATE_TABLE_SURVEY_ENTRY = " CREATE TABLE " + TABLE_NAME_SURVEY_ENTRY + " ( " +
            COL_TARGET_SURVEY_ENTRY_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_TARGET_SURVEY_ENTRY_CATEGORY_ID + " INTEGER," +
            COL_TARGET_SURVEY_ENTRY_NAME + " TEXT," +
            COL_TARGET_SURVEY_CRAETED_BY + " TEXT," +
            COL_TARGET_SURVEY_MODIFIED_BY + " TEXT)";



    /*Survey Logic*/
    public static final String TABLE_NAME_SURVEY_LOGIC = "survey_logic";

    public static final String COL_TARGET_SURVEY_LOGIC_TABLE_ID = "id";
    public static final String COL_TARGET_SURVEY_LOGIC_QUESTION_CHOICE_ID = "survey_question_choice_id";
    public static final String COL_TARGET_SURVEY_LOGIC_QUESTION_ID = "survey_question_id";


    public static final String CREATE_TABLE_SURVEY_LOGIC = " CREATE TABLE " + TABLE_NAME_SURVEY_LOGIC + " ( " +
            COL_TARGET_SURVEY_LOGIC_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_TARGET_SURVEY_LOGIC_QUESTION_CHOICE_ID + " INTEGER," +
            COL_TARGET_SURVEY_LOGIC_QUESTION_ID + " INTEGER)";




    /*Survey Category*/

    public static final String TABLE_NAME_SURVEY_CATEGORY = "survey_category";

    public static final String COL_SURVEY_CATEGORY_TABLE_ID = "id";
    public static final String COL_SURVEY_CATEGORY_NAME = "name";
    public static final String COL_SURVEY_CATEGORY_CRAETED_BY = "created_by";
    public static final String  COL_SURVEY_CATEGORY_MODIFIED_BY = "modified_by";


    public static final String CREATE_TABLE_SURVEY_CATEGORY = " CREATE TABLE " + TABLE_NAME_SURVEY_CATEGORY + " ( " +
            COL_SURVEY_CATEGORY_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_SURVEY_CATEGORY_NAME + " TEXT," +
            COL_SURVEY_CATEGORY_CRAETED_BY + " TEXT," +
            COL_SURVEY_CATEGORY_MODIFIED_BY + " TEXT)";


    /*SC INFO*/

    public static final String TABLE_NAME_SC_INFO = "sc_infos";

    public static final String COL_SC_INFO_TABLE_ID = "id";
    public static final String COL_SC_INFO_PROJECT_ID = "project_id";
    public static final String COL_SC_INFO_PROJECT_UNIT = "program_unit";
    public static final String COL_SC_INFO_DISTRICT_ID = "district_id";
    public static final String COL_SC_INFO_UPAZILA_ID = "upazila_id";
    public static final String COL_SC_INFO_COMMUNITY_ID = "community_id";
    public static final String COL_SC_INFO_COMMUNITY_NAME= "community_name";
    public static final String COL_SC_INFO_COMMUNITY_WORKER_ID = "community_worker_id";
    public static final String COL_SC_INFO_SC_NUMBER = "sc_number";
    public static final String COL_SC_INFO_SC_FULL_NAME = "sc_full_name";
    public static final String COL_SC_INFO_SC_NAME_KNOWN_BY = "sc_name_known_by";
    public static final String COL_SC_INFO_SC_DATE_OF_BIRTH = "day_of_birth";
    public static final String COL_SC_INFO_SC_GENDER = "gender";
    public static final String COL_SC_INFO_SC_JOINING_DATE = "joining_date";
    public static final String COL_SC_INFO_REASON_FOR_JOINING = "reason_for_joining";
    public static final String COL_SC_INFO_REASON_FOR_LEAVING = "reason_for_leaving";
    public static final String COL_SC_INFO_SC_LIVING_IN_SAME_HOUSE = "sc_living_in_same_house";
    public static final String COL_SC_INFO_LATITUDE = "latitude";
    public static final String COL_SC_INFO_LONGITUDE= "longitude";
    public static final String COL_SC_INFO_PHOTO_LINK = "photo";

    public static final String COL_SC_INFO_SC_PRIORITY_FLAG = "priority_flag";
    public static final String COL_SC_INFO_SC_DATE_FLAG = "date_flag";
    public static final String COL_SC_INFO_SC_EXTRA_FLAG = "extra_flag";


    public static final String CREATE_TABLE_SC_INFO = " CREATE TABLE " + TABLE_NAME_SC_INFO + " ( " +
            COL_SC_INFO_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_SC_INFO_PROJECT_ID + " INTEGER," +
            COL_SC_INFO_PROJECT_UNIT + " INTEGER," +
            COL_SC_INFO_DISTRICT_ID + " INTEGER," +
            COL_SC_INFO_UPAZILA_ID + " INTEGER," +
            COL_SC_INFO_COMMUNITY_ID + " INTEGER," +
            COL_SC_INFO_COMMUNITY_WORKER_ID + " INTEGER," +
            COL_SC_INFO_SC_NUMBER + " INTEGER," +
            COL_SC_INFO_SC_FULL_NAME + " TEXT," +
            COL_SC_INFO_SC_NAME_KNOWN_BY + " TEXT," +
            COL_SC_INFO_SC_DATE_OF_BIRTH + " NUMERIC," +
            COL_SC_INFO_SC_GENDER + " TEXT," +
            COL_SC_INFO_SC_JOINING_DATE + " TEXT," +
            COL_SC_INFO_REASON_FOR_JOINING + " TEXT," +
            COL_SC_INFO_REASON_FOR_LEAVING + " TEXT," +
            COL_SC_INFO_SC_LIVING_IN_SAME_HOUSE + " TEXT," +
            COL_SC_INFO_LATITUDE + " TEXT," +
            COL_SC_INFO_LONGITUDE + " TEXT," +
            COL_SC_INFO_PHOTO_LINK + " TEXT," +
            COL_SC_INFO_SC_PRIORITY_FLAG + " TEXT," +
            COL_SC_INFO_SC_DATE_FLAG + " NUMERIC," +
            COL_SC_INFO_SC_EXTRA_FLAG +
            " TEXT, division_id INTEGER, union_id INTEGER, village_id INTEGER, ward_id INTEGER, religion text, \tlanguage text, sponsor_name text, sponsor_country text)";


    /*survey_question_choice*/


    public static final String TABLE_NAME_SURVEY_QUESTION_CHOICE = "survey_question_choice";

    public static final String COL_SURVEY_SURVEY_QUESTION_CHOICE_TABLE_ID = "id";
    public static final String COL_SURVEY_SURVEY_QUESTION_CHOICE_ID = "survey_question_id";
    public static final String COL_SURVEY_SURVEY_QUESTION_CHOICE_ANS_NO = "ansNo";
    public static final String COL_SURVEY_SURVEY_QUESTION_CHOICE_ANSWER_CHOICE= "answer_choice";


    public static final String CREATE_TABLE_SURVEY_QUESTION_CHOICE = " CREATE TABLE " + TABLE_NAME_SURVEY_QUESTION_CHOICE + " ( " +
            COL_SURVEY_SURVEY_QUESTION_CHOICE_TABLE_ID + " INTEGER PRIMARY KEY," +
            COL_SURVEY_SURVEY_QUESTION_CHOICE_ID + " INTEGER," +
            COL_SURVEY_SURVEY_QUESTION_CHOICE_ANS_NO + " INTEGER," +
            COL_SURVEY_SURVEY_QUESTION_CHOICE_ANSWER_CHOICE + " TEXT)";


    public static java.lang.String CreateTableAnsKeep="CREATE TABLE [anskeep] (\n" +
            "\t[id] integer NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
            "\t[scnumber] integer, \n" +
            "\t[json] text\n" +
            ")";
    public static java.lang.String CreateTableActivity="create table tbl_activity (\n" +
            "\tid integer NOT NULL PRIMARY KEY AUTOINCREMENT\n" +
            "\t,project_id integer\n" +
            "\t,target_id integer\n" +
            "\t,age_range_id integer\n" +
            "\t,activities text\n" +
            "\t,created_by text\n" +
            "\t,modified_by text\n" +
            "\t,created_at text\n" +
            "\t,updated_at text\n" +
            "\t)";
    public static java.lang.String CreateTableScSurveyComplete= "CREATE TABLE [sc_survey_complete] (\n" +
            "\t[ID] integer NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
            "\t[scnumber] integer NOT NULL, \n" +
            "\t[surveyid] integer NOT NULL, \n" +
            "\t[completed] boolean NOT NULL DEFAULT 0\n" +
            ")";
    public static java.lang.String CreateTableAudioStatement= "CREATE TABLE [audioStatement] (\n" +
            "\t[id] integer NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
            "\t[audio] text, \n" +
            "\t[scnumber] integer NOT NULL, \n" +
            "\t[surveydate] datetime NOT NULL\n" +
            ");";

    public static java.lang.String CreateTableOccupations= "CREATE TABLE [occupations] (\n" +
            "\t[id] integer NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
            "\t[code] text, \n" +
            "\t[name] text\n" +
            ");";

    public static java.lang.String CreateTableRelationships= "CREATE TABLE [relationships] (\n" +
            "\t[id] integer NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
            "\t[code] text, \n" +
            "\t[name] text\n" +
            ");";
}
