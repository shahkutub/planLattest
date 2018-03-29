package com.nanosoft.planInternational.tracking.jsinterface;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nanosoft.planInternational.tracking.database.manager.DatabaseManager;
import com.nanosoft.planInternational.tracking.database.manager.QuestioneryManager;
import com.nanosoft.planInternational.tracking.database.manager.SurveyEntry;
import com.nanosoft.planInternational.tracking.database.model.Project;
import com.nanosoft.planInternational.tracking.database.model.ScInfoModel;
import com.nanosoft.planInternational.tracking.database.model.SurveyQuestion;
import com.nanosoft.planInternational.tracking.utility.Operation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Nanosoft-Android on 5/9/2017.
 */

public class DataBroker {
    public static ScInfoModel scInfoModel;
    public static int scNumber;
    public static int scAgeRange = 0;
    public static int surveyEntryId = 0;
    private Operation operation;
    private Context ctx;
    private QuestioneryManager questioneryManager;
    private ArrayList<SurveyQuestion> surveyQuestions;
    private DatabaseManager databaseManager;
    private AudioRecorder audioRecorder;

    public DataBroker(Context ctx) {
        this.ctx = ctx;
        questioneryManager = new QuestioneryManager(ctx);
        surveyQuestions = new ArrayList<>();
        operation = new Operation(ctx);
        databaseManager = new DatabaseManager(ctx);
        audioRecorder = new AudioRecorder(ctx);
    }

    @JavascriptInterface
    public void ShowMessage(String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public String GetPrimaryQs() {
        surveyQuestions = questioneryManager.getSurveyQuestionList(surveyEntryId, scAgeRange);
        Gson g = new Gson();
        return g.toJson(surveyQuestions);
    }

    @JavascriptInterface
    public String Getsurvey_question_idbysurveylogic(int survey_question_choice_id) {
        return new Gson().toJson(questioneryManager.Getsurvey_question_idbysurveylogic(survey_question_choice_id));
    }

    @JavascriptInterface
    public String GetChoices() {
        return new Gson().toJson(questioneryManager.GetChoices(surveyEntryId));
    }

    @JavascriptInterface
    public String GetChoicesById(String seid) {
        String json = new Gson().toJson(questioneryManager.GetChoices(Integer.parseInt(seid)));
        return json;
    }

    @JavascriptInterface
    public boolean HasSubQuestion(String surveyquestionchoiceid) {
        return questioneryManager.GetChoices(Integer.parseInt(surveyquestionchoiceid)).size() > 0;
    }

    @JavascriptInterface
    public String GetProjectTitle() {
        return questioneryManager.GetSurveyTitle(surveyEntryId);
    }

    @JavascriptInterface
    public boolean SaveAns(String ans) {
        UUID uuid = UUID.randomUUID();
        String url = scNumber == 0 ? "/api/save-general-question-answer" : "/api/save-question-answer";
        //try to upload
        String userid = questioneryManager.getUserId();
        try {
            final JSONArray array = new JSONArray(ans);
            int len = array.length();

            final JSONArray arrayForGQ = new JSONArray();
            if (scNumber == 0) {
                for (int i = 0; i < len; i++) {
                    JSONObject obj = array.getJSONObject(i);

                    JSONObject objg = new JSONObject();
                    objg.put("question_id", obj.get("questionId"));
                    objg.put("answer", obj.get("answer"));
                    objg.put("isText", obj.get("isText"));
                    objg.put("userid", userid);
                    objg.put("parentId", obj.get("parentId"));
                    objg.put("question_set_id", uuid);
                    arrayForGQ.put(objg);
                }
            } else {
                for (int i = 0; i < len; i++) {
                    JSONObject obj = array.getJSONObject(i);
                    obj.put("userid", userid);
                    obj.put("scNumber", scNumber);
                    obj.put("question_set_id", uuid);
                    array.put(i, obj);
                }
            }
            if (operation.checkInternetConnection(ctx)) {
                RequestQueue rq = Volley.newRequestQueue(ctx);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Operation.BaseUrl + url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.d("SEREVER-RESPONSE",response);
                                try {
                                    ShowMessage("Data  Saved to server!");
//                                    closeActivity();
                                } catch (Exception e) {
                                    ShowMessage("Wrong!");
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
                        parameters.put("data", scNumber == 0 ? arrayForGQ.toString() : array.toString());
                        return parameters;
                    }
                };
                rq.add(stringRequest);
            } else {
                questioneryManager.keepTempAnswer(scNumber, scNumber == 0 ? arrayForGQ.toString() : array.toString());
                ShowMessage("Data  Saved locally!");
            }
            //Log.i("nice", arrayForGQ.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // ShowMessage(ans);
        if (!isGeneral())
            databaseManager.SaveScSurveyCompleted(scNumber, surveyEntryId);
        return true;
    }

    @JavascriptInterface
    public void closeActivity() {
        ((Activity) ctx).finish();
    }

    @JavascriptInterface
    public String GetProjectList() {
        ArrayList<Project> projectList = databaseManager.getProjectList(scInfoModel);
        JSONArray array = new
                JSONArray();
        try {
            for (Project p : projectList) {
                JSONObject obj = new JSONObject();
                obj.put("name", p.getProject_title());
                obj.put("categoryId", p.getCategory_id());
                array.put(obj);
            }
        } catch (JSONException ignored) {
        }
        return array.toString();
    }

    @JavascriptInterface
    public boolean isGeneral() {
        return scNumber == 0;
    }

    @JavascriptInterface
    public String GetSurveyList(int catId) {
        ArrayList<SurveyEntry> surveyList = databaseManager.getSurveyList(catId);
        JSONArray array = new
                JSONArray();
        try {
            for (SurveyEntry p : surveyList) {
                JSONObject obj = new JSONObject();
                obj.put("name", p.Name);
                obj.put("id", p.ID);
                obj.put("completed", !isGeneral() && p.Completed);
                array.put(obj);
            }
        } catch (JSONException ignored) {
        }
        return array.toString();
    }

    @JavascriptInterface
    public void setSurveyId(int id) {
        surveyEntryId = id;
    }

    @JavascriptInterface
    public void StartRecording() {
        audioRecorder.startRecording();
    }

    @JavascriptInterface
    public void StopRecording() {
        audioRecorder.stopRecording();
    }

    @JavascriptInterface
    public void StartPlaying() {
        audioRecorder.startPlaying();
    }

    @JavascriptInterface
    public void StopPlaying() {
        audioRecorder.stopPlaying();
    }

    @JavascriptInterface
    public void SaveAudio() {
        audioRecorder.stopPlaying();
        Toast.makeText(ctx, "Please wait while saving data!", Toast.LENGTH_SHORT).show();
        String data = audioRecorder.SaveAudio();
        databaseManager.saveAudio(data, scNumber);
        Toast.makeText(ctx, "Saved!", Toast.LENGTH_SHORT).show();
    }
}
