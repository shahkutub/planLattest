package com.nanosoft.planInternational.tracking.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.adapter.SCListCustomAdapter;
import com.nanosoft.planInternational.tracking.database.manager.DatabaseManager;
import com.nanosoft.planInternational.tracking.database.manager.DivisionManager;
import com.nanosoft.planInternational.tracking.database.model.Community;
import com.nanosoft.planInternational.tracking.database.model.District;
import com.nanosoft.planInternational.tracking.database.model.DistrictUsersModel;
import com.nanosoft.planInternational.tracking.database.model.Division;
import com.nanosoft.planInternational.tracking.database.model.Union;
import com.nanosoft.planInternational.tracking.database.model.Upazila;
import com.nanosoft.planInternational.tracking.database.model.Wards;
import com.nanosoft.planInternational.tracking.utility.Operation;
import com.nanosoft.planInternational.tracking.utility.ResponseCode;
import com.nanosoft.planInternational.tracking.view.fragment.AllSCListFragment;
import com.nanosoft.planInternational.tracking.view.fragment.MapFragment;
import com.nanosoft.planInternational.tracking.view.fragment.SCtobeScheduledFragment;
import com.nanosoft.planInternational.tracking.view.fragment.ScheduledSCFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Button listAllBTN, unSchedulBTN, scheduledBTN, mapBTN;

    //SPINNER
    private Spinner spinnerDivision, spinnerDistrict, spinnerThana, spinnerUnion, spinnerWard, spinnerCommunity, spinnerCountry;

    /*searchbar*/
    private Toolbar toolbar;
    private Toolbar searchToolbar;
    private boolean isSearch = false;

    private Fragment allSCListFragment, mapFragment, scheduledSCFragment, sctobeScheduledFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    private ArrayAdapter<String> adapter;

    private DivisionManager divisionManager;
    private DatabaseManager databaseManager;

    private ArrayList<String> countryList;
    private ArrayList<Division> divisionList;
    private ArrayList<District> districtList;
    private ArrayList<Upazila> upazillaList;
    private ArrayList<Union> unionList;
    private ArrayList<Wards> wardList;
    private ArrayList<Community> communityList;

    private Operation operation;
    private String email;
    private int userId;
    private int userRoleId;

    private int userVolunteerRoleIdStatic = 100;
    private int userDistrictRoleIdStatic = 101;




    private LinearLayout scpropertyContainer;
    private SCListCustomAdapter scListCustomAdapter;

    private ArrayList<Integer> assignVolunteerCommunityIdList;
    private ArrayList<Community> communityListFromUser;
    private ArrayList<DistrictUsersModel> districtUserList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);


        toolbar = (Toolbar) findViewById(R.id.toolbar_viewpger);
        searchToolbar = (Toolbar) findViewById(R.id.toolbar_search);

        //prepareActionBar(toolbar);



        findViewById();

    }

    private void findViewById() {
        mapBTN = (Button) findViewById(R.id.mapBTN);
        listAllBTN = (Button) findViewById(R.id.listAllBTN);
        unSchedulBTN = (Button) findViewById(R.id.unSchedulBTN);
        scheduledBTN = (Button) findViewById(R.id.scheduledBTN);
        scpropertyContainer = (LinearLayout) findViewById(R.id.scpropertyContainer);

        allSCListFragment = new AllSCListFragment();
        sctobeScheduledFragment = new SCtobeScheduledFragment();
        scheduledSCFragment = new ScheduledSCFragment();
        mapFragment = new MapFragment();
        fragmentManager = getFragmentManager();

        listAllBTN.setBackgroundColor(Color.BLUE);
        unSchedulBTN.setBackgroundColor(Color.parseColor("#fb423d"));
        scheduledBTN.setBackgroundColor(Color.parseColor("#fb423d"));
        mapBTN.setBackgroundColor(Color.parseColor("#fb423d"));


        //SPINNER
        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        spinnerDivision = (Spinner) findViewById(R.id.spinnerDivision);
        spinnerDistrict = (Spinner) findViewById(R.id.spinnerDistrict);
        spinnerThana = (Spinner) findViewById(R.id.spinnerThana);
        spinnerUnion = (Spinner) findViewById(R.id.spinnerUnion);
        spinnerWard = (Spinner) findViewById(R.id.spinnerWard);
        spinnerCommunity = (Spinner) findViewById(R.id.spinnerCommunity);

        divisionManager = new DivisionManager(MainActivity.this);
        databaseManager = new DatabaseManager(MainActivity.this);
        operation = new Operation(MainActivity.this);

        email = operation.getString("email", "");
        userId = databaseManager.getUser(email).getUsersTableId();
        userRoleId = databaseManager.getUser(email).getUserRoleId();


        assignVolunteerCommunityIdList = new ArrayList<>();
        districtUserList = new ArrayList<>();
        assignVolunteerCommunityIdList = divisionManager.getAssignVolunteer(userId);
        districtUserList = divisionManager.getDistrictUser(userId);


        communityListFromUser = new ArrayList<>();
        for (int i = 0; i < assignVolunteerCommunityIdList.size(); i++) {
            communityListFromUser.add(divisionManager.getCommunity(assignVolunteerCommunityIdList.get(i)));
        }

        // setCountrySpinner();
        setDivisionSpinner();

    }




    @Override
    protected void onResume() {
        super.onResume();

    }



    public void onClickProfile(View view) {
        Button button = (Button) view;
        int id = button.getId();

        if (id == R.id.listAllBTN) {
            listAllBTN.setBackgroundColor(Color.BLUE);
            unSchedulBTN.setBackgroundColor(Color.parseColor("#fb423d"));
            scheduledBTN.setBackgroundColor(Color.parseColor("#fb423d"));
            mapBTN.setBackgroundColor(Color.parseColor("#fb423d"));

            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.scpropertyContainer, allSCListFragment);
            fragmentTransaction.commit();

        } else if (id == R.id.unSchedulBTN) {
            unSchedulBTN.setBackgroundColor(Color.BLUE);
            scheduledBTN.setBackgroundColor(Color.parseColor("#fb423d"));
            listAllBTN.setBackgroundColor(Color.parseColor("#fb423d"));
            mapBTN.setBackgroundColor(Color.parseColor("#fb423d"));
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.scpropertyContainer, sctobeScheduledFragment);

            fragmentTransaction.commit();
            // Toast.makeText(this, "unSchedulBTN ALL ", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.scheduledBTN) {

            scheduledBTN.setBackgroundColor(Color.BLUE);
            unSchedulBTN.setBackgroundColor(Color.parseColor("#fb423d"));
            listAllBTN.setBackgroundColor(Color.parseColor("#fb423d"));
            mapBTN.setBackgroundColor(Color.parseColor("#fb423d"));

            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.scpropertyContainer, scheduledSCFragment);

            fragmentTransaction.commit();
            //  Toast.makeText(this, "scheduledBTN ALL ", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.mapBTN) {

            unSchedulBTN.setBackgroundColor(Color.parseColor("#fb423d"));
            scheduledBTN.setBackgroundColor(Color.parseColor("#fb423d"));
            listAllBTN.setBackgroundColor(Color.parseColor("#fb423d"));
            mapBTN.setBackgroundColor(Color.BLUE);

            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.scpropertyContainer, mapFragment);
            fragmentTransaction.commit();
            //Toast.makeText(this, "mapBTN ALL ", Toast.LENGTH_SHORT).show();
        }
    }

    private void setCountrySpinner() {

        countryList = new ArrayList<>();
        //countryList = divisionManager.getCountryList();
        countryList.add("Bangladesh");

        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, countryList);
        spinnerCountry.setAdapter(adapter);
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                setDivisionSpinner();
                //Toast.makeText(MainActivity.this, divisionManager.getDivision().get(0), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    private void setDivisionSpinner() {

        divisionList = new ArrayList<>();
        divisionList = divisionManager.getDivisionList();
        ArrayList<String> divisionNames = new ArrayList<>();
        for (int i = 0; i < divisionList.size(); i++) {
            divisionNames.add(divisionList.get(i).getDivision_name()+"\n");
        }

        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, divisionNames);
        spinnerDivision.setAdapter(adapter);
        spinnerDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                //  if (position>0) {
                int divisionId = divisionList.get(position).getId();
                setDistrictSpinner(divisionId);
                // }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if (userRoleId == userVolunteerRoleIdStatic) {

            for (int i = 0; i < divisionList.size(); i++) {
                if (communityListFromUser.size() > 0) {
                    if (divisionList.get(i).getId() == communityListFromUser.get(0).getDivision_id()) {
                        spinnerDivision.setSelection(i);
                        spinnerDivision.setEnabled(false);
                    }
                }
            }
        }

        if (userRoleId == userDistrictRoleIdStatic) {
            for (int i = 0; i < divisionList.size(); i++) {

                if (districtUserList.size()>0) {
                    if (divisionList.get(i).getId() == districtUserList.get(0).getDivision_id()) {
                        spinnerDivision.setSelection(i);
                        spinnerDivision.setEnabled(false);
                    }
                }
            }
        }
    }

    private void setDistrictSpinner(int id) {
        districtList = new ArrayList<>();
        districtList = divisionManager.getDistrict(id);

        ArrayList<String> districtNames = new ArrayList<>();
        for (int i = 0; i < districtList.size(); i++) {
            districtNames.add(districtList.get(i).getDistrict_name()+"\n");
        }


        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, districtNames);
        spinnerDistrict.setAdapter(adapter);
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                int districtId = districtList.get(position).getId();
                setUpazilaSpinner(districtId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        if (userRoleId == userVolunteerRoleIdStatic) {

            for (int i = 0; i < districtList.size(); i++) {
                if (communityListFromUser.size() > 0) {
                    if (districtList.get(i).getId() == communityListFromUser.get(0).getDistrict_id()) {
                        spinnerDistrict.setSelection(i);
                        spinnerDistrict.setEnabled(false);
                    }
                }
            }
        }

        if (userRoleId == userDistrictRoleIdStatic) {
            for (int i = 0; i < districtList.size(); i++) {
                if (districtUserList.size()>0) {
                    if (districtList.get(i).getId() == districtUserList.get(0).getDistrict_id()) {
                        spinnerDistrict.setSelection(i);
                        spinnerDistrict.setEnabled(false);
                    }
                }
            }
        }


    }

    private void setUpazilaSpinner(int id) {

        upazillaList = new ArrayList<>();
        upazillaList = divisionManager.getUpazila(id);

        ArrayList<String> upazillaNames = new ArrayList<>();
        for (int i = 0; i < upazillaList.size(); i++) {
            upazillaNames.add(upazillaList.get(i).getUpazila_name()+"\n");
        }


        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, upazillaNames);
        spinnerThana.setAdapter(adapter);
        spinnerThana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                int upazillaId = upazillaList.get(position).getId();
                setUnionSpinner(upazillaId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if (userRoleId == userVolunteerRoleIdStatic) {

            for (int i = 0; i < upazillaList.size(); i++) {
                if (communityListFromUser.size() > 0) {
                    if (upazillaList.get(i).getId() == communityListFromUser.get(0).getUpazila_id()) {
                        spinnerThana.setSelection(i);
                        spinnerThana.setEnabled(false);
                    }
                }
            }
        }

    }

    private void setUnionSpinner(int id) {

        unionList = new ArrayList<>();
        unionList = divisionManager.getUnion(id);

        ArrayList<String> unionNames = new ArrayList<>();
        for (int i = 0; i < unionList.size(); i++) {
            unionNames.add(unionList.get(i).getUnion_name()+"\n");
        }

        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, unionNames);
        spinnerUnion.setAdapter(adapter);
        spinnerUnion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                int unionId = unionList.get(position).getId();
                setWardSpinner(unionId);
                setCommunitySpinner(unionId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if (userRoleId == userVolunteerRoleIdStatic) {

            for (int i = 0; i < unionList.size(); i++) {
                if (communityListFromUser.size() > 0) {
                    if (unionList.get(i).getId() == communityListFromUser.get(0).getUnion_id()) {
                        spinnerUnion.setSelection(i);
                        spinnerUnion.setEnabled(false);
                    }
                }
            }
        }

    }

    private void setWardSpinner(int id) {

        wardList = new ArrayList<>();
        wardList = divisionManager.getWard(id);

        ArrayList<String> wardNames = new ArrayList<>();
        for (int i = 0; i < wardList.size(); i++) {
            wardNames.add(wardList.get(i).getName()+"\n");
        }

        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, wardNames);
        spinnerWard.setAdapter(adapter);
        spinnerWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                int wardId = wardList.get(position).getId();
                //setCommunitySpinner(wardId);

              //  Toast.makeText(MainActivity.this, ""+wardId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if (userRoleId == userVolunteerRoleIdStatic) {

            for (int i = 0; i < wardList.size(); i++) {
                if (communityListFromUser.size() > 0) {
                    if (wardList.get(i).getId() == communityListFromUser.get(0).getWardId()) {
                        spinnerWard.setSelection(i);
                        spinnerWard.setEnabled(false);
                    }
                }
            }
        }

    }


    private void setCommunitySpinner(int id) {

        communityList = new ArrayList<>();
        communityList = divisionManager.getCommunityListByUnionId(id);


        ArrayList<String> communityNames = new ArrayList<>();
        for (int i = 0; i < communityList.size(); i++) {
            communityNames.add(communityList.get(i).getCommunity_name()+"\n");
        }

        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, communityNames);
        spinnerCommunity.setAdapter(adapter);
        spinnerCommunity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                int communityId = 0;

                if (userRoleId != userVolunteerRoleIdStatic) {
                    communityId = communityList.get(position).getId();
                } else {
                    if (communityListFromUser.size() > 1) {
                        communityId = communityListFromUser.get(position).getId();
                    }else {
                        communityId = communityListFromUser.get(0).getId();
                    }
                }
                AllSCListFragment.communityId = communityId;
                SCtobeScheduledFragment.communityId = communityId;
                ScheduledSCFragment.communityId = communityId;

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.scpropertyContainer, allSCListFragment);
                fragmentTransaction.commit();

                ResponseCode.addEventListener.setEvent("a");
                ResponseCode.addEventListener.setEvent("b");
                ResponseCode.addEventListener.setEvent("c");

                listAllBTN.setBackgroundColor(Color.BLUE);
                unSchedulBTN.setBackgroundColor(Color.parseColor("#fb423d"));
                scheduledBTN.setBackgroundColor(Color.parseColor("#fb423d"));
                mapBTN.setBackgroundColor(Color.parseColor("#fb423d"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        if (userRoleId == userVolunteerRoleIdStatic) {

            if (communityListFromUser.size() < 2) {
                for (int i = 0; i < communityList.size(); i++) {
                    if (communityListFromUser.size() > 0) {
                        if (communityList.get(i).getId() == communityListFromUser.get(0).getId()) {
                            spinnerCommunity.setSelection(i);
                            spinnerCommunity.setEnabled(false);
                        }
                    }
                }
            } else {

                ArrayList<String> communityNames2 = new ArrayList<>();
                for (int i = 0; i < communityListFromUser.size(); i++) {
                    communityNames2.add(communityListFromUser.get(i).getCommunity_name());
                }

                adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, communityNames2);
                spinnerCommunity.setAdapter(adapter);
            }
        }
    }

}
