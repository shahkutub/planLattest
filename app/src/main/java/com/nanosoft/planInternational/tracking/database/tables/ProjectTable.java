package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class ProjectTable {
    /*  public function up()
    {
        Schema::create('tbl_project', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('category_id');
            $table->string('project_title');
            $table->integer('country_code');
            $table->text('profit_center_name');
            $table->integer('project_center_code');
            $table->integer('impact_area');
            $table->string('project_code');
            $table->date('start_date');
            $table->date('end_date');
            $table->string('project_location' );
            $table->text('project_beneficiaries' );
            $table->float('project_budget' );
            $table->string('fad_spad_link' );
            $table->text('project_summary' );
            $table->text('project_imp_patner');
            $table->timestamps();
            $table->integer('created_by');
            $table->integer('modified_by');
        });
    }
*/
    public static final String TBL_PROJECT = "tbl_project";
    public static final String COL_PROJECT_TBL_ID = "id";
    public static final String COL_PROJECT_CATEGORY_ID = "category_id";       //survey_category
    public static final String COL_PROJECT_TBL_PROJECT_TITLE = "project_title";
    public static final String COL_PROJECT_COUNTRY_CODE = "country_code";
    public static final String COL_PROJECT_TBL_PROFIT_CENTER_NAME = "profit_center_name";
    public static final String COL_PROJECT_TBL_PROJECT_CENTER_CODE = "project_center_code";
    public static final String COL_PROJECT_IMPACT_AREA = "impact_area";
    public static final String COL_PROJECT_TBL_PROJECT_CODE = "project_code";
    public static final String COL_PROJECT_START_DATE = "start_date";
    public static final String COL_PROJECT_END_DATE = "end_date";
    public static final String COL_PROJECT_PROJECT_LOCATION = "project_location";

    public static final String COL_PROJECT_TBL_PROJECT_BENEFICIARIES = "project_beneficiaries";
    public static final String COL_PROJECT_TBL_PROJECT_BUDJET = "project_budget";
    public static final String COL_PROJECT_FAD_SPAD_LINK = "fad_spad_link";
    public static final String COL_PROJECT_TBL_PROJECT_SUMMARY = "project_summary";
    public static final String COL_PROJECT_TBL_PROJECT_IMP_PARTNER = "project_imp_partner";
    public static final String COL_PROJECT_TBL_PROJECT_REMARK = "remarks";
    public static final String COL_PROJECT_TBL_CREATED_BY = "created_by";
    public static final String COL_PROJECT_TBL_MODIFIED_BY = "modified_by";


    public static final String CREATE_TBL_PROJECT = "CREATE TABLE " + TBL_PROJECT + " ( " +
            COL_PROJECT_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_PROJECT_TBL_PROJECT_TITLE + " TEXT, " +
            COL_PROJECT_CATEGORY_ID + " INTEGER, " +
            COL_PROJECT_COUNTRY_CODE + " INTEGER, " +
            COL_PROJECT_TBL_PROFIT_CENTER_NAME + " TEXT, " +
            COL_PROJECT_TBL_PROJECT_CENTER_CODE + " INTEGER, " +
            COL_PROJECT_IMPACT_AREA + " INTEGER, " +
            COL_PROJECT_TBL_PROJECT_CODE + " TEXT, " +
            COL_PROJECT_START_DATE + " NUMERIC, " +
            COL_PROJECT_END_DATE + " NUMERIC, " +
            COL_PROJECT_PROJECT_LOCATION + " TEXT, " +
            COL_PROJECT_TBL_PROJECT_BENEFICIARIES + " TEXT, " +
            COL_PROJECT_TBL_PROJECT_BUDJET + " REAL, " +
            COL_PROJECT_FAD_SPAD_LINK + " TEXT, " +
            COL_PROJECT_TBL_PROJECT_SUMMARY + " TEXT, " +
            COL_PROJECT_TBL_PROJECT_IMP_PARTNER + " TEXT, " +
            COL_PROJECT_TBL_PROJECT_REMARK + " TEXT, " +
            COL_PROJECT_TBL_CREATED_BY + " TEXT, " +
            COL_PROJECT_TBL_MODIFIED_BY + " TEXT," +
            " is_drafted Text)";
}
