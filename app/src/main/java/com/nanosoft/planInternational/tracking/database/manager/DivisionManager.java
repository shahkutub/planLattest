package com.nanosoft.planInternational.tracking.database.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nanosoft.planInternational.tracking.database.helper.DatabaseHelper;
import com.nanosoft.planInternational.tracking.database.model.Community;
import com.nanosoft.planInternational.tracking.database.model.District;
import com.nanosoft.planInternational.tracking.database.model.DistrictUsersModel;
import com.nanosoft.planInternational.tracking.database.model.Division;
import com.nanosoft.planInternational.tracking.database.model.Union;
import com.nanosoft.planInternational.tracking.database.model.Upazila;
import com.nanosoft.planInternational.tracking.database.model.Wards;
import com.nanosoft.planInternational.tracking.database.tables.AssignVolunteerTable;
import com.nanosoft.planInternational.tracking.database.tables.CommunityTable;
import com.nanosoft.planInternational.tracking.database.tables.CountriesTable;
import com.nanosoft.planInternational.tracking.database.tables.DistrictUsersTable;
import com.nanosoft.planInternational.tracking.database.tables.DivisionWiseTable;
import com.nanosoft.planInternational.tracking.database.tables.SCFamilyMemberTypeTable;
import com.nanosoft.planInternational.tracking.database.tables.TableStructure;
import com.nanosoft.planInternational.tracking.database.tables.WardTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Nanosoft-Android on 4/8/2017.
 */

public class DivisionManager {

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;

    private Context context;

    private Division division;
    private District district;
    private Upazila upazila;
    private Union union;
    private Wards wards;
    //private Community community;


    public DivisionManager(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    public boolean addVillage(String villageName) {
        openDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DivisionWiseTable.COL_VILLAGE_NAME, villageName);
        long insertVillage = sqLiteDatabase.insert(DivisionWiseTable.TBL_VILLAGE, null, contentValues);
        if (insertVillage > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addData() {

        openDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCFamilyMemberTypeTable.COL_SC_FAMILY_MEMBER_TYPE_TBL_ID, 2);

        long insertData = sqLiteDatabase.insert(SCFamilyMemberTypeTable.TBL_SC_FAMILY_MEMBER_TYPE, null, contentValues);
        closeDataBase();
        if (insertData > 0) {

            return true;
        } else {
            return false;

        }
    }

    public boolean multipleTableDataInsert(String tableName, JSONArray jsonArray) {
        // int tableColumNumber = tableName.getColumnNumber();
        // String tableColumName = tableName.getColumnNumber();
        JSONObject jsonObject = new JSONObject();
        int columnNumber = jsonObject.length();
        String keys = jsonObject.keys().next();
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(tableName);//now original string is changed
        System.out.println(sb);//prints Hello Java
        try {
            String colum1 = jsonObject.getString("id");
            String colum2 = jsonObject.getString("name");
  /*      long jkkj =  "INSERT INTO 'tablename' (colum1, colum2) VALUES
        ('data1', 'data2'),
        ('data1', 'data2'),
        ('data1', 'data2'),
        ('data1', 'data2')";*/
        } catch (JSONException e) {
            e.printStackTrace();
        }


          /*  "id": 1,
            "name": "Bangladesh",
            "code": 880,*/
        return true;
    }


    public boolean addValue() {
        openDataBase();
        ContentValues divisionContentValues = new ContentValues();
        for (int i = 0; i < divisionName.length; i++) {
            divisionContentValues.put(DivisionWiseTable.COL_DIV_NAME, divisionName[i]);
            long insert1 = sqLiteDatabase.insert(DivisionWiseTable.TBL_DIVISION, null, divisionContentValues);

            if (i == 0) {
                ContentValues districtDBContentValues = new ContentValues();
                for (int j = 0; j < districtDB.length; j++) {
                    districtDBContentValues.put(DivisionWiseTable.COL_DIST_NAME, districtDB[j]);
                    districtDBContentValues.put(DivisionWiseTable.COL_DIV_FOREIGN_KEY, String.valueOf(i));
                    long insert2 = sqLiteDatabase.insert(DivisionWiseTable.TBL_DISTRICT, null, districtDBContentValues);

                    if (j == 0) {
                        ContentValues thanadhContentValues = new ContentValues();
                        for (int k = 0; k < thanadh.length; k++) {
                            thanadhContentValues.put(DivisionWiseTable.COL_UPAZILA_NAME, thanadh[k]);
                            thanadhContentValues.put(DivisionWiseTable.COL_DISTRICT_FOREIGN_KEY, String.valueOf(j));
                            long insert3 = sqLiteDatabase.insert(DivisionWiseTable.TBL_UPAZILA, null, thanadhContentValues);

                            if (k == 0) {
                                ContentValues unionFulpurContentValues = new ContentValues();
                                for (int l = 0; l < unionFulpur.length; l++) {
                                    unionFulpurContentValues.put(DivisionWiseTable.COL_UNION_NAME, unionFulpur[l]);
                                    unionFulpurContentValues.put(DivisionWiseTable.COL_UPAZILA_FOREIGN_KEY, String.valueOf(k));
                                    long insert4 = sqLiteDatabase.insert(DivisionWiseTable.TBL_UNINION, null, unionFulpurContentValues);

                                    if (l == 0) {
                                        ContentValues villagesRambodrapurContentValues = new ContentValues();
                                        for (int m = 0; m < villagesRambodrapur.length; m++) {
                                            villagesRambodrapurContentValues.put(DivisionWiseTable.COL_VILLAGE_NAME, villagesRambodrapur[m]);
                                            villagesRambodrapurContentValues.put(DivisionWiseTable.COL_WARDS_FOREIGN_KEY, String.valueOf(l));
                                            long insert5 = sqLiteDatabase.insert(DivisionWiseTable.TBL_VILLAGE, null, villagesRambodrapurContentValues);


                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        closeDataBase();
        //if(insertVillage>0) {
        //  return true;
        // }else {
        return false;

    }

    private void openDataBase() {
        sqLiteDatabase = databaseHelper.getWritableDatabase();

    }

    private void closeDataBase() {
        databaseHelper.close();
    }

    public ArrayList<String> getCountryList() {
        ArrayList<String> divisionNameList = new ArrayList<>();

        openDataBase();

        Cursor cursor = sqLiteDatabase.query(CountriesTable.TBL_COUNTRIES, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String divisionNames = cursor.getString(cursor.getColumnIndex(CountriesTable.COL_COUNTRIES_NAME));

            divisionNameList.add(divisionNames);
        }
        closeDataBase();
        return divisionNameList;
    }


    public ArrayList<Division> getDivisionList() {

        ArrayList<Division> divisionNameList = new ArrayList<>();

        division = new Division(0, "Division");
        divisionNameList.add(division);

        openDataBase();

        Cursor cursor = sqLiteDatabase.query(DivisionWiseTable.TBL_DIVISION, null, null, null, null, null, "division_name");
        while (cursor.moveToNext()) {
            int divisionId = cursor.getInt(cursor.getColumnIndex(DivisionWiseTable.COL_DIV_ID));
            String divisionName = cursor.getString(cursor.getColumnIndex(DivisionWiseTable.COL_DIV_NAME));
            //  int countryId = cursor.getInt(cursor.getColumnIndex(DivisionWiseTable.COL_COUNTRY_FOREIGN_KEY));


            division = new Division(divisionId, divisionName);
            divisionNameList.add(division);
        }
        cursor.close();
        closeDataBase();
        return divisionNameList;
    }


    public ArrayList<District> getDistrict(int id) {
        ArrayList<District> districtNameList = new ArrayList<>();


        district = new District(0, "District");
        districtNameList.add(district);


        openDataBase();
        String sql = "SELECT * FROM " + DivisionWiseTable.TBL_DISTRICT + " WHERE " + DivisionWiseTable.COL_DIV_FOREIGN_KEY + " = '" + id + "' order by district_name";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int districtId = cursor.getInt(cursor.getColumnIndex(DivisionWiseTable.COL_DIST_ID));
            String districtNames = cursor.getString(cursor.getColumnIndex(DivisionWiseTable.COL_DIST_NAME));
            district = new District(districtId, districtNames);
            districtNameList.add(district);
        }
        cursor.close();
        closeDataBase();
        return districtNameList;
    }

    public ArrayList<Upazila> getUpazila(int id) {
        ArrayList<Upazila> upazillaNameList = new ArrayList<>();

        upazila = new Upazila(0, "Upazila");
        upazillaNameList.add(upazila);

        openDataBase();
        String sql = "SELECT * FROM " + DivisionWiseTable.TBL_UPAZILA + " WHERE " + DivisionWiseTable.COL_DISTRICT_FOREIGN_KEY + " = '" + id + "' order by upazila_name";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            int upazillaId = cursor.getInt(cursor.getColumnIndex(DivisionWiseTable.COL_UPAZILA_ID));
            String upazillaName = cursor.getString(cursor.getColumnIndex(DivisionWiseTable.COL_UPAZILA_NAME));

            upazila = new Upazila(upazillaId, upazillaName);
            upazillaNameList.add(upazila);
        }
        cursor.close();
        closeDataBase();
        return upazillaNameList;
    }


    public ArrayList<Union> getUnion(int id) {
        ArrayList<Union> unionNameList = new ArrayList<>();

        union = new Union(0, "Union");
        unionNameList.add(union);

        openDataBase();
        String sql = "SELECT * FROM " + DivisionWiseTable.TBL_UNINION + " WHERE " + DivisionWiseTable.COL_UPAZILA_FOREIGN_KEY + " = '" + id + "' order by union_name";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int unionId = cursor.getInt(cursor.getColumnIndex(DivisionWiseTable.COL_UNION_ID));
            String unionNames = cursor.getString(cursor.getColumnIndex(DivisionWiseTable.COL_UNION_NAME));

            union = new Union(unionId, unionNames);
            unionNameList.add(union);
        }
        cursor.close();
        closeDataBase();
        return unionNameList;
    }


    public ArrayList<Wards> getWard(int id) {
        ArrayList<Wards> wordNameList = new ArrayList<>();

        wards = new Wards(0, "Ward", "0");
        wordNameList.add(wards);

        openDataBase();
        String sql = "SELECT * FROM " + WardTable.TBL_WARDS + " WHERE " + WardTable.COL_WARDS_UNION_ID + " = '" + id + "' order by name";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int wardId = cursor.getInt(cursor.getColumnIndex(WardTable.COL_WARDS_TBL_ID));
            String wardNames = cursor.getString(cursor.getColumnIndex(WardTable.COL_WARDS_NAME));
            String wardCode = cursor.getString(cursor.getColumnIndex(WardTable.COL_WARDS_CODE));

            wards = new Wards(wardId, wardNames, wardCode);
            wordNameList.add(wards);
        }
        cursor.close();
        closeDataBase();
        return wordNameList;
    }


    /*public ArrayList<String> getDivisionList(String id) {
        ArrayList<String> divisionNameList = new ArrayList<>();
        openDataBase();
        String sql = "SELECT * FROM "+DivisionWiseTable.TBL_DIVISION +" WHERE "+DivisionWiseTable.COL_COUNTRY_FOREIGN_KEY+" = '"+id+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        while (cursor.moveToNext())
        {
            String divisionNames = cursor.getString(cursor.getColumnIndex(DivisionWiseTable.COL_DIV_NAME));

            divisionNameList.add(divisionNames);
        }
        closeDataBase();
        return divisionNameList;
    }*/

    public ArrayList<String> getVillage(int id) {
        ArrayList<String> villageNameList = new ArrayList<>();
        openDataBase();
        String sql = "SELECT * FROM " + DivisionWiseTable.TBL_VILLAGE + " WHERE " + DivisionWiseTable.COL_WARDS_FOREIGN_KEY + " = '" + id + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String villageNames = cursor.getString(cursor.getColumnIndex(DivisionWiseTable.COL_VILLAGE_NAME));

            villageNameList.add(villageNames);
        }
        cursor.close();
        closeDataBase();
        return villageNameList;
    }


    public ArrayList<Community> getCommunityList(int id) {
        ArrayList<Community> communityList = new ArrayList<>();

        community = new Community(0, 0, 0, 0, 0, 0, 0, 0, "Community", "", "");
        communityList.add(community);

        openDataBase();
        String sql = "SELECT * FROM " + CommunityTable.TBL_COMMUNITY + " WHERE " + CommunityTable.COL_COMMUNITY_TBL__WARD_ID + " = '" + id + "' order by community_name";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            int communityId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL_ID));
            int projectId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL_PROJECT_ID));
            int divisionId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL_DIVISION_ID));
            int districtId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__DISTRICT_ID));
            int upazillaId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__UPAZILA_ID));
            int unionId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__UNION_ID));
            int wardId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__WARD_ID));
            int villageId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__VILLAGE_ID));
            String communityName = cursor.getString(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__COMMUNITY_NAME));
            String createdBy = cursor.getString(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__CREATED_BY));
            String modifiedBy = cursor.getString(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__MODIFIED_BY));

            community = new Community(communityId, projectId, divisionId, districtId,
                    upazillaId, unionId, wardId, villageId, communityName, createdBy, modifiedBy);
            communityList.add(community);
        }
        cursor.close();
        closeDataBase();
        return communityList;
    }


    public ArrayList<Community> getCommunityListByUnionId(int id) {
        ArrayList<Community> communityList = new ArrayList<>();

        community = new Community(0, 0, 0, 0, 0, 0, 0, 0, "Community", "", "");
        communityList.add(community);

        openDataBase();
        String sql = "SELECT * FROM " + CommunityTable.TBL_COMMUNITY + " WHERE " + CommunityTable.COL_COMMUNITY_TBL__UNION_ID + " = '" + id + "' order by community_name";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            int communityId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL_ID));
            int projectId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL_PROJECT_ID));
            int divisionId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL_DIVISION_ID));
            int districtId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__DISTRICT_ID));
            int upazillaId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__UPAZILA_ID));
            int unionId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__UNION_ID));
            int wardId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__WARD_ID));
            int villageId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__VILLAGE_ID));
            String communityName = cursor.getString(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__COMMUNITY_NAME));
            String createdBy = cursor.getString(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__CREATED_BY));
            String modifiedBy = cursor.getString(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__MODIFIED_BY));

            community = new Community(communityId, projectId, divisionId, districtId,
                    upazillaId, unionId, wardId, villageId, communityName, createdBy, modifiedBy);
            communityList.add(community);
        }
        cursor.close();
        closeDataBase();
        return communityList;
    }


    public ArrayList<Integer> getAssignVolunteer(int id) {

        ArrayList<Integer> assignVolunteerList = new ArrayList<>();
        int volunteerId = 0;
        openDataBase();
        String sql = "SELECT * FROM " + TableStructure.TABLE_NAME_VOLUNTEER + " WHERE " + TableStructure.COL_VOLUNTEER_USER_ID + " = '" + id + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            volunteerId = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_VOLUNTEER_TABLE_ID));
        }
        cursor.close();
        closeDataBase();

        if (volunteerId > 0) {
            openDataBase();
            String sql2 = "SELECT * FROM " + AssignVolunteerTable.TBL_ASSIGN_VOLUNTEER + " WHERE " + AssignVolunteerTable.COL_ASSIGN_VOLUNTEER_TBL_VOLUNTEER_ID + " = '" + volunteerId + "'";
            Cursor cursor2 = sqLiteDatabase.rawQuery(sql2, null);
            while (cursor2.moveToNext()) {
                int communityId = cursor2.getInt(cursor2.getColumnIndex(AssignVolunteerTable.COL_ASSIGN_VOLUNTEER_TBL_COMMUNITY_ID));
                assignVolunteerList.add(communityId);
            }
            cursor2.close();
            closeDataBase();
        }
        return assignVolunteerList;
    }


    private Community community;

    public Community getCommunity(int id) {

        openDataBase();
        String sql = "SELECT * FROM " + CommunityTable.TBL_COMMUNITY + " WHERE " + CommunityTable.COL_COMMUNITY_TBL_ID + " = '" + id + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            int communityId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL_ID));
            int projectId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL_PROJECT_ID));
            int divisionId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL_DIVISION_ID));
            int districtId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__DISTRICT_ID));
            int upazillaId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__UPAZILA_ID));
            int unionId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__UNION_ID));
            int wardId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__WARD_ID));
            int villageId = cursor.getInt(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__VILLAGE_ID));
            String communityName = cursor.getString(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__COMMUNITY_NAME));
            String createdBy = cursor.getString(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__CREATED_BY));
            String modifiedBy = cursor.getString(cursor.getColumnIndex(CommunityTable.COL_COMMUNITY_TBL__MODIFIED_BY));

            community = new Community(communityId, projectId, divisionId, districtId,
                    upazillaId, unionId, wardId, villageId, communityName, createdBy, modifiedBy);
        }
        cursor.close();
        closeDataBase();
        return community;
    }

    DistrictUsersModel districtUsersModel;

    public ArrayList<DistrictUsersModel> getDistrictUser(int userId) {

        ArrayList<DistrictUsersModel> districtUserList = new ArrayList<>();

        openDataBase();
        String sql = "SELECT * FROM " + DistrictUsersTable.TBL_DISTRICT_USERS + " WHERE " + DistrictUsersTable.COL_DISTRICT_USERS_TBL_USER_ID + " = '" + userId + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int tableId = cursor.getInt(cursor.getColumnIndex(DistrictUsersTable.COL_DISTRICT_USERS_TBL_ID));
            int divisionId = cursor.getInt(cursor.getColumnIndex(DistrictUsersTable.COL_DISTRICT_USERS_TBL_DIVISION_ID));
            int districtId = cursor.getInt(cursor.getColumnIndex(DistrictUsersTable.COL_DISTRICT_USERS_TBL_DISTRICT_ID));
            districtUsersModel = new DistrictUsersModel(tableId, userId, divisionId, districtId);
            districtUserList.add(districtUsersModel);
        }
        cursor.close();
        closeDataBase();
        return districtUserList;
    }


    String[] divisionName = {"Dhaka", "Chittagong"};
    String[] districtDB = {"Gazipur", "mymensingh"};
    String[] thanadh = {"fulpur", "trisal"};
    String[] unionFulpur = {"rambodropur", "sondora"};
    String[] villagesRambodrapur = {"viet kandi", "kharia para"};
    String[] villagesSondora = {"horinadi", "utpagla"};
    String[] unionTrisal = {"boilor", "boraduba"};
    String[] villgesboilor = {"ABCD", "EFGH"};
    String[] villagesBoraduba = {"IZKL", "MNOPQ"};

    String[] districtCH = {"Noakhali", "feni"};
    String[] thanaFni = {"sunagaji", "feni sodor"};
    String[] unionSunagaji = {"Arif", "Azhar"};
    String[] unionFenisodor = {"Daktarpar", "Ukilpara"};
    String[] villagesDaktarpar = {"niskungora", "domgat"};
    String[] villagesukilpara = {"mithasora", "santirhat"};


}
