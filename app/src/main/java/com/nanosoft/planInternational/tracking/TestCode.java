package com.nanosoft.planInternational.tracking;

/**
 * Created by Nanosoft-Android on 8/7/2017.
 */

public class TestCode {


    public static void  main( String[]args){
        System.out.println("INSERT INTO [sc_survey_complete]\n" +
                "([scnumber]\n" +
                ",[surveyid]\n" +
                ",[completed])\n" +
                "\tVALUES\n" +
                "(?\n" +
                ",?\n" +
                ",1)");
    }
}
