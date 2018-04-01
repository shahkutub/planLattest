package com.nanosoft.planInternational.tracking.utility;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nanosoft.planInternational.tracking.database.model.ScInfoModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppConstant {

    public static String lat ="lat";
    public static String lng ="lng";
    public static String BaseUrl ="BaseUrl";

    public static ArrayList<ScInfoModel> sponsoredChildInfoArrayList = new ArrayList<>();



    public static void saveSharedPreferencesLogList(Context context, ArrayList<ScInfoModel> callLog) {
        SharedPreferences mPrefs = context.getSharedPreferences("myJson", context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(callLog);
        prefsEditor.putString("myJson", json);
        prefsEditor.commit();
    }


    public static void saveSharedPreferencesRelationList(Context context, List<ScRelation> callLog) {
        SharedPreferences mPrefs = context.getSharedPreferences("myRelation", context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(callLog);
        prefsEditor.putString("myRelation", json);
        prefsEditor.commit();
    }


    public static void saveSharedPreferencesOccupation(Context context, List<ScRelation> callLog) {
        SharedPreferences mPrefs = context.getSharedPreferences("myOccupation", context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(callLog);
        prefsEditor.putString("myRelation", json);
        prefsEditor.commit();
    }


    public static ArrayList<ScRelation> loadSharedPreferencesOccupation(Context context) {
        ArrayList<ScRelation> callLog = new ArrayList<ScRelation>();
        SharedPreferences mPrefs = context.getSharedPreferences("myOccupation", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("myOccupation", "");
        if (json.isEmpty()) {
            callLog = new ArrayList<ScRelation>();
        } else {
            Type type = new TypeToken<ArrayList<ScRelation>>() {
            }.getType();
            callLog = gson.fromJson(json, type);
        }
        return callLog;
    }

    public static ArrayList<ScRelation> loadSharedPreferencesRelationList(Context context) {
        ArrayList<ScRelation> callLog = new ArrayList<ScRelation>();
        SharedPreferences mPrefs = context.getSharedPreferences("myRelation", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("myRelation", "");
        if (json.isEmpty()) {
            callLog = new ArrayList<ScRelation>();
        } else {
            Type type = new TypeToken<ArrayList<ScRelation>>() {
            }.getType();
            callLog = gson.fromJson(json, type);
        }
        return callLog;
    }


    public static ArrayList<ScInfoModel> loadSharedPreferencesLogList(Context context) {
        ArrayList<ScInfoModel> callLog = new ArrayList<ScInfoModel>();
        SharedPreferences mPrefs = context.getSharedPreferences("myJson", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("myJson", "");
        if (json.isEmpty()) {
            callLog = new ArrayList<ScInfoModel>();
        } else {
            Type type = new TypeToken<ArrayList<ScInfoModel>>() {
            }.getType();
            callLog = gson.fromJson(json, type);
        }
        return callLog;
    }

}
