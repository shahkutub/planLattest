package com.nanosoft.planInternational.tracking.database.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.Toast;

import com.nanosoft.planInternational.tracking.database.helper.DatabaseHelper;
import com.nanosoft.planInternational.tracking.database.model.Project;
import com.nanosoft.planInternational.tracking.database.model.SCFamilyInfos;
import com.nanosoft.planInternational.tracking.database.model.ScGeneralQuestionAnswer;
import com.nanosoft.planInternational.tracking.database.model.ScInfoModel;
import com.nanosoft.planInternational.tracking.database.model.UsersModel;
import com.nanosoft.planInternational.tracking.database.tables.AgeRangeTable;
import com.nanosoft.planInternational.tracking.database.tables.AnswerTable;
import com.nanosoft.planInternational.tracking.database.tables.AssignVolunteerTable;
import com.nanosoft.planInternational.tracking.database.tables.CommunityTable;
import com.nanosoft.planInternational.tracking.database.tables.CountriesTable;
import com.nanosoft.planInternational.tracking.database.tables.DistrictUsersTable;
import com.nanosoft.planInternational.tracking.database.tables.DivisionWiseTable;
import com.nanosoft.planInternational.tracking.database.tables.FamilyQuestionAnswersTable;
import com.nanosoft.planInternational.tracking.database.tables.FamilyQuestionTitlesTable;
import com.nanosoft.planInternational.tracking.database.tables.GeoLocationByProjectTable;
import com.nanosoft.planInternational.tracking.database.tables.MenuPermissionTable;
import com.nanosoft.planInternational.tracking.database.tables.MenusTable;
import com.nanosoft.planInternational.tracking.database.tables.ModulesTable;
import com.nanosoft.planInternational.tracking.database.tables.OutputTable;
import com.nanosoft.planInternational.tracking.database.tables.ProjectTable;
import com.nanosoft.planInternational.tracking.database.tables.RoleTable;
import com.nanosoft.planInternational.tracking.database.tables.SCFamilyInfosTable;
import com.nanosoft.planInternational.tracking.database.tables.SCFamilyMemberTable;
import com.nanosoft.planInternational.tracking.database.tables.SCFamilyMemberTypeTable;
import com.nanosoft.planInternational.tracking.database.tables.ScQuestionAnswersTable;
import com.nanosoft.planInternational.tracking.database.tables.ScStaticQuestionTable;
import com.nanosoft.planInternational.tracking.database.tables.TableStructure;
import com.nanosoft.planInternational.tracking.database.tables.WardTable;
import com.nanosoft.planInternational.tracking.utility.AgeCalculator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.nanosoft.planInternational.tracking.database.tables.SCFamilyInfosTable.TBL_SC_FAMILY_INFOS;
import static com.nanosoft.planInternational.tracking.database.tables.TableStructure.COL_SC_INFO_LATITUDE;
import static com.nanosoft.planInternational.tracking.jsinterface.DataBroker.scNumber;
import static java.lang.Integer.parseInt;

/**
 * Created by Nanosoft-Android on 4/19/2017.
 */

public class DatabaseManager {

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;

    private Context context;


    public DatabaseManager(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }


    private void openDataBase() {
        sqLiteDatabase = databaseHelper.getWritableDatabase();

    }

    private void closeDataBase() {
        databaseHelper.close();
    }

    public void getAllTable() {
        openDataBase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM planInternational.db WHERE type='table'", null);
        while (cursor.moveToNext()) {
            String table = cursor.getString(0);
            Toast.makeText(context, "vjbi" + table, Toast.LENGTH_SHORT).show();
        }
        closeDataBase();
    }
    private UsersModel usersModel;

    public UsersModel getUser(String userEmail) {

        openDataBase();

        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='users'";

        Cursor c = sqLiteDatabase.rawQuery(sql, null);
        boolean exists = false;
        while (c.moveToNext()) {
            exists = true;
            break;
        }
        c.close();
        if (!exists) return null;


        String query = "select * from " + TableStructure.TABLE_NAME_USERS
                + " where  " + TableStructure.COL_USERS_EMAIL + "='" + userEmail + "' ";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int userTableId = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_USERS_TABLE_ID));
                int userRoleId = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_USERS_ROLE_ID));
                String userName = cursor.getString(cursor.getColumnIndex(TableStructure.COL_USERS_NAME));
                String userPassword = cursor.getString(cursor.getColumnIndex(TableStructure.COL_USERS_PASSWORD));
                int userStatus = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_USERS_STATUS));
                String userPhoto = cursor.getString(cursor.getColumnIndex(TableStructure.COL_USERS_PHOTO));
                String remember_token = cursor.getString(cursor.getColumnIndex(TableStructure.COL_USERS_REMEMBER_TOKEN));
                String crated_by = cursor.getString(cursor.getColumnIndex(TableStructure.COL_USERS_CREATE_BY));
                String modified_by = cursor.getString(cursor.getColumnIndex(TableStructure.COL_USERS_MODIFIED_BY));
                String user_type = cursor.getString(cursor.getColumnIndex(TableStructure.COL_USERS_USER_TYPE));

                usersModel = new UsersModel(userTableId, userRoleId, userName, userEmail, userPassword,
                        userStatus, userPhoto, remember_token, crated_by, modified_by, user_type);
            }
        }
        cursor.close();
        closeDataBase();

        return usersModel;
    }


    public ArrayList<String> getAllUserEmail() {

        ArrayList<String> userEmailList = new ArrayList<>();

        openDataBase();

        Cursor cursor = sqLiteDatabase.query(TableStructure.TABLE_NAME_USERS, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String userEmail = cursor.getString(cursor.getColumnIndex(TableStructure.COL_USERS_EMAIL));
            userEmailList.add(userEmail);
        }
        cursor.close();
        closeDataBase();

        return userEmailList;
    }

    public boolean checkPassword(String userEmail, String password) {

        openDataBase();

        String pwd = "";

        String query = "select * from " + TableStructure.TABLE_NAME_USERS
                + " where  " + TableStructure.COL_USERS_EMAIL + "='" + userEmail + "' ";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                pwd = cursor.getString(cursor.getColumnIndex(TableStructure.COL_USERS_PASSWORD));
            }
        }

        //  boolean isMatched = BCrypt.checkpw(password, pwd);
//        boolean isMatched = BCrypt.checkpw(password, pwd);
//
//        return isMatched;
        return true;
    }


    private ScInfoModel scInfoModel;


    public ArrayList<ScInfoModel> getAllSCList() {

        ArrayList<ScInfoModel> scInfoModels = new ArrayList<>();

        openDataBase();
        Cursor cursor = sqLiteDatabase.query(TableStructure.TABLE_NAME_SC_INFO, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_TABLE_ID));
            String name = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_FULL_NAME));
            int scId = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_NUMBER));

            String photo = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_PHOTO_LINK));
            String date = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_DATE_OF_BIRTH));

            String[] dateArray = date.split("-");
            String age = getAge(dateArray[2], dateArray[1], dateArray[0]);

            String priorityFlag = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_PRIORITY_FLAG));
            String dateFlag = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_DATE_FLAG));
            scInfoModel = new ScInfoModel(id, scId, name, age, photo, priorityFlag, dateFlag);
            scInfoModels.add(scInfoModel);

        }


        closeDataBase();

        return scInfoModels;

    }


    public ArrayList<ScInfoModel> getSCListByCommunityId(int communityId) {

        ArrayList<ScInfoModel> scInfoModels = new ArrayList<>();

        openDataBase();

        String sql = "SELECT * FROM " + TableStructure.TABLE_NAME_SC_INFO + " WHERE " + TableStructure.COL_SC_INFO_COMMUNITY_ID + " = '" + communityId + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        //  Cursor cursor = sqLiteDatabase.query(TableStructure.TABLE_NAME_SC_INFO, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_TABLE_ID));
            String name = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_FULL_NAME));
            int scId = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_NUMBER));

            String photo = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_PHOTO_LINK));
            String date = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_DATE_OF_BIRTH));
            String joining_date = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_JOINING_DATE));


            String[] dateArray = date.split("-");
            String age = getAge(dateArray[2], dateArray[1], dateArray[0]);

            String priorityFlag = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_PRIORITY_FLAG));
            String dateFlag = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_DATE_FLAG));


            scInfoModel = new ScInfoModel(id, scId, name, age, photo, priorityFlag, dateFlag,joining_date);
            scInfoModels.add(scInfoModel);

        }


        cursor.close();
        closeDataBase();

        return scInfoModels;

    }


    public ArrayList<ScInfoModel> getPrioritySCList(int communityId, String priorityKey) {

        ArrayList<ScInfoModel> scInfoModels = new ArrayList<>();

        openDataBase();

        String sql = "SELECT * FROM " + TableStructure.TABLE_NAME_SC_INFO + " WHERE " + TableStructure.COL_SC_INFO_SC_PRIORITY_FLAG + " = '" + priorityKey + "' AND "
                + TableStructure.COL_SC_INFO_COMMUNITY_ID + " = '" + communityId + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        //  Cursor cursor = sqLiteDatabase.query(TableStructure.TABLE_NAME_SC_INFO, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_TABLE_ID));
            String name = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_FULL_NAME));
            int scId = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_NUMBER));

            String photo = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_PHOTO_LINK));
            String date = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_DATE_OF_BIRTH));
            String joining_date = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_JOINING_DATE));

            String[] dateArray = date.split("-");
            String age = getAge(dateArray[2], dateArray[1], dateArray[0]);

            String priorityFlag = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_PRIORITY_FLAG));
            String dateFlag = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_DATE_FLAG));
            scInfoModel = new ScInfoModel(id, scId, name, age, photo, priorityFlag, dateFlag,joining_date);
            scInfoModels.add(scInfoModel);

        }


        closeDataBase();

        return scInfoModels;

    }


    public ArrayList<ScInfoModel> getPrioritySCListByDate(String date) {

        ArrayList<ScInfoModel> scInfoModels = new ArrayList<>();

        openDataBase();

        String sql = "SELECT * FROM " + TableStructure.TABLE_NAME_SC_INFO + " WHERE " + TableStructure.COL_SC_INFO_SC_DATE_FLAG + " = '" + date + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        //  Cursor cursor = sqLiteDatabase.query(TableStructure.TABLE_NAME_SC_INFO, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_TABLE_ID));
            String name = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_FULL_NAME));
            int scId = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_NUMBER));

            String photo = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_PHOTO_LINK));
            String dateOfBirth = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_DATE_OF_BIRTH));

            String[] dateArray = dateOfBirth.split("-");
            String age = getAge(dateArray[2], dateArray[1], dateArray[0]);

            String priorityFlag = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_PRIORITY_FLAG));
            String dateFlag = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_DATE_FLAG));
            scInfoModel = new ScInfoModel(id, scId, name, age, photo, priorityFlag, dateFlag);
            scInfoModels.add(scInfoModel);

        }


        closeDataBase();

        return scInfoModels;

    }


    public ArrayList<String> getDateList(String priorityKey) {

        ArrayList<String> scInfoModels = new ArrayList<>();

        openDataBase();

        String sql = "SELECT * FROM " + TableStructure.TABLE_NAME_SC_INFO + " WHERE " + TableStructure.COL_SC_INFO_SC_PRIORITY_FLAG + " = '" + priorityKey + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);


        while (cursor.moveToNext()) {
            String dateFlag = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_DATE_FLAG));
            scInfoModels.add(dateFlag);

            HashSet<String> hashSet = new HashSet<String>();
            hashSet.addAll(scInfoModels);
            scInfoModels.clear();
            scInfoModels.addAll(hashSet);
        }
        cursor.close();

        closeDataBase();

        return scInfoModels;

    }

    public ScInfoModel getScProfile(int id) {

        ScInfoModel scInfoModel = null;
        openDataBase();
        String sql = "SELECT\n" +
                "  sc_infos.*,\n" +
                "  tbl_division.division_name,\n" +
                "  tbl_district.district_name,\n" +
                "  tbl_upazila.upazila_name,\n" +
                "  tbl_union.union_name,\n" +
                "  wards.name ward_name,\n" +
                "  tbl_community.community_name\n" +
                "FROM\n" +
                "  `sc_infos`\n" +
                "left outer JOIN\n" +
                "  `tbl_division` ON tbl_division.id = sc_infos.division_id\n" +
                "left outer JOIN\n" +
                "  tbl_district ON tbl_district.id = sc_infos.district_id\n" +
                "left outer JOIN\n" +
                "  tbl_upazila ON tbl_upazila.id = sc_infos.upazila_id\n" +
                "left outer JOIN\n" +
                "  tbl_union ON tbl_union.id = sc_infos.union_id\n" +
                "left outer JOIN\n" +
                "  wards ON wards.id = sc_infos.ward_id\n" +
                "left outer JOIN\n" +
                "  tbl_community ON tbl_community.id = sc_infos.community_id\n" +
                "WHERE\n" +
                "  sc_infos.id = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{String.valueOf(id)});
        while (cursor.moveToNext()) {
            //  String districtNames = cursor.getString(cursor.getColumnIndex(DivisionWiseTable.COL_DIST_NAME));
            int scInfoProjectId = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_PROJECT_ID));
            int scInfoProgramUnit = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_PROJECT_UNIT));
            int scInfoDistrictId = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_DISTRICT_ID));
            int scInfoUpazillaId = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_UPAZILA_ID));
            int scInfoCommunityId = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_COMMUNITY_ID));
            int scInfoCommunityWorkerId = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_COMMUNITY_WORKER_ID));
            int scInfoScNumber = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_NUMBER));
            String scInfoScFullName = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_FULL_NAME));
            String scInfoScNameKnownBy = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_NAME_KNOWN_BY));
            String scInfoScDateOfBirth = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_DATE_OF_BIRTH));

            String scInfoScGender = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_GENDER));
            String scInfoCommunityName = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_COMMUNITY_NAME));
            String joining_date = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_JOINING_DATE));
            String scInfoScReasonForJoining = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_REASON_FOR_JOINING));
            String scInfoScReasonForLeaving = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_REASON_FOR_LEAVING));
            String scInfoScLivingInSameHouse = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_LIVING_IN_SAME_HOUSE));
            String latitude = cursor.getString(cursor.getColumnIndex(COL_SC_INFO_LATITUDE));
            String longitude = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_LONGITUDE));
            String photo = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_PHOTO_LINK));

            String priorityFlag = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_PRIORITY_FLAG));
            String dateFlag = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_DATE_FLAG));
            String extraFlag = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SC_INFO_SC_EXTRA_FLAG));

            String[] dateArray = scInfoScDateOfBirth.split("-");
            String age = getAge(dateArray[2], dateArray[1], dateArray[0]);
            scInfoModel = new ScInfoModel(scInfoProjectId, scInfoProgramUnit, scInfoDistrictId, scInfoUpazillaId,
                    scInfoCommunityId, scInfoCommunityWorkerId, scInfoScNumber, scInfoScFullName, scInfoScNameKnownBy,
                    age, scInfoScGender,scInfoCommunityName,joining_date, scInfoScReasonForJoining, scInfoScReasonForLeaving,
                    scInfoScLivingInSameHouse, latitude, longitude, photo, priorityFlag, dateFlag, extraFlag,
                    parseInt(dateArray[2]), parseInt(dateArray[1]), parseInt(dateArray[0]));
            scInfoModel.setScInfoLocationAddress(cursor.getString(cursor.getColumnIndex("division_name")) + ", " +
                    cursor.getString(cursor.getColumnIndex("district_name")) + ", " +
                    cursor.getString(cursor.getColumnIndex("upazila_name")) + ", " +
                    cursor.getString(cursor.getColumnIndex("union_name")) + ", " +
                    cursor.getString(cursor.getColumnIndex("ward_name")) + ", " +
                    cursor.getString(cursor.getColumnIndex("community_name")));
            scInfoModel.setScInfoLanguage(cursor.getString(cursor.getColumnIndex("language")));
            scInfoModel.setScInfoReligion(cursor.getString(cursor.getColumnIndex("religion")));
            //scInfoModel = new ScInfoModel(scInfoProjectId,scInfoProgramUnit,scInfoDistrictId,scInfoUpazillaId,scInfoCommunityId,scInfoCommunityWorkerId,scInfoScNumber,scInfoScFullName,scInfoScNameKnownBy,scInfoScDateOfBirth,scInfoScGender,scInfoScReasonForJoining,scInfoScReasonForLeaving,scInfoScLivingInSameHouse,latitude,longitude,photo,priorityFlag,dateFlag,extraFlag)
        }
        closeDataBase();
        return scInfoModel;
    }


    public ArrayList<SCFamilyInfos> getFamilyInfo(int scId) {

        ArrayList<SCFamilyInfos> scFamilyInfosList = new ArrayList<>();
        SCFamilyInfos scFamilyInfos;
        openDataBase();
        String sql = "SELECT * FROM " + TBL_SC_FAMILY_INFOS + " WHERE " + SCFamilyInfosTable.COL_SC_FAMILY_INFOS_SC_ID + " = '" + scId + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            int id = cursor.getInt(cursor.getColumnIndex(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_TBL_ID));
            String member_full_name = cursor.getString(cursor.getColumnIndex(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MEMBER_FULL_NAME));
            String member_name_known_by = cursor.getString(cursor.getColumnIndex(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MEMBER_NAME_KNOWN_BY));
            String member_relationship = cursor.getString(cursor.getColumnIndex(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_RELATIONSHIP));
            String member_occupation = cursor.getString(cursor.getColumnIndex(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_OCCUPATION));
            String member_gender = cursor.getString(cursor.getColumnIndex(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_GENDER));
            int member_birth_year = cursor.getInt(cursor.getColumnIndex(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_BIRTH_YEAR));
            String member_is_primary_carer = cursor.getString(cursor.getColumnIndex(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_IS_PRIMARY_CAREER));

             String who_left_household = cursor.getString(cursor.getColumnIndex(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_WHO_LEFT));

             String reson_for_left = cursor.getString(cursor.getColumnIndex(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_REASON_FOR_LEAVE));

             String reson_for_join = cursor.getString(cursor.getColumnIndex(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_REASON_FOR_JOIN));



            scFamilyInfos = new SCFamilyInfos(id, scId, member_full_name, member_name_known_by, member_relationship,
                    member_occupation, member_gender, member_birth_year, member_is_primary_carer,who_left_household,reson_for_left,reson_for_join);

            scFamilyInfosList.add(scFamilyInfos);
        }
        cursor.close();
        closeDataBase();
        return scFamilyInfosList;
    }

    public ArrayList<ScGeneralQuestionAnswer> getScGeneralQuestionAnswerList(int scnumber) {

        ArrayList<ScGeneralQuestionAnswer> scGeneralQuestionList = new ArrayList<>();
        ScGeneralQuestionAnswer scGeneralQuestionAnswer;

        openDataBase();
        String sql = "SELECT\n" +
                "  table_sc_static_question_answers.sc_id,\n" +
                "  table_sc_static_questions.question_serial_no,\n" +
                "  table_sc_static_questions.question_name,\n" +
                "  table_sc_static_question_answers.answer,\n" +
                "  table_sc_static_question_answers.date,\n" +
                "  table_sc_static_questions.id,\n" +
                "  table_sc_static_question_answers.id\n" +
                "FROM\n" +
                "  table_sc_static_question_answers\n" +
                "INNER JOIN\n" +
                "  table_sc_static_questions ON table_sc_static_question_answers.static_question_id = table_sc_static_questions.question_serial_no\n" +
                "WHERE\n" +
                "  table_sc_static_question_answers.sc_id = '" + scnumber + "' AND table_sc_static_question_answers.answer != ''";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            scGeneralQuestionAnswer = new ScGeneralQuestionAnswer(scnumber, cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6));
            scGeneralQuestionList.add(scGeneralQuestionAnswer);
        }
        cursor.close();
        closeDataBase();
        return scGeneralQuestionList;
    }


    public ArrayList<Project> getProjectList(ScInfoModel scInfoModel) {
        ArrayList<Project> scProjectList = new ArrayList<>();
        Project project;

        //get age range id
        int ageRngId = getAgeRangeId(scInfoModel);

        openDataBase();
        //TEST
//        Cursor cursor = sqLiteDatabase.rawQuery("select tbl_project.id, tbl_project.category_id, tbl_project.project_title from tbl_project",null);



        //get projects
        Cursor cursor = sqLiteDatabase.rawQuery("select tbl_project.id, tbl_project.category_id, tbl_project.project_title from tbl_project" +
                        " join tbl_activity on tbl_activity.project_id = tbl_project.id " +
                        " join tbl_geo_location_by_project on tbl_geo_location_by_project.project_id=tbl_project.id " +
                        " where tbl_geo_location_by_project.community_id=? and tbl_activity.age_range_id=?",
                new String[]{String.valueOf(scInfoModel.getScInfoCommunityId()), String.valueOf(ageRngId)});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int category_id = cursor.getInt(1);
            String project_title = cursor.getString(2);
            project = new Project(id, category_id, project_title);
            scProjectList.add(project);
        }
/*
        String sql = "SELECT * FROM " + ProjectTable.TBL_PROJECT + " WHERE " + ProjectTable.COL_PROJECT_TBL_ID + " = '" + 0 + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = 0;
            int category_id = cursor.getInt(cursor.getColumnIndex(ProjectTable.COL_PROJECT_CATEGORY_ID));
            String project_title = cursor.getString(cursor.getColumnIndex(ProjectTable.COL_PROJECT_TBL_PROJECT_TITLE));

            project = new Project(id, category_id, project_title);

            scProjectList.add(project);
        }
        cursor.close();
        */

        cursor.close();
        closeDataBase();
        return scProjectList;
    }


    public int getSurveyEntry(int categoryId) {
        int id = 0;
        openDataBase();
        String sql = "SELECT id FROM " + TableStructure.TABLE_NAME_SURVEY_ENTRY + " WHERE survey_category_id = '" + categoryId + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            id = cursor.getInt(0);
            break;
        }
        cursor.close();
        closeDataBase();

        return id;
    }


    public boolean priorityUpdate(int id, String priorityFlag, String date) {

        openDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("priority_flag", priorityFlag);
        contentValues.put("date_flag", date);

        int update = sqLiteDatabase.update(TableStructure.TABLE_NAME_SC_INFO, contentValues, TableStructure.COL_SC_INFO_TABLE_ID + " = " + id, null);


        if (update > 0) {
            return true;
        }

        return false;
    }



    /* public void updateLocalDatabase(String name, int syncStatus,SQLiteDatabase sqLiteDatabase,int id){
        DBHelper dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContact.SYNC_STATUS,syncStatus);
       // String selection = DbContact.NAME+" LIKE ?";
        String selection = DbContact.ID+"="+id;
        //String[] selection_arg = {name};
      //  sqLiteDatabase.update(DbContact.TABLE_NAME,contentValues,selection,selection_arg);
        sqLiteDatabase.update(DbContact.TABLE_NAME,contentValues,selection,null);
        dbHelper.close();
    }*/


    public String getAge(String day_of_Birth, String month_of_Birth, String year_of_Birth) {
        Calendar now = Calendar.getInstance();
        int td = now.get(Calendar.DAY_OF_MONTH);
        int tm = now.get(Calendar.MONTH) + 1;
        int ty = now.get(Calendar.YEAR);

        int fd = parseInt(day_of_Birth);
        int fm = parseInt(month_of_Birth);
        int fy = parseInt(year_of_Birth);
        int[] age = new AgeCalculator(fd, fm, fy, td, tm, ty).getAge();
        return String.format(Locale.ENGLISH, "%s day(s), %s month(s), %s year(s)", age[2], age[1], age[0]);
    }


    public int getScIdByTableId(int scInfoTableId) {
        int id = 0;
        openDataBase();
        Cursor c = sqLiteDatabase.rawQuery("select sc_number from sc_infos where id = ?", new String[]{scInfoTableId + ""});
        while (c.moveToNext()) {
            id = c.getInt(0);
        }
        c.close();
        closeDataBase();
        return id;
    }

    public void clearFamilyInfo(int scId) {
        openDataBase();
        sqLiteDatabase.delete("sc_family_infos", "sc_id=?", new String[]{scId + ""});
        closeDataBase();
    }

    public void saveFamilyInfo(String fullName, String nameKnown, String birth, String gender,
                               String relation, String career, String occu, String wholeft,String reasonLeft, String reasonJoin, int scId) {
        openDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_SC_ID, scId);
        contentValues.put(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MEMBER_FULL_NAME, fullName);
        contentValues.put(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MEMBER_NAME_KNOWN_BY, nameKnown);
        contentValues.put(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_RELATIONSHIP, relation);
        contentValues.put(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_OCCUPATION, occu);
        contentValues.put(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_GENDER, gender);
        contentValues.put(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_BIRTH_YEAR, birth);
        contentValues.put(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_IS_PRIMARY_CAREER, career);
        contentValues.put(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_WHO_LEFT, wholeft);
        contentValues.put(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_REASON_FOR_LEAVE, reasonLeft);
        contentValues.put(SCFamilyInfosTable.COL_SC_FAMILY_INFOS_MWMBER_REASON_FOR_JOIN, reasonJoin);
        sqLiteDatabase.insert("sc_family_infos", null, contentValues);
        closeDataBase();
    }

    public int getAgeRangeId(ScInfoModel mdl) {
        openDataBase();
        //get age range id
        int ageRngId = 0;
        String ageRngSql = "select id,type,start,end from tbl_age_range where start is not 'null'";
        Cursor ageRngCursor = sqLiteDatabase.rawQuery(ageRngSql, null);
        while (ageRngCursor.moveToNext()) {
            String type = ageRngCursor.getString(1);
            String startAge = ageRngCursor.getString(2);
            String endAge = ageRngCursor.getString(3);
            int fromAge = Integer.parseInt(startAge);
            int toAge = parseInt(endAge);
            if (type.equals("year")) {
                fromAge *= 12;
                toAge *= 12;
            }
            int ageMonths = mdl.getAgeMonths();
            if (ageMonths >= fromAge && ageMonths <= toAge) {
                ageRngId = ageRngCursor.getInt(0);
                break;
            }
        }
        ageRngCursor.close();
        closeDataBase();
        return ageRngId;
    }

    public ArrayList<SurveyEntry> getGeneralSurveyList() {
        ArrayList<SurveyEntry> list = new ArrayList<>();
        openDataBase();
        String sql = "select survey_entry.name,survey_entry.id from survey_entry " +
                " inner JOIN survey_category on survey_category.id=survey_entry.survey_category_id " +
                " WHERE survey_category.id=1";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            list.add(new SurveyEntry(cursor.getString(0), cursor.getInt(1), false));
        }
        cursor.close();
        closeDataBase();
        return list;
    }

    public ArrayList<SurveyEntry> getSurveyList(int categoryId) {
        ArrayList<SurveyEntry> list = new ArrayList<>();
        openDataBase();
        String sql = "select survey_entry.name,survey_entry.id from survey_entry inner JOIN survey_category on survey_category.id=survey_entry.survey_category_id WHERE survey_category.id=?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{String.valueOf(categoryId)});
        while (cursor.moveToNext()) {
            list.add(new SurveyEntry(cursor.getString(0), cursor.getInt(1), false));
        }
        cursor.close();
        closeDataBase();

        for (SurveyEntry surveyEntry : list) {
            openDataBase();
            Cursor c = sqLiteDatabase.rawQuery("select completed from sc_survey_complete where surveyid=? and scnumber=?", new String[]{String.valueOf(surveyEntry.ID), String.valueOf(scNumber)});
            surveyEntry.Completed = false;
            while (c.moveToNext()) {
                surveyEntry.Completed = c.getInt(0) == 1;
                break;
            }
            c.close();
            closeDataBase();
        }

        return list;
    }

    public Map<String, String> getStaticAnswers(int scId) {
        Map<String, String> map = new HashMap<>();
        openDataBase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT\n" +
                "  static_question_id,\n" +
                "  answer\n" +
                "FROM\n" +
                "  table_sc_static_question_answers\n" +
                "WHERE\n" +
                "  sc_id = '" + scId + "'", null);
        while (cursor.moveToNext()) {
            map.put(cursor.getString(0), cursor.getString(1));
        }
        cursor.close();
        closeDataBase();
        return map;
    }

    public void DeleteAllTables() {
        List<String> tables = new ArrayList<String>();
        openDataBase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM sqlite_master WHERE type='table';", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String tableName = cursor.getString(1);
            if (!tableName.equals("android_metadata") &&
                    !tableName.equals("sqlite_sequence"))
                tables.add(tableName);
            cursor.moveToNext();
        }
        cursor.close();

        for (String tableName : tables) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
        }
        closeDataBase();
        //createTables();
    }

    public void createTables() {
        openDataBase();
        Cursor eCursor = sqLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='sc_infos'", null);
        if (eCursor.getCount() > 0) {
            closeDataBase();
            eCursor.close();
            return;
        }
        eCursor.close();

        sqLiteDatabase.execSQL(DivisionWiseTable.CREATE_TBL_VILLAGE);
        sqLiteDatabase.execSQL(DivisionWiseTable.CREATE_TBL_UNION);
        sqLiteDatabase.execSQL(DivisionWiseTable.CREATE_TBL_UPAZILA);
        sqLiteDatabase.execSQL(DivisionWiseTable.CREATE_TBL_DISTRICT);
        sqLiteDatabase.execSQL(DivisionWiseTable.CREATE_TBL_DIVISION);
        sqLiteDatabase.execSQL(CountriesTable.CREATE_TBL_COUNTRIES);
        sqLiteDatabase.execSQL(AgeRangeTable.CREATE_TBL_AGE_RANGE);
        sqLiteDatabase.execSQL(AssignVolunteerTable.CREATE_TBL_ASSIGN_VOLUNTEER);
        sqLiteDatabase.execSQL(CommunityTable.CREATE_TBL_COMMUNITY);
        sqLiteDatabase.execSQL(GeoLocationByProjectTable.CREATE_TBL_GEOLOCATION_BY_PROJECT);
        sqLiteDatabase.execSQL(MenuPermissionTable.CREATE_TBL_MENU_PERMISSION);
        sqLiteDatabase.execSQL(MenusTable.CREATE_TBL_MENUS);
        sqLiteDatabase.execSQL(OutputTable.CREATE_TBL_OUTPUT);
        sqLiteDatabase.execSQL(ProjectTable.CREATE_TBL_PROJECT);
        sqLiteDatabase.execSQL(RoleTable.CREATE_TBL_ROLE);
        sqLiteDatabase.execSQL(ModulesTable.CREATE_TBL_MODULES);
        sqLiteDatabase.execSQL(SCFamilyInfosTable.CREATE_TBL_SC_FAMILY_INFOS);
        sqLiteDatabase.execSQL(SCFamilyMemberTable.CREATE_TBL_SC_FAMILY_MAMBER);
        sqLiteDatabase.execSQL(SCFamilyMemberTypeTable.CREATE_TBL_SC_FAMILY_MEMBER_TYPE);
        sqLiteDatabase.execSQL(WardTable.CREATE_TBL_WARDS);

        /*akbar*/
        sqLiteDatabase.execSQL(TableStructure.CREATE_TABLE_VOLUNTEER);
        sqLiteDatabase.execSQL(TableStructure.CREATE_TABLE_USERS);
        sqLiteDatabase.execSQL(TableStructure.CREATE_TABLE_TARGET_GROUP);
        sqLiteDatabase.execSQL(TableStructure.CREATE_TABLE_SURVEY_QUESTION_OPTION);
        sqLiteDatabase.execSQL(TableStructure.CREATE_TABLE_SURVEY_QUESTION);
        sqLiteDatabase.execSQL(TableStructure.CREATE_TABLE_SURVEY_ENTRY);
        sqLiteDatabase.execSQL(TableStructure.CREATE_TABLE_SURVEY_CATEGORY);
        sqLiteDatabase.execSQL(TableStructure.CREATE_TABLE_SC_INFO);

        sqLiteDatabase.execSQL(TableStructure.CREATE_TABLE_SURVEY_LOGIC);
        sqLiteDatabase.execSQL(TableStructure.CREATE_TABLE_SURVEY_QUESTION_CHOICE);


        sqLiteDatabase.execSQL(AnswerTable.CREATE_TABLE_ANSWER);
        sqLiteDatabase.execSQL(ScStaticQuestionTable.CREATE_TABLE_SC_STATIC_QUESTION);
        sqLiteDatabase.execSQL(ScQuestionAnswersTable.CREATE_TABLE_SC_STATIC_QUESTION_ANSWER);
        sqLiteDatabase.execSQL(FamilyQuestionTitlesTable.CREATE_TABLE_FAMILY_QUESTION_TITLES);
        sqLiteDatabase.execSQL(FamilyQuestionAnswersTable.CREATE_TABLE_FAMILY_QUESTION_ANSWER);


        sqLiteDatabase.execSQL(DistrictUsersTable.CREATE_TBL_DISTRICT_USERS);


        //create answerKeep
        sqLiteDatabase.execSQL(TableStructure.CreateTableAnsKeep);

        //create table Activity
        sqLiteDatabase.execSQL(TableStructure.CreateTableActivity);

        sqLiteDatabase.execSQL(TableStructure.CreateTableScSurveyComplete);
        sqLiteDatabase.execSQL(TableStructure.CreateTableAudioStatement);

        sqLiteDatabase.execSQL(TableStructure.CreateTableOccupations);
        sqLiteDatabase.execSQL(TableStructure.CreateTableRelationships);
        closeDataBase();
    }

    public void SaveScSurveyCompleted(int scNumber, int surveyEntryId) {
        openDataBase();
        sqLiteDatabase.execSQL("INSERT INTO [sc_survey_complete]\n" +
                "([scnumber]\n" +
                ",[surveyid]\n" +
                ",[completed])\n" +
                "\tVALUES\n" +
                "(?\n" +
                ",?\n" +
                ",1)", new Object[]{scNumber, surveyEntryId});
        closeDataBase();
    }

    public void saveAudio(String data, int scNumber) {
        openDataBase();
        sqLiteDatabase.execSQL("INSERT INTO [audioStatement]  ([audio]  ,[scnumber]  ,[surveydate]) VALUES  (?  ,?  ,datetime('now'))", new Object[]{data, scNumber});
        closeDataBase();
    }


    public void SaveLatLon(int scInfoNumber, String latlon) {
        if (!TextUtils.isEmpty(latlon) && latlon.contains("\n")) {
            String[] arr = latlon.split("\n");
            if (arr.length < 2) return;
            String lat = arr[0];
            String lon = arr[1];
            openDataBase();
            ContentValues values = new ContentValues();
            values.put(TableStructure.COL_SC_INFO_LATITUDE, lat);
            values.put(TableStructure.COL_SC_INFO_LONGITUDE, lon);
            sqLiteDatabase.update(TableStructure.TABLE_NAME_SC_INFO, values, TableStructure.COL_SC_INFO_SC_NUMBER + "=?", new String[]{scInfoNumber + ""});
            closeDataBase();
        }
    }

    public String[] getLatLonByScNumber(int scInfoNumber) {
        String[] latlon = new String[2];
        openDataBase();
        Cursor cursor = sqLiteDatabase.rawQuery("select latitude,longitude from " + TableStructure.TABLE_NAME_SC_INFO + " where sc_number=?", new String[]{scInfoNumber + ""});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            latlon[0] = cursor.getString(0);
            latlon[1] = cursor.getString(1);
        }
        cursor.close();
        closeDataBase();
        return latlon;
    }

    public String[] getOccupationList() {
        openDataBase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT name,code FROM occupations",null);
        String[] result=new String[cursor.getCount()+1];
        result[0]="Select Your Occupation";
        int c=1;
        while (cursor.moveToNext()){
            result[c]=cursor.getString(1)+" - "+ cursor.getString(0);
            c++;
        }
        cursor.close();
        closeDataBase();
        return  result;
    }

    public String[] getRelationShipList() {
        openDataBase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT name,code FROM relationships",null);
        String[] result=new String[cursor.getCount()+1];
        result[0]="Select Your Relationship";
        int c=1;
        while (cursor.moveToNext()){
            result[c]=cursor.getString(1)+" - "+ cursor.getString(0);
            c++;
        }
        cursor.close();
        closeDataBase();
        return  result;
    }
}
