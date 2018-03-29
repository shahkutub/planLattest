package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class Role {

    private int id;
    private String name;

    private String created_by;
    private String modified_by;

    public Role(int id, String name, String created_by, String modified_by) {
        this.id = id;
        this.name = name;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public Role(String name, String created_by, String modified_by) {
        this.name = name;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
