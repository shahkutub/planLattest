package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class GeoLocationByProject {
    private int id;
    private int project_id;
    private int division_id;
    private int district_id;
    private int union_id;
    private int village_id;
    private String community_id;
    private String created_by;
    private String modified_by;


    public GeoLocationByProject(int id, int project_id, int division_id, int district_id, int union_id, int village_id,
                                String community_id, String created_by, String modified_by) {
        this.id = id;
        this.project_id = project_id;
        this.division_id = division_id;
        this.district_id = district_id;
        this.union_id = union_id;
        this.village_id = village_id;
        this.community_id = community_id;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public GeoLocationByProject(int project_id, int division_id, int district_id, int union_id, int village_id,
                                String community_id, String created_by, String modified_by) {
        this.project_id = project_id;
        this.division_id = division_id;
        this.district_id = district_id;
        this.union_id = union_id;
        this.village_id = village_id;
        this.community_id = community_id;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public String getCommunity_id() {
        return community_id;
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

    public int getDivision_id() {
        return division_id;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public int getUnion_id() {
        return union_id;
    }

    public int getVillage_id() {
        return village_id;
    }
}
