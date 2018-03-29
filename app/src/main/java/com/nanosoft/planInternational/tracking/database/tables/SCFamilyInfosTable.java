package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class SCFamilyInfosTable {

    /* public function up()
    {
        Schema::create('sc_family_infos', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('sc_id');
            $table->string('member_full_name');
            $table->string('member_name_known_by');
            $table->string('member_relationship');
            $table->string('member_occupation');
            $table->string('member_gender');
            $table->integer('member_birth_year');
            $table->string('member_is_primary_carer');
            $table->timestamps();
        });
    }


*/

    public static final String TBL_SC_FAMILY_INFOS = "sc_family_infos";
    public static final String COL_SC_FAMILY_INFOS_TBL_ID = "id";
    public static final String COL_SC_FAMILY_INFOS_SC_ID = "sc_id";
    public static final String COL_SC_FAMILY_INFOS_MEMBER_FULL_NAME = "member_full_name";
    public static final String COL_SC_FAMILY_INFOS_MEMBER_NAME_KNOWN_BY = "member_name_known_by";
    public static final String COL_SC_FAMILY_INFOS_MWMBER_RELATIONSHIP = "member_relationship";
    public static final String COL_SC_FAMILY_INFOS_MWMBER_OCCUPATION = "member_occupation";
    public static final String COL_SC_FAMILY_INFOS_MWMBER_GENDER = "member_gender";
    public static final String COL_SC_FAMILY_INFOS_MWMBER_BIRTH_YEAR = "member_birth_year";
    public static final String COL_SC_FAMILY_INFOS_MWMBER_IS_PRIMARY_CAREER = "member_is_primary_carer";
    public static final String COL_SC_FAMILY_INFOS_MWMBER_WHO_LEFT = "member_who_left";
    public static final String COL_SC_FAMILY_INFOS_MWMBER_REASON_FOR_LEAVE = "member_reason_for_leave";
    public static final String COL_SC_FAMILY_INFOS_MWMBER_REASON_FOR_JOIN = "member_reason_for_join";

    public static final String CREATE_TBL_SC_FAMILY_INFOS = "CREATE TABLE " + TBL_SC_FAMILY_INFOS + " ( " +
            COL_SC_FAMILY_INFOS_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_SC_FAMILY_INFOS_SC_ID + " INTEGER, " +
            COL_SC_FAMILY_INFOS_MEMBER_FULL_NAME + " TEXT, " +
            COL_SC_FAMILY_INFOS_MEMBER_NAME_KNOWN_BY + " TEXT, " +
            COL_SC_FAMILY_INFOS_MWMBER_RELATIONSHIP + " TEXT, " +
            COL_SC_FAMILY_INFOS_MWMBER_OCCUPATION + " TEXT, " +
            COL_SC_FAMILY_INFOS_MWMBER_GENDER + " TEXT, " +
            COL_SC_FAMILY_INFOS_MWMBER_BIRTH_YEAR + " INTEGER, " +
            COL_SC_FAMILY_INFOS_MWMBER_IS_PRIMARY_CAREER + " TEXT, " +
            COL_SC_FAMILY_INFOS_MWMBER_WHO_LEFT + " TEXT, " +
            COL_SC_FAMILY_INFOS_MWMBER_REASON_FOR_LEAVE + " TEXT, " +
            COL_SC_FAMILY_INFOS_MWMBER_REASON_FOR_JOIN + " TEXT)";
}
