package com.nanosoft.planInternational.tracking.utility;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nanosoft.planInternational.tracking.database.model.ScInfoModel;

import java.util.ArrayList;

public class AppConstant {

    public static String lat ="lat";
    public static String lng ="lng";
    public static String BaseUrl ="BaseUrl";

    public static ArrayList<ScInfoModel> sponsoredChildInfoArrayList = new ArrayList<>();



    public static boolean saveArray(ArrayList<ScInfoModel> sponsoredChildInfoArrayList,Context context)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor mEdit1 = sp.edit();
    /* sKey is an array */
        mEdit1.putInt("Status_size", sponsoredChildInfoArrayList.size());

        for(int i=0;i<sponsoredChildInfoArrayList.size();i++)
        {
            mEdit1.remove("Status_" + i);
            mEdit1.putString("Status_" + i, String.valueOf(sponsoredChildInfoArrayList.get(i)));
        }

        return mEdit1.commit();
    }



    public static void loadArray(Context mContext)
    {
        SharedPreferences mSharedPreference1 =   PreferenceManager.getDefaultSharedPreferences(mContext);
        //sKey.clear();
        int size = mSharedPreference1.getInt("Status_size", 0);

        for(int i=0;i<size;i++)
        {
            //sKey.add(mSharedPreference1.getString("Status_" + i, null));
        }

    }
}
