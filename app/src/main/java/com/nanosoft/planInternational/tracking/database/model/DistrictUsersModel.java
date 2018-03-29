package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by NanoSoft on 5/13/2017.
 */

public class DistrictUsersModel {

/*   {
          "id": 1,
          "user_id": 3,
          "division_id": 8,
          "district_id": 18,
          "name": "District User 1",
          "designation": "google",
          "address": "dhaka",
          "mobile": "12345",
          "email": "dis.1@gmail.com",
          "password": "$2y$10$vRkUVKutfHBDAlhZM2AKheNbYZaplg4HKMrHZNutC3iG.IhJ4HSbO",
          "created_by": "1",
          "modified_by": "1"
        },
        {*/

    private int id;
    private int user_id;
    private int division_id;
    private int district_id;
    private String name;
    private String designation;
    private String address;
    private String mobile;
    private String email;
    private String password;
    private String created_by;
    private String modified_by;


    public DistrictUsersModel(int id, int user_id, int division_id, int district_id) {
        this.id = id;
        this.user_id = user_id;
        this.division_id = division_id;
        this.district_id = district_id;
    }

    public DistrictUsersModel(int id, int user_id, int division_id, int district_id, String name, String designation, String address,
                              String mobile, String email, String password, String created_by, String modified_by) {
        this.id = id;
        this.user_id = user_id;
        this.division_id = division_id;
        this.district_id = district_id;
        this.name = name;
        this.designation = designation;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public DistrictUsersModel(int user_id, int division_id, int district_id, String name, String designation, String address,
                              String mobile, String email, String password, String created_by, String modified_by) {
        this.user_id = user_id;
        this.division_id = division_id;
        this.district_id = district_id;
        this.name = name;
        this.designation = designation;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
