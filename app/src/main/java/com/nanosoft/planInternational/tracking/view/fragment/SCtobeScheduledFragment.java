package com.nanosoft.planInternational.tracking.view.fragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.adapter.SCListCustomAdapter;
import com.nanosoft.planInternational.tracking.database.helper.DatabaseAssetHelper;
import com.nanosoft.planInternational.tracking.database.manager.DatabaseManager;
import com.nanosoft.planInternational.tracking.database.model.ScInfoModel;
import com.nanosoft.planInternational.tracking.utility.ResponseCode;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.nanosoft.planInternational.tracking.model.SponsoredChildInfo.mySelectedList;

/**
 * Created by Nanosoft-Android on 4/11/2017.
 */

public class SCtobeScheduledFragment extends Fragment {

    public SCtobeScheduledFragment() {
    }

    private DatabaseManager databaseManager;

    private View view;
    private Button submitBtn;
    private RecyclerView recyclerViewProfile;
    private ArrayList<ScInfoModel> sponsoredChildInfoArrayList;
    private SCListCustomAdapter scListCustomAdapter;

    private DatabaseAssetHelper mDBHelper;
    public static int communityId ;


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
        view = inflater.inflate(R.layout.fragment_tobe_scheduled, container, false);
        initialize();

        return view;
    }

    private void initialize() {



        toolbar = (Toolbar) view.findViewById(R.id.toolbar_viewpager);
        searchToolbar = (Toolbar) view.findViewById(R.id.toolbar_search);


        databaseManager = new DatabaseManager(getActivity());
        recyclerViewProfile = (RecyclerView) view.findViewById(R.id.recyclerViewProfile);
        submitBtn = (Button) view.findViewById(R.id.submitBtn) ;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewProfile.setLayoutManager(layoutManager);

        mDBHelper = new DatabaseAssetHelper(getActivity());
        sponsoredChildInfoArrayList = new ArrayList<>();


       // sponsoredChildInfoArrayList = databaseManager.getAllSCList();
        sponsoredChildInfoArrayList = databaseManager.getPrioritySCList(communityId,"0");
       // sponsoredChildInfoArrayList = mDBHelper.getAllScList();
      //  sponsoredChildInfoArrayList = filter(mDBHelper.getAllScList());
       // sponsoredChildInfoArrayList = filter(databaseManager.getAllSCList());
       // sponsoredChildInfoArrayList = scListCustomAdapter.getCheckBoxList();
       // sponsoredChildInfoArrayList = scListCustomAdapter.getCheckBoxList();
        scListCustomAdapter = new SCListCustomAdapter(getActivity(), sponsoredChildInfoArrayList, ResponseCode.RESPONSE_CODE_TO_BE_SCHEDULE,recyclerViewProfile,submitBtn);

        recyclerViewProfile.setAdapter(scListCustomAdapter);
        prepareActionBar(toolbar);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePick();

            }
        });


        ResponseCode.addEventListener.addEvent(new AddListener() {
            @Override
            public void getEvent(Object data) {
                if (data.toString().equals("b")){
                    sponsoredChildInfoArrayList = databaseManager.getPrioritySCList(communityId,"0");
                    scListCustomAdapter = new SCListCustomAdapter(getActivity(), sponsoredChildInfoArrayList, ResponseCode.RESPONSE_CODE_TO_BE_SCHEDULE,recyclerViewProfile,submitBtn);
                    recyclerViewProfile.setAdapter(scListCustomAdapter);
                }
            }
        });


    }

    private ArrayList<ScInfoModel> filter(ArrayList<ScInfoModel> allSCList) {
        for (ScInfoModel p : mySelectedList) {
            boolean contains = false;
            ScInfoModel personToRemove = null;
            for (ScInfoModel pp : allSCList) {
                if (pp.getScInfoScNumber() == p.getScInfoScNumber()) {
                    contains = true;
                    personToRemove = pp;
                    break;
                }
            }
            if (contains)
                allSCList.remove(personToRemove);
        }
        return allSCList;
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

    private void datePick() {

        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                DateFormat dateFormat = new java.text.SimpleDateFormat("dd-MMM-yyyy");
               // ScInfoModel.addToSelectedItemsList(scListCustomAdapter.getSelectedPeople());
                String dt = dateFormat.format(myCalendar.getTime());
/*
                for (ScInfoModel p : mySelectedList) {
                    p.dateTime = dt;
                    sponsoredChildInfoArrayList.remove(p);
                }*/

                for (int i = 0; i<SCListCustomAdapter.cbList.size();i++){
                    int id = SCListCustomAdapter.cbList.get(i).getId();
                    databaseManager.priorityUpdate(id,"1",dt);
                }
                SCListCustomAdapter.cbList.clear();
                sponsoredChildInfoArrayList = databaseManager.getPrioritySCList(communityId,"0");
                scListCustomAdapter = new SCListCustomAdapter(getActivity(), sponsoredChildInfoArrayList, ResponseCode.RESPONSE_CODE_TO_BE_SCHEDULE,recyclerViewProfile,submitBtn);
                recyclerViewProfile.setAdapter(scListCustomAdapter);
                scListCustomAdapter.notifyDataSetChanged();

                submitBtn.setVisibility(View.GONE);
                //Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
              //  startActivity(new Intent(MainActivity.this, AnotherListViewActivity.class));
             //   startActivity(new Intent(getActivity(), ScheduledSCFragment.class));
              //  Intent intent = new Intent(view.getContext(), ScheduledSCFragment.class);
               // view.getContext().startActivity(intent);
               // getActivity().finish();
            }

        };

        DatePickerDialog datePickerDialog= new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}
