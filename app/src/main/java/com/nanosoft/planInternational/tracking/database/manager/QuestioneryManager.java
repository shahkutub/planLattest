package com.nanosoft.planInternational.tracking.database.manager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nanosoft.planInternational.tracking.database.helper.DatabaseHelper;
import com.nanosoft.planInternational.tracking.database.model.SurveyQuestion;
import com.nanosoft.planInternational.tracking.database.model.SurveyQuestionChoice;
import com.nanosoft.planInternational.tracking.database.tables.ScQuestionAnswersTable;
import com.nanosoft.planInternational.tracking.database.tables.TableStructure;
import com.nanosoft.planInternational.tracking.offlineupdate.DatabaseHandler;
import com.nanosoft.planInternational.tracking.offlineupdate.OfflineData;
import com.nanosoft.planInternational.tracking.utility.Operation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nanosoft.planInternational.tracking.view.activity.WelcomeActivity.databaseHelper;
import static com.nanosoft.planInternational.tracking.view.activity.WelcomeActivity.sqLiteDatabase;

/**
 * Created by NanoSoft on 4/2/2017.
 */

public class QuestioneryManager  {

    private final Operation operation;

    Context context;
    Activity activity;
    Handler handler = new Handler();
    int status = 0;
    Button button;
    ProgressDialog progressdialog;

    public QuestioneryManager(Context context) {
        this.context = context;
        operation = new Operation(context);
    }


    public QuestioneryManager(Context context,Activity activity) {
        this.context = context;
        this.activity = activity;
        operation = new Operation(context);
    }
    private void openDataBase() {
        if (databaseHelper == null) databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    private void closeDataBase() {
        databaseHelper.close();
    }


    public ArrayList<String> getQuestionTypeValueList(int projectId, int questionId) {
        ArrayList<String> questionTypeValueList = new ArrayList<>();

        openDataBase();
        String sql = "SELECT * FROM " + TableStructure.TABLE_NAME_SURVEY_QUESTION_CHOICE + " WHERE " + TableStructure.COL_SURVEY_SURVEY_QUESTION_CHOICE_ID + " = '" + questionId + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String districtNames = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SURVEY_SURVEY_QUESTION_CHOICE_ANSWER_CHOICE));
            questionTypeValueList.add(districtNames);
        }
        cursor.close();
        closeDataBase();


        return questionTypeValueList;
    }

    ArrayList<Integer> staticAnswerId;

    //UPDATE
    public long updateProfile(int id, String questionId, String date, String answer) {




        try {
            ContentValues cv = new ContentValues();
            cv.put(ScQuestionAnswersTable.COL_SC_STATIC_QUESTION_ANSWER_QUESTION_ID, questionId);
            cv.put(ScQuestionAnswersTable.COL_SC_STATIC_QUESTION_ANSWER_DATE, date);
            cv.put(ScQuestionAnswersTable.COL_SC_STATIC_QUESTION_ANSWER_ANSWER, answer);

            return sqLiteDatabase.update(ScQuestionAnswersTable.TABLE_NAME_SC_STATIC_QUESTION_ANSWER, cv, ScQuestionAnswersTable.COL_SC_STATIC_QUESTION_ANSWER_SC_ID + " =?", new String[]{String.valueOf(id)});

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }

    public ArrayList<String> getOthersEditTextValueList(int projectId) {
        ArrayList<String> othersEditTextValueList = new ArrayList<>();


        openDataBase();
        // String sql = "SELECT * FROM "+ TableStructure.TABLE_NAME_SURVEY_QUESTION +" WHERE "+TableStructure.COL_DISTRICT_FOREIGN_KEY+" = '"+id+"'";
        Cursor cursor = sqLiteDatabase.query(TableStructure.TABLE_NAME_SURVEY_QUESTION, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String otherAnswerOption = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_OTHER_ANSWER_OPTION));
            othersEditTextValueList.add(otherAnswerOption);
        }
        cursor.close();
        closeDataBase();

        return othersEditTextValueList;
    }

    public ArrayList<String> getQuestionList(int projectId) {
        ArrayList<String> questionList = new ArrayList<>();

        openDataBase();
        // String sql = "SELECT * FROM "+ TableStructure.TABLE_NAME_SURVEY_QUESTION +" WHERE "+TableStructure.COL_DISTRICT_FOREIGN_KEY+" = '"+id+"'";
        Cursor cursor = sqLiteDatabase.query(TableStructure.TABLE_NAME_SURVEY_QUESTION, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String questions = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_NAME));

            questionList.add(questions);
        }
        cursor.close();
        closeDataBase();


//        for (int i = 0; i < question.length; i++) {
//            questionList.add(question[i]);
//        }

        return questionList;
    }


    public ArrayList<String> getQuestionTypeList(int projectId) {
        ArrayList<String> questionTypeList = new ArrayList<>();


        openDataBase();
        // String sql = "SELECT * FROM "+ TableStructure.TABLE_NAME_SURVEY_QUESTION +" WHERE "+TableStructure.COL_DISTRICT_FOREIGN_KEY+" = '"+projectId+"'";
        Cursor cursor = sqLiteDatabase.query(TableStructure.TABLE_NAME_SURVEY_QUESTION, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String questionType = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_TYPE));
            questionTypeList.add(questionType);
        }
        cursor.close();
        closeDataBase();

        return questionTypeList;
    }

    SurveyQuestion surveyQuestion;

    public ArrayList<SurveyQuestion> getSurveyQuestionList(int surveyEntryId, int scAgeRangeId) {
        ArrayList<SurveyQuestion> questionTypeList = new ArrayList<>();
        openDataBase();
        String sql = "SELECT * FROM " + TableStructure.TABLE_NAME_SURVEY_QUESTION + " WHERE " + TableStructure.COL_SURVEY_QUESTION_ENTRY_ID + " = '" + surveyEntryId + "'"
                + (scAgeRangeId > 0 ? " and (ageRange='0' or ageRange='" + scAgeRangeId + "')" : "");

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        // Cursor cursor = sqLiteDatabase.query(TableStructure.TABLE_NAME_SURVEY_QUESTION, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String questionType = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_TYPE));
            String crated_by = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_CRAETED_BY));
            String modified_by = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_MODIFIED_BY));
            int tableId = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_TABLE_ID));
            String questionName = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_NAME));
            int serialNo = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_SERIAL_NO));
            int otherAnswerOption = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_OTHER_ANSWER_OPTION));
            int answerRequired = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_ANSWER_REQUIRED));
            String reqtext = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_REQ_TEXT));
            int validateAnswerFormat = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_VALIDATION_ANSWER_FORMAT));
            int characters = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_CHARACTERS));
            int scale = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_SCALE));
            int ageRange = cursor.getInt(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_AGE_RANGE));
            String is_primary_question = cursor.getString(cursor.getColumnIndex(TableStructure.COL_SURVEY_QUESTION_TABLE_IS_PRIMARY_QUESTION));


            surveyQuestion = new SurveyQuestion(tableId, crated_by, modified_by, surveyEntryId, questionType,
                    questionName, serialNo, otherAnswerOption, answerRequired, reqtext, validateAnswerFormat, characters, scale, ageRange, is_primary_question);
            questionTypeList.add(surveyQuestion);
        }
        cursor.close();
        closeDataBase();

        return questionTypeList;
    }

    public List<Integer> Getsurvey_question_idbysurveylogic(int survey_question_choice_id) {
        List<Integer> list = new ArrayList<>();
        openDataBase();
        Cursor cursor = sqLiteDatabase.rawQuery("select survey_question_id from survey_logic where survey_question_choice_id = ?",
                new String[]{"" + survey_question_choice_id});
        while (cursor.moveToNext()) {
            list.add(cursor.getInt(cursor.getColumnIndex(TableStructure.COL_TARGET_SURVEY_LOGIC_QUESTION_ID)));
        }
        cursor.close();
        closeDataBase();
        return list;
    }

    String[] list = {"editText", "spinner", "radioGroup", "checkBox", "starRating",
            "datePicker", "timePicker", "spinner", "radioGroup", "editText",
            "dateTimePicker", "checkBox"};

    String[] question = {"What is your name?", "What class do you read?", "Your Gender",
            "Your Favourite colour?", "App Rating...?", "Date...?", "Time...?", "Spinner....?",
            "Your Country.....?", "editText....?", "Date Time .....?", "CheckNox........?"};

    String[] othersEditTextValue = {"1", "0", "1", "1", "1", "1", "0", "0", "1", "0", "1", "1"};


    String[] spinnerValue = {"Class 1", "Class 2", "Class 3", "Class 4", "Class 5 ", "Class 6",
            "Class 7", "Class 8", "Class 9", "Class 10", "Class 11"};
    String[] spinnerValue2 = {"Dhaka", "Chittagong", "Feni", ""};
    String[] radioGroup = {"Male", "Female", "Others"};
    String[] radioGroup2 = {"Bangladesh", "India", "USA", "UK", "Russia", "Iran", "AUS"};
    String[] checkBoxText = {"Red", "Green", "Black", "Blue", "Yellow"};
    String[] checkBoxText2 = {"Check1", "Check2", "Check3", "Check4", "Check5"};
    String[] editTextValue = {"Name", "phoneNumber", "email", "address"};
    String[] editTextValue2 = {"House", "Road", "Office"};
    public List<SurveyQuestionChoice> GetChoices(int surveyEntryId) {
        ArrayList<SurveyQuestionChoice> list = new ArrayList<>();
        openDataBase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From   survey_question_choice Where  survey_question_id = ? ",
                new String[]{"" + surveyEntryId});
        while (cursor.moveToNext()) {
            list.add(new SurveyQuestionChoice(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getInt(cursor.getColumnIndex("survey_question_id")),
                    cursor.getString(cursor.getColumnIndex("answer_choice")),
                    cursor.getInt(cursor.getColumnIndex("ansNo"))));
        }
        cursor.close();
        closeDataBase();
        return list;
    }
    public String GetSurveyTitle(int surveyEntryId) {
        String s = "";
        openDataBase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT name FROM " + TableStructure.TABLE_NAME_SURVEY_ENTRY +
                " WHERE id='" + surveyEntryId + "'", null);
        while (cursor.moveToNext()) {
            s = cursor.getString(cursor.getColumnIndex("name"));
            break;
        }
        cursor.close();
        return s;
    }
    public void InsertStaticQuestion(String questionId, String answer, String scnumber) {
        openDataBase();
        Cursor c = sqLiteDatabase.rawQuery("select * from table_sc_static_question_answers " +
                "where static_question_id='" + questionId + "' and sc_id=?", new String[]{scnumber});
        boolean exists = c.getCount() > 0;
        c.close();
        ContentValues cvs = new ContentValues();
        cvs.put("answer", answer);
        cvs.put("date", new Date().toString());
        if (!exists) {
            sqLiteDatabase.execSQL("INSERT INTO [table_sc_static_question_answers]([static_question_id],[sc_id],[answer])VALUES('" + questionId + "','" + scnumber + "',?);", new Object[]{answer});
        } else {
            sqLiteDatabase.update("table_sc_static_question_answers", cvs, "static_question_id='" + questionId + "' and sc_id=?", new String[]{scnumber});
        }
        closeDataBase();
    }
    public String getUserId() {
        String userid = "";
        openDataBase();
        Cursor cursor = sqLiteDatabase.rawQuery("select id from users where email = ?",
                new String[]{operation.getString("email", "")});
        if (cursor.moveToNext()) {
            userid = cursor.getInt(0) + "";
        }
        cursor.close();
        closeDataBase();
        return userid;
    }
    public void keepTempAnswer(int scNumber, String json) {
        openDataBase();
        ContentValues cvs = new ContentValues();
        cvs.put("scnumber", scNumber);
        cvs.put("json", json);
        sqLiteDatabase.insert("anskeep", null, cvs);
        closeDataBase();
    }
    public void UploadAnswers() {

        if (operation.checkInternetConnection(context)) {
            RequestQueue rq = Volley.newRequestQueue(context);
            openDataBase();

            String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='anskeep'";

            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
            boolean exists = false;
            while (cursor.moveToNext()) {
                exists = true;
                break;
            }
            cursor.close();
            if (!exists) {
                closeDataBase();
                return;
            }

            final Cursor c = sqLiteDatabase.rawQuery("SELECT [json] FROM [anskeep]", null);
            while (c.moveToNext()) {
                final String data = c.getString(0);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Operation.BaseUrl + "/api/save-question-answer",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String successValue = jsonObject.getString("success");
                                    String message = jsonObject.getString("message");
                                    if (successValue.equals("1")) {
                                    } else if (successValue.equals("0")) {
                                    }

                                } catch (Exception e) {
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                      //  parameters.put("data", c.getString(0));
                        parameters.put("data", data);
                        return parameters;
                    }
                };
                rq.add(stringRequest);
            }
            c.close();
            sqLiteDatabase.execSQL("DELETE FROM anskeep;");
            closeDataBase();
        }
    }
    String responseValue="";
    public void SendProfileDataToNet(JSONArray jsonArray, int scInfoNumber) {


        CreateProgressDialog();

        ShowProgressDialog();

        openDataBase();
        final JSONObject finalObject = new JSONObject();
        final JSONArray array = new JSONArray();
        Cursor cursor = sqLiteDatabase.rawQuery("select sc_id,answer,static_question_id from table_sc_static_question_answers where sc_id='" + scInfoNumber + "'", null);
        int c = 0;
        while (cursor.moveToNext()) {
            try {
                JSONObject object = new JSONObject();
                object.put("sc_id", cursor.getString(0));
                double sqid = tryParseDouble(cursor.getString(2));
                //if (sqid < 8) continue;
                String ans = cursor.getString(1);
                if (sqid == 9.1 || sqid == 9.2) {
                    object.put("answer", "");
                    object.put("img", ans != null ? ans : "");
                } else
                    object.put("answer", ans != null ? ans : "");
                object.put("static_question_id", cursor.getString(2));
                object.put("date", new Date().toString());
                array.put(object);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            finalObject.put("table_sc_static_question_answers", array);
             finalObject.put("familyInfo", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("info", finalObject.toString());

//        final BusyDialog busyNow = new BusyDialog(context, true, false);
//        busyNow.show();
//        double dataLength = 0, insertProgress = 0;


        RequestQueue rq = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Operation.BaseUrl + "/api/save-static-question-answer",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("true")){
                            responseValue ="true";
                        }

//                        if (response!=null){
//                            try {
//                                JSONObject jsonObject = new JSONObject();
//                                responseValue =jsonObject.getString(response);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                responseValue= "false";
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                String data = finalObject.toString();
                parameters.put("data", data);
                return parameters;
            }
        };
        rq.add(stringRequest);
        cursor.close();
        closeDataBase();
    }


    public void saveScLocalToSendWhenGetConn(JSONArray jsonArray, int scInfoNumber){

        CreateProgressDialog();

        showProgressDialogOffline();

        openDataBase();
        final JSONObject finalObject = new JSONObject();
        final JSONArray array = new JSONArray();
        Cursor cursor = sqLiteDatabase.rawQuery("select sc_id,answer,static_question_id from table_sc_static_question_answers where sc_id='" + scInfoNumber + "'", null);
        int c = 0;
        while (cursor.moveToNext()) {
            try {
                JSONObject object = new JSONObject();
                object.put("sc_id", cursor.getString(0));
                double sqid = tryParseDouble(cursor.getString(2));
                //if (sqid < 8) continue;
                String ans = cursor.getString(1);
                if (sqid == 9.1 || sqid == 9.2) {
                    object.put("answer", "");
                    object.put("img", ans != null ? ans : "");
                } else
                    object.put("answer", ans != null ? ans : "");
                object.put("static_question_id", cursor.getString(2));
                object.put("date", new Date().toString());
                array.put(object);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            finalObject.put("table_sc_static_question_answers", array);
            finalObject.put("familyInfo", jsonArray);

            DatabaseHandler db = new DatabaseHandler(context);

            db.addContact(new OfflineData(String.valueOf(scInfoNumber),finalObject.toString()));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("info", finalObject.toString());
    }


    public void CreateProgressDialog()
    {

        progressdialog = new ProgressDialog(context);

        progressdialog.setIndeterminate(false);

        progressdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressdialog.setCancelable(false);

        progressdialog.setMax(100);

        progressdialog.show();

    }

    public void showProgressDialogOffline() {
        status = 0;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(status < 100){

                    status +=1;

                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            progressdialog.setProgress(status);

                            if(status == 100){

                                progressdialog.dismiss();

                                Toast.makeText(context, "Save to offline!", Toast.LENGTH_SHORT).show();
                                activity.finish();
                            }
                        }
                    });
                }
            }
        }).start();

    }


    public void ShowProgressDialog() {
        status = 0;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(status < 100){

                    status +=1;

                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            progressdialog.setProgress(status);

                            if(status == 100){

                                progressdialog.dismiss();


                                if(responseValue=="true"){
                                    Toast.makeText(context, "Data Saved to server!", Toast.LENGTH_SHORT).show();
                                    //context.startActivity(new Intent(context, SurveyListActivity.class));
                                    activity.finish();

                                }else {
                                    Toast.makeText(context, "Data connection error!", Toast.LENGTH_SHORT).show();
                                }



                            }
                        }
                    });
                }
            }
        }).start();

    }
    private double tryParseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public void ClearAnswerBeforeNewInsert(int scId) {
        openDataBase();
        sqLiteDatabase.execSQL("delete from table_sc_static_question_answers where sc_id='" + scId + "'");
        closeDataBase();
    }


}
