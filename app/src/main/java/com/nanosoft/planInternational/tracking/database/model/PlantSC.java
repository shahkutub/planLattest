package com.nanosoft.planInternational.tracking.database.model;

import com.nanosoft.planInternational.tracking.utility.AgeCalculator;

import java.util.Calendar;

/**
 * Created by Nanosoft-Android on 3/6/2017.
 */

public class PlantSC {

    private int id;
    private String communityWorker;
    private String sc_Number;
    private String family_Member;
    private String sc_Full_LegalName;
    private String sc_NameKnownBy;
    private String day_of_Birth;
    private String month_of_Birth;
    private String year_of_Birth;
    private String relationship_with_SC;
    private String gender;
    private String occupation;
    private String is_Primary_Carer;
    private String reason_for_Joining;
    private String reason_for_leaving;
    private String is_the_SC_living_in_the_same_household_as_12_months_ago_at_the_time_of_the_last_interview;
    private boolean selected;
    public PlantSC(int id, String is_the_SC_living_in_the_same_household_as_12_months_ago_at_the_time_of_the_last_interview, String reason_for_Joining, String is_Primary_Carer, String reason_for_leaving, String occupation, String gender, String relationship_with_SC, String year_of_Birth, String month_of_Birth, String day_of_Birth, String sc_NameKnownBy, String sc_Full_LegalName, String family_Member, String sc_Number, String communityWorker) {
        this.id = id;
        this.is_the_SC_living_in_the_same_household_as_12_months_ago_at_the_time_of_the_last_interview = is_the_SC_living_in_the_same_household_as_12_months_ago_at_the_time_of_the_last_interview;
        this.reason_for_Joining = reason_for_Joining;
        this.is_Primary_Carer = is_Primary_Carer;
        this.reason_for_leaving = reason_for_leaving;
        this.occupation = occupation;
        this.gender = gender;
        this.relationship_with_SC = relationship_with_SC;
        this.year_of_Birth = year_of_Birth;
        this.month_of_Birth = month_of_Birth;
        this.day_of_Birth = day_of_Birth;
        this.sc_NameKnownBy = sc_NameKnownBy;
        this.sc_Full_LegalName = sc_Full_LegalName;
        this.family_Member = family_Member;
        this.sc_Number = sc_Number;
        this.communityWorker = communityWorker;
    }

    public PlantSC(int id, String sc_Full_LegalName, String occupation) {
        this.id = id;
        this.sc_Full_LegalName = sc_Full_LegalName;
        this.occupation = occupation;
    }

    public PlantSC(int id, String sc_Full_LegalName, String year_of_Birth, String sc_Number) {
        this.id = id;
        this.sc_Full_LegalName = sc_Full_LegalName;
        this.year_of_Birth = year_of_Birth;
        this.sc_Number = sc_Number;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIs_the_SC_living_in_the_same_household_as_12_months_ago_at_the_time_of_the_last_interview() {
        return is_the_SC_living_in_the_same_household_as_12_months_ago_at_the_time_of_the_last_interview;
    }

    public void setIs_the_SC_living_in_the_same_household_as_12_months_ago_at_the_time_of_the_last_interview(String is_the_SC_living_in_the_same_household_as_12_months_ago_at_the_time_of_the_last_interview) {
        this.is_the_SC_living_in_the_same_household_as_12_months_ago_at_the_time_of_the_last_interview = is_the_SC_living_in_the_same_household_as_12_months_ago_at_the_time_of_the_last_interview;
    }

    public String getReason_for_leaving() {
        return reason_for_leaving;
    }

    public void setReason_for_leaving(String reason_for_leaving) {
        this.reason_for_leaving = reason_for_leaving;
    }

    public String getReason_for_Joining() {
        return reason_for_Joining;
    }

    public void setReason_for_Joining(String reason_for_Joining) {
        this.reason_for_Joining = reason_for_Joining;
    }

    public String getIs_Primary_Carer() {
        return is_Primary_Carer;
    }

    public void setIs_Primary_Carer(String is_Primary_Carer) {
        this.is_Primary_Carer = is_Primary_Carer;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRelationship_with_SC() {
        return relationship_with_SC;
    }

    public void setRelationship_with_SC(String relationship_with_SC) {
        this.relationship_with_SC = relationship_with_SC;
    }

    public String getYear_of_Birth() {
        return year_of_Birth;
    }

    public void setYear_of_Birth(String year_of_Birth) {
        this.year_of_Birth = year_of_Birth;
    }

    public String getMonth_of_Birth() {
        return month_of_Birth;
    }

    public void setMonth_of_Birth(String month_of_Birth) {
        this.month_of_Birth = month_of_Birth;
    }

    public String getDay_of_Birth() {
        return day_of_Birth;
    }

    public void setDay_of_Birth(String day_of_Birth) {
        this.day_of_Birth = day_of_Birth;
    }

    public String getSc_NameKnownBy() {
        return sc_NameKnownBy;
    }

    public void setSc_NameKnownBy(String sc_NameKnownBy) {
        this.sc_NameKnownBy = sc_NameKnownBy;
    }

    public String getSc_Full_LegalName() {
        return sc_Full_LegalName;
    }

    public void setSc_Full_LegalName(String sc_Full_LegalName) {
        this.sc_Full_LegalName = sc_Full_LegalName;
    }

    public String getFamily_Member() {
        return family_Member;
    }

    public void setFamily_Member(String family_Member) {
        this.family_Member = family_Member;
    }

    public String getSc_Number() {
        return sc_Number;
    }

    public void setSc_Number(String sc_Number) {
        this.sc_Number = sc_Number;
    }

    public String getCommunityWorker() {
        return communityWorker;
    }

    public void setCommunityWorker(String communityWorker) {
        this.communityWorker = communityWorker;
    }

    public String getAge() {
        Calendar now = Calendar.getInstance();
        int td = now.get(Calendar.DAY_OF_MONTH);
        int tm = now.get(Calendar.MONTH) + 1;
        int ty = now.get(Calendar.YEAR);

        int fd = Integer.parseInt(day_of_Birth);
        int fm = Integer.parseInt(month_of_Birth);
        int fy = Integer.parseInt(year_of_Birth);
        return new AgeCalculator(fy, fm, fd, ty, tm, td).getAgeString();
    }
}
