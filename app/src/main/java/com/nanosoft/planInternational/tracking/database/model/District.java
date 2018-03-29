package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class District {

    private int id;
    private  int division_id;
    private String district_name;
    private String district_created_by;
    private String district_modified_by;

    public District(int id, int division_id, String district_name,
                    String district_created_by, String district_modified_by) {
        this.id = id;
        this.division_id = division_id;
        this.district_name = district_name;
        this.district_created_by = district_created_by;
        this.district_modified_by = district_modified_by;
    }

    public District(int id, String district_name) {
        this.id = id;
        this.district_name = district_name;
    }

    public String getDistrict_created_by() {
        return district_created_by;
    }

    public String getDistrict_modified_by() {
        return district_modified_by;
    }

    public int getId() {
        return id;
    }

    public int getDivision_id() {
        return division_id;
    }

    public String getDistrict_name() {
        return district_name;
    }
}
