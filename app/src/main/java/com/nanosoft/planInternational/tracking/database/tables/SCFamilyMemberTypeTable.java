package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class SCFamilyMemberTypeTable {

    /*  public function up() {
        Schema::create('tbl_sc_family_member_type', function (Blueprint $table) {
            $table->increments('id');
            $table->string('mamber_type');
            $table->string('created_by');
            $table->string('modified_by');
            $table->timestamps();
        });
    }*/

    public static final String TBL_SC_FAMILY_MEMBER_TYPE = "tbl_sc_family_member_type";
    public static final String COL_SC_FAMILY_MEMBER_TYPE_TBL_ID = "id";
    public static final String COL_SC_FAMILY_MEMBER_TYPE_TBL_MEMBER_TYPE = "mamber_type";
    public static final String COL_SC_FAMILY_MEMBER_TYPE_TBL_CREATED_BY = "created_by";
    public static final String COL_SC_FAMILY_MEMBER_TYPE_TBL_MODIFIED_BY = "modified_by";

    public static final String CREATE_TBL_SC_FAMILY_MEMBER_TYPE = "CREATE TABLE " + TBL_SC_FAMILY_MEMBER_TYPE + " ( " +
            COL_SC_FAMILY_MEMBER_TYPE_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_SC_FAMILY_MEMBER_TYPE_TBL_MEMBER_TYPE + " TEXT, " +
            COL_SC_FAMILY_MEMBER_TYPE_TBL_CREATED_BY + " TEXT, " +
            COL_SC_FAMILY_MEMBER_TYPE_TBL_MODIFIED_BY + " TEXT )";

}
