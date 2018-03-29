package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class SCFamilyMemberTable {
    /* public function up() {
        Schema::create('tbl_sc_family_member', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('member_type_id');
            $table->integer('sc_mamber_id');
            $table->string('member_name');
            $table->string('member_occupation');
            $table->string('created_by');
            $table->string('modified_by');
            $table->timestamps();
        });
    }*/
    public static final String TBL_SC_FAMILY_MAMBER = "tbl_sc_family_member";
    public static final String COL_SC_FAMILY_MAMBER_TBL_ID = "id";
    public static final String COL_SC_FAMILY_MAMBER_TBL_MEMBER_TYPE_ID = "member_type_id";
    public static final String COL_SC_FAMILY_MAMBER_TBL_SC_MEMBER_ID = "sc_mamber_id";
    public static final String COL_SC_FAMILY_MAMBER_TBL_MEMBER_NAME = "member_name";
    public static final String COL_SC_FAMILY_MAMBER_TBL_MEMBER_OCCUPATION = "member_occupation";
    public static final String COL_SC_FAMILY_MAMBER_TBL_CREATED_BY = "created_by";
    public static final String COL_SC_FAMILY_MAMBER_TBL_MODIFIED_BY = "modified_by";


    public static final String CREATE_TBL_SC_FAMILY_MAMBER = "CREATE TABLE " + TBL_SC_FAMILY_MAMBER + " ( " +
            COL_SC_FAMILY_MAMBER_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_SC_FAMILY_MAMBER_TBL_MEMBER_TYPE_ID + " INTEGER, " +
            COL_SC_FAMILY_MAMBER_TBL_SC_MEMBER_ID + " INTEGER, " +
            COL_SC_FAMILY_MAMBER_TBL_MEMBER_NAME + " TEXT, " +
            COL_SC_FAMILY_MAMBER_TBL_MEMBER_OCCUPATION + " TEXT, " +
            COL_SC_FAMILY_MAMBER_TBL_CREATED_BY + " TEXT, " +
            COL_SC_FAMILY_MAMBER_TBL_MODIFIED_BY + " TEXT )";
}
