package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class AssignVolunteer {

    private int id;
    private int volunteer_id;
    private int community_id;
    private String created_by;
    private String modified_by;


    public AssignVolunteer(int id, int volunteer_id, int community_id, String created_by, String modified_by) {
        this.id = id;
        this.volunteer_id = volunteer_id;
        this.community_id = community_id;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public AssignVolunteer(int volunteer_id, int community_id, String created_by, String modified_by) {
        this.volunteer_id = volunteer_id;
        this.community_id = community_id;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVolunteer_id() {
        return volunteer_id;
    }

    public void setVolunteer_id(int volunteer_id) {
        this.volunteer_id = volunteer_id;
    }

    public int getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(int community_id) {
        this.community_id = community_id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }
}
