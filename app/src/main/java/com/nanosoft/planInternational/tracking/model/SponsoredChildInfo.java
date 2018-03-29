package com.nanosoft.planInternational.tracking.model;

import android.view.View;

import com.nanosoft.planInternational.tracking.database.model.ScInfoModel;

import java.util.ArrayList;

/**
 * Created by Nanosoft-Android on 4/22/2017.
 */

public class SponsoredChildInfo {




    public static ArrayList<ScInfoModel> mySelectedList = new ArrayList<>();

    public static void addToSelectedItemsList(ArrayList<ScInfoModel> list) {
        for (ScInfoModel p : list) {
            boolean contains = false;
            for (ScInfoModel pp : mySelectedList) {
                if (pp.getScInfoScNumber() == (p.getScInfoScNumber())) {
                    contains = true;
                    break;
                }
            }
            if(!contains)
                mySelectedList.add(p);
        }
    }


    private  int id;
    private  String scPhotoUrl;
    private  String scName;
    private  String scId;
    private  String scAge;
    private String checkBoxStatus;

    private boolean isSelected;
    public int CheckBoxVisible = View.VISIBLE;
    public String dateTime = "";



    public SponsoredChildInfo(int id, String scPhotoUrl, String scName, String scId, String scAge, String checkBoxStatus) {
        this.id = id;
        this.scPhotoUrl = scPhotoUrl;
        this.scName = scName;
        this.scId = scId;
        this.scAge = scAge;
        this.checkBoxStatus = checkBoxStatus;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getId() {
        return id;
    }

    public SponsoredChildInfo(String checkBoxStatus) {
        this.checkBoxStatus = checkBoxStatus;
    }

    public String getCheckBoxStatus() {
        return checkBoxStatus;
    }

    public String getScPhotoUrl() {
        return scPhotoUrl;
    }

    public String getScName() {
        return scName;
    }

    public String getScId() {
        return scId;
    }

    public String getScAge() {
        return scAge;
    }
}
