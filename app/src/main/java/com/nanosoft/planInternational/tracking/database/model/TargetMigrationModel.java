package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by NanoSoft on 4/16/2017.
 */

public class TargetMigrationModel {

    private int targetMigrationTableId;
    private String targetMigrationTableGroupName;
    private String crated_by;
    private String modified_by;

    public TargetMigrationModel(int targetMigrationTableId, String targetMigrationTableGroupName, String crated_by, String modified_by) {
        this.targetMigrationTableId = targetMigrationTableId;
        this.targetMigrationTableGroupName = targetMigrationTableGroupName;
        this.crated_by = crated_by;
        this.modified_by = modified_by;
    }

    public TargetMigrationModel(String targetMigrationTableGroupName, String crated_by, String modified_by) {
        this.targetMigrationTableGroupName = targetMigrationTableGroupName;
        this.crated_by = crated_by;
        this.modified_by = modified_by;
    }

    public String getCrated_by() {
        return crated_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public int getTargetMigrationTableId() {
        return targetMigrationTableId;
    }

    public String getTargetMigrationTableGroupName() {
        return targetMigrationTableGroupName;
    }
}
