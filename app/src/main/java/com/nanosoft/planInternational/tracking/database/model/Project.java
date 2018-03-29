package com.nanosoft.planInternational.tracking.database.model;

import java.util.Date;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class Project {
    private int id;
    private int category_id;
    private String project_title;
    private int country_code;
    private String profit_center_name;
    private int project_center_code;
    private int impact_area;
    private String project_code;
    private Date start_date;
    private Date end_date;
    private String project_location;
    private String project_beneficiaries;
    private float project_budget;
    private String fad_spad_link;
    private String project_summary;
    private String project_imp_partner;
    private String project_remark;

    private String created_by;
    private String modified_by;


    public Project(int id, int category_id, String project_title) {
        this.id = id;
        this.category_id = category_id;
        this.project_title = project_title;
    }

    public Project(String project_imp_partner, int id, int category_id, String project_title, int country_code, String profit_center_name, int project_center_code, int impact_area, String project_code, Date start_date, Date end_date, String project_location, String project_beneficiaries, float project_budget, String fad_spad_link, String project_summary,
                   String project_remark, String created_by, String modified_by) {
        this.project_imp_partner = project_imp_partner;
        this.id = id;
        this.category_id = category_id;
        this.project_title = project_title;
        this.country_code = country_code;
        this.profit_center_name = profit_center_name;
        this.project_center_code = project_center_code;
        this.impact_area = impact_area;
        this.project_code = project_code;
        this.start_date = start_date;
        this.end_date = end_date;
        this.project_location = project_location;
        this.project_beneficiaries = project_beneficiaries;
        this.project_budget = project_budget;
        this.fad_spad_link = fad_spad_link;
        this.project_summary = project_summary;
        this.project_remark = project_remark;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public Project(int category_id, String project_title, int country_code, String profit_center_name, int project_center_code, int impact_area, String project_code, Date start_date, Date end_date, String project_location, String project_beneficiaries, float project_budget, String fad_spad_link, String project_summary, String project_imp_partner,
                   String project_remark, String created_by, String modified_by) {
        this.category_id = category_id;
        this.project_title = project_title;
        this.country_code = country_code;
        this.profit_center_name = profit_center_name;
        this.project_center_code = project_center_code;
        this.impact_area = impact_area;
        this.project_code = project_code;
        this.start_date = start_date;
        this.end_date = end_date;
        this.project_location = project_location;
        this.project_beneficiaries = project_beneficiaries;
        this.project_budget = project_budget;
        this.fad_spad_link = fad_spad_link;
        this.project_summary = project_summary;
        this.project_imp_partner = project_imp_partner;
        this.project_remark = project_remark;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public String getProject_imp_partner() {
        return project_imp_partner;
    }

    public String getProject_remark() {
        return project_remark;
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

    public int getCategory_id() {
        return category_id;
    }

    public String getProject_title() {
        return project_title;
    }

    public int getCountry_code() {
        return country_code;
    }

    public String getProfit_center_name() {
        return profit_center_name;
    }

    public int getProject_center_code() {
        return project_center_code;
    }

    public int getImpact_area() {
        return impact_area;
    }

    public String getProject_code() {
        return project_code;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public String getProject_location() {
        return project_location;
    }

    public String getProject_beneficiaries() {
        return project_beneficiaries;
    }

    public float getProject_budget() {
        return project_budget;
    }

    public String getFad_spad_link() {
        return fad_spad_link;
    }

    public String getProject_summary() {
        return project_summary;
    }


}
