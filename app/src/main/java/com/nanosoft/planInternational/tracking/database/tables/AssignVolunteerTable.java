package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class AssignVolunteerTable {


    /*    public function up() {
        Schema::create('tbl_assign_volunteer', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('volunteer_id');
            $table->integer('community_id');
            $table->string('created_by');
            $table->string('modified_by');
            $table->timestamps();
        });
    }*/


    public  final static  String TBL_ASSIGN_VOLUNTEER = "tbl_assign_volunteer";
    public  final static  String COL_ASSIGN_VOLUNTEER_TBL_ID = "id";
    public  final static  String COL_ASSIGN_VOLUNTEER_TBL_VOLUNTEER_ID = "volunteer_id";
    public  final static  String COL_ASSIGN_VOLUNTEER_TBL_COMMUNITY_ID = "community_id";
    public  final static  String COL_ASSIGN_VOLUNTEER_CREATED_BY = "created_by";
    public  final static  String COL_ASSIGN_VOLUNTEER_MODIFIED_BY = "modified_by";

    public static final String CREATE_TBL_ASSIGN_VOLUNTEER = "CREATE TABLE " + TBL_ASSIGN_VOLUNTEER + " ( " +
            COL_ASSIGN_VOLUNTEER_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_ASSIGN_VOLUNTEER_TBL_VOLUNTEER_ID + " INTEGER , " +
            COL_ASSIGN_VOLUNTEER_TBL_COMMUNITY_ID + " INTEGER , " +
            COL_ASSIGN_VOLUNTEER_CREATED_BY + " TEXT , " +
            COL_ASSIGN_VOLUNTEER_MODIFIED_BY + " TEXT )" ;




}
