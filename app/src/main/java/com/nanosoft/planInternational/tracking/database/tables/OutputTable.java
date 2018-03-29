package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class OutputTable {
    /*  public function up()
    {
        Schema::create('tbl_output', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('project_id');
            $table->text('output');
            $table->timestamps();
            $table->integer('created_by');
            $table->integer('modified_by');
        });
    }
*/
    public static final String TBL_OUTPUT = "tbl_output";
    public static final String COL_OUTPUT_TBL_ID = "id";
    public static final String COL_OUTPUT_PROJECT_ID = "project_id";
    public static final String COL_OUTPUT_TBL_OUTPUT = "output";
    public static final String COL_OUTPUT_TBL_CREATED_BY = "created_by";
    public static final String COL_OUTPUT_TBL_MODIFIED_BY = "modified_by";

    public static final String CREATE_TBL_OUTPUT = "CREATE TABLE " + TBL_OUTPUT + " ( " +
            COL_OUTPUT_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_OUTPUT_PROJECT_ID + " INTEGER, " +
            COL_OUTPUT_TBL_OUTPUT + " TEXT, " +
            COL_OUTPUT_TBL_CREATED_BY + " TEXT, " +
            COL_OUTPUT_TBL_MODIFIED_BY + " TEXT )";


}
