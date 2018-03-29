package com.nanosoft.planInternational.tracking.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nanosoft.planInternational.tracking.utility.AgeCalculator;

import java.util.Calendar;

/**
 * Created by NgocTri on 11/7/2015.
 */
public class DatabaseAssetHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "plan_db.sqlite";
    public static final String DBLOCATION = "/data/data/com.tutorials.hp.recyclersqlite/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseAssetHelper(Context context) {
        super(context, DBNAME, null, 2);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
    //    mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
        //mDatabase = this.getWritableDatabase();
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }


  /*  public List<PlantSC> getListPlanSC() {
        PlantSC plantSC = null;
        List<PlantSC> planSCList = new ArrayList<>();
        openDatabase();
        //  Cursor cursor = mDatabase.rawQuery("SELECT * FROM PRODUCT", null);
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM ar where [SC Name Known By] is not null", null);
*//*
* 0	 1	    2	   3	 4	     5	        6	    7	     8	                 9	         10	              11	                           12	               13	           14	           15	          16	                   17	  18	         19	                  20	                 21            22
ES4 Code	PU	Level 4	Level 5	Community	Level 7	Level 8	Community Worker	SC Number	Family Member	SC Full Legal Name/First Name	SC Name Known By	Day of Birth	Month of Birth	Year of Birth	Relationship with SC	Gender	Occupation	Is Primary Carer?	Reason for Joining	Reason for leaving	Is the SC living in the same household as 12 months ago (at the time of the last interview)?

    public PlantSC(,,
    String reason_for_Joining, String is_Primary_Carer, String reason_for_leaving, String occupation,
    String gender, String relationship_with_SC, String year_of_Birth, String month_of_Birth,
    String day_of_Birth, String sc_NameKnownBy, String sc_Full_LegalName, String family_Member,
    String sc_Number, String communityWorker) {

* *//*
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            plantSC = new PlantSC(cursor.getInt(0), cursor.getString(21), cursor.getString(20), cursor.getString(19),
                    cursor.getString(18), cursor.getString(17), cursor.getString(16), cursor.getString(15),
                    cursor.getString(14), cursor.getString(13), cursor.getString(12), cursor.getString(11),
                    cursor.getString(10), cursor.getString(9),String.valueOf(cursor.getInt(8)), cursor.getString(7));
            planSCList.add(plantSC);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return planSCList;
    }*/

  /*  public ArrayList<SponsoredChildInfo> getAllScList() {
        SponsoredChildInfo sponsoredChildInfo = null;
        ArrayList<SponsoredChildInfo> planSCList = new ArrayList<>();
        openDatabase();
        //  Cursor cursor = mDatabase.rawQuery("SELECT * FROM PRODUCT", null);
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM ar where [SC Name Known By] is not null", null);
*//*
* 0	 1	    2	   3	 4	     5	        6	    7	     8	                 9	         10	              11	                           12	               13	           14	           15	          16	                   17	  18	         19	                  20	                 21            22
ES4 Code	PU	Level 4	Level 5	Community	Level 7	Level 8	Community Worker	SC Number	Family Member	SC Full Legal Name/First Name	SC Name Known By	Day of Birth	Month of Birth	Year of Birth	Relationship with SC	Gender	Occupation	Is Primary Carer?	Reason for Joining	Reason for leaving	Is the SC living in the same household as 12 months ago (at the time of the last interview)?

    public PlantSC(,,
    String reason_for_Joining, String is_Primary_Carer, String reason_for_leaving, String occupation,
    String gender, String relationship_with_SC, String year_of_Birth, String month_of_Birth,
    String day_of_Birth, String sc_NameKnownBy, String sc_Full_LegalName, String family_Member,
    String sc_Number, String communityWorker) {

* *//*
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {


           *//* plantSC = new PlantSC(cursor.getInt(0), cursor.getString(21), cursor.getString(20), cursor.getString(19),
                    cursor.getString(18), cursor.getString(17), cursor.getString(16), cursor.getString(15),
                    cursor.getString(14), cursor.getString(13), cursor.getString(12), cursor.getString(11),
                    cursor.getString(10), cursor.getString(9),String.valueOf(cursor.getInt(8)), cursor.getString(7));*//*

            String day = cursor.getString(12);
            String month = cursor.getString(13);
            String year = cursor.getString(14);

            String age = getAge(day,month,year);

            sponsoredChildInfo = new SponsoredChildInfo(cursor.getInt(0),"", cursor.getString(10),String.valueOf(cursor.getInt(8)),age,cursor.getString(22));

            planSCList.add(sponsoredChildInfo);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return planSCList;
    }*/

    public String getAge(String day_of_Birth, String month_of_Birth,String year_of_Birth) {
        Calendar now = Calendar.getInstance();
        int td = now.get(Calendar.DAY_OF_MONTH);
        int tm = now.get(Calendar.MONTH) + 1;
        int ty = now.get(Calendar.YEAR);

        int fd = Integer.parseInt(day_of_Birth);
        int fm = Integer.parseInt(month_of_Birth);
        int fy = Integer.parseInt(year_of_Birth);
        return new AgeCalculator(fd, fm, fy, td, tm, ty).getAgeString();
    }

}
