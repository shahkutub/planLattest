package com.nanosoft.planInternational.tracking.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.adapter.SchedulePlanSCListAdapter;
import com.nanosoft.planInternational.tracking.database.helper.DatabaseAssetHelper;
import com.nanosoft.planInternational.tracking.database.model.PlantSC;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SchedulePlantSCListActivity extends AppCompatActivity implements View.OnClickListener {


    private DatabaseAssetHelper mDBHelper;
    /*SC WISE LIST*/

    private ListView listviewPlanSC;
    private SchedulePlanSCListAdapter adaptersc;
    private List<PlantSC> mPlanSCList;
    private Button testbutton;
    /*simle list*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_plant_sclist);

        listviewPlanSC = (ListView)findViewById(R.id.schedule_listview_plantsc);
        testbutton = (Button) findViewById(R.id.testbutton);
        mDBHelper = new DatabaseAssetHelper(this);

        //Check exists database
        File database = getApplicationContext().getDatabasePath(DatabaseAssetHelper.DBNAME);
        if(false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //Get product list in db when db exists
        // mProductList = mDBHelper.getListProduct();
      //  mPlanSCList = mDBHelper.getListPlanSC();
        //Init adapter
        //adapter = new ListProductAdapter(this, mProductList);
        adaptersc = new SchedulePlanSCListAdapter(SchedulePlantSCListActivity.this, mPlanSCList);


        listviewPlanSC.setAdapter(adaptersc);
        listviewPlanSC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                final TextView label = (TextView) v.findViewById(R.id.tv_sc_name_schedule);
                TextView labelOne = (TextView) v.findViewById(R.id.tv_sc_id_schedule);
                TextView labelTwo = (TextView) v.findViewById(R.id.tv_sc_age_schedule);
                CheckBox checkbox = (CheckBox) v.findViewById(R.id.check_schedule);

                checkbox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] name = {label.getText().toString()};
                    }
                });
                Toast.makeText(v.getContext(), label.getText().toString()+" "+labelOne.getText().toString()+" "+labelTwo.getText().toString()+" "+isCheckedOrNot(checkbox), Toast.LENGTH_LONG).show();


            }
        });

        testbutton = (Button) findViewById(R.id.testbutton);

        //listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//        listView.setAdapter(adapter);
        testbutton.setOnClickListener(this);
    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseAssetHelper.DBNAME);
            String outFileName = DatabaseAssetHelper.DBLOCATION + DatabaseAssetHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    private String isCheckedOrNot(CheckBox checkbox) {
        if(checkbox.isChecked())
            return "is checked";
        else
            return "is not checked";
    }

    @Override
    public void onClick(View v) {
        final TextView label = (TextView) v.findViewById(R.id.tv_sc_name_schedule);


        CheckBox checkbox = (CheckBox) v.findViewById(R.id.check_schedule);

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> name = new ArrayList<String>();
                name.add( label.getText().toString());
                SparseBooleanArray checked = listviewPlanSC.getCheckedItemPositions();

                for ( int i = 0; i < checked.size(); i++) {
                    // Item position in adapter
                    int position = checked.keyAt(i);
                    // Add sport if it is checked i.e.) == TRUE!

                    if (checked.valueAt(i))
                        name.add((String) adaptersc.getItem(position));
                    listviewPlanSC.getChildAt(position).setVisibility(View.GONE);
                }
            }
        });

        ArrayList<String> selectedItems = new ArrayList<String>();


    /*    for ( int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!

            Toast.makeText(this, "checked"+position, Toast.LENGTH_SHORT).show();
            if (checked.valueAt(i))
                selectedItems.add((String) adaptersc.getItem(position));
            listviewPlanSC.getChildAt(position).setVisibility(View.GONE);
        }*/

        String[] outputStrArr = new String[selectedItems.size()];

        for ( int i = 0; i < selectedItems.size(); i++) {
            outputStrArr[i] = selectedItems.get(i);

        }

        Intent intent = new Intent(getApplicationContext(),
                ScheduleResultActivity.class);

        // Create a bundle object
        Bundle b = new Bundle();
        b.putStringArray("selectedItems", outputStrArr);

        // Add the bundle to the intent.
        intent.putExtras(b);

        // start the ResultActivity
        startActivity(intent);
    }
}

