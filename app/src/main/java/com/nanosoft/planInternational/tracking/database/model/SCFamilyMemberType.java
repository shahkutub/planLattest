package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class SCFamilyMemberType {

    private int id;
    private String mamber_type;
    private String created_by;
    private String modified_by;

    public SCFamilyMemberType(String mamber_type, String created_by, String modified_by, int id) {
        this.mamber_type = mamber_type;
        this.created_by = created_by;
        this.modified_by = modified_by;
        this.id = id;
    }

    public SCFamilyMemberType(String mamber_type, String created_by, String modified_by) {
        this.mamber_type = mamber_type;
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

    public String getMamber_type() {
        return mamber_type;
    }
}
