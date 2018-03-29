package com.nanosoft.planInternational.tracking.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.adapter.SCListCustomAdapter;
import com.nanosoft.planInternational.tracking.database.helper.DatabaseAssetHelper;
import com.nanosoft.planInternational.tracking.database.manager.DatabaseManager;
import com.nanosoft.planInternational.tracking.database.model.ScInfoModel;
import com.nanosoft.planInternational.tracking.utility.Operation;
import com.nanosoft.planInternational.tracking.utility.ResponseCode;

import java.util.ArrayList;

/**
 * Created by Nanosoft-Android on 4/11/2017.
 */

public class AllSCListFragment extends Fragment {


    public AllSCListFragment() {
    }

    public static int communityId;

    private RecyclerView recyclerViewProfile;
    // private ArrayList<SponsoredChildInfo> sponsoredChildInfoArrayList;
    private ArrayList<ScInfoModel> sponsoredChildInfoArrayList;
    private ArrayList<ScInfoModel> sCSurveyModeList;
    private ArrayList<ScInfoModel> sCUpdateSurveyModeList;

    private SCListCustomAdapter scListCustomAdapter;
    private View view;

    private DatabaseAssetHelper mDBHelper;
    private DatabaseManager databaseManager;
    private RadioButton radioSurvey, radioUpdateSurvey;
    int reminderMonths = 0;

    private Toolbar toolbar;
    private Toolbar searchToolbar;
    private boolean isSearch = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_sclist, container, false);
        initialize();

        return view;
    }

    private void initialize() {

        toolbar = (Toolbar) view.findViewById(R.id.toolbar_viewpager);
        searchToolbar = (Toolbar) view.findViewById(R.id.toolbar_search);


        mDBHelper = new DatabaseAssetHelper(getActivity());
        databaseManager = new DatabaseManager(getActivity());
        sponsoredChildInfoArrayList = new ArrayList<>();
        sponsoredChildInfoArrayList = databaseManager.getSCListByCommunityId(communityId);

        recyclerViewProfile = (RecyclerView) view.findViewById(R.id.recyclerViewProfile);

        recyclerViewProfile.setScrollBarSize(20);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewProfile.setLayoutManager(layoutManager);

        radioSurvey = (RadioButton) view.findViewById(R.id.radioSurvey);
        radioUpdateSurvey = (RadioButton) view.findViewById(R.id.radioUpdateSurvey);


        scListCustomAdapter = new SCListCustomAdapter(getActivity(), sponsoredChildInfoArrayList, ResponseCode.RESPONSE_CODE_ALL_SC_LIST, recyclerViewProfile);
        recyclerViewProfile.setAdapter(scListCustomAdapter);

        prepareActionBar(toolbar);

        ResponseCode.addEventListener.addEvent(new AddListener() {
            @Override
            public void getEvent(Object data) {
                if (data.toString().equals("a")) {

                    sponsoredChildInfoArrayList = databaseManager.getSCListByCommunityId(communityId);
                    scListCustomAdapter = new SCListCustomAdapter(getActivity(), sponsoredChildInfoArrayList, ResponseCode.RESPONSE_CODE_ALL_SC_LIST, recyclerViewProfile);
                    recyclerViewProfile.setAdapter(scListCustomAdapter);
                }
            }
        });


        radioSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioSurvey.isChecked()) {

                    for (int i = 0; i < sponsoredChildInfoArrayList.size(); i++) {
                        if (sponsoredChildInfoArrayList.get(i) != null && !TextUtils.isEmpty(sponsoredChildInfoArrayList.get(i).getJoining_date()) && (sponsoredChildInfoArrayList.get(i).getJoining_date() != null)) {
                            reminderMonths = Operation.joiningDateDifference(getActivity(), sponsoredChildInfoArrayList.get(i).getJoining_date());
                            if (reminderMonths <= 9) {
                                sCSurveyModeList.add(sponsoredChildInfoArrayList.get(i));
                                scListCustomAdapter = new SCListCustomAdapter(getActivity(), sCSurveyModeList, ResponseCode.RESPONSE_CODE_ALL_SC_LIST, recyclerViewProfile);
                                recyclerViewProfile.setAdapter(scListCustomAdapter);
                            }
                        }
                    }
                }
            }
        });

        radioUpdateSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioUpdateSurvey.isChecked()) {

                    for (int i = 0; i < sponsoredChildInfoArrayList.size(); i++) {
                        if (sponsoredChildInfoArrayList.get(i) != null && !TextUtils.isEmpty(sponsoredChildInfoArrayList.get(i).getJoining_date()) && (sponsoredChildInfoArrayList.get(i).getJoining_date() != null)) {
                            reminderMonths = Operation.joiningDateDifference(getActivity(), sponsoredChildInfoArrayList.get(i).getJoining_date());
                            if (reminderMonths >= 9) {
                                sCUpdateSurveyModeList.add(sponsoredChildInfoArrayList.get(i));
                                scListCustomAdapter = new SCListCustomAdapter(getActivity(), sCUpdateSurveyModeList, ResponseCode.RESPONSE_CODE_ALL_SC_LIST, recyclerViewProfile);
                                recyclerViewProfile.setAdapter(scListCustomAdapter);
                            }
                        }
                    }
                }
            }
        });


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(isSearch ? R.menu.menu_search_toolbar : R.menu.menu_main, menu);
        if (isSearch) {
            inflater.inflate(R.menu.menu_search_toolbar, menu);
            final SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
            search.setIconified(false);
            search.setQueryHint("Search ID...");

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    scListCustomAdapter.getFilter().filter(s);
                    return true;
                }
            });
            search.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    closeSearch();
                    return true;
                }
            });

        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search: {
                isSearch = true;
                searchToolbar.setVisibility(View.VISIBLE);
                prepareActionBar(searchToolbar);
                ((AppCompatActivity) getActivity()).supportInvalidateOptionsMenu();
                return true;
            }
            case android.R.id.home:
                closeSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void closeSearch() {
        if (isSearch) {
            isSearch = false;
            prepareActionBar(toolbar);
            searchToolbar.setVisibility(View.GONE);
            ((AppCompatActivity) getActivity()).supportInvalidateOptionsMenu();

        }
    }

    private void prepareActionBar(Toolbar toolbar) {

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void refreshList() {

        sponsoredChildInfoArrayList = databaseManager.getSCListByCommunityId(communityId);
        scListCustomAdapter = new SCListCustomAdapter(getActivity(), sponsoredChildInfoArrayList, ResponseCode.RESPONSE_CODE_ALL_SC_LIST, recyclerViewProfile);
        recyclerViewProfile.setAdapter(scListCustomAdapter);

    }

}
