package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class SCFamilyInfos {

    private int id;
    private int sc_id;
    private String member_full_name;
    private String member_name_known_by;
    private String member_relationship;
    private String member_occupation;
    private String member_gender;
    private int member_birth_year;
    private String member_is_primary_carer;
    private String who_left_household;
    private String no_longer_in_household;
    private String reason_family_lives_with_SC;


    public SCFamilyInfos(int id, int sc_id, String member_full_name, String member_name_known_by, String member_relationship,
                         String member_occupation, String member_gender, int member_birth_year, String member_is_primary_carer
    ,String who_left_household,String no_longer_in_household,String reason_family_lives_with_SC) {
        this.id = id;
        this.sc_id = sc_id;
        this.member_full_name = member_full_name;
        this.member_name_known_by = member_name_known_by;
        this.member_relationship = member_relationship;
        this.member_occupation = member_occupation;
        this.member_gender = member_gender;
        this.member_birth_year = member_birth_year;
        this.member_is_primary_carer = member_is_primary_carer;
        this.who_left_household = who_left_household;
        this.no_longer_in_household = no_longer_in_household;
        this.reason_family_lives_with_SC = reason_family_lives_with_SC;
    }

    public String getWho_left_household() {
        return who_left_household;
    }

    public void setWho_left_household(String who_left_household) {
        this.who_left_household = who_left_household;
    }

    public SCFamilyInfos(int sc_id, String member_full_name, String member_name_known_by, String member_relationship,
                         String member_occupation, String member_gender, int member_birth_year, String member_is_primary_carer) {
        this.sc_id = sc_id;
        this.member_full_name = member_full_name;
        this.member_name_known_by = member_name_known_by;
        this.member_relationship = member_relationship;
        this.member_occupation = member_occupation;
        this.member_gender = member_gender;
        this.member_birth_year = member_birth_year;
        this.member_is_primary_carer = member_is_primary_carer;
    }

    public SCFamilyInfos() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSc_id(int sc_id) {
        this.sc_id = sc_id;
    }

    public void setMember_full_name(String member_full_name) {
        this.member_full_name = member_full_name;
    }

    public void setMember_name_known_by(String member_name_known_by) {
        this.member_name_known_by = member_name_known_by;
    }

    public void setMember_relationship(String member_relationship) {
        this.member_relationship = member_relationship;
    }

    public void setMember_occupation(String member_occupation) {
        this.member_occupation = member_occupation;
    }

    public void setMember_gender(String member_gender) {
        this.member_gender = member_gender;
    }

    public void setMember_birth_year(int member_birth_year) {
        this.member_birth_year = member_birth_year;
    }

    public void setMember_is_primary_carer(String member_is_primary_carer) {
        this.member_is_primary_carer = member_is_primary_carer;
    }

    public String getNo_longer_in_household() {
        return no_longer_in_household;
    }

    public void setNo_longer_in_household(String no_longer_in_household) {
        this.no_longer_in_household = no_longer_in_household;
    }

    public String getReason_family_lives_with_SC() {
        return reason_family_lives_with_SC;
    }

    public void setReason_family_lives_with_SC(String reason_family_lives_with_SC) {
        this.reason_family_lives_with_SC = reason_family_lives_with_SC;
    }

    public int getId() {
        return id;
    }

    public int getSc_id() {
        return sc_id;
    }

    public String getMember_full_name() {
        return member_full_name;
    }

    public String getMember_name_known_by() {
        return member_name_known_by;
    }

    public String getMember_relationship() {
        return member_relationship;
    }

    public String getMember_occupation() {
        return member_occupation;
    }

    public String getMember_gender() {
        return member_gender;
    }

    public int getMember_birth_year() {
        return member_birth_year;
    }

    public String getMember_is_primary_carer() {
        return member_is_primary_carer;
    }
}
