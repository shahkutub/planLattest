package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 5/3/2017.
 */

public class WardTable {


    public static final String TBL_WARDS ="wards";
    public static final String COL_WARDS_TBL_ID ="id";
    public static final String COL_WARDS_UNION_ID ="union_id";
    public static final String COL_WARDS_NAME="name";
    public static final String COL_WARDS_CODE="code";
    public static final String COL_WARDS_CREATED_BY ="created_by";
    public static final String COL_WARDS_MODIFIED_BY ="modified_by";

    public static final String CREATE_TBL_WARDS= "CREATE TABLE " + TBL_WARDS + " ( " +
            COL_WARDS_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_WARDS_UNION_ID + " INTEGER , " +
            COL_WARDS_NAME + " TEXT, " +
            COL_WARDS_CODE + " TEXT, " +
            COL_WARDS_CREATED_BY + " TEXT, " +
            COL_WARDS_MODIFIED_BY + " TEXT, division_id INTEGER, district_id INTEGER, upazila_id INTEGER )";

}

