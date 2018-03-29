package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by NanoSoft on 5/13/2017.
 */

public class ImplementingPartnersModel {

    private int id;
    private String name;
    private String created_by;
    private String modified_by;

    public ImplementingPartnersModel(int id, String name, String created_by, String modified_by) {
        this.id = id;
        this.name = name;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getModified_by() {
        return modified_by;
    }
}
