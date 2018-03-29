package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 5/3/2017.
 */

public class Wards {

    /* "table_name": "wards",
      "values": [
        {
          "id": 2,
          "union_id": 1,
          "name": "tytsdg87",
          "code": "45489789",
          "created_by": 1,
          "modified_by": 1
        }*/

    private int id;
    private int union_id;
    private String name;
    private String code;
    private String created_by;
    private  String modified_by;


    public Wards(int id, int union_id, String name, String code, String created_by, String modified_by) {
        this.id = id;
        this.union_id = union_id;
        this.name = name;
        this.code = code;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public Wards(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnion_id() {
        return union_id;
    }

    public void setUnion_id(int union_id) {
        this.union_id = union_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
