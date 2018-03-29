package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class AgeRange {

    private int id;
    private String title;
    private String type;
    private String start;
    private String end;
    private int target_grp_id;
    private String created_by;
    private String modified_by;

    public AgeRange(int id, String title, String type,
                    String start, String end, int target_grp_id, String created_by, String modified_by) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.start = start;
        this.end = end;
        this.target_grp_id = target_grp_id;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public AgeRange(String title, String type, String start,
                    String end, int target_grp_id, String created_by, String modified_by) {
        this.title = title;
        this.type = type;
        this.start = start;
        this.end = end;
        this.target_grp_id = target_grp_id;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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

    public int getTarget_grp_id() {
        return target_grp_id;
    }

    public void setTarget_grp_id(int target_grp_id) {
        this.target_grp_id = target_grp_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
