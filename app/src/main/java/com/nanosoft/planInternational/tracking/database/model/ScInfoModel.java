package com.nanosoft.planInternational.tracking.database.model;

import android.view.View;

import com.nanosoft.planInternational.tracking.utility.AgeCalculator;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by NanoSoft on 4/16/2017.
 */

public class ScInfoModel {

    private int scInfoTableId;
    private int scInfoProjectId;
    private int scInfoProgramUnit;
    private int scInfoDistrictId;
    private int scInfoUpazillaId;
    private int scInfoCommunityId;
    private String scInfoCommunityName;
    private int scInfoCommunityWorkerId;
    private int scInfoScNumber;
    private String scInfoScFullName;
    private String scInfoScNameKnownBy;
    private String scInfoScDateOfBirth;
    private String scInfoScAge;
    private String scInfoScGender;
    private String joining_date;
    private String scInfoScReasonForJoining;
    private String scInfoScReasonForLeaving;
    private String scInfoScLivingInSameHouse;
    private String latitude;
    private String longitude;
    private String photo;

    private String priorityFlag;
    private String dateFlag;
    private String extraFlag;
    int bDay, bMonth, bYear;
    private String scInfoLocationAddress;
    private String scInfoLanguage;
    private String scInfoReligion;


    public ScInfoModel() {
    }

    public ScInfoModel(int scInfoTableId, String priorityFlag, String dateFlag) {
        this.scInfoTableId = scInfoTableId;
        this.priorityFlag = priorityFlag;
        this.dateFlag = dateFlag;
    }

    public ScInfoModel(int scInfoProjectId, int scInfoProgramUnit, int scInfoDistrictId, int scInfoUpazillaId, int scInfoCommunityId, int scInfoCommunityWorkerId, int scInfoScNumber, String scInfoScFullName, String scInfoScNameKnownBy, String scInfoScAge, String scInfoScGender,String scInfoCommunityName,String joining_date, String scInfoScReasonForJoining, String scInfoScReasonForLeaving, String scInfoScLivingInSameHouse, String latitude, String longitude, String photo, String priorityFlag,
                       String dateFlag, String extraFlag, int bDay, int bMonth, int bYear ) {
        this.scInfoProjectId = scInfoProjectId;
        this.scInfoProgramUnit = scInfoProgramUnit;
        this.scInfoDistrictId = scInfoDistrictId;
        this.scInfoUpazillaId = scInfoUpazillaId;
        this.scInfoCommunityId = scInfoCommunityId;
        this.scInfoCommunityWorkerId = scInfoCommunityWorkerId;
        this.scInfoScNumber = scInfoScNumber;
        this.scInfoScFullName = scInfoScFullName;
        this.scInfoScNameKnownBy = scInfoScNameKnownBy;
        this.scInfoScAge = scInfoScAge;
        this.scInfoScGender = scInfoScGender;
        this.scInfoCommunityName = scInfoCommunityName;
        this.joining_date = joining_date;
        this.scInfoScReasonForJoining = scInfoScReasonForJoining;
        this.scInfoScReasonForLeaving = scInfoScReasonForLeaving;
        this.scInfoScLivingInSameHouse = scInfoScLivingInSameHouse;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photo = photo;
        this.priorityFlag = priorityFlag;
        this.dateFlag = dateFlag;
        this.extraFlag = extraFlag;
        this.bDay=bDay;
        this.bMonth=bMonth;
        this.bYear=bYear;
        this.scInfoScDateOfBirth=bDay+"/"+bMonth+"/"+bYear;
       // this.scInfoScDateOfBirth=bYear+"/"+bMonth+"/"+bDay;
    }




    public int getScInfoProjectId() {
        return scInfoProjectId;
    }

    public void setScInfoProjectId(int scInfoProjectId) {
        this.scInfoProjectId = scInfoProjectId;
    }

    public static ArrayList<ScInfoModel> mySelectedList = new ArrayList<>();


    public static void addToSelectedItemsList(ArrayList<ScInfoModel> list) {
        for (ScInfoModel p : list) {
            boolean contains = false;
            for (ScInfoModel pp : mySelectedList) {
                if (pp.getScInfoScNumber() == p.getScInfoScNumber()) {
                    contains = true;
                    break;
                }
            }
            if (!contains)
                mySelectedList.add(p);
        }
    }


    private boolean isSelected;
    public int CheckBoxVisible = View.VISIBLE;
    public String dateTime = "";


    public ScInfoModel(int scInfoTableId, int scInfoScNumber,
                       String scInfoScFullName, String scInfoScAge, String photo, String priorityFlag, String dateFlag) {
        this.scInfoTableId = scInfoTableId;
        this.scInfoScNumber = scInfoScNumber;
        this.scInfoScFullName = scInfoScFullName;
        this.scInfoScAge = scInfoScAge;
        this.photo = photo;
        this.priorityFlag = priorityFlag;
        this.dateFlag = dateFlag;
    }

    public ScInfoModel(int scInfoTableId, int scInfoScNumber,
                       String scInfoScFullName, String scInfoScAge, String photo, String priorityFlag, String dateFlag,String joining_date) {
        this.scInfoTableId = scInfoTableId;
        this.scInfoScNumber = scInfoScNumber;
        this.scInfoScFullName = scInfoScFullName;
        this.scInfoScAge = scInfoScAge;
        this.photo = photo;
        this.priorityFlag = priorityFlag;
        this.dateFlag = dateFlag;
        this.joining_date = joining_date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhoto() {
        return photo;
    }

    public ScInfoModel(String priorityFlag, String dateFlag, String extraFlag) {
        this.priorityFlag = priorityFlag;
        this.dateFlag = dateFlag;
        this.extraFlag = extraFlag;
    }

    public String getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(String joining_date) {
        this.joining_date = joining_date;
    }

    public String getPriorityFlag() {
        return priorityFlag;
    }

    public String getDateFlag() {
        return dateFlag;
    }

    public String getExtraFlag() {
        return extraFlag;
    }

    public int getScInfoTableId() {
        return scInfoTableId;
    }

    public int getScInfoProgramUnit() {
        return scInfoProgramUnit;
    }

    public int getScInfoDistrictId() {
        return scInfoDistrictId;
    }

    public int getScInfoUpazillaId() {
        return scInfoUpazillaId;
    }

    public int getScInfoCommunityId() {
        return scInfoCommunityId;
    }

    public int getScInfoCommunityWorkerId() {
        return scInfoCommunityWorkerId;
    }

    public int getScInfoScNumber() {
        return scInfoScNumber;
    }

    public String getScInfoScFullName() {
        return scInfoScFullName;
    }

    public String getScInfoScNameKnownBy() {
        return scInfoScNameKnownBy;
    }

    public String getScInfoScAge() {
        return scInfoScAge;
    }

    public String getScInfoDateOfBirth() {
        return scInfoScDateOfBirth;
    }
    public String getScInfoScGender() {
        return scInfoScGender;
    }

    public String getScInfoScReasonForJoining() {
        return scInfoScReasonForJoining;
    }

    public String getScInfoScReasonForLeaving() {
        return scInfoScReasonForLeaving;
    }

    public String getScInfoScLivingInSameHouse() {
        return scInfoScLivingInSameHouse;
    }

    public int getAgeMonths(){
        Calendar now = Calendar.getInstance();
        int td = now.get(Calendar.DAY_OF_MONTH);
        int tm = now.get(Calendar.MONTH) + 1;
        int ty = now.get(Calendar.YEAR);
        return new AgeCalculator(bDay, bMonth, bYear, td, tm, ty).getMonths();
    }

    public String getScInfoLocationAddress() {
        return scInfoLocationAddress;
    }

    public void setScInfoLocationAddress(String scInfoLocationAddress) {
        this.scInfoLocationAddress = scInfoLocationAddress;
    }

    public String getLanguage() {
        return scInfoLanguage;
    }

    public void setScInfoLanguage(String scInfoLanguage) {
        this.scInfoLanguage = scInfoLanguage;
    }

    public void setScInfoReligion(String scInfoReligion) {
        this.scInfoReligion = scInfoReligion;
    }

    public String getScInfoReligion() {
        return scInfoReligion;
    }

    public void setScInfoTableId(int scInfoTableId) {
        this.scInfoTableId = scInfoTableId;
    }

    public void setScInfoProgramUnit(int scInfoProgramUnit) {
        this.scInfoProgramUnit = scInfoProgramUnit;
    }

    public void setScInfoDistrictId(int scInfoDistrictId) {
        this.scInfoDistrictId = scInfoDistrictId;
    }

    public void setScInfoUpazillaId(int scInfoUpazillaId) {
        this.scInfoUpazillaId = scInfoUpazillaId;
    }

    public void setScInfoCommunityId(int scInfoCommunityId) {
        this.scInfoCommunityId = scInfoCommunityId;
    }

    public String getScInfoCommunityName() {
        return scInfoCommunityName;
    }

    public void setScInfoCommunityName(String scInfoCommunityName) {
        this.scInfoCommunityName = scInfoCommunityName;
    }

    public void setScInfoCommunityWorkerId(int scInfoCommunityWorkerId) {
        this.scInfoCommunityWorkerId = scInfoCommunityWorkerId;
    }

    public void setScInfoScNumber(int scInfoScNumber) {
        this.scInfoScNumber = scInfoScNumber;
    }

    public void setScInfoScFullName(String scInfoScFullName) {
        this.scInfoScFullName = scInfoScFullName;
    }

    public void setScInfoScNameKnownBy(String scInfoScNameKnownBy) {
        this.scInfoScNameKnownBy = scInfoScNameKnownBy;
    }

    public String getScInfoScDateOfBirth() {
        return scInfoScDateOfBirth;
    }

    public void setScInfoScDateOfBirth(String scInfoScDateOfBirth) {
        this.scInfoScDateOfBirth = scInfoScDateOfBirth;
    }

    public void setScInfoScAge(String scInfoScAge) {
        this.scInfoScAge = scInfoScAge;
    }

    public void setScInfoScGender(String scInfoScGender) {
        this.scInfoScGender = scInfoScGender;
    }

    public void setScInfoScReasonForJoining(String scInfoScReasonForJoining) {
        this.scInfoScReasonForJoining = scInfoScReasonForJoining;
    }

    public void setScInfoScReasonForLeaving(String scInfoScReasonForLeaving) {
        this.scInfoScReasonForLeaving = scInfoScReasonForLeaving;
    }

    public void setScInfoScLivingInSameHouse(String scInfoScLivingInSameHouse) {
        this.scInfoScLivingInSameHouse = scInfoScLivingInSameHouse;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPriorityFlag(String priorityFlag) {
        this.priorityFlag = priorityFlag;
    }

    public void setDateFlag(String dateFlag) {
        this.dateFlag = dateFlag;
    }

    public void setExtraFlag(String extraFlag) {
        this.extraFlag = extraFlag;
    }

    public int getbDay() {
        return bDay;
    }

    public void setbDay(int bDay) {
        this.bDay = bDay;
    }

    public int getbMonth() {
        return bMonth;
    }

    public void setbMonth(int bMonth) {
        this.bMonth = bMonth;
    }

    public int getbYear() {
        return bYear;
    }

    public void setbYear(int bYear) {
        this.bYear = bYear;
    }

    public String getScInfoLanguage() {
        return scInfoLanguage;
    }

    public static ArrayList<ScInfoModel> getMySelectedList() {
        return mySelectedList;
    }

    public static void setMySelectedList(ArrayList<ScInfoModel> mySelectedList) {
        ScInfoModel.mySelectedList = mySelectedList;
    }

    public int getCheckBoxVisible() {
        return CheckBoxVisible;
    }

    public void setCheckBoxVisible(int checkBoxVisible) {
        CheckBoxVisible = checkBoxVisible;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}


