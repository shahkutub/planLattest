package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class Upazila {
    private int id;
    private  int district_id;
    private String upazila_name;

    public Upazila(int id, int district_id, String upazila_name) {
        this.id = id;
        this.district_id = district_id;
        this.upazila_name = upazila_name;
    }

    public Upazila(int id, String upazila_name) {
        this.id = id;
        this.upazila_name = upazila_name;
    }

    public int getId() {
        return id;
    }

    public String getUpazila_name() {
        return upazila_name;
    }

    public int getDistrict_id() {
        return district_id;
    }
}
