package com.nanosoft.planInternational.tracking.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.database.helper.DatabaseHelper;
import com.nanosoft.planInternational.tracking.database.manager.DatabaseManager;
import com.nanosoft.planInternational.tracking.database.manager.DivisionManager;
import com.nanosoft.planInternational.tracking.database.manager.QuestioneryManager;
import com.nanosoft.planInternational.tracking.receiver.ConnectivityReceiver;
import com.nanosoft.planInternational.tracking.utility.Operation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WelcomeActivityDemo extends AppCompatActivity implements ConnectivityReceiver.ConnectivityRecieverListener {
    private EditText usernameET, passwordET;
    private LinearLayout myLinearLayout;
    private TextView gotoProfile;
    private boolean isConnected;
    private Operation operation;
    private String email;
    private String password;
    private DivisionManager divisionManager;
    public static DatabaseHelper databaseHelper;
    public static SQLiteDatabase sqLiteDatabase;
    ProgressDialog progress, progressDialog;
    String baseUrl;
    private final String fullDatabaseUrl = /*Operation.BaseUrl*/ baseUrl+ "/api/get-all-data";
    private final String loginUrl = /*Operation.BaseUrl*/baseUrl + "/api/user";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        operation = new Operation(this);
        baseUrl = operation.getString("URL", "http://45.64.135.228/benefit_tracking");
        String userEmail = operation.getString("email", "");
        String userPassword = operation.getString("password", "");
        if (userEmail.length() > 1 && userPassword.length() > 1) {
            Intent intent = new Intent(getApplicationContext(), FeatureActivity.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_welcome);
            showSplashscreen();

            initialize();

            gotoProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    email = usernameET.getText().toString();
                    password = passwordET.getText().toString();
                    if (email.length() < 1) {
                        Toast.makeText(WelcomeActivityDemo.this, "Username blank !!!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (password.length() < 1) {
                        Toast.makeText(WelcomeActivityDemo.this, "Password  blank !!!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // jsonObjectRequest();
                    progress = new ProgressDialog(WelcomeActivityDemo.this);
                    if (!progress.isShowing()) {
                        progressDialog.setTitle("Go Feature");
                        progress.setMessage("please,wait Data loading....");
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.setCancelable(false);
                        progress.show();
                    }
                    checkDatabase();
                }
            });
        }
        //if there is pending answers to be uploaded then upload them
        QuestioneryManager qmgr = new QuestioneryManager(this);
        qmgr.UploadAnswers();
    }

    private void initialize() {
        myLinearLayout = (LinearLayout) findViewById(R.id.my_welcome_layout);
        usernameET = (EditText) findViewById(R.id.usernameET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        gotoProfile = (TextView) findViewById(R.id.gotoProfile);
        divisionManager = new DivisionManager(this);
        databaseManager = new DatabaseManager(this);
        databaseHelper = new DatabaseHelper(this);

    }

    private void showSplashscreen() {
         /*USE SPLASH SCREEEN*/
        View v = (View) findViewById(R.id.imageView);
        //  Animation animation =new ScaleAnimation(1,5,1,5,.5f,.5f);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        //  Animation animation = new TranslateAnimation(0, -1 * width - 100f, 0, 0);
        Animation animation = new TranslateAnimation(0, -1 * width * 2 - 100f, 0, 0);
        animation.setStartOffset(500);
        animation.setInterpolator(new AccelerateInterpolator(200));
        animation.setDuration(5000);
        animation.setFillAfter(true);
        Animation animation1 = new AlphaAnimation(1f, 0f);
        animation1.setDuration(2000);
        animation1.setFillAfter(true);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(animation);
        animationSet.setDuration(2000);
        animationSet.setFillAfter(true);
        v.startAnimation(animationSet);
       /*USE SPLASH SCREEEN*/
    }

    private void jsonStringRequest() {
        ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPONSE",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String successValue = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            if (successValue.equals("1")) {
                                //jasonArrayRequest();
                                operation.saveString("email", email);
                                operation.saveString("password", password);
                                insertDatabase();
                            } else if (successValue.equals("0")) {
                                Toast.makeText(WelcomeActivityDemo.this, message, Toast.LENGTH_SHORT).show();
                                if (progress.isShowing()) {
                                    progress.dismiss();
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(WelcomeActivityDemo.this, "E1", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                finish();
                Toast.makeText(getApplicationContext(), "Slow net connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("email", email);
                parameters.put("password", password);
                return parameters;
            }
        };
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            queue.add(stringRequest);
        }
    }

    private void insertDatabase() {
        ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, fullDatabaseUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            new InsertDatabaseTask().execute(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Slow net connection", Toast.LENGTH_SHORT).show();
                //logout
                operation.LogOut();
                databaseManager.DeleteAllTables();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                // parameters.put("username", userPhone);
                //parameters.put("passwor", userPhone);
                return parameters;
            }
        };
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            queue.add(stringRequest);
        }
        //alertDialog
    }

    private void checkDatabase() {
        databaseManager.createTables();
        if (databaseManager.getAllSCList().size() == 0) {
            if (checkConnectivity()) {
                jsonStringRequest();
            } else {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                //showSnackBar();
                if (progress.isShowing()) {
                    progress.dismiss();
                }
            }
        } else {
            checkUserIdPassword();
        }
    }

    DatabaseManager databaseManager;
    ArrayList<String> userEmailList;

    private void checkUserIdPassword() {
        boolean checkUserEmail = false;
        userEmailList = new ArrayList<>();
        userEmailList = databaseManager.getAllUserEmail();
        for (int i = 0; i < userEmailList.size(); i++) {
            if (email.equals(userEmailList.get(i))) {
                checkUserEmail = true;
                if (databaseManager.checkPassword(email, password)) {
                    if (progress.isShowing()) {
                        progress.dismiss();
                    }
                    operation.saveString("email", email);
                    operation.saveString("password", password);
                    Intent intent = new Intent(getApplicationContext(), FeatureActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (progress.isShowing()) {
                        progress.dismiss();
                    }
                    Toast.makeText(this, "Password Not Match", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            if (i == userEmailList.size() - 1) {
                if (!checkUserEmail) {
                    Toast.makeText(this, "Wrong Email Address", Toast.LENGTH_SHORT).show();
                }
                if (progress.isShowing()) {
                    progress.dismiss();
                }
            }
        }
    }

    class InsertDatabaseTask extends AsyncTask<String, String, Void> {
        @Override
        protected void onPreExecute() {

            progressDialog.setMessage("Data loading....");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(false);
            progressDialog.setMax(100);
            progressDialog.show();

            super.onPreExecute();
        }

        String tableName;
        JSONArray jsonArrayValues;

        @Override
        protected Void doInBackground(String... response) {
            try {
                JSONObject jsonObject = new JSONObject(response[0]);
                JSONArray result = jsonObject.getJSONArray("result");
                for (int i = 0; i < result.length(); i++) {
                    JSONObject table = result.getJSONObject(i);
                    tableName = table.getString("table_name");
                    jsonArrayValues = table.getJSONArray("values");

                    if (jsonArrayValues.length() == 0) continue;
                    sqLiteDatabase = databaseHelper.getWritableDatabase();

                    insertWithLoop(tableName, jsonArrayValues, sqLiteDatabase);

                }
            } catch (Exception e) {
                Log.i("exception", String.valueOf(e));
            }
            return null;
        }

        private void insertWithLoop(String tableName, JSONArray jsonArray, SQLiteDatabase sqLiteDatabase) {
            JSONObject jsonObject;
            ArrayList<String> keyList = new ArrayList<>();
            sqLiteDatabase.beginTransaction();
            try {
                for (int i = 0; i < jsonArray.length(); i++) {

                    jsonObject = jsonArray.getJSONObject(i);
                    if (i == 0) {
                        Iterator iter = jsonObject.keys();
                        while (iter.hasNext()) {
                            String key = iter.next().toString();
                            keyList.add(key);
                        }
                    }

                    for (int k = 0; k < jsonObject.length(); k++) {
                        String sql = "INSERT INTO " + tableName + " (";
                        String values = "VALUES(";
                        for (String key : keyList) {
                            sql += key + ",";
                            values += "'" + jsonObject.get(key).toString().replace("'", "''") + "',";
                        }
                        sql = sql.substring(0, sql.length() - 1) + ")";
                        values = values.substring(0, values.length() - 1) + ")";


                        try {
                            sqLiteDatabase.execSQL(sql + values);
                        } catch (SQLException ignored) {
                        }
                    }
                }
                sqLiteDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                databaseManager.DeleteAllTables();
                operation.LogOut();
                Log.e("Error", e.getMessage());
            } finally {
                sqLiteDatabase.endTransaction();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            sqLiteDatabase.close();

            startActivity(new Intent(WelcomeActivityDemo.this, FeatureActivity.class));
            finish();
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Toast.makeText(WelcomeActivityDemo.this, values[0], Toast.LENGTH_SHORT).show();
            progressDialog.setProgress(Integer.parseInt(values[0]));
            if (progressDialog.getMax() == 100) {
                progressDialog.dismiss();
            }
        }
    }

    private String insertFullDataInSQlite(String tableName, JSONArray jsonArray) {
        StringBuilder stringBuilder = new StringBuilder();
        JSONObject jsonObject = new JSONObject();
        stringBuilder.append("INSERT INTO '");
        stringBuilder.append(tableName + "' (");
        ArrayList<String> keyList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonObject = jsonArray.getJSONObject(i);
                if (i == 0) {
                    Iterator iter = jsonObject.keys();
                    while (iter.hasNext()) {
                        String key = iter.next().toString();
                        stringBuilder.append(key + ",");
                        keyList.add(key);
                    }
                    stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
                    stringBuilder.append(") VALUES ");
                }
            } catch (Exception e) {
                Toast.makeText(WelcomeActivityDemo.this, "E2", Toast.LENGTH_SHORT).show();
            }
            stringBuilder.append("(");
            for (int k = 0; k < jsonObject.length(); k++) {
                try {
                    String vlu = String.format("%s", jsonObject.get(keyList.get(k))).replace("'", "''");
                    stringBuilder.append(" '" + vlu + "' ");
                    if (k == jsonObject.length() - 1) {
                        // stringBuilder.append(")");
                    } else {
                        stringBuilder.append(",");
                    }
                } catch (JSONException e) {
                    Toast.makeText(WelcomeActivityDemo.this, "E3", Toast.LENGTH_SHORT).show();
                }
            }
            if (i == jsonArray.length() - 1) {
                stringBuilder.append(")");
            } else {
                stringBuilder.append("),");
            }
        }
        stringBuilder.append(";");
        return stringBuilder.toString();
    }


    public boolean checkConnectivity() {
        return ConnectivityReceiver.isConnected();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void OnNetworkChange(boolean inConnected) {
        this.isConnected = inConnected;
    }

    public void showSnackBar() {

        new Thread(new Runnable() {
            public void run() {
                try {
                    Snackbar.make(myLinearLayout, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.btn_settings), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                                }
                            }).setActionTextColor(Color.RED).show();
                } catch (Exception e) {

                }
            }
        }).start();
    }
}
