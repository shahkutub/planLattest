package com.nanosoft.planInternational.tracking.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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


/**
 * Created by Nanosoft-Android on 4/8/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "planInternational.db";
    public static final int VERSION_NAME = 1;
    Context c;

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION_NAME);
        this.c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }


}
