package com.nanosoft.planInternational.tracking.utility;

import com.nanosoft.planInternational.tracking.database.model.QuestionAnswer;
import com.nanosoft.planInternational.tracking.view.fragment.AddEventListener;

import java.util.ArrayList;

/**
 * Created by NanoSoft on 3/30/2017.
 */

public class ResponseCode {

    public static ArrayList<QuestionAnswer> FINAL_RESULT = new ArrayList<>();

    public static AddEventListener addEventListener = new AddEventListener();

    public static final String TEXT_VIEW = "textView";
    public static final String EDIT_TEXT = "7";
    public static final String EDIT_TEXT_SINGLE = "3";
    public static final String EDIT_TEXT_COMMENT_BOX = "4";
    public static final String SPINNER = "2";
    public static final String CHECK_BOX = "1";
    public static final String RADIO_GROUP = "9";
    public static final String STAR_RATING = "6";
    public static final String RANKING = "5";
    public static final String DATE_PICKER = "datePicker";
    public static final String TIME_PICKER = "timePicker";
    public static final String DATE_TIME_PICKER = "8";

    public static final int RESPONSE_CODE_ALL_SC_LIST = 101;
    public static final int RESPONSE_CODE_TO_BE_SCHEDULE = 102;
    public static final int RESPONSE_CODE_SC_SCHEDULED = 103;



}
