package com.nanosoft.planInternational.tracking.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanosoft.planInternational.tracking.R;

/**
 * Created by Nanosoft-Android on 4/11/2017.
 */

public class MapFragment extends Fragment {


    public MapFragment() {
        // Required empty public constructor
    }


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);
        initialize();

        return view;
    }

    private void initialize() {


    }

}
