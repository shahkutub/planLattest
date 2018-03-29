package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class Division {

    private int id;
    private String division_name;
    private int country_id;


    public Division(int id, String division_name) {
        this.id = id;
        this.division_name = division_name;
    }

    public int getCountry_id() {
        return country_id;
    }

    public Division(String division_name) {
        this.division_name = division_name;
    }

    public int getId() {
        return id;
    }

    public String getDivision_name() {
        return division_name;
    }
}
