package com.nanosoft.planInternational.tracking.model;

/**
 * Created by Nanosoft-Android on 5/2/2017.
 */

public class SCPriorityListModel {
    private int id;
    private int idPosition;
    private  int listPosition;


    public SCPriorityListModel(int id, int idPosition, int listPosition) {
        this.id = id;
        this.idPosition = idPosition;
        this.listPosition = listPosition;
    }

    public int getId() {
        return id;
    }

    public int getIdPosition() {
        return idPosition;
    }

    public int getListPosition() {
        return listPosition;
    }
}
