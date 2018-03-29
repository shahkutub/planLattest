package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class MenusTable {
    /* public function up()
    {
        Schema::create('menus', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('parent_id')->nullable();
            $table->integer('action')->nullable();
            $table->string('name');
            $table->string('menu_url')->nullable();
            $table->integer('module_id');
            $table->integer('status');
        });
    }*/
    public static String TBL_MENUS = "menus";
    public static String COL_MENUS_TBL_ID = "id";
    public static String COL_MENUS_PARENT_ID = "parent_id";
    public static String COL_MENUS_ACTION = "action";
    public static String COL_MENUS_NAME = "name";
    public static String COL_MENUS_MENU_URL = "menu_url";
    public static String COL_MENUS_MODEL_ID = "module_id";
    public static String COL_MENUS_STATUS = "status";

    public static final String CREATE_TBL_MENUS = "CREATE TABLE " + TBL_MENUS + " ( " +
            COL_MENUS_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_MENUS_PARENT_ID + " INTEGER, " +
            COL_MENUS_ACTION + " INTEGER, " +
            COL_MENUS_NAME + " TEXT, " +
            COL_MENUS_MENU_URL + " TEXT, " +
            COL_MENUS_MODEL_ID + " INTEGER, " +
            COL_MENUS_STATUS + " INTEGER )";
}
