package com.nanosoft.planInternational.tracking.database.model;

import java.util.ArrayList;

/**
 * Created by Nanosoft-Android on 4/29/2017.
 */

public class QuestionModel {

    private int questionId;
    private int generalId;
    private int surveyId;
    private int childId;
    private int projectId;

    private String question;
    private String questionType;

    private String questionFilter;
    private String otherEditTextFlag;

    private ArrayList<String> optionValueList;

}
