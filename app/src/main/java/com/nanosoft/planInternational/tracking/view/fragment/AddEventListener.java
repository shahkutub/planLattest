package com.nanosoft.planInternational.tracking.view.fragment;

import java.util.ArrayList;

/**
 * Created by NanoSoft on 3/13/2017.
 */

interface AddListener{
    void getEvent(Object data);
}

public class AddEventListener {

    ArrayList<AddListener> eventList = new ArrayList<AddListener>();

    public void addEvent (AddListener data){
        eventList.add(data);
    }

    public void setEvent(Object data){

        for (AddListener addListener : eventList ){
            addListener.getEvent(data);
        }
    }
}
