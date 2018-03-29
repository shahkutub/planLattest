package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class SCFamilyMember {

    private int id;
    private int member_type_id;
    private int sc_mamber_id;
    private String member_name;
    private String member_occupation;

    private String created_by;
    private String modified_by;

    public SCFamilyMember(int id, int member_type_id, int sc_mamber_id,
                          String member_name, String member_occupation, String created_by, String modified_by) {
        this.id = id;
        this.member_type_id = member_type_id;
        this.sc_mamber_id = sc_mamber_id;
        this.member_name = member_name;
        this.member_occupation = member_occupation;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public SCFamilyMember(int member_type_id, int sc_mamber_id,
                          String member_name, String member_occupation, String created_by, String modified_by) {
        this.member_type_id = member_type_id;
        this.sc_mamber_id = sc_mamber_id;
        this.member_name = member_name;
        this.member_occupation = member_occupation;
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

    public int getMember_type_id() {
        return member_type_id;
    }

    public int getSc_mamber_id() {
        return sc_mamber_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public String getMember_occupation() {
        return member_occupation;
    }
}
