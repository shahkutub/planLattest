package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class Village {

    private int id;
    private int union_id;
    private String village_name;

    public Village(String village_name, int id, int union_id) {
        this.village_name = village_name;
        this.id = id;
        this.union_id = union_id;
    }

    public Village(String village_name, int union_id) {
        this.village_name = village_name;
        this.union_id = union_id;
    }

    public int getId() {
        return id;
    }

    public int getUnion_id() {
        return union_id;
    }

    public String getVillage_name() {
        return village_name;
    }
}
