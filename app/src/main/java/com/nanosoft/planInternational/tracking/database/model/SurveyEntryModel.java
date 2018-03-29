package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by NanoSoft on 4/16/2017.
 */

public class SurveyEntryModel {

    private int surveyEntryId;
    private int surveyEntryCategoryId;
    private String surveyEntryName;
    private String crated_by;
    private String modified_by;

    public SurveyEntryModel(int surveyEntryId, int surveyEntryCategoryId,
                            String surveyEntryName, String crated_by, String modified_by) {
        this.surveyEntryId = surveyEntryId;
        this.surveyEntryCategoryId = surveyEntryCategoryId;
        this.surveyEntryName = surveyEntryName;
        this.crated_by = crated_by;
        this.modified_by = modified_by;
    }

    public SurveyEntryModel(int surveyEntryCategoryId, String surveyEntryName, String crated_by, String modified_by) {
        this.surveyEntryCategoryId = surveyEntryCategoryId;
        this.surveyEntryName = surveyEntryName;
        this.crated_by = crated_by;
        this.modified_by = modified_by;
    }

    public String getCrated_by() {
        return crated_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public int getSurveyEntryId() {
        return surveyEntryId;
    }

    public int getSurveyEntryCategoryId() {
        return surveyEntryCategoryId;
    }

    public String getSurveyEntryName() {
        return surveyEntryName;
    }
}
