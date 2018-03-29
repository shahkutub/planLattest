package com.nanosoft.planInternational.tracking.database.manager;

/**
 * Created by Nanosoft-Android on 5/29/2017.
 */
public class SurveyEntry {
    public String Name;
    public int ID;
    public boolean Completed;

    public SurveyEntry(String name, int ID, boolean completed) {
        Name = name;
        this.ID = ID;
        Completed = completed;
    }

    @Override
    public String toString() {
        return Name;
    }
}
