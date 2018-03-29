package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class CountriesTable {
    /* public function up()
    {
        Schema::create('countries', function (Blueprint $table) {
            $table->increments('id');
            $table->string('name')->unique();
            $table->integer('code')->nullable();
            $table->timestamps();
        });
    }*/

    public static final String TBL_COUNTRIES ="countries";
    public static final String COL_COUNTRIES_TBL_ID ="id";
    public static final String COL_COUNTRIES_NAME ="name";
    public static final String COL_COUNTRIES_CODE ="code";

    public static final String CREATE_TBL_COUNTRIES= "CREATE TABLE " + TBL_COUNTRIES + " ( " +
            COL_COUNTRIES_TBL_ID + " INTEGER PRIMARY KEY, " +
            COL_COUNTRIES_NAME + " TEXT, " +
            COL_COUNTRIES_CODE + " TEXT )";

}
