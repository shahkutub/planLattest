package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by NanoSoft on 4/16/2017.
 */

public class SurveyCategoryModel {


    private int SurveyCategoryTableId;
    private String SurveyCategoryTableName;
    private String crated_by;
    private String modified_by;

    public SurveyCategoryModel(int surveyCategoryTableId,
                               String surveyCategoryTableName, String crated_by, String modified_by) {
        SurveyCategoryTableId = surveyCategoryTableId;
        SurveyCategoryTableName = surveyCategoryTableName;
        this.crated_by = crated_by;
        this.modified_by = modified_by;
    }

    public SurveyCategoryModel(String surveyCategoryTableName, String crated_by, String modified_by) {
        SurveyCategoryTableName = surveyCategoryTableName;
        this.crated_by = crated_by;
        this.modified_by = modified_by;
    }

    public String getCrated_by() {
        return crated_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public int getSurveyCategoryTableId() {
        return SurveyCategoryTableId;
    }

    public String getSurveyCategoryTableName() {
        return SurveyCategoryTableName;
    }
}
