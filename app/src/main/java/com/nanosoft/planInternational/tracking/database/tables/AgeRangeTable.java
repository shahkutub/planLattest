package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class AgeRangeTable {

    /*    Schema::create('tbl_age_range', function (Blueprint $table) {
            $table->increments('id');
            $table->string('title');
            $table->string('range');
            $table->integer('target_grp_id');
            $table->timestamps();
            $table->integer('created_by');
            $table->integer('modified_by');
        });
    }*/

    public static final String TBL_AGE_RANGE = "tbl_age_range";
    public static final String COL_AGE_RANGE_TBL_ID = "id";
    public static final String COL_AGE_RANGE_TITLE = "title";
    public static final String COL_AGE_RANGE_TYPE = "type";
    public static final String COL_AGE_RANGE_START = "start";
    public static final String COL_AGE_RANGE_END = "end";
    public static final String COL_AGE_RANGE_TARGET_GRP_ID = "target_grp_id";
    public static final String COL_AGE_RANGE_CREATED_BY = "created_by";
    public static final String COL_AGE_RANGE_MODIFIED_BY = "modified_by";




    public static final String CREATE_TBL_AGE_RANGE = "CREATE TABLE " + TBL_AGE_RANGE + " ( " +
            COL_AGE_RANGE_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_AGE_RANGE_TITLE + " TEXT, " +
            COL_AGE_RANGE_TYPE + " TEXT, " +
            COL_AGE_RANGE_START + " TEXT, " +
            COL_AGE_RANGE_END + " TEXT, " +
            COL_AGE_RANGE_TARGET_GRP_ID + " INTEGER, " +
            COL_AGE_RANGE_CREATED_BY + " TEXT, " +
            COL_AGE_RANGE_MODIFIED_BY + " TEXT )" ;


}
