package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class Community {

    private int id;
    private int project_id;
    private int division_id;
    private int district_id;
    private int upazila_id;
    private int union_id;
    private int wardId;
    private int village_id;
    private String community_name;
    private String created_by;
    private String modified_by;




    public Community(int id, int project_id, int division_id, int district_id, int upazila_id, int union_id, int wardId, int village_id,
                     String community_name, String created_by, String modified_by) {
        this.id = id;
        this.project_id = project_id;
        this.division_id = division_id;
        this.district_id = district_id;
        this.upazila_id = upazila_id;
        this.union_id = union_id;
        this.wardId = wardId;
        this.village_id = village_id;
        this.community_name = community_name;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public Community(int project_id, int division_id, int district_id, int upazila_id, int union_id,
                     int village_id, String community_name, String created_by, String modified_by) {
        this.project_id = project_id;
        this.division_id = division_id;
        this.district_id = district_id;
        this.upazila_id = upazila_id;
        this.union_id = union_id;
        this.village_id = village_id;
        this.community_name = community_name;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public int getWardId() {
        return wardId;
    }

    public int getUpazila_id() {
        return upazila_id;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getDivision_id() {
        return division_id;
    }

    public void setDivision_id(int division_id) {
        this.division_id = division_id;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public int getUnion_id() {
        return union_id;
    }

    public void setUnion_id(int union_id) {
        this.union_id = union_id;
    }

    public int getVillage_id() {
        return village_id;
    }

    public void setVillage_id(int village_id) {
        this.village_id = village_id;
    }

    public String getCommunity_name() {
        return community_name;
    }

    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }
}
