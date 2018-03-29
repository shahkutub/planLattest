package com.nanosoft.planInternational.tracking.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.adapter.SCListCustomAdapter;
import com.nanosoft.planInternational.tracking.database.helper.DatabaseAssetHelper;
import com.nanosoft.planInternational.tracking.database.manager.DatabaseManager;
import com.nanosoft.planInternational.tracking.database.model.ScInfoModel;
import com.nanosoft.planInternational.tracking.utility.ResponseCode;

import java.util.ArrayList;

/**
 * Created by Nanosoft-Android on 4/11/2017.
 */

public class ScheduledSCFragment extends Fragment {


    public ScheduledSCFragment() {

    }

    DatabaseManager databaseManager;
    View view;
    RecyclerView recyclerViewProfile;
    private DatabaseAssetHelper mDBHelper;
    SCListCustomAdapter scListCustomAdapter;
    //private ArrayList<ScInfoModel> sponsoredChildInfoArrayList;

    private ArrayList<ScInfoModel> sponsoredChildInfoArrayList;
    public static int communityId ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scheduledsc, container, false);
        initialize();

        return view;
    }

    private void initialize() {
        recyclerViewProfile = (RecyclerView) view.findViewById(R.id.myRecyclerForScheduledsc);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewProfile.setLayoutManager(layoutManager);

        databaseManager = new DatabaseManager(getActivity());
        mDBHelper = new DatabaseAssetHelper(getActivity());
        sponsoredChildInfoArrayList = new ArrayList<>();

      //  sponsoredChildInfoArrayList = ScInfoModel.mySelectedList;
        sponsoredChildInfoArrayList = databaseManager.getPrioritySCList(communityId,"1");

        scListCustomAdapter = new SCListCustomAdapter(getActivity(), sponsoredChildInfoArrayList, ResponseCode.RESPONSE_CODE_SC_SCHEDULED,recyclerViewProfile);
        recyclerViewProfile.setAdapter(scListCustomAdapter);

        ResponseCode.addEventListener.addEvent(new AddListener() {
            @Override
            public void getEvent(Object data) {
                if (data.toString().equals("c")){

                    sponsoredChildInfoArrayList = databaseManager.getPrioritySCList(communityId,"1");
                    scListCustomAdapter = new SCListCustomAdapter(getActivity(), sponsoredChildInfoArrayList, ResponseCode.RESPONSE_CODE_SC_SCHEDULED,recyclerViewProfile);
                    recyclerViewProfile.setAdapter(scListCustomAdapter);
                }
            }
        });


    }
}
