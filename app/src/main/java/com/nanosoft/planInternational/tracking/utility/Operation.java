package com.nanosoft.planInternational.tracking.utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.nanosoft.planInternational.tracking.view.activity.WelcomeActivity;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Nanosoft-Android on 4/10/2017.
 */

public class Operation {

    public static int currentImageViewId;
    public static String currentPhotoQuestionId;
    private static Context con;
    private static SharedPreferences sharedPreference;
    //last local
    //public static String BaseUrl = "http://192.168.0.115/planinternational";
    //public static String BaseUrl = "http://45.64.135.228/benefit_tracking";//with joining date
//  public static String BaseUrl = "http://203.190.0.102:3000/";
    //public static String BaseUrl = "http://planbgd.org/benefit_tracking";
//    public static String BaseUrl = "http://45.64.135.228/benefit_tracking";

    //last live
    public static String BaseUrl = "http://45.64.135.226:8081/benefit_tracking";

    // public  String BaseUrl = Operation.getString("URL","");
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;


    public Operation(Context context) {
        this.context = context;
    }

    public void saveString(String key, String value) {
        sharedPreferences = context.getSharedPreferences("LOGIN", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static int joiningDateDifference(Context context, String joiningDate) {

        int reminderMonths = 0;
        String str[] = joiningDate.split("-");
        //  String str[] = scInfoModel.getJoining_date().split("-");
        int year = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        int day = Integer.parseInt(str[2]);

        Calendar thatDay = Calendar.getInstance();
        thatDay.set(Calendar.DAY_OF_MONTH, day);
        thatDay.set(Calendar.MONTH, month); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, year);

        Calendar today = Calendar.getInstance();

        /*long diff = today.getTimeInMillis() - thatDay.getTimeInMillis(); //result in millis

        long days = diff / (24*60*60*1000);
        */


        AgeCalculator calculator = new AgeCalculator(day, month, year, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR));
        int[] age = calculator.getAge();
        long months = age[0] * 12 + age[1];

        if (months > 12) {
            reminderMonths = (int) (months % 12);
        } else {
            reminderMonths = (int) months;
        }
        return reminderMonths;
    }

    public void saveInteger(String key, int value) {
        sharedPreferences = context.getSharedPreferences("LOGIN", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public String getString(String key, String defaultvalue) {
        sharedPreferences = context.getSharedPreferences("LOGIN", MODE_PRIVATE);

        return sharedPreferences.getString(key, defaultvalue);
    }


    public int getInteger(String key, int defaultvalue) {
        sharedPreferences = context.getSharedPreferences("LOGIN", MODE_PRIVATE);

        return sharedPreferences.getInt(key, defaultvalue);
    }

    public boolean checkInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void LogOut() {
        saveString("email", "");
        saveString("password", "");
        context.startActivity(new Intent(context, WelcomeActivity.class));
    }
}
