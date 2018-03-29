package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class CommunityTable {

    /*  public function up() {
        Schema::create('tbl_community', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('project_id');
            $table->integer('division_id');
            $table->integer('district_id');
            $table->integer('union_id');
            $table->integer('village_id');
            $table->string('community_name');
            $table->string('created_by');
            $table->string('modified_by');
            $table->timestamps();



             "id": 10,
          "project_id": 1,
          "division_id": 8,
          "district_id": 18,
          "upazila_id": 122,
          "union_id": 1,
          "ward_id": 2,
          "village_id": 1,
          "community_name": "zxzxzxzx",
          "created_by": "1",
          "modified_by": "1"

        });
    }*/

    /* {
          "id": 1,
          "project_id": 0,
          "division_id": 1,
          "district_id": 1,
          "upazila_id": 1,
          "union_id": 1,
          "village_id": 1,
          "community_name": "community name",
          "created_by": "2",
          "modified_by": "2"
        },*/


    /*
    * "table_name": "tbl_community",
            "values": [
                {
                    "id": 1,
                    "project_id": null,
                    "division_id": 1,
                    "district_id": 3,
                    "upazila_id": 1,
                    "union_id": 1,
                    "ward_id": null,
                    "village_id": null,
                    "community_name": "CPB - Citypolly Basti",
                    "created_by": "1",
                    "modified_by": "27"
                },*/
    public static String TBL_COMMUNITY = "tbl_community";

    public static String COL_COMMUNITY_TBL_ID = "id";
    public static String COL_COMMUNITY_TBL_PROJECT_ID = "project_id";
    public static String COL_COMMUNITY_TBL_DIVISION_ID = "division_id";
    public static String COL_COMMUNITY_TBL__DISTRICT_ID = "district_id";
    public static String COL_COMMUNITY_TBL__UPAZILA_ID = "upazila_id";
    public static String COL_COMMUNITY_TBL__UNION_ID = "union_id";
    public static String COL_COMMUNITY_TBL__WARD_ID = "ward_id";
    public static String COL_COMMUNITY_TBL__VILLAGE_ID = "village_id";
    public static String COL_COMMUNITY_TBL__COMMUNITY_NAME = "community_name";
    public static String COL_COMMUNITY_TBL__CREATED_BY = "created_by";
    public static String COL_COMMUNITY_TBL__MODIFIED_BY = "modified_by";

    public static final String CREATE_TBL_COMMUNITY = "CREATE TABLE " + TBL_COMMUNITY + " ( " +
            COL_COMMUNITY_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_COMMUNITY_TBL_PROJECT_ID + " INTEGER, " +
            COL_COMMUNITY_TBL_DIVISION_ID + " INTEGER, " +
            COL_COMMUNITY_TBL__DISTRICT_ID + " INTEGER, " +
            COL_COMMUNITY_TBL__UPAZILA_ID + " INTEGER, " +
            COL_COMMUNITY_TBL__UNION_ID + " INTEGER, " +
            COL_COMMUNITY_TBL__WARD_ID + " INTEGER, " +
            COL_COMMUNITY_TBL__VILLAGE_ID + " INTEGER, " +
            COL_COMMUNITY_TBL__COMMUNITY_NAME + " TEXT, " +
            COL_COMMUNITY_TBL__CREATED_BY + " TEXT, " +
            COL_COMMUNITY_TBL__MODIFIED_BY + " TEXT )";
}
