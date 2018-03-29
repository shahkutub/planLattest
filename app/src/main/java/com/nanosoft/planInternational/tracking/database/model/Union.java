package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class Union {

    private int id;
    private int upazila_id;
    private String union_name;

    public Union(int id, int upazila_id, String union_name) {
        this.id = id;
        this.upazila_id = upazila_id;
        this.union_name = union_name;
    }

    public Union(int id, String union_name) {
        this.id = id;
        this.union_name = union_name;
    }

    public int getId() {
        return id;
    }

    public int getUpazila_id() {
        return upazila_id;
    }

    public String getUnion_name() {
        return union_name;
    }
}
