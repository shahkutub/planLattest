package com.nanosoft.planInternational.tracking.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.database.manager.QuestioneryManager;
import com.nanosoft.planInternational.tracking.jsinterface.DataBroker;

public class QuestioneryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionery);
        initialize();
    }

    private void initialize() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_main);
        linearLayout.setPadding(10, 0, 10, 0);
        int surveyEntryId = getIntent().getIntExtra("surveyEntryId", 0);
        int scInfoNumber = getIntent().getIntExtra("scNumber", 0);
        QuestioneryManager questioneryManager = new QuestioneryManager(this);

        DataBroker.scNumber = scInfoNumber;
        DataBroker.surveyEntryId= surveyEntryId;

        WebView wv = (WebView) findViewById(R.id.webviewProject);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.addJavascriptInterface(new DataBroker(this), "and");
        wv.loadUrl("file:///android_asset/webapp/index.html");
    }

    @Override
    public void onBackPressed() {
    }

    private int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == this.REQUEST_RECORD_AUDIO_PERMISSION) {
            this.permissionToRecordAccepted = grantResults[0] == 0;
        }

        if(!this.permissionToRecordAccepted) {
            this.finish();
        }
    }



}
