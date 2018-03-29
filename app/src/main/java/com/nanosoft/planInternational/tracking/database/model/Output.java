package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class Output {
    private int id;
    private int project_id;
    private String output;
    private String created_by;
    private String modified_by;

    public Output(int id, int project_id, String output, String created_by, String modified_by) {
        this.id = id;
        this.project_id = project_id;
        this.output = output;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public Output(int project_id, String output, String created_by, String modified_by) {
        this.project_id = project_id;
        this.output = output;
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

    public int getProject_id() {
        return project_id;
    }

    public String getOutput() {
        return output;
    }
}
