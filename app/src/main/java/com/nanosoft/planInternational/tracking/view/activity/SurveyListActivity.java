package com.nanosoft.planInternational.tracking.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.adapter.SCListCustomAdapter;
import com.nanosoft.planInternational.tracking.database.manager.DatabaseManager;
import com.nanosoft.planInternational.tracking.database.model.ScInfoModel;
import com.nanosoft.planInternational.tracking.utility.AppConstant;
import com.nanosoft.planInternational.tracking.utility.ResponseCode;

import java.util.ArrayList;

public class SurveyListActivity extends AppCompatActivity {

    private Spinner spinnerDate;
    ArrayList<String> dateArrayList;
    ArrayAdapter<String> adapter;

    private DatabaseManager databaseManager;
    private RecyclerView myRecyclerForScheduledsc;
    private SCListCustomAdapter scListCustomAdapter;
    private ArrayList<ScInfoModel> sponsoredChildInfoArrayList;
    String date;

    ListView listviewdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById();

    }

    private void findViewById() {
        spinnerDate = (Spinner) findViewById(R.id.spinnerDate);
        myRecyclerForScheduledsc = (RecyclerView) findViewById(R.id.myRecyclerForScheduledsc);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        myRecyclerForScheduledsc.setLayoutManager(layoutManager);

        databaseManager = new DatabaseManager(this);
        dateArrayList = new ArrayList<>();
        dateArrayList = databaseManager.getDateList("1");
        if(dateArrayList.size()==0){
            for (int i = 0; i < AppConstant.sponsoredChildInfoArrayList.size(); i++) {

                dateArrayList.add(AppConstant.sponsoredChildInfoArrayList.get(i).getDateFlag());
            }
        }



        dateArrayList.add(0,"Select your Scheduled Date");
//        for(int i = 0; i<dateArrayList.size();i++){
//            if(dateArrayList.get(i).equalsIgnoreCase("Select your Scheduled Date")){
//                dateArrayList.set(1,"Select your Scheduled Date");
//            }
//        }
        adapter = new ArrayAdapter<String>(this, R.layout.item_spinner, dateArrayList);

        spinnerDate.setAdapter(adapter);
        setspinnserSelected();
        listviewdate = (ListView) findViewById(R.id.listviewdate);

        // myRecyclerForScheduledsc.setAdapter(adapter);
        // listviewdate.setAdapter(adapter);
        listviewdate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String date = view.getParent().toString();

                // Toast.makeText(SurveyListActivity.this, date, Toast.LENGTH_SHORT).show();


                setListItem(date);
                // listviewdate.setVisibility(View.GONE);
            }

        });

        //listviewdate.setAdapter(adapter);
        spinnerDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                date = parentView.getSelectedItem().toString();

                //Toast.makeText(SurveyListActivity.this, date, Toast.LENGTH_SHORT).show();

                setListItem(date);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    private void setspinnserSelected() {
        for(int i = 0; i<dateArrayList.size();i++){
            if(dateArrayList.get(i).toString().equalsIgnoreCase("Select your Scheduled Date")){
                spinnerDate.setSelection(i);
            }
        }
    }

    private void setListItem(String date) {
        if(!date.equalsIgnoreCase("Select your Scheduled Date")){
            sponsoredChildInfoArrayList = databaseManager.getPrioritySCListByDate(date);
            scListCustomAdapter = new SCListCustomAdapter(this, sponsoredChildInfoArrayList, ResponseCode.RESPONSE_CODE_SC_SCHEDULED,myRecyclerForScheduledsc,date);
            myRecyclerForScheduledsc.setAdapter(scListCustomAdapter);
        }
        setspinnserSelected();



    }




}
