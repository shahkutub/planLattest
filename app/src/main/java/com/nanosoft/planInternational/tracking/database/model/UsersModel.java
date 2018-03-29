package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by NanoSoft on 4/16/2017.
 */

public class UsersModel {


    private int usersTableId;
    private int userRoleId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private int userStatus;
    private String userPhoto;
    private String remember_token;
    private String crated_by;
    private String modified_by;
    private String user_type;

    public UsersModel(int usersTableId, int userRoleId, String userName, String userEmail, String userPassword, int userStatus,
                      String userPhoto, String remember_token, String crated_by, String modified_by, String user_type) {
        this.usersTableId = usersTableId;
        this.userRoleId = userRoleId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
        this.userPhoto = userPhoto;
        this.remember_token = remember_token;
        this.crated_by = crated_by;
        this.modified_by = modified_by;
        this.user_type = user_type;
    }

    public UsersModel(int userRoleId, String userName, String userEmail, String userPassword, int userStatus, String userPhoto,
                      String remember_token, String crated_by, String modified_by, String user_type) {
        this.userRoleId = userRoleId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
        this.userPhoto = userPhoto;
        this.remember_token = remember_token;
        this.crated_by = crated_by;
        this.modified_by = modified_by;
        this.user_type = user_type;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getCrated_by() {
        return crated_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public int getUsersTableId() {
        return usersTableId;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public String getUserPhoto() {
        return userPhoto;
    }
}
