package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class MenuPermissionTable {
    /*  public function up()
    {
        Schema::create('menu_permission', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('role_id');
            $table->integer('menu_id');
        });
    }
*/

    public static final String TBL_MENU_PERMISSION ="menu_permission";
    public static final String COL_MENU_PERMISSION_TBL_ID ="id";
    public static final String COL_MENU_PERMISSION_ROLE_ID ="role_id";
    public static final String COL_MENU_PERMISSION_MENU_ID ="menu_id";

    public static final String  CREATE_TBL_MENU_PERMISSION = "CREATE TABLE " + TBL_MENU_PERMISSION + " ( " +
            COL_MENU_PERMISSION_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_MENU_PERMISSION_ROLE_ID + " INTEGER, " +
            COL_MENU_PERMISSION_MENU_ID + " INTEGER )" ;


}
