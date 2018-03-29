package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 5/13/2017.
 */

public class DistrictUsersTable
{



    /*"table_name": "district_users",
      "values": [
        {
          "id": 1,
          "user_id": 3,
          "division_id": 8,
          "district_id": 18,
          "name": "District User 1",
          "designation": "google",
          "address": "dhaka",
          "mobile": "12345",
          "email": "dis.1@gmail.com",
          "password": "$2y$10$vRkUVKutfHBDAlhZM2AKheNbYZaplg4HKMrHZNutC3iG.IhJ4HSbO",
          "created_by": "1",
          "modified_by": "1"
        },*/

    public static String TBL_DISTRICT_USERS = "district_users";//district_users

    public static String COL_DISTRICT_USERS_TBL_ID = "id";
    public static String COL_DISTRICT_USERS_TBL_USER_ID = "user_id";
    public static String COL_DISTRICT_USERS_TBL_DIVISION_ID = "division_id";
    public static String COL_DISTRICT_USERS_TBL_DISTRICT_ID = "district_id";
    public static String COL_DISTRICT_USERS_TBL__NAME = "name";
    public static String COL_DISTRICT_USERS_TBL_DESIGNATION = "designation";
    public static String COL_DISTRICT_USERS_TBL_ADDRESS = "address";
    public static String COL_DISTRICT_USERS_TBL_MOBILE = "mobile";
    public static String COL_DISTRICT_USERS_TBL_EMAIL = "email";
    public static String COL_DISTRICT_USERS_TBL_PASSWORD = "password";
    public static String COL_DISTRICT_USERS_TBL__CREATED_BY = "created_by";
    public static String COL_DISTRICT_USERS_TBL__MODIFIED_BY = "modified_by";

    public static final String CREATE_TBL_DISTRICT_USERS = " CREATE TABLE " + TBL_DISTRICT_USERS + " ( " +
            COL_DISTRICT_USERS_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_DISTRICT_USERS_TBL_USER_ID + " INTEGER, " +
            COL_DISTRICT_USERS_TBL_DIVISION_ID + " INTEGER, " +
            COL_DISTRICT_USERS_TBL_DISTRICT_ID + " INTEGER, " +
            COL_DISTRICT_USERS_TBL__NAME + " TEXT, " +
            COL_DISTRICT_USERS_TBL_DESIGNATION + " TEXT, " +
            COL_DISTRICT_USERS_TBL_ADDRESS + " TEXT, " +
            COL_DISTRICT_USERS_TBL_MOBILE + " TEXT, " +
            COL_DISTRICT_USERS_TBL_EMAIL + " TEXT, " +
            COL_DISTRICT_USERS_TBL_PASSWORD + " TEXT, " +
            COL_DISTRICT_USERS_TBL__CREATED_BY + " TEXT, " +
            COL_DISTRICT_USERS_TBL__MODIFIED_BY + " TEXT )";
}
