package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class RoleTable {

    /* public function up()
    {
        Schema::create('role', function (Blueprint $table) {
            $table->increments('id');
            $table->string('name')->unique();
            $table->timestamps();
            $table->integer('created_by');
            $table->integer('modified_by');
        });
    }
*/
    public final static String TBL_ROLE = "role";
    public final static String COL_ROL_TBL_ID = "id";
    public final static String COL_ROL_NAME = "name";
    public final static String COL_ROL_CREATED_BY = "created_by";
    public final static String COL_ROL_MODIFIED_BY = "modified_by";


    public static final String CREATE_TBL_ROLE = "CREATE TABLE " + TBL_ROLE + " ( " +
            COL_ROL_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_ROL_NAME + " TEXT, " +
            COL_ROL_CREATED_BY + " TEXT, " +
            COL_ROL_MODIFIED_BY + " TEXT )";

}
