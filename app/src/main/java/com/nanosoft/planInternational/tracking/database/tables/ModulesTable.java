package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class ModulesTable {
    /*
    public function up()
    {
        Schema::create('modules', function (Blueprint $table) {
        $table->increments('id');
        $table->string('name');
        $table->string('icon_class');
        $table->tinyInteger('status');
        $table->timestamps();
    });
    }
    */

    public static final String TBL_MODULES = "modules";
    public static final String COL_MODULES_TBL_ID = "id";
    public static final String COL_MODULES_NAME = "name";
    public static final String COL_MODULES_ICON_CLASS = "icon_class";
    public static final String COL_MODULES_STATUS = "status";

    public static final String CREATE_TBL_MODULES = "CREATE TABLE " + TBL_MODULES + " ( " +
            COL_MODULES_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_MODULES_NAME + " TEXT, " +
            COL_MODULES_ICON_CLASS + " TEXT, " +
            COL_MODULES_STATUS + " INTEGER )";
}
