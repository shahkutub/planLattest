package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class GeoLocationByProjectTable {


    /*   public function up()
    {
        Schema::create('tbl_geo_location_by_project', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('division_id');
            $table->integer('district_id');
            $table->integer('upazila_id');
            $table->integer('union_id');
            $table->integer('village_id');
            $table->timestamps();
            $table->integer('created_by');
            $table->integer('modified_by');
        });
    }
*/
/* "table_name": "tbl_geo_location_by_project",
      "values": [
        {
          "id": 1,
          "division_id": 6,
          "district_id": 13,
          "upazila_id": 2,
          "union_id": 1,
          "village_id": 1,
          "community_id": 2,
          "created_by": 2,
          "modified_by": 2
        },
        {*/

/*
    "id": 1,
            "project_id": 4,
            "division_id": 8,
            "district_id": 18,
            "upazila_id": 122,
            "union_id": 1,
            "ward_id": 1,
            "village_id": 1,
            "community_id": 10,
            "created_by": 1,
            "modified_by": 1

    */

    public static String TBL_GEOLOCATION_BY_PROJECT = "tbl_geo_location_by_project";

    public static String COL_GEOLOCATION_BY_PROJECT_TBL_ID = "id";
    public static String COL_GEOLOCATION_BY_PROJECT_ID = "project_id";
    public static String COL_GEOLOCATION_BY_PROJECT_DIVISION_ID = "division_id";
    public static String COL_GEOLOCATION_BY_PROJECT_DISTRICT_ID = "district_id";
    public static String COL_GEOLOCATION_BY_PROJECT_UPAZILA_ID = "upazila_id";
    public static String COL_GEOLOCATION_BY_PROJECT_UNION_ID = "union_id";
    public static String COL_GEOLOCATION_BY_PROJECT_WARD_ID = "ward_id";
    public static String COL_GEOLOCATION_BY_PROJECT_VILLAGE_ID = "village_id";
    public static String COL_GEOLOCATION_BY_PROJECT_COMMUNITY_ID = "community_id";
    public static String COL_GEOLOCATION_BY_PROJECT_CREATED_BY = "created_by";
    public static String COL_GEOLOCATION_BY_PROJECT_MODIFIED_BY = "modified_by";


    public static final String CREATE_TBL_GEOLOCATION_BY_PROJECT= "CREATE TABLE " + TBL_GEOLOCATION_BY_PROJECT + " ( " +
            COL_GEOLOCATION_BY_PROJECT_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_GEOLOCATION_BY_PROJECT_ID + " INTEGER, " +
            COL_GEOLOCATION_BY_PROJECT_DIVISION_ID + " INTEGER, " +
            COL_GEOLOCATION_BY_PROJECT_DISTRICT_ID + " INTEGER, " +
            COL_GEOLOCATION_BY_PROJECT_UPAZILA_ID + " INTEGER, " +
            COL_GEOLOCATION_BY_PROJECT_UNION_ID + " INTEGER, " +
            COL_GEOLOCATION_BY_PROJECT_WARD_ID + " INTEGER, " +
            COL_GEOLOCATION_BY_PROJECT_VILLAGE_ID + " INTEGER, " +
            COL_GEOLOCATION_BY_PROJECT_COMMUNITY_ID + " INTEGER, " +
            COL_GEOLOCATION_BY_PROJECT_CREATED_BY + " TEXT, " +
            COL_GEOLOCATION_BY_PROJECT_MODIFIED_BY + " TEXT )" ;


}
