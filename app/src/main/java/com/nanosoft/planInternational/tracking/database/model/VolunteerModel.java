package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by NanoSoft on 4/16/2017.
 */

public class VolunteerModel {

    private int volunteerTableId;
    private int volunteerUserId;
    private String volunteerName;
    private String volunteerDesignation;
    private String volunteerAddress;
    private String volunteerMobile;
    private String volunteerEmail;
    private String volunteerUsername;
    private String volunteerPassword;
    private String crated_by;
    private String modified_by;


    /*"values": [
        {
          "id": 1,
          "user_id": 3,
          "community_id": 10,
          "volunteer_name": "Volunteer Name",
          "volunteer_designation": "volunteer",
          "volunteer_address": "dhaka",
          "volunteer_mobile": "12345678",
          "volunteer_email": "volunteer.email@gmail.com",
          "volunteer_username": "",
          "volunteer_password": "$2y$10$1LXY3j1/glO4TdQncA95S.6SSzivsWeRG7KpmdvLI//fZEBFMEjG6",
          "created_by": "1",
          "modified_by": "1"
        },*/
    public VolunteerModel(int volunteerTableId, int volunteerUserId, String volunteerName, String volunteerDesignation, String volunteerAddress, String volunteerMobile, String volunteerEmail, String volunteerUsername,
                          String volunteerPassword, String crated_by, String modified_by) {
        this.volunteerTableId = volunteerTableId;
        this.volunteerUserId = volunteerUserId;
        this.volunteerName = volunteerName;
        this.volunteerDesignation = volunteerDesignation;
        this.volunteerAddress = volunteerAddress;
        this.volunteerMobile = volunteerMobile;
        this.volunteerEmail = volunteerEmail;
        this.volunteerUsername = volunteerUsername;
        this.volunteerPassword = volunteerPassword;
        this.crated_by = crated_by;
        this.modified_by = modified_by;
    }

    public VolunteerModel(int volunteerUserId, String volunteerName, String volunteerDesignation, String volunteerAddress, String volunteerMobile, String volunteerEmail, String volunteerUsername,
                          String volunteerPassword, String crated_by, String modified_by) {
        this.volunteerUserId = volunteerUserId;
        this.volunteerName = volunteerName;
        this.volunteerDesignation = volunteerDesignation;
        this.volunteerAddress = volunteerAddress;
        this.volunteerMobile = volunteerMobile;
        this.volunteerEmail = volunteerEmail;
        this.volunteerUsername = volunteerUsername;
        this.volunteerPassword = volunteerPassword;
        this.crated_by = crated_by;
        this.modified_by = modified_by;
    }

    public int getVolunteerUserId() {
        return volunteerUserId;
    }

    public String getCrated_by() {
        return crated_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public int getVolunteerTableId() {
        return volunteerTableId;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public String getVolunteerDesignation() {
        return volunteerDesignation;
    }

    public String getVolunteerAddress() {
        return volunteerAddress;
    }

    public String getVolunteerMobile() {
        return volunteerMobile;
    }

    public String getVolunteerEmail() {
        return volunteerEmail;
    }

    public String getVolunteerUsername() {
        return volunteerUsername;
    }

    public String getVolunteerPassword() {
        return volunteerPassword;
    }
}
