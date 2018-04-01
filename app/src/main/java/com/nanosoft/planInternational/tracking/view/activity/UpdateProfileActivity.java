package com.nanosoft.planInternational.tracking.view.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.database.manager.DatabaseManager;
import com.nanosoft.planInternational.tracking.database.manager.QuestioneryManager;
import com.nanosoft.planInternational.tracking.database.model.SCFamilyInfos;
import com.nanosoft.planInternational.tracking.database.model.ScGeneralQuestionAnswer;
import com.nanosoft.planInternational.tracking.database.model.ScInfoModel;
import com.nanosoft.planInternational.tracking.receiver.ConnectivityReceiver;
import com.nanosoft.planInternational.tracking.utility.AppConstant;
import com.nanosoft.planInternational.tracking.utility.LocationMgr;
import com.nanosoft.planInternational.tracking.utility.Operation;
import com.nanosoft.planInternational.tracking.utility.ScRelation;
import com.nanosoft.planInternational.tracking.utility.UploadToServer;
import com.nanosoft.planInternational.tracking.utility.custom_font.MyTextView;
import com.rey.material.widget.FrameLayout;
import com.rey.material.widget.ImageButton;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nanosoft.planInternational.tracking.R.id.rdbtnNotvaccination;

public class UpdateProfileActivity extends AppCompatActivity {

    private LinearLayout familyInfoUpdateLayout, addFamilyInfoLayout;


    private View view;

    private CheckBox ckbxMath,ckbxScience,ckbxHistory,ckbxGeography,ckbxLanguages,ckbxPE,ckbxDrawing,ckbxMusic,ckbxReligious,ckbxHomeEconomics,ckbxDrama,ckbxTechnology,ckbxReading,ckbxMotherTongue,ckbxPlay,ckbxSchoolClubs;

    int familyMember;

    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> nameKnownList = new ArrayList<>();
    ArrayList<String> birthList = new ArrayList<>();
    ArrayList<String> relationList = new ArrayList<>();
    ArrayList<String> relationSpList = new ArrayList<>();
    ArrayList<String> occupationList = new ArrayList<>();
    ArrayList<String> occupationSpList = new ArrayList<>();
    ArrayList<String> careerList = new ArrayList<>();

    ArrayList<String> genderList = new ArrayList<>();
    ArrayList<String> reasonForJoinList = new ArrayList<>();

    ArrayList<EditText> nameListEt = new ArrayList<>();
    ArrayList<EditText> birthListEt = new ArrayList<>();
    ArrayList<EditText> relationListEt = new ArrayList<>();

    ArrayList<Spinner> relationListSpinner = new ArrayList<>();
    ArrayList<EditText> occupationListEt = new ArrayList<>();
    ArrayList<Spinner> occupationListSpinner = new ArrayList<>();
    ArrayList<EditText> careerListEt = new ArrayList<>();
    ArrayList<EditText> nameKnownListEt = new ArrayList<>();
    ArrayList<EditText> genderListEt = new ArrayList<>();
    ArrayList<EditText> reasonForJoinListEt = new ArrayList<>();


    ArrayList<ImageButton> deleteBtn = new ArrayList<>();

    private EditText[] nameEt, birthEt, relationEt, occupationEt, careerEt;

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "SCProfileUpdateFragment";
    private ImageView scProfleEdit, imgAddMember, imageView3;
    private ImageView usericon;
    private Button scdobBTN;

    //INTERVIEWER ADDRESS
    private RadioGroup radioGroupReasonNotLivingWithSC, radioGroupSameHouseholdTweleveMonthAgo, radioGroupWhynotRegistered,
            radioGroupWhereRegistered, radioGroupHasRegisteredBirth, radioGroupInterviewerIs, radioGroupQuestionType, radioGroupRelation;
    private RadioButton radioBtnRegisteredShow, radioBtnRegisteredNotShow;

    private LinearLayout layoutwhereRegisteredradiogrp, layoutwhereRegistered, layoutnotRegistered, layoutnotRegisteredrdg;
    private TextInputLayout textInputLayoutnotRegistered, textInputLayoutRegistered;


    private CheckBox checkboxPragnent;
    private EditText etInterviwerName, etIntervieweeName, etIntervieweeRelationship, etOthersRegistered, etOthersNotRegistered;
    private Button btnDateOfInterView;
    private String reasonNotLivingWithSC, sameHouseholdTweleveMonthAgo, whynotRegistered, whereRegistered, hasRegisteredBirth, interviewerIs, questionType, pragnent,
            interviwerName, intervieweeName, intervieweeRelationship, othersRegistered, othersNotRegistered, DateOfInterView;

    private TextView tvscId;

    //INTERVIEWER ADDRESS


    //HEALTH
    private RadioGroup radioGroupMMRInjection, radioGroupIfnotWhyNot, radioGroupSeekTreatment,
            radioGroupHealthIssue, radioGroupExperienceHealthIssue, radioGroupSCRecovered, radioGroupNotSeekTreatment,radioGroupImpairments;
    private EditText etOthersIssue;
    private String mmrInjection, IfnotWhyNot, SeekTreatment, HealthIssue, SCRecovered, ExperienceHealthIssue, NotSeekTreatment, impirement, othersIssue;
    private LinearLayout layoutNotseekingtreatment, layoutNotseekingtreatmentrdp, layouthealthroot, layoutYesHealtIssuerdgp, layoutSCRecovered, layoutSCRecoveredrdbtn, layoutseektreatment, layoutseektreatmentrdbtn, layoutYesHealtIssue, layoutnotwhynot, layoutnotwhynotrdgp;
    private RadioButton rdbtnYes;
    private TextInputLayout textInputOthershealthissue;


    //HEALTH


    //EDUCATION
    private RadioGroup radioGroupFormalEducation, radioGroupTypeOfFormalEducation, radioGroupBoardingSchool, radioGroupNotAttendingFormalEducation,
            radioGroupNonFormalEducation, radioGroupTypeNonFormalEducation, radioGroupTimeTakeGoSchool;
    private String formalEducation, typeOfFormalEducation, boardingSchool, notAttendingFormalEducation, nonFormalEducation, typeNonFormalEducation, timeTakeGoSchool, SCFavouriteActivity;
    private LinearLayout layouttypeofFormaleducation, layouttypeofFormaleducationrgdp, layoutbordingschool, layoutbordingschoolrgdp, layoutwhyattendformaleducation, layoutwhyattendformaleducationrgdp, layoutTypeofnonformaleducation, layoutTypeofnonformaleducationrgdp;
    //EDUCATION


    //HOUSEHOLD AND HOUSING
    private RadioGroup radioGroupTimeofCollectWater, radioGroupHouseholdDrinkingWater, radioGroupToiletFacilities, radioGroupHouseholdElectricity, radioGroupFuelCooking, radioGroupMainMaterialFloor, radioGroupMainMaterial, radioGroupWallMaterial;
    private String timeOfCollectWater, houseHoldDrinkingtWater, toiletFacilities, householdElectricity, fuelCooking, mainMaterialFloor, mainMaterial, wallMaterial;

    //HOUSEHOLD AND HOUSING


    //COMMITMENT

    RadioGroup radioGroupGeneralNews, radioGroupProtectionAgainstAbuse, radioGroupWhoProvides, radioGroupSatisfiedBenefit, radioGroupSCInformed;
    EditText etGeneralNews, etProvidesService, etHappyness;
    String generalNews, protectionAgainstAbusse, whoProvides, satisfiedBenefit, sCInformed, generalNewsOther, providesService, happynessYes;
    private LinearLayout layoutprovideyesservice, layoutprovideyesservicergdp, layoutReasonforHappy, layoutReasonforHappyrdgd, layoutReasonforNotHappy, layoutReasonforNotHappyrdg;
    private TextInputLayout textinputlayoutyesservice, textinputlayoutreason;

    //COMMITMENT

    //PHOTO
    ImageView imvSisterPhoto, imvBrotherPhoto, imvFatherPhoto, imvMotherPhoto, imvGrandFatherPhoto, imvGrandMotherPhoto;
    //PHOTO


    //Expandable layout



    ExpandableLayout expandableLayout1, expandableLayout2, expandableLayout3,

    expandableLayout4, expandableLayout5, expandableLayout6;


   /* ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3,
            expandableLayout4, expandableLayout5, expandableLayout6;*/
   private RecyclerView recyclerView;
    private FamalyInfoAdapter mAdapter;

    private int scInfoTableId;
    private int scInfoNumber;
    private ArrayList<SCFamilyInfos> scFamilyInfosesList;
    private DatabaseManager databaseManager;
    private int currentImageViewId;
    private RadioGroup radioGroupPragnent;
    private MyTextView tvLatLng;
    private ScInfoModel scInfoModel;

    private List<ScRelation> listScRelations = new ArrayList<>();
    List <String> relations = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        scInfoTableId = getIntent().getExtras().getInt("SCINFOTABLEID");
        initialize();

        listScRelations = AppConstant.loadSharedPreferencesRelationList(getApplicationContext());

        for (int i = 0; i <listScRelations.size() ; i++) {
            relations.add(listScRelations.get(i).getName());
        }


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.MyCustomClass() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private String addSC = "1";


    String interviewDate;

    private void initialize() {


        tvLatLng = (MyTextView) findViewById(R.id.tvLatLng);
        mgr = new LocationMgr(this, tvLatLng);
/*HEALTH*/
        layoutNotseekingtreatment = (LinearLayout) findViewById(R.id.layoutNotseekingtreatment);
        layoutNotseekingtreatmentrdp = (LinearLayout) findViewById(R.id.layoutNotseekingtreatmentrdp);

        familyInfoUpdateLayout = (LinearLayout) findViewById(R.id.familyInfoUpdateLayout);
        addFamilyInfoLayout = (LinearLayout) findViewById(R.id.addFamilyInfoLayout);
        scProfleEdit = (ImageView) findViewById(R.id.scProfleEdit);
        imgAddMember = (ImageView) findViewById(R.id.imgAddMember);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imvSisterPhoto = (ImageView) findViewById(R.id.imvSisterPhoto);
        imvBrotherPhoto = (ImageView) findViewById(R.id.imvBrotherPhoto);
        imvFatherPhoto = (ImageView) findViewById(R.id.imvFatherPhoto);
        imvGrandFatherPhoto = (ImageView) findViewById(R.id.imvGrandFatherPhoto);
        imvMotherPhoto = (ImageView) findViewById(R.id.imvMotherPhoto);
        imvGrandMotherPhoto = (ImageView) findViewById(R.id.imvGrandMotherPhoto);
        usericon = (ImageView) findViewById(R.id.usericon);

        databaseManager = new DatabaseManager(this);
        scInfoModel = databaseManager.getScProfile(scInfoTableId);

        scFamilyInfosesList = new ArrayList<>();
        scInfoNumber = databaseManager.getScIdByTableId(scInfoTableId);
        scFamilyInfosesList = databaseManager.getFamilyInfo(scInfoTableId);

        familyMember = scFamilyInfosesList.size();

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Operation.currentImageViewId = usericon.getId();
                Operation.currentPhotoQuestionId = "100";
                openImageChooser();
            }
        });


        //EXPANDABLE LAYOUT
        expandableLayout1 = (ExpandableLayout) findViewById(R.id.expandableLayout1);
        expandableLayout2 = (ExpandableLayout) findViewById(R.id.expandableLayout2);
        expandableLayout3 = (ExpandableLayout) findViewById(R.id.expandableLayout3);
        expandableLayout4 = (ExpandableLayout) findViewById(R.id.expandableLayout4);
        expandableLayout5 = (ExpandableLayout) findViewById(R.id.expandableLayout5);

        setArrayListValue();

        scProfleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveData2();
            }
        });

        imgAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SCFamilyInfos scFamilyInfos = new SCFamilyInfos();
                scFamilyInfosesList.add(scFamilyInfos);
                famaliInfo();
                //addMember1();
            }
        });

        if (addSC.equals("1")) {
            setData2();
        }

        //INTERVIEWER ADDRESS
        radioGroupReasonNotLivingWithSC = (RadioGroup) findViewById(R.id.radioGroupReasonNotLivingWithSC);
        radioGroupSameHouseholdTweleveMonthAgo = (RadioGroup) findViewById(R.id.radioGroupSameHouseholdTweleveMonthAgo);
        radioGroupWhynotRegistered = (RadioGroup) findViewById(R.id.radioGroupWhynotRegistered);
        radioGroupWhereRegistered = (RadioGroup) findViewById(R.id.radioGroupWhereRegistered);
        radioGroupHasRegisteredBirth = (RadioGroup) findViewById(R.id.radioGroupHasRegisteredBirth);
        radioGroupPragnent = (RadioGroup) findViewById(R.id.radioGroupPragnent);
        tvscId = (TextView) findViewById(R.id.tvscId);


        radioGroupHasRegisteredBirth.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                layoutnotRegistered = (LinearLayout) findViewById(R.id.layoutnotRegistered);
                layoutnotRegisteredrdg = (LinearLayout) findViewById(R.id.layoutnotRegisteredrdg);
                layoutwhereRegisteredradiogrp = (LinearLayout) findViewById(R.id.layoutwhereRegisteredradiogrp);
                layoutwhereRegistered = (LinearLayout) findViewById(R.id.layoutwhereRegistered);
                textInputLayoutRegistered = (TextInputLayout) findViewById(R.id.textInputLayoutRegistered);
                textInputLayoutnotRegistered = (TextInputLayout) findViewById(R.id.textInputLayoutnotRegistered);
                if (i == R.id.radioBtnRegisteredShow || i == R.id.radioBtnRegisteredNotShow) {


                    SetAllRadioUnchecked(R.id.radioGroupWhynotRegistered);
                    //  layoutnotRegistered.setVisibility(View.INVISIBLE);
                    //  layoutnotRegisteredrdg.setVisibility(View.INVISIBLE);
                    SetAllRadioUnchecked(R.id.radioGroupBoardingSchool);
                    layoutnotRegistered.setVisibility(View.GONE);
                    layoutnotRegisteredrdg.setVisibility(View.GONE);
                    textInputLayoutnotRegistered.setVisibility(View.GONE);
                    textInputLayoutRegistered.setVisibility(View.GONE);

                    layoutwhereRegistered.setVisibility(View.VISIBLE);
                    layoutwhereRegisteredradiogrp.setVisibility(View.VISIBLE);
                    radioGroupWhereRegistered.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                            if (i == R.id.rdbtnOtherRegister) {
                                textInputLayoutRegistered.setVisibility(View.VISIBLE);
                            } else {
                                textInputLayoutRegistered.setVisibility(View.GONE);
                            }
                        }
                    });

                } else if (i == R.id.rdbtnNotregistered || i == R.id.rdbtnNotKnow) {

                    SetAllRadioUnchecked(R.id.radioGroupWhereRegistered);
                    layoutwhereRegistered.setVisibility(View.GONE);
                    layoutwhereRegisteredradiogrp.setVisibility(View.GONE);
                    textInputLayoutRegistered.setVisibility(View.GONE);
                    textInputLayoutnotRegistered.setVisibility(View.GONE);

                    layoutnotRegistered.setVisibility(View.VISIBLE);
                    layoutnotRegisteredrdg.setVisibility(View.VISIBLE);
                    radioGroupWhynotRegistered.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                            if (i == R.id.rdbtnOtherNotRegistered) {
                                textInputLayoutnotRegistered.setVisibility(View.VISIBLE);
                            } else {
                                textInputLayoutnotRegistered.setVisibility(View.GONE);
                            }
                        }
                    });

                }
            }
        });


        radioGroupInterviewerIs = (RadioGroup) findViewById(R.id.radioGroupInterviewerIs);
        radioGroupQuestionType = (RadioGroup) findViewById(R.id.radioGroupQuestionType);
        radioGroupRelation = (RadioGroup) findViewById(R.id.radioGroupRelation);


        radioGroupQuestionType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.scurddbtn) {
                    tvscId.setVisibility(View.VISIBLE);
                    tvscId.setText("Sponsored Child ID: " + scInfoNumber + "");
                } else {
                    tvscId.setVisibility(View.GONE);
                }
            }
        });

//        checkboxPragnent = (CheckBox) findViewById(R.id.checkboxPragnent);

        etInterviwerName = (EditText) findViewById(R.id.etInterviwerName);
        etIntervieweeName = (EditText) findViewById(R.id.etIntervieweeName);
        etIntervieweeRelationship = (EditText) findViewById(R.id.etIntervieweeRelationship);
        etOthersRegistered = (EditText) findViewById(R.id.etOthersRegistered);
        etOthersNotRegistered = (EditText) findViewById(R.id.etOthersNotRegistered);


        btnDateOfInterView = (Button) findViewById(R.id.btnDateOfInterView);
        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                DateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
                interviewDate = dateFormat.format(myCalendar.getTime());
                btnDateOfInterView.setText(interviewDate);
            }
        };
        final DatePickerDialog d = new DatePickerDialog(this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));


        btnDateOfInterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.show();
            }
        });


        radioBtnRegisteredShow = (RadioButton) findViewById(R.id.radioBtnRegisteredShow);
        radioBtnRegisteredNotShow = (RadioButton) findViewById(R.id.radioBtnRegisteredNotShow);


        //INTERVIEWER ADDRESS

//HEALTH
        radioGroupMMRInjection = (RadioGroup) findViewById(R.id.radioGroupMMRInjection);
        radioGroupIfnotWhyNot = (RadioGroup) findViewById(R.id.radioGroupIfnotWhyNot);
        radioGroupSeekTreatment = (RadioGroup) findViewById(R.id.radioGroupSeekTreatment);
        radioGroupHealthIssue = (RadioGroup) findViewById(R.id.radioGroupHealthIssue);
        radioGroupExperienceHealthIssue = (RadioGroup) findViewById(R.id.radioGroupExperienceHealthIssue);
        radioGroupSCRecovered = (RadioGroup) findViewById(R.id.radioGroupSCRecovered);
        radioGroupNotSeekTreatment = (RadioGroup) findViewById(R.id.radioGroupNotSeekTreatment);
        radioGroupImpairments = (RadioGroup) findViewById(R.id.radioGroupImpairments);
        etOthersIssue = (EditText) findViewById(R.id.etOthersIssue);


        //HEALTH

        radioGroupMMRInjection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                layoutnotwhynot = (LinearLayout) findViewById(R.id.layoutnotwhynot);
                layoutnotwhynotrdgp = (LinearLayout) findViewById(R.id.layoutnotwhynotrdgp);
                if (i == rdbtnNotvaccination) {
                    layoutnotwhynot.setVisibility(View.VISIBLE);
                    layoutnotwhynotrdgp.setVisibility(View.VISIBLE);
                } else {
                    SetAllRadioUnchecked(R.id.radioGroupIfnotWhyNot);
                    layoutnotwhynot.setVisibility(View.GONE);
                    layoutnotwhynotrdgp.setVisibility(View.GONE);
                }

            }
        });


        radioGroupHealthIssue.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                layoutYesHealtIssue = (LinearLayout) findViewById(R.id.layoutYesHealtIssue);
                layoutYesHealtIssuerdgp = (LinearLayout) findViewById(R.id.layoutYesHealtIssuerdgp);
                layoutSCRecovered = (LinearLayout) findViewById(R.id.layoutSCRecovered);
                layoutSCRecoveredrdbtn = (LinearLayout) findViewById(R.id.layoutSCRecoveredrdbtn);
                layoutseektreatment = (LinearLayout) findViewById(R.id.layoutseektreatment);
                layoutseektreatmentrdbtn = (LinearLayout) findViewById(R.id.layoutseektreatmentrdbtn);

                textInputOthershealthissue = (TextInputLayout) findViewById(R.id.textInputOthershealthissue);

                if (i == R.id.rdbtnYes) {

                    SetAllRadioUnchecked(R.id.radioGroupExperienceHealthIssue);
                    SetAllRadioUnchecked(R.id.radioGroupSCRecovered);
                    SetAllRadioUnchecked(R.id.radioGroupSeekTreatment);
                    SetAllRadioUnchecked(R.id.radioGroupNotSeekTreatment);

                    layoutYesHealtIssue.setVisibility(View.VISIBLE);
                    layoutYesHealtIssuerdgp.setVisibility(View.VISIBLE);
                    layoutSCRecovered.setVisibility(View.VISIBLE);
                    layoutSCRecoveredrdbtn.setVisibility(View.VISIBLE);
                    layoutseektreatment.setVisibility(View.VISIBLE);
                    layoutseektreatmentrdbtn.setVisibility(View.VISIBLE);
                    textInputOthershealthissue.setVisibility(View.GONE);

                    radioGroupExperienceHealthIssue.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                            if (i == R.id.rdbtnOtherHealthIssue) {
                                textInputOthershealthissue.setVisibility(View.VISIBLE);
                            } else {
                                textInputOthershealthissue.setVisibility(View.GONE);
                            }
                        }
                    });

                    layoutNotseekingtreatment.setVisibility(View.GONE);
                    layoutNotseekingtreatmentrdp.setVisibility(View.GONE);

                } else {

                    layoutYesHealtIssue.setVisibility(View.GONE);
                    layoutYesHealtIssuerdgp.setVisibility(View.GONE);
                    textInputOthershealthissue.setVisibility(View.GONE);
                    layoutSCRecovered.setVisibility(View.GONE);
                    layoutSCRecoveredrdbtn.setVisibility(View.GONE);
                    layoutseektreatment.setVisibility(View.GONE);
                    layoutseektreatmentrdbtn.setVisibility(View.GONE);
                    layoutNotseekingtreatment.setVisibility(View.GONE);
                    layoutNotseekingtreatmentrdp.setVisibility(View.GONE);
                }
            }
        });


        radioGroupImpairments.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {

                LinearLayout layCbxImp = (LinearLayout)findViewById(R.id.layCbxImp);
                LinearLayout lay3ten = (LinearLayout)findViewById(R.id.lay3ten);

                if (id == R.id.impBtnYes) {
                    layCbxImp.setVisibility(View.VISIBLE);
                    lay3ten.setVisibility(View.VISIBLE);
                }else {
                    layCbxImp.setVisibility(View.GONE);
                    lay3ten.setVisibility(View.GONE);

                }

            }
        });

        radioGroupSeekTreatment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
           /*     layoutNotseekingtreatment = (LinearLayout) findViewById(R.id.layoutNotseekingtreatment);
                layoutNotseekingtreatmentrdp = (LinearLayout) findViewById(R.id.layoutNotseekingtreatmentrdp);*/
                layouthealthroot = (LinearLayout) findViewById(R.id.layouthealthroot);
                if (i == R.id.rbtnNotTakingTretment) {

                    SetAllRadioUnchecked(R.id.radioGroupNotSeekTreatment);

                    layoutNotseekingtreatment.setVisibility(View.VISIBLE);
                    layoutNotseekingtreatmentrdp.setVisibility(View.VISIBLE);
                } else if (i == R.id.rdbtnhospital || i == R.id.rdbtntraditionalhealer || i == R.id.rdbtnmobileHealtyh || i == R.id.rdbtnpharmacy || i == R.id.rdbtnhealthcenter || i == R.id.rdbtnhealthworker || i == R.id.rdbtnprivateDoctor) {
                    layoutNotseekingtreatment.setVisibility(View.GONE);
                    layoutNotseekingtreatmentrdp.setVisibility(View.GONE);
                }
            }
        });

//HEALTH

        //EDUCATION
        radioGroupFormalEducation = (RadioGroup) findViewById(R.id.radioGroupFormalEducation);
        radioGroupTypeOfFormalEducation = (RadioGroup) findViewById(R.id.radioGroupTypeOfFormalEducation);
        radioGroupBoardingSchool = (RadioGroup) findViewById(R.id.radioGroupBoardingSchool);
        radioGroupNotAttendingFormalEducation = (RadioGroup) findViewById(R.id.radioGroupNotAttendingFormalEducation);
        radioGroupNonFormalEducation = (RadioGroup) findViewById(R.id.radioGroupNonFormalEducation);
        radioGroupTypeNonFormalEducation = (RadioGroup) findViewById(R.id.radioGroupTypeNonFormalEducation);
        radioGroupTimeTakeGoSchool = (RadioGroup) findViewById(R.id.radioGroupTimeTakeGoSchool);

//        radioGroupSCFavouriteActivity = (RadioGroup) findViewById(R.id.radioGroupSCFavouriteActivity);
//        //EDUCATION

        radioGroupFormalEducation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                layouttypeofFormaleducation = (LinearLayout) findViewById(R.id.layouttypeofFormaleducation);
                layouttypeofFormaleducationrgdp = (LinearLayout) findViewById(R.id.layouttypeofFormaleducationrgdp);
                layoutbordingschool = (LinearLayout) findViewById(R.id.layoutbordingschool);
                layoutbordingschoolrgdp = (LinearLayout) findViewById(R.id.layoutbordingschoolrgdp);
                layoutwhyattendformaleducation = (LinearLayout) findViewById(R.id.layoutwhyattendformaleducation);
                layoutwhyattendformaleducationrgdp = (LinearLayout) findViewById(R.id.layoutwhyattendformaleducationrgdp);

                if (i == R.id.rdbtnYeseducation) {
                    SetAllRadioUnchecked(R.id.radioGroupBoardingSchool);
                    layouttypeofFormaleducation.setVisibility(View.VISIBLE);
                    layouttypeofFormaleducationrgdp.setVisibility(View.VISIBLE);
                    layoutbordingschool.setVisibility(View.VISIBLE);
                    layoutbordingschoolrgdp.setVisibility(View.VISIBLE);

                    layoutwhyattendformaleducation.setVisibility(View.GONE);
                    layoutwhyattendformaleducationrgdp.setVisibility(View.GONE);
                } else {
                    SetAllRadioUnchecked(R.id.radioGroupTypeOfFormalEducation);
                    layoutwhyattendformaleducation.setVisibility(View.VISIBLE);
                    layoutwhyattendformaleducationrgdp.setVisibility(View.VISIBLE);
                    layouttypeofFormaleducation.setVisibility(View.GONE);
                    layouttypeofFormaleducationrgdp.setVisibility(View.GONE);
                    layoutbordingschool.setVisibility(View.GONE);
                    layoutbordingschoolrgdp.setVisibility(View.GONE);

                }

            }
        });
        radioGroupNonFormalEducation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                layoutTypeofnonformaleducation = (LinearLayout) findViewById(R.id.layoutTypeofnonformaleducation);
                layoutTypeofnonformaleducationrgdp = (LinearLayout) findViewById(R.id.layoutTypeofnonformaleducationrgdp);
                if (i == R.id.rdbtnyesnonformaleducation) {
                    layoutTypeofnonformaleducation.setVisibility(View.VISIBLE);
                    layoutTypeofnonformaleducationrgdp.setVisibility(View.VISIBLE);
                } else {
                    layoutTypeofnonformaleducation.setVisibility(View.GONE);
                    layoutTypeofnonformaleducationrgdp.setVisibility(View.GONE);
                }

            }
        });

//
//        //HOUSEHOLD AND HOUSING
        radioGroupTimeofCollectWater = (RadioGroup) findViewById(R.id.radioGroupTimeofCollectWater);
        radioGroupHouseholdDrinkingWater = (RadioGroup) findViewById(R.id.radioGroupHouseholdDrinkingWater);
        radioGroupToiletFacilities = (RadioGroup) findViewById(R.id.radioGroupToiletFacilities);
        radioGroupHouseholdElectricity = (RadioGroup) findViewById(R.id.radioGroupHouseholdElectricity);
        radioGroupFuelCooking = (RadioGroup) findViewById(R.id.radioGroupFuelCooking);
        radioGroupMainMaterialFloor = (RadioGroup) findViewById(R.id.radioGroupMainMaterialFloor);
        radioGroupMainMaterial = (RadioGroup) findViewById(R.id.radioGroupMainMaterial);
        radioGroupWallMaterial = (RadioGroup) findViewById(R.id.radioGroupWallMaterial);
        //HOUSEHOLD AND HOUSING

//COMMITMENT
        radioGroupGeneralNews = (RadioGroup) findViewById(R.id.radioGroupGeneralNews);
        radioGroupProtectionAgainstAbuse = (RadioGroup) findViewById(R.id.radioGroupProtectionAgainstAbuse);
        radioGroupWhoProvides = (RadioGroup) findViewById(R.id.radioGroupWhoProvides);
        radioGroupSatisfiedBenefit = (RadioGroup) findViewById(R.id.radioGroupSatisfiedBenefit);
        radioGroupSCInformed = (RadioGroup) findViewById(R.id.radioGroupSCInformed);

        etGeneralNews = (EditText) findViewById(R.id.etOtherGeneralNews);
        etProvidesService = (EditText) findViewById(R.id.etProvidesService);
        etHappyness = (EditText) findViewById(R.id.etHappyness);
//COMMITMENT

        radioGroupGeneralNews.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                TextInputLayout textInputOtherGetNews = (TextInputLayout) findViewById(R.id.textInputOtherGetNews);
                if (i == R.id.rdbtntextInputOtherGetNews) {

                    textInputOtherGetNews.setVisibility(View.VISIBLE);
                } else {
                    textInputOtherGetNews.setVisibility(View.GONE);
                }
            }
        });

        radioGroupProtectionAgainstAbuse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                layoutprovideyesservice = (LinearLayout) findViewById(R.id.layoutprovideyesservice);
                layoutprovideyesservicergdp = (LinearLayout) findViewById(R.id.layoutprovideyesservicergdp);
                textinputlayoutreason = (TextInputLayout) findViewById(R.id.textinputlayoutreason);
                textinputlayoutyesservice = (TextInputLayout) findViewById(R.id.textinputlayoutyesservice);
                if (i == R.id.rbtnyesservicecommunity) {
                    layoutprovideyesservice.setVisibility(View.VISIBLE);
                    layoutprovideyesservicergdp.setVisibility(View.VISIBLE);
                    textinputlayoutyesservice.setVisibility(View.GONE);
                    radioGroupWhoProvides.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                            if (i == R.id.rdbtnOtherProvide) {
                                textinputlayoutyesservice.setVisibility(View.VISIBLE);
                            } else {
                                textinputlayoutyesservice.setVisibility(View.GONE);
                            }
                        }
                    });
                } else {
                    layoutprovideyesservice.setVisibility(View.GONE);
                    layoutprovideyesservicergdp.setVisibility(View.GONE);
                    textinputlayoutyesservice.setVisibility(View.GONE);
                }

            }
        });


        radioGroupSatisfiedBenefit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                textinputlayoutreason = (TextInputLayout) findViewById(R.id.textinputlayoutreason);
                layoutReasonforHappy = (LinearLayout) findViewById(R.id.layoutReasonforHappy);
                layoutReasonforHappyrdgd = (LinearLayout) findViewById(R.id.layoutReasonforHappyrdgd);
                layoutReasonforNotHappy = (LinearLayout) findViewById(R.id.layoutReasonforNotHappy);
                layoutReasonforNotHappyrdg = (LinearLayout) findViewById(R.id.layoutReasonforNotHappyrdg);
                /*,,,*/

                if (i == R.id.rdbtnveryhappy || i == R.id.rdbtnhappy) {
                    SetAllRadioUnchecked(R.id.radioGroupReasonforNotHappy);

                    layoutReasonforNotHappy.setVisibility(View.GONE);
                    layoutReasonforNotHappyrdg.setVisibility(View.GONE);
                    layoutReasonforHappy.setVisibility(View.VISIBLE);
                    layoutReasonforHappyrdgd.setVisibility(View.VISIBLE);

                } else if (i == R.id.rdbtnnothappy) {
                    SetAllRadioUnchecked(R.id.radioGroupReasonforHappyness);

                    layoutReasonforNotHappy.setVisibility(View.VISIBLE);
                    layoutReasonforNotHappyrdg.setVisibility(View.VISIBLE);
                    layoutReasonforHappy.setVisibility(View.GONE);
                    layoutReasonforHappyrdgd.setVisibility(View.GONE);
                } else if (i == R.id.rdbtnnotapplicable) {
                    SetAllRadioUnchecked(R.id.radioGroupReasonforHappyness);
                    SetAllRadioUnchecked(R.id.radioGroupReasonforNotHappy);
                    layoutReasonforNotHappy.setVisibility(View.GONE);
                    layoutReasonforNotHappyrdg.setVisibility(View.GONE);
                    layoutReasonforHappy.setVisibility(View.GONE);
                    layoutReasonforHappyrdgd.setVisibility(View.GONE);
                }
            }
        });

        loadSavedData();
        famaliInfo();

    }


    private void famaliInfo() {
        //scFamilyInfosesList = databaseManager.getFamilyInfo(scInfoTableId);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new FamalyInfoAdapter(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public class FamalyInfoAdapter extends RecyclerView.Adapter<FamalyInfoAdapter.MyViewHolder> {

        //private List <SCFamilyInfos> listFamily = new ArrayList<>();
        private Context con;
        SCFamilyInfos familyInfos;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public EditText et3_2, et3_4,et3_5, et3_6,et3_7;
            public Spinner sp3_5,sp3_5_a,sp3_7,sp3_8,sp3_8a,sp3_9;
            ImageView imgDelete;
            LinearLayout layRelation,layOccupation;

            public MyViewHolder(View view) {
                super(view);
                et3_2 = (EditText) view.findViewById(R.id.et3_2);
                et3_4 = (EditText) view.findViewById(R.id.et3_4);
                et3_6 = (EditText) view.findViewById(R.id.et3_6);
                et3_5 = (EditText) view.findViewById(R.id.et3_5);
                et3_7 = (EditText) view.findViewById(R.id.et3_7);

                sp3_5 = (Spinner)view.findViewById(R.id.sp3_5);
                sp3_5_a = (Spinner)view.findViewById(R.id.sp3_5_a);
                sp3_7 = (Spinner)view.findViewById(R.id.sp3_7);
                sp3_8 = (Spinner)view.findViewById(R.id.sp3_8);
                sp3_8a = (Spinner)view.findViewById(R.id.sp3_8a);
                sp3_9 = (Spinner)view.findViewById(R.id.sp3_9);

                imgDelete = (ImageView)view.findViewById(R.id.imgDelete);

                layRelation = (LinearLayout)view.findViewById(R.id.layRelation);
                layOccupation = (LinearLayout)view.findViewById(R.id.layOccupation);

            }
        }

        public FamalyInfoAdapter(Context con) {
            this.con = con;
        }

        public FamalyInfoAdapter(Context con,List<SCFamilyInfos> moviesList) {
            this.con = con;
            //this.listFamily = moviesList;
        }

        @Override
        public FamalyInfoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.raw_family_info, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            familyInfos = scFamilyInfosesList.get(position);

//            final String[] arraysp3_5 = new String[] {
//                    "Select Relationship",
//                    "Mother",
//                    "Father",
//                    "Sister",
//                    "Brother",
//                    "Twin Sister",
//                    "Twin Brother",
//                    "Aunt",
//                    "Uncle",
//                    "Uncle",
//                    "Grandfather",
//                    "Step Mother",
//                    "Step Father",
//                    "Others"
//            };
//
//            final List <String> relations = new ArrayList<>();
//            relations.add("Select Relationship");
//            relations.add("Mother");
//            relations.add("Father");
//            relations.add("Sister");
//            relations.add("Brother");
//            relations.add("Twin Sister");
//            relations.add("Twin Brother");
//            relations.add("Aunt");
//            relations.add("Uncle");
//            relations.add("Grandfather");
//            relations.add("Step Mother");
//            relations.add("Step Father");
//            relations.add("Others");


//            final List <String> wholeft = new ArrayList<>();
//            wholeft.add("Select who left");
//            wholeft.add("Mother");
//            wholeft.add("Father");
//            wholeft.add("Sister");
//            wholeft.add("Brother");
//            wholeft.add("Twin Sister");
//            wholeft.add("Twin Brother");
//            wholeft.add("Aunt");
//            wholeft.add("Uncle");
//            wholeft.add("Grandfather");
//            wholeft.add("Step Mother");
//            wholeft.add("Step Father");



            final List <String> occpations = new ArrayList<>();
            occpations.add("Select Occupation");
            occpations.add("Not Applicable");
            occpations.add("Peasant Farmer");
            occpations.add("Craftsman");
            occpations.add("Street Hawker");
            occpations.add("Policeman");
            occpations.add("Driver");
            occpations.add("Miner");
            occpations.add("Factory Worker");
            occpations.add("Cook");
            occpations.add("Cleaner");

            occpations.add("Shoe Shiner");
            occpations.add("Body Massage");
            occpations.add("Self Employed");
            occpations.add("Working in Private Sector");
            occpations.add("Working forces (military service)");
            occpations.add("Recycling Work");

            occpations.add("Small business owner");
            occpations.add("Migrant/overseas worker");
            occpations.add("Civil defence force");
            occpations.add("Village Doctor");
            occpations.add("Local grape picker");
            occpations.add("Others");


            holder.et3_2.setText(familyInfos.getMember_full_name()+"");
            holder.et3_4.setText(familyInfos.getMember_birth_year()+"");
            holder.et3_6.setText(familyInfos.getMember_is_primary_carer()+"");


            holder.et3_2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    familyInfos.setMember_full_name(s.toString());
                }
            });

            holder.et3_4.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(!TextUtils.isEmpty(s.toString())){
                        familyInfos.setMember_birth_year(Integer.parseInt(s.toString()));
                    }


                }
            });

            holder.et3_5.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {

                    familyInfos.setMember_relationship(s.toString());

                }
            });

            holder.et3_7.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {

                    familyInfos.setMember_occupation(s.toString());

                }
            });

            holder.et3_6.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {

                    familyInfos.setMember_is_primary_carer(s.toString());
                }
            });


//            for (int i = 0; i <relations.size() ; i++) {
//                if(!TextUtils.isEmpty(familyInfos.getMember_relationship())){
//                    if(!familyInfos.getMember_relationship().equalsIgnoreCase(relations.get(i))){
//                        relations.add(familyInfos.getMember_relationship());
//                        break;
//                    }
//                }
//            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(con,
                    R.layout.spinner_item_family, relations);
            holder.sp3_5.setAdapter(adapter);


            for (int i = 0; i <relations.size() ; i++) {
                if(!TextUtils.isEmpty(familyInfos.getMember_relationship())){
                    if(familyInfos.getMember_relationship().equalsIgnoreCase(relations.get(i))){
                        holder.sp3_5.setSelection(i);
                    }
                }
            }

            holder.sp3_5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if(!holder.sp3_5.getSelectedItem().toString().equalsIgnoreCase("Select Relationship")){
                        familyInfos.setMember_relationship(holder.sp3_5.getSelectedItem().toString());
                    }else {

                        familyInfos.setMember_relationship(" ");
//                        holder.layRelation.setVisibility(View.GONE);
//                        holder.et3_5.setVisibility(View.VISIBLE);
                    }



                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            List <String> arraysp3_5_a = new ArrayList<>();
            arraysp3_5_a.add("Select Gender");
            arraysp3_5_a.add("Male");
            arraysp3_5_a.add("Female");

//            String[] arraysp3_5_a = new String[] {
//                    "Select Gender",
//                    "Male",
//                    "Female"
//            };


            ArrayAdapter<String> adapter3_5_a = new ArrayAdapter<String>(con,
                    R.layout.spinner_item_family, arraysp3_5_a);
            holder.sp3_5_a.setAdapter(adapter3_5_a);

            for (int i = 0; i <arraysp3_5_a.size() ; i++) {
                if(!TextUtils.isEmpty(familyInfos.getMember_gender())){
                    if(familyInfos.getMember_gender().equalsIgnoreCase(arraysp3_5_a.get(i)+" ")){
                        holder.sp3_5_a.setSelection(i);
                    }
                }
            }
            holder.sp3_5_a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if(!holder.sp3_5_a.getSelectedItem().toString().equalsIgnoreCase("Select Gender")){
                        familyInfos.setMember_gender(holder.sp3_5_a.getSelectedItem().toString());
                    }else {
                        //familyInfos.setMember_gender(" ");
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });



//            for (int i = 0; i <occpations.size() ; i++) {
//                if(!TextUtils.isEmpty(familyInfos.getMember_occupation())){
//                    if(!familyInfos.getMember_occupation().equalsIgnoreCase(occpations.get(i))){
//                        occpations.add(familyInfos.getMember_occupation());
//                        break;
//                    }
//                }
//            }


            ArrayAdapter<String> adaptersp3_7 = new ArrayAdapter<String>(con,
                    R.layout.spinner_item_family, occpations);
            holder.sp3_7.setAdapter(adaptersp3_7);


            for (int i = 0; i <occpations.size() ; i++) {
                if(!TextUtils.isEmpty(familyInfos.getMember_occupation())){
                    if(familyInfos.getMember_occupation().equalsIgnoreCase(occpations.get(i)+" ")){
                        holder.sp3_7.setSelection(i);
                    }
                }
            }


            holder.sp3_7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(!holder.sp3_7.getSelectedItem().toString().equalsIgnoreCase("Select Occupation")){
                        familyInfos.setMember_occupation(holder.sp3_7.getSelectedItem().toString());
                    }else {
                        //familyInfos.setMember_occupation(" ");
//                        holder.layOccupation.setVisibility(View.GONE);
//                        holder.et3_7.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });





            ArrayAdapter<String> adaptersp3_8 = new ArrayAdapter<String>(con,
                    R.layout.spinner_item_family, relations);
            holder.sp3_8.setAdapter(adaptersp3_8);

            for (int i = 0; i <relations.size() ; i++) {
                if(!TextUtils.isEmpty(familyInfos.getWho_left_household())){
                    if(familyInfos.getWho_left_household().equalsIgnoreCase(relations.get(i)+" ")){
                        holder.sp3_8.setSelection(i);
                    }
                }
            }

            holder.sp3_8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if(!holder.sp3_8.getSelectedItem().toString().equalsIgnoreCase("Select who left")){
                        familyInfos.setWho_left_household(holder.sp3_8.getSelectedItem().toString());
                    }else {
                       // familyInfos.setWho_left_household(" ");
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            List <String> arraysp3_8a = new ArrayList<>();

            arraysp3_8a.add("Select Reason left household");
            arraysp3_8a.add("To seek employment");
            arraysp3_8a.add("To live with relatives");
            arraysp3_8a.add("To study");
            arraysp3_8a.add("Due to Marriage");
            arraysp3_8a.add("Separation from spouse");
            arraysp3_8a.add("For more fertile land");
            arraysp3_8a.add("Family separation");
            arraysp3_8a.add("Due to war");
            arraysp3_8a.add("unknown");
            arraysp3_8a.add("Death");


//            String[] arraysp3_8a = new String[] {
//                    "Select Reason left household",
//                    "To seek employment",
//                    "To live with relatives",
//                    "To study",
//                    "Due to Marriage",
//                    "Separation from spouse",
//                    "For more fertile land",
//                    "For religious instruction",
//                    "Family separation",
//                    "Due to war",
//                    "unknown",
//                    "Death"
//
//            };

            ArrayAdapter<String> adaptersp3_8a = new ArrayAdapter<String>(con,
                    R.layout.spinner_item_family, arraysp3_8a);
            holder.sp3_8a.setAdapter(adaptersp3_8a);

            for (int i = 0; i <arraysp3_8a.size() ; i++) {
                if(!TextUtils.isEmpty(familyInfos.getNo_longer_in_household())){
                    if(familyInfos.getNo_longer_in_household().equalsIgnoreCase(arraysp3_8a.get(i)+" ")){
                        holder.sp3_8a.setSelection(i);
                    }
                }
            }


            holder.sp3_8a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if(!holder.sp3_8a.getSelectedItem().toString().equalsIgnoreCase("Select Reason left household")){
                        familyInfos.setNo_longer_in_household(holder.sp3_8a.getSelectedItem().toString());
                    }else {
                        //familyInfos.setNo_longer_in_household(" ");
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            List <String> arraysp3_9 = new ArrayList<>();
            arraysp3_9.add("Select Reasons Joined");
            arraysp3_9.add("New Born");
            arraysp3_9.add("Returned – study");
            arraysp3_9.add("Returned – relatives");
            arraysp3_9.add("Returned – military service");
            arraysp3_9.add("Returned - harvest");
            arraysp3_9.add("Lost own Home");
            arraysp3_9.add("Marriage");
            arraysp3_9.add("Family Request");
            arraysp3_9.add("Separation from spouse");
            arraysp3_9.add("Previously omitted");
            arraysp3_9.add("Returned – for better work opportunities");


//           final String[] arraysp3_9 = new String[] {
//                    "Select Reasons Joined",
//                    "New Born",
//                    "Returned – study",
//                    "Returned – relatives",
//                    "Returned – military service",
//                    "Returned - harvest",
//                    "Lost own Home",
//                    "Marriage",
//                    "Family Request",
//                    "Separation from spouse",
//                    "Previously omitted",
//                    "Returned – for better work opportunities"
//
//            };
            ArrayAdapter<String> adaptersp3_9 = new ArrayAdapter<String>(con,
                    R.layout.spinner_item_family, arraysp3_9);
            holder.sp3_9.setAdapter(adaptersp3_9);

            for (int i = 0; i <arraysp3_9.size() ; i++) {
                if(!TextUtils.isEmpty(familyInfos.getReason_family_lives_with_SC())){
                    if(familyInfos.getReason_family_lives_with_SC().equalsIgnoreCase(arraysp3_9.get(i)+" ")){
                        holder.sp3_9.setSelection(i);
                    }
                }
            }

            holder.sp3_9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(!holder.sp3_9.getSelectedItem().toString().equalsIgnoreCase("Select Reasons Joined")){
                        familyInfos.setReason_family_lives_with_SC(holder.sp3_9.getSelectedItem().toString());
                    }else {
                        //familyInfos.setReason_family_lives_with_SC(" ");
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(scFamilyInfosesList.size()>0){
                        scFamilyInfosesList.remove(position);
                        famaliInfo();
                    }

                }
            });



        }

        private void setFamilyInfo(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7) {


        }


        @Override
        public int getItemCount() {
            return scFamilyInfosesList.size();
        }
    }





    private void SetAllRadioUnchecked(@IdRes int radioGroup) {
        RadioGroup rg = (RadioGroup) findViewById(radioGroup);
        int i = rg.getChildCount();
        for (int e = 0; e < i; e++) {
            RadioButton radioButton = (RadioButton) rg.getChildAt(e);
            radioButton.setChecked(false);
        }
    }

    private void loadSavedData() {
        //load latLon
        String[] latLon = databaseManager.getLatLonByScNumber(scInfoNumber);
        tvLatLng.setText(latLon[0] + "\n" + latLon[1]);

        //load Answers
        ArrayList<ScGeneralQuestionAnswer> list = databaseManager.getScGeneralQuestionAnswerList(scInfoNumber);
        for (ScGeneralQuestionAnswer o : list) {
            String ans = o.getAnswer();
            switch (o.getQuestion_serial_no()) {
                case "1.1":
                    SetAnswerToRadioGroup(radioGroupQuestionType, ans);
                    break;
                case "1.3":
                    etInterviwerName.setText(ans);
                    break;
                case "1.4":
                    SetAnswerToRadioGroup(radioGroupInterviewerIs, ans);
                    break;
                case "1.5":
                    btnDateOfInterView.setText(ans);
                    break;

                case "1.9":
                    etIntervieweeName.setText(ans);
                    break;
                case "1.10":
                    SetAnswerToRadioGroup(radioGroupRelation, ans);
                    break;
                case "1.15":
                    SetAnswerToRadioGroup(radioGroupPragnent, ans);
                    break;
                case "2.3":
                    SetAnswerToRadioGroup(radioGroupHasRegisteredBirth, ans);
                    break;
                case "2.4":
                    SetAnswerToRadioGroup(radioGroupWhereRegistered, ans);
                    break;
                case "2.4.A":
                    etOthersRegistered.setText(ans);
                    break;
                case "2.5":
                    break;
                case "2.5.A":
                    etOthersNotRegistered.setText(ans);
                    break;
                case "3.1":
                    SetAnswerToRadioGroup(radioGroupSameHouseholdTweleveMonthAgo, ans);
                    break;
                case "3.10":
                    SetAnswerToRadioGroup(radioGroupReasonNotLivingWithSC, ans);
                    break;
                case "4.1":
                    SetAnswerToRadioGroup(radioGroupMMRInjection, ans);
                    break;
                case "4.2":
                    SetAnswerToRadioGroup(radioGroupIfnotWhyNot, ans);
                    break;
                case "4.3":
                    SetAnswerToRadioGroup(radioGroupHealthIssue, ans);
                    break;
                case "4.4":
                    SetAnswerToRadioGroup(radioGroupExperienceHealthIssue, ans);
                    break;
                case "4.4.A":
                    etOthersIssue.setText(ans);
                    break;
                case "4.5":
                    SetAnswerToRadioGroup(radioGroupSCRecovered, ans);
                    break;
                case "4.6":
                    SetAnswerToRadioGroup(radioGroupSeekTreatment, ans);
                    break;
                case "4.7":
                    SetAnswerToRadioGroup(radioGroupNotSeekTreatment, ans);
                    break;
                case "4.8":
                    SetAnswerToRadioGroup(radioGroupImpairments, ans);
                    break;
                case "4.9":
                    SetCheckboxAnswersImp(ans);
                    break;
                case "5.1":
                    SetAnswerToRadioGroup(radioGroupFormalEducation, ans);
                    break;
                case "5.2":
                    SetAnswerToRadioGroup(radioGroupTypeOfFormalEducation, ans);
                    break;
                case "5.2.A":
                    SetAnswerToRadioGroup(radioGroupBoardingSchool, ans);
                    break;
                case "5.3":
                    SetAnswerToRadioGroup(radioGroupNotAttendingFormalEducation, ans);
                    break;
                case "5.4":
                    SetAnswerToRadioGroup(radioGroupNonFormalEducation, ans);
                    break;
                case "5.5":
                    SetAnswerToRadioGroup(radioGroupTypeNonFormalEducation, ans);
                    break;
                case "5.6":
                    SetAnswerToRadioGroup(radioGroupTimeTakeGoSchool, ans);
                    break;
                case "5.7":
                    SetCheckboxAnswers(ans);
                    break;
                case "6.1":
                    SetAnswerToRadioGroup(radioGroupWallMaterial, ans);
                    break;
                case "6.2":
                    SetAnswerToRadioGroup(radioGroupMainMaterial, ans);
                    break;
                case "6.3":
                    SetAnswerToRadioGroup(radioGroupMainMaterialFloor, ans);
                    break;
                case "6.4":
                    SetAnswerToRadioGroup(radioGroupFuelCooking, ans);
                    break;
                case "6.5":
                    SetAnswerToRadioGroup(radioGroupHouseholdElectricity, ans);
                    break;
                case "6.6":
                    SetAnswerToRadioGroup(radioGroupToiletFacilities, ans);
                    break;
                case "7.1":
                    SetAnswerToRadioGroup(radioGroupHouseholdDrinkingWater, ans);
                    break;
                case "7.2":
                    SetAnswerToRadioGroup(radioGroupTimeofCollectWater, ans);
                    break;
                case "8.1":
                    SetAnswerToRadioGroup(radioGroupGeneralNews, ans);
                    break;
                case "8.1.A":
                    etGeneralNews.setText(ans);
                    break;
                case "8.2":
                    SetAnswerToRadioGroup(radioGroupProtectionAgainstAbuse, ans);
                    break;
                case "8.3":
                    SetAnswerToRadioGroup(radioGroupWhoProvides, ans);
                    break;
                case "8.3.A":
                    etProvidesService.setText(ans);
                    break;
                case "8.4":
                    SetAnswerToRadioGroup(radioGroupSatisfiedBenefit, ans);
                    break;
                case "8.4.A":
                    SetAnswerToRadioGroup(((RadioGroup) findViewById(R.id.radioGroupReasonforHappyness)), ans);
                    break;
                case "8.4.B":
                    SetAnswerToRadioGroup(((RadioGroup) findViewById(R.id.radioGroupReasonforNotHappy)), ans);
                    break;
                case "8.5":
                    SetAnswerToRadioGroup(radioGroupSCInformed, ans);
                    break;
                case "9.1":
                    SetImageToView(ans, R.id.imvMotherPhoto);
                    break;
                case "9.2":
                    SetImageToView(ans, R.id.usericon);
                    break;
                default:
                    break;
            }
        }
    }

    private void SetImageToView(String ans, @IdRes int imgAddMember) {
        byte[] decodedString = Base64.decode(ans, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ((ImageView) findViewById(imgAddMember)).setImageBitmap(decodedByte);
    }


    private void SetCheckboxAnswers(String ans) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ckbxHolder);
        int c = ll.getChildCount() - 1;
        List<String> splitted = Arrays.asList(ans.split(", "));
        for (int i = 0; i < c; i++) {
            CheckBox cb = (CheckBox) ll.getChildAt(i);
            if (Collections.frequency(splitted, cb.getText()) > 0){
                cb.setChecked(true);
                CheckBoxClick(cb);
            }else {
                cb.setChecked(false);
            }

        }
    }


     private void SetCheckboxAnswersImp(String ans) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.cbHolderImp);
        int c = ll.getChildCount() - 1;
        List<String> splitted = Arrays.asList(ans.split(", "));
        for (int i = 0; i < c; i++) {
            CheckBox cb = (CheckBox) ll.getChildAt(i);
            if (Collections.frequency(splitted, cb.getText()) > 0){
                cb.setChecked(true);
                CheckBoxClickImp(cb);
            }else {
                cb.setChecked(false);
            }

        }
    }




    private void SetAnswerToRadioGroup(RadioGroup radioGroup, String answer) {
        int i = radioGroup.getChildCount();
        for (int e = 0; e < i; e++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(e);
            if (radioButton.getText().equals(answer)) {
                radioButton.setChecked(true);
            } else radioButton.setChecked(false);
        }
    }

    //EXPANDABLE LAYOUT
    public void expandableButton1(View view) {
     //   expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout1);
        expandableLayout1.toggle(); // toggle expand and collapse

        expandableLayout2.collapse();
        expandableLayout3.collapse();
        expandableLayout4.collapse();
        expandableLayout5.collapse();


    }

    public void expandableButton2(View view) {
       // expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        expandableLayout2.toggle(); // toggle expand and collapse

        expandableLayout1.collapse();
        expandableLayout3.collapse();
        expandableLayout4.collapse();
        expandableLayout5.collapse();
    }

    public void expandableButton3(View view) {
        //expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
        expandableLayout3.toggle(); // toggle expand and collapse

        expandableLayout1.collapse();
        expandableLayout2.collapse();
        expandableLayout4.collapse();
        expandableLayout5.collapse();
    }

    public void expandableButton4(View view) {
     //   expandableLayout4 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout4);
        expandableLayout4.toggle(); // toggle expand and collapse

        expandableLayout1.collapse();
        expandableLayout3.collapse();
        expandableLayout2.collapse();
        expandableLayout5.collapse();
    }

    public void expandableButton5(View view) {
       // expandableLayout5 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout5);
        expandableLayout5.toggle(); // toggle expand and collapse

        expandableLayout1.collapse();
        expandableLayout3.collapse();
        expandableLayout4.collapse();
        expandableLayout2.collapse();
    }

  /*  public void expandableButton6(View view) {
      expandableLayout6 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout6);
        expandableLayout6.toggle(); // toggle expand and collapse
    }*/

    public void updateChiledProfile(View view) {

        //HEALTH
        int selectedMMRInjectionId = radioGroupMMRInjection.getCheckedRadioButtonId();
        int selectedIfnotWhyNotId = radioGroupIfnotWhyNot.getCheckedRadioButtonId();
        int selectedSeekTreatmentId = radioGroupSeekTreatment.getCheckedRadioButtonId();
        int selectedHealthIssueId = radioGroupHealthIssue.getCheckedRadioButtonId();
        int selectedExperienceHealthIssueId = radioGroupExperienceHealthIssue.getCheckedRadioButtonId();
        int selectedSCRecoveredId = radioGroupSCRecovered.getCheckedRadioButtonId();
        int selectedNotSeekTreatmentId = radioGroupNotSeekTreatment.getCheckedRadioButtonId();
        int selectedImpairmentsId = radioGroupImpairments.getCheckedRadioButtonId();


        RadioButton mmrInjectionrdo = (RadioButton) findViewById(selectedMMRInjectionId);
        mmrInjection = mmrInjectionrdo != null ? mmrInjectionrdo.getText().toString() : "";
        RadioButton IfnotWhyNotrdo = (RadioButton) findViewById(selectedIfnotWhyNotId);
        IfnotWhyNot = IfnotWhyNotrdo != null ? IfnotWhyNotrdo.getText().toString() : "";
        RadioButton SeekTreatmentrdo = (RadioButton) findViewById(selectedSeekTreatmentId);
        SeekTreatment = SeekTreatmentrdo != null ? SeekTreatmentrdo.getText().toString() : "";
        RadioButton HealthIssuerdo = (RadioButton) findViewById(selectedHealthIssueId);
        HealthIssue = HealthIssuerdo != null ? HealthIssuerdo.getText().toString() : "";
        RadioButton ExperienceHealthIssuerdo = (RadioButton) findViewById(selectedExperienceHealthIssueId);
        ExperienceHealthIssue = ExperienceHealthIssuerdo != null ? ExperienceHealthIssuerdo.getText().toString() : "";
        RadioButton SCRecoveredrdo = (RadioButton) findViewById(selectedSCRecoveredId);
        SCRecovered = SCRecoveredrdo != null ? SCRecoveredrdo.getText().toString() : "";
        RadioButton NotSeekTreatmentrdo = (RadioButton) findViewById(selectedNotSeekTreatmentId);
        NotSeekTreatment = NotSeekTreatmentrdo != null ? NotSeekTreatmentrdo.getText().toString() : "";

        RadioButton impirmentrdo = (RadioButton) findViewById(selectedImpairmentsId);
        impirement = impirmentrdo != null ? impirmentrdo.getText().toString() : "";



        othersIssue = etOthersIssue.getText().toString();
        //HEALTH

        //EDUCATION
        int selectedFormalEducationId = radioGroupFormalEducation.getCheckedRadioButtonId();
        int selectedTypeOfFormalEducationId = radioGroupTypeOfFormalEducation.getCheckedRadioButtonId();
        int selectedradioGroupBoardingSchoolId = radioGroupBoardingSchool.getCheckedRadioButtonId();
        int selectedradioGroupNotAttendingFormalEducationId = radioGroupNotAttendingFormalEducation.getCheckedRadioButtonId();
        int selectedradioGroupNonFormalEducationId = radioGroupNonFormalEducation.getCheckedRadioButtonId();
        int selectedradioGroupTypeNonFormalEducationId = radioGroupTypeNonFormalEducation.getCheckedRadioButtonId();
        int selectedradioGroupradioGroupTimeTakeGoSchoolId = radioGroupTimeTakeGoSchool.getCheckedRadioButtonId();
//        int selectedradioGroupSCFavouriteActivityId = radioGroupSCFavouriteActivity.getCheckedRadioButtonId();


        RadioButton formalEducationrdo = ((RadioButton) findViewById(selectedFormalEducationId));
        formalEducation = formalEducationrdo != null ? formalEducationrdo.getText().toString() : "";
        RadioButton typeOfFormalEducationrdo = ((RadioButton) findViewById(selectedTypeOfFormalEducationId));
        typeOfFormalEducation = typeOfFormalEducationrdo != null ? typeOfFormalEducationrdo.getText().toString() : "";
        RadioButton boardingSchoolrdo = ((RadioButton) findViewById(selectedradioGroupBoardingSchoolId));
        boardingSchool = boardingSchoolrdo != null ? boardingSchoolrdo.getText().toString() : "";
        RadioButton groupNotAttendingFormalrdo = ((RadioButton) findViewById(selectedradioGroupNotAttendingFormalEducationId));
        notAttendingFormalEducation = groupNotAttendingFormalrdo != null ? groupNotAttendingFormalrdo.getText().toString() : "";
        RadioButton nonformalEducationrdo = ((RadioButton) findViewById(selectedradioGroupNonFormalEducationId));
        nonFormalEducation = nonformalEducationrdo != null ? nonformalEducationrdo.getText().toString() : "";
        RadioButton typeNonFormalEducationrdo = ((RadioButton) findViewById(selectedradioGroupTypeNonFormalEducationId));
        typeNonFormalEducation = typeNonFormalEducationrdo != null ? typeNonFormalEducationrdo.getText().toString() : "";
        RadioButton timeTakeGoSchoolrdo = ((RadioButton) findViewById(selectedradioGroupradioGroupTimeTakeGoSchoolId));
        timeTakeGoSchool = timeTakeGoSchoolrdo != null ? timeTakeGoSchoolrdo.getText().toString() : "";
//        RadioButton SCFavouriteActivityrdo = ((RadioButton) findViewById(selectedradioGroupSCFavouriteActivityId));
//        timeTakeGoSchool = SCFavouriteActivityrdo != null ? SCFavouriteActivityrdo.getText().toString() : "";
        RadioButton TypeNonFormalEducationrdo = ((RadioButton) findViewById(selectedradioGroupTypeNonFormalEducationId));
        SCFavouriteActivity = TypeNonFormalEducationrdo != null ? TypeNonFormalEducationrdo.getText().toString() : "";

        //EDUCATION


        //INTERVIEWER ADDRESS
        int selectedReasonNotLivingWithSCId = radioGroupReasonNotLivingWithSC.getCheckedRadioButtonId();
        int selectedSameHouseholdTweleveMonthAgoId = radioGroupSameHouseholdTweleveMonthAgo.getCheckedRadioButtonId();
        int selectedWhynotRegisteredId = radioGroupWhynotRegistered.getCheckedRadioButtonId();
        int selectedWhereRegisteredId = radioGroupWhereRegistered.getCheckedRadioButtonId();
        int selectedHasRegisteredBirthId = radioGroupHasRegisteredBirth.getCheckedRadioButtonId();
        int selectedRelationId = radioGroupRelation.getCheckedRadioButtonId();

        int selectedInterviewerIsId = radioGroupInterviewerIs.getCheckedRadioButtonId();
        int selectedGroupQuestionTypeId = radioGroupQuestionType.getCheckedRadioButtonId();

        RadioButton reasonNotLivingWithSCrdo = (RadioButton) findViewById(selectedReasonNotLivingWithSCId);
        reasonNotLivingWithSC = reasonNotLivingWithSCrdo != null ? reasonNotLivingWithSCrdo.getText().toString() : "";

        RadioButton sameHouseholdTweleveMonthAgordo = (RadioButton) findViewById(selectedSameHouseholdTweleveMonthAgoId);
        sameHouseholdTweleveMonthAgo = sameHouseholdTweleveMonthAgordo != null ? sameHouseholdTweleveMonthAgordo.getText().toString() : "";
        RadioButton whynotRegisteredrdo = (RadioButton) findViewById(selectedWhynotRegisteredId);
        whynotRegistered = whynotRegisteredrdo != null ? whynotRegisteredrdo.getText().toString() : "";

        RadioButton whereRegisteredrdo = (RadioButton) findViewById(selectedWhereRegisteredId);
        whereRegistered = whereRegisteredrdo != null ? whereRegisteredrdo.getText().toString() : "";


        RadioButton hasRegisteredBirthrdo = (RadioButton) findViewById(selectedHasRegisteredBirthId);
        hasRegisteredBirth = hasRegisteredBirthrdo != null ? hasRegisteredBirthrdo.getText().toString() : "";

        RadioButton hasRelationrdo = (RadioButton) findViewById(selectedRelationId);
        //hasRegisteredBirth = hasRelationrdo != null ? hasRelationrdo.getText().toString() : "";


        RadioButton interviewerIsrdo = (RadioButton) findViewById(selectedInterviewerIsId);
        interviewerIs = interviewerIsrdo != null ? interviewerIsrdo.getText().toString() : "";

        RadioButton questionTyperdo = (RadioButton) findViewById(selectedGroupQuestionTypeId);
        questionType = questionTyperdo != null ? questionTyperdo.getText().toString() : "";

        RadioButton pragnentrdo = (RadioButton) findViewById(radioGroupPragnent.getCheckedRadioButtonId());
        pragnent = pragnentrdo != null ? pragnentrdo.getText().toString() : "";

        interviwerName = etInterviwerName.getText().toString();
        intervieweeName = etIntervieweeName.getText().toString();
        RadioButton intervieweeRelationshiprdo = (RadioButton) findViewById(selectedRelationId);
        intervieweeRelationship = intervieweeRelationshiprdo != null ? intervieweeRelationshiprdo.getText().toString() : "";

        othersRegistered = etOthersRegistered.getText().toString();
        othersNotRegistered = etOthersNotRegistered.getText().toString();


        //INTERVIEWER ADDRESS


        //HOUSEHOLD AND HOUSING
        int selectedTimeofCollectWaterId = radioGroupTimeofCollectWater.getCheckedRadioButtonId();
        int selectedHouseholdDrinkingWaterId = radioGroupHouseholdDrinkingWater.getCheckedRadioButtonId();
        int selectedradioGroupToiletFacilitiesId = radioGroupToiletFacilities.getCheckedRadioButtonId();
        int selectedHouseholdElectricityId = radioGroupHouseholdElectricity.getCheckedRadioButtonId();
        int selectedFuelCookingId = radioGroupFuelCooking.getCheckedRadioButtonId();
        int selectedMainMaterialFloorId = radioGroupMainMaterialFloor.getCheckedRadioButtonId();
        int selectedradioGroupMainMaterialId = radioGroupMainMaterial.getCheckedRadioButtonId();
        int selectedWallMaterialId = radioGroupWallMaterial.getCheckedRadioButtonId();


        RadioButton timeOfCollectWaterrdo = ((RadioButton) findViewById(selectedTimeofCollectWaterId));
        timeOfCollectWater = timeOfCollectWaterrdo != null ? timeOfCollectWaterrdo.getText().toString() : "";
        RadioButton houseHoldDrinkingtWaterrdo = (RadioButton) findViewById(selectedHouseholdDrinkingWaterId);
        houseHoldDrinkingtWater = houseHoldDrinkingtWaterrdo != null ? houseHoldDrinkingtWaterrdo.getText().toString() : "";
        RadioButton toiletFacilitiesrdo = (RadioButton) findViewById(selectedradioGroupToiletFacilitiesId);
        toiletFacilities = toiletFacilitiesrdo != null ? toiletFacilitiesrdo.getText().toString() : "";
        RadioButton householdElectricityrdo = (RadioButton) findViewById(selectedHouseholdElectricityId);
        householdElectricity = householdElectricityrdo != null ? householdElectricityrdo.getText().toString() : "";
        RadioButton fuelCookingrdo = (RadioButton) findViewById(selectedFuelCookingId);
        fuelCooking = fuelCookingrdo != null ? fuelCookingrdo.getText().toString() : "";
        RadioButton mainMaterialFloorrdo = (RadioButton) findViewById(selectedMainMaterialFloorId);
        mainMaterialFloor = mainMaterialFloorrdo != null ? mainMaterialFloorrdo.getText().toString() : "";
        RadioButton mainMaterialrdo = (RadioButton) findViewById(selectedradioGroupMainMaterialId);
        mainMaterial = mainMaterialrdo != null ? mainMaterialrdo.getText().toString() : "";
        RadioButton wallMaterialrdo = (RadioButton) findViewById(selectedWallMaterialId);
        wallMaterial = wallMaterialrdo != null ? wallMaterialrdo.getText().toString() : "";
        //HOUSEHOLD AND HOUSING

        //COMITMMENT
        int selectedGroupGeneralNewsId = radioGroupGeneralNews.getCheckedRadioButtonId();
        int selectedGroupProtectionAgainstAbuseId = radioGroupProtectionAgainstAbuse.getCheckedRadioButtonId();
        int selectedGroupWhoProvidesId = radioGroupWhoProvides.getCheckedRadioButtonId();
        int selectedGroupSatisfiedBenefitId = radioGroupSatisfiedBenefit.getCheckedRadioButtonId();
        int selectedGroupSCInformedId = radioGroupSCInformed.getCheckedRadioButtonId();

        RadioButton generalNewsrdo = ((RadioButton) findViewById(selectedGroupGeneralNewsId));
        generalNews = generalNewsrdo != null ? generalNewsrdo.getText().toString() : "";
        RadioButton protectionAgainstAbuserdo = ((RadioButton) findViewById(selectedGroupProtectionAgainstAbuseId));
        protectionAgainstAbusse = protectionAgainstAbuserdo != null ? protectionAgainstAbuserdo.getText().toString() : "";
        RadioButton WhoProvidesrdo = ((RadioButton) findViewById(selectedGroupWhoProvidesId));
        whoProvides = WhoProvidesrdo != null ? WhoProvidesrdo.getText().toString() : "";
        RadioButton satisfiedBenefitrdo = ((RadioButton) findViewById(selectedGroupSatisfiedBenefitId));
        satisfiedBenefit = satisfiedBenefitrdo != null ? satisfiedBenefitrdo.getText().toString() : "";
        RadioButton SCInformedrdo = ((RadioButton) findViewById(selectedGroupSCInformedId));
        sCInformed = SCInformedrdo != null ? SCInformedrdo.getText().toString() : "";


        generalNewsOther = etGeneralNews.getText().toString();
        providesService = etProvidesService.getText().toString();
        RadioButton happynessRDO = ((RadioButton) findViewById(((RadioGroup) findViewById(R.id.radioGroupReasonforHappyness)).getCheckedRadioButtonId()));
        happynessYes = happynessRDO != null ? happynessRDO.getText().toString() : "";
        RadioButton happynessNotRDO = ((RadioButton) findViewById(((RadioGroup) findViewById(R.id.radioGroupReasonforNotHappy)).getCheckedRadioButtonId()));
        String happynessNot = happynessNotRDO != null ? happynessNotRDO.getText().toString() : "";


        qmgr.ClearAnswerBeforeNewInsert(scInfoTableId);
        insertToDb("1.1", questionType);
        insertToDb("1.3", interviwerName);
        insertToDb("1.4", interviewerIs);
        insertToDb("1.5", interviewDate);

        insertToDb("1.6", scInfoModel.getScInfoCommunityName());
        insertToDb("1.7", String.valueOf(scInfoModel.getScInfoCommunityWorkerId()));
        insertToDb("1.8", scInfoModel.getScInfoScGender());
        insertToDb("1.9", intervieweeName);
        insertToDb("1.10", intervieweeRelationship);
        insertToDb("1.15", pragnent);

        insertToDb("2.1", scInfoModel.getLanguage());
        insertToDb("2.2", scInfoModel.getScInfoReligion());

        insertToDb("2.3", hasRegisteredBirth);
        insertToDb("2.4", whereRegistered);
        insertToDb("2.4.A", othersRegistered);
        insertToDb("2.5", whynotRegistered);
        insertToDb("2.5.A", othersNotRegistered);
        insertToDb("3.1", sameHouseholdTweleveMonthAgo);

        insertToDb("3.10", reasonNotLivingWithSC);
        insertToDb("4.1", mmrInjection);
        insertToDb("4.2", IfnotWhyNot);
        insertToDb("4.3", HealthIssue);
        insertToDb("4.4", ExperienceHealthIssue);
        insertToDb("4.4.A", othersIssue);
        insertToDb("4.5", SCRecovered);
        insertToDb("4.6", SeekTreatment);
        insertToDb("4.7", NotSeekTreatment);
        insertToDb("4.8", impirement);
        insertToDb("4.9", getCheckBoxAnswersImp());
        insertToDb("5.1", formalEducation);
        insertToDb("5.2", typeOfFormalEducation);
        insertToDb("5.2.A", boardingSchool);
        insertToDb("5.3", notAttendingFormalEducation);
        insertToDb("5.4", nonFormalEducation);
        insertToDb("5.5", typeNonFormalEducation);
        insertToDb("5.6", timeTakeGoSchool);

        String fbbActivity = getCheckBoxAnswers();
        insertToDb("5.7", fbbActivity);

        insertToDb("6.1", wallMaterial);
        insertToDb("6.2", mainMaterial);
        insertToDb("6.3", mainMaterialFloor);
        insertToDb("6.4", fuelCooking);
        insertToDb("6.5", householdElectricity);
        insertToDb("6.6", toiletFacilities);
        insertToDb("7.1", houseHoldDrinkingtWater);
        insertToDb("7.2", timeOfCollectWater);
        insertToDb("8.1", generalNews);
        insertToDb("8.1.A", generalNewsOther);
        insertToDb("8.2", protectionAgainstAbusse);
        insertToDb("8.3", whoProvides);
        insertToDb("8.3.A", providesService);
        insertToDb("8.4", satisfiedBenefit);
        insertToDb("8.4.A", happynessYes);
        insertToDb("8.4.B", happynessNot);
        insertToDb("8.5", sCInformed);

        insertToDb("9.1", mapImgStrData.containsKey("img91") ? mapImgStrData.get("img91") : "");
        insertToDb("9.2", mapImgStrData.containsKey("img100") ? mapImgStrData.get("img100") : "");

        //save lat lon
        databaseManager.SaveLatLon(scInfoNumber, tvLatLng.getText().toString());
        insertToDb("9.3", tvLatLng.getText().toString().replaceAll("\n", "~"));


        if (checkConnectivity()) {
            JSONArray arr= saveData2Copy();
            qmgr.SendProfileDataToNet(arr,scInfoNumber);
        } else {
            JSONArray arr= saveData2Copy();
            qmgr.saveScLocalToSendWhenGetConn(arr,scInfoNumber);
            //Toast.makeText(this, "Data Connection Error in Update Profile", Toast.LENGTH_SHORT).show();
        }


    }

    public boolean checkConnectivity() {
        return ConnectivityReceiver.isConnected();
        // showSnackBar(isConnected);
    }

    QuestioneryManager qmgr = new QuestioneryManager(this,UpdateProfileActivity.this);

    private void insertToDb(String questionId, String answer) {
        if (answer == null || answer.equals("")) return;
        qmgr.InsertStaticQuestion(questionId, answer, scInfoNumber + "");
    }

    String imgStrData;
    Map<String, String> mapImgStrData = new HashMap<>();

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ((ImageView) findViewById(Operation.currentImageViewId)).setImageBitmap(bitmap);
                imgStrData = getBitmapAsBas64String(bitmap);
//                if(!Operation.currentPhotoQuestionId.equals("100"))
                mapImgStrData.put("img" + Operation.currentPhotoQuestionId, imgStrData);
//                else
//                    sendProfileImage(bitmap,scInfoNumber);
            }
        }
    }

    private void sendProfileImage(Bitmap bitmap, int scId) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        new UploadToServer().uploadFile(outputStream.toByteArray(), "profilePic.png", scId);
    }


    public static String getBitmapAsBas64String(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }


    private void openImageChooser() {
        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent1, SELECT_PICTURE);
    }


    private void setArrayListValue() {

        for (int i = 0; i < familyMember; i++) {
            nameList.add("");
            nameKnownList.add("");
            birthList.add("");
            genderList.add("");
            relationList.add("");
            relationSpList.add("");
            reasonForJoinList.add("");
            occupationList.add("");
            occupationSpList.add("");
            careerList.add("");

        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        //addMember1();

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    private void addMember1() {

        final CardView cardView = new CardView(this);
        LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        cardLp.setMargins(0, 50, 0, 0);
        cardView.setLayoutParams(cardLp);
        LinearLayout linearLayoutForCArdView = new LinearLayout(this);
        linearLayoutForCArdView.setOrientation(LinearLayout.VERTICAL);

        nameList.add("");
        nameKnownList.add("");
        birthList.add("");
        genderList.add("");
        relationList.add("");
        relationSpList.add("");
        occupationList.add("");
        occupationSpList.add("");
        careerList.add("");

        reasonForJoinList.add("");


        deleteBtn.add(new ImageButton(this));
        deleteBtn.get(familyMember).setBackgroundColor(Color.parseColor("#ffffff"));
        deleteBtn.get(familyMember).setImageResource(R.drawable.ic_action_delete);


        deleteBtn.get(familyMember).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageButton b = (ImageButton) view;
                //int id = (int) b.getTag();
                int id = deleteBtn.indexOf(b);

                nameListEt.get(id).setText("");
                nameKnownListEt.get(id).setText("");
                birthListEt.get(id).setText("");
                genderListEt.get(id).setText("");
                relationListEt.get(id).setText("");
                occupationListEt.get(id).setText("");
                careerListEt.get(id).setText("");
                reasonForJoinListEt.get(id).setText("");

                familyInfoUpdateLayout.removeView(deleteBtn.get(id));
                familyInfoUpdateLayout.removeView(nameListEt.get(id));
                familyInfoUpdateLayout.removeView(nameKnownListEt.get(id));
                familyInfoUpdateLayout.removeView(birthListEt.get(id));
                familyInfoUpdateLayout.removeView(genderListEt.get(id));
                familyInfoUpdateLayout.removeView(relationListEt.get(id));
                familyInfoUpdateLayout.removeView(occupationListEt.get(id));
                familyInfoUpdateLayout.removeView(careerListEt.get(id));
                familyInfoUpdateLayout.removeView(reasonForJoinListEt.get(id));
                familyInfoUpdateLayout.removeView(cardView);

                deleteBtn.remove(id);

                nameList.remove(id);
                nameKnownList.remove(id);
                birthList.remove(id);
                genderList.remove(id);
                relationList.remove(id);
                occupationList.remove(id);
                careerList.remove(id);
                reasonForJoinList.remove(id);

                nameListEt.remove(id);
                nameKnownListEt.remove(id);
                birthListEt.remove(id);
                genderListEt.remove(id);
                relationListEt.remove(id);
                occupationListEt.remove(id);
                careerListEt.remove(id);
                reasonForJoinListEt.remove(id);

                familyMember = familyMember - 1;


            }
        });


        nameListEt.add(new EditText(this));

        LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        nameParams.setMargins(40, 50, 0, 0);
        nameListEt.get(familyMember).setHint("Full Name");
        nameListEt.get(familyMember).setTextColor(Color.BLACK);
        nameListEt.get(familyMember).setLayoutParams(nameParams);


        nameKnownListEt.add(new EditText(this));
        LinearLayout.LayoutParams nameKnownL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        nameKnownL.setMargins(40, 0, 0, 0);
        nameKnownListEt.get(familyMember).setHint("Name Known By");
        nameKnownListEt.get(familyMember).setTextColor(Color.BLACK);
        nameKnownListEt.get(familyMember).setLayoutParams(nameKnownL);


        birthListEt.add(new EditText(this));
        LinearLayout.LayoutParams birthL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        birthL.setMargins(40, 0, 0, 0);
        birthListEt.get(familyMember).setHint("Birth Year");
        birthListEt.get(familyMember).setTextColor(Color.BLACK);
        birthListEt.get(familyMember).setLayoutParams(birthL);

        genderListEt.add(new EditText(this));
        LinearLayout.LayoutParams genderL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        genderL.setMargins(40, 0, 0, 0);
        genderListEt.get(familyMember).setHint("Gender");
        genderListEt.get(familyMember).setTextColor(Color.BLACK);
        genderListEt.get(familyMember).setLayoutParams(genderL);


        relationListEt.add(new EditText(this));
        LinearLayout.LayoutParams relationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        relationL.setMargins(40, 0, 0, 0);
        relationListEt.get(familyMember).setHint("Relationship");
        relationListEt.get(familyMember).setTextColor(Color.BLACK);
        relationListEt.get(familyMember).setLayoutParams(relationL);
        relationListEt.get(familyMember).setVisibility(View.GONE);

        //     ArrayList<String> relations = new ArrayList<>();
        String[] relations = databaseManager.getRelationShipList();

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, relations);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relationListSpinner.add(new Spinner(this));
        LinearLayout.LayoutParams relationspinL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        relationspinL.setMargins(40, 20, 0, 0);
        relationListSpinner.get(familyMember).setLayoutParams(relationspinL);
        //  relationListSpinner.get(familyMember).setBackgroundColor(Color.RED);
        relationListSpinner.get(familyMember).setAdapter(spinnerArrayAdapter);

        //     linearLayoutForCArdView.addView(relationListSpinner.get(familyMember));

        //relationListSpinner.get(familyMember);


        occupationListEt.add(new EditText(this));
        LinearLayout.LayoutParams occupationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        occupationL.setMargins(40, 0, 0, 0);
        occupationListEt.get(familyMember).setHint("Occupation");
        occupationListEt.get(familyMember).setTextColor(Color.BLACK);
        occupationListEt.get(familyMember).setLayoutParams(occupationL);
        occupationListEt.get(familyMember).setVisibility(View.GONE);


        String[] occupations = databaseManager.getOccupationList();

        ArrayAdapter<String> spinnerArrayAdapterOccupation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, occupations);
        spinnerArrayAdapterOccupation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupationListSpinner.add(new Spinner(this));
        LinearLayout.LayoutParams occupationspinL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        occupationspinL.setMargins(40, 20, 0, 0);
        occupationListSpinner.get(familyMember).setLayoutParams(occupationspinL);
        occupationListSpinner.get(familyMember).setAdapter(spinnerArrayAdapterOccupation);


        careerListEt.add(new EditText(this));
        LinearLayout.LayoutParams primaryCareerL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        primaryCareerL.setMargins(40, 0, 0, 0);
        careerListEt.get(familyMember).setHint("Career");
        careerListEt.get(familyMember).setTextColor(Color.BLACK);
        careerListEt.get(familyMember).setLayoutParams(primaryCareerL);


        reasonForJoinListEt.add(new EditText(this));
        LinearLayout.LayoutParams reasonForJoinL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        reasonForJoinL.setMargins(40, 0, 0, 50);
        reasonForJoinListEt.get(familyMember).setHint("Reason For Join");
        reasonForJoinListEt.get(familyMember).setTextColor(Color.BLACK);
        reasonForJoinListEt.get(familyMember).setLayoutParams(reasonForJoinL);


        View view = new View(this);
        view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 1));
        view.setBackgroundColor(Color.parseColor("#a33b07"));


        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.RIGHT);
//        linearLayout.addView(deleteBtn.get(familyMember));
//
        linearLayoutForCArdView.addView(linearLayout);
        linearLayoutForCArdView.addView(nameListEt.get(familyMember));
        linearLayoutForCArdView.addView(nameKnownListEt.get(familyMember));
        linearLayoutForCArdView.addView(birthListEt.get(familyMember));
        linearLayoutForCArdView.addView(genderListEt.get(familyMember));
        linearLayoutForCArdView.addView(relationListEt.get(familyMember));
        ///  linearLayoutForCArdView.addView(relationListSpinner.get(familyMember));

        if(linearLayoutForCArdView.getParent()!=null){
            ((ViewGroup)linearLayoutForCArdView.getParent()).removeView(linearLayoutForCArdView);
        }
        linearLayoutForCArdView.addView(relationListSpinner.get(familyMember));
        linearLayoutForCArdView.addView(occupationListEt.get(familyMember));
        linearLayoutForCArdView.addView(occupationListSpinner.get(familyMember));
        linearLayoutForCArdView.addView(careerListEt.get(familyMember));
        linearLayoutForCArdView.addView(reasonForJoinListEt.get(familyMember));
        // linearLayoutForCArdView.addView(com.tutorials.hp.recyclersqlite.view);

        cardView.addView(linearLayoutForCArdView);
        familyInfoUpdateLayout.addView(cardView);


        nameListEt.get(familyMember).setText("");
        nameKnownListEt.get(familyMember).setText("");
        birthListEt.get(familyMember).setText("");
        genderListEt.get(familyMember).setText("");
        relationListEt.get(familyMember).setText("");
        relationListSpinner.get(familyMember).setAdapter(spinnerArrayAdapter);
        occupationListSpinner.get(familyMember).setAdapter(spinnerArrayAdapterOccupation);
        occupationListEt.get(familyMember).setText("");
        careerListEt.get(familyMember).setText("");

        reasonForJoinListEt.get(familyMember).setText("");


        familyMember = familyMember + 1;

    }


    private void setData2() {


        for (int i = 0; i < familyMember; i++) {

            final CardView cardView = new CardView(this);
            LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            cardLp.setMargins(0, 10, 0, 0);
            cardView.setLayoutParams(cardLp);
            LinearLayout linearLayoutForCArdView = new LinearLayout(this);
            linearLayoutForCArdView.setOrientation(LinearLayout.VERTICAL);

            ImageButton btn = new ImageButton(this);
            deleteBtn.add(btn);

            RelativeLayout.LayoutParams btnParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            btnParams.setMargins(40, 50, 0, 0);

          deleteBtn.get(i).setLayoutParams(btnParams);
            deleteBtn.get(i).setBackgroundColor(Color.parseColor("#ffffff"));
            deleteBtn.get(i).setImageResource(R.drawable.ic_action_delete);

            deleteBtn.get(i).setTag(i);

            deleteBtn.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ImageButton b = (ImageButton) view;
                    //int id = (int) b.getTag();
                    int id = deleteBtn.indexOf(b);


                    nameListEt.get(id).setText("");
                    nameKnownListEt.get(id).setText("");
                    birthListEt.get(id).setText("");
                    genderListEt.get(id).setText("");
                    relationListEt.get(id).setText("");
                    occupationListEt.get(id).setText("");
                    careerListEt.get(id).setText("");
                    reasonForJoinListEt.get(id).setText("");


                    familyInfoUpdateLayout.removeView(deleteBtn.get(id));
                    familyInfoUpdateLayout.removeView(nameListEt.get(id));
                    familyInfoUpdateLayout.removeView(nameKnownListEt.get(id));
                    familyInfoUpdateLayout.removeView(birthListEt.get(id));
                    familyInfoUpdateLayout.removeView(genderListEt.get(id));
                    familyInfoUpdateLayout.removeView(relationListEt.get(id));
                    familyInfoUpdateLayout.removeView(occupationListEt.get(id));
                    familyInfoUpdateLayout.removeView(careerListEt.get(id));
                    familyInfoUpdateLayout.removeView(reasonForJoinListEt.get(id));
                    familyInfoUpdateLayout.removeView(cardView);


                    deleteBtn.remove(id);

                    nameList.remove(id);
                    nameKnownList.remove(id);
                    birthList.remove(id);
                    genderList.remove(id);
                    relationList.remove(id);
                    occupationList.remove(id);
                    careerList.remove(id);
                    reasonForJoinList.remove(id);

                    nameListEt.remove(id);
                    nameKnownListEt.remove(id);
                    birthListEt.remove(id);
                    genderListEt.remove(id);
                    relationListEt.remove(id);
                    relationListSpinner.remove(id);
                    occupationListSpinner.remove(id);
                    occupationListEt.remove(id);
                    careerListEt.remove(id);
                    reasonForJoinListEt.remove(id);

                    familyMember = familyMember - 1;

                }
            });


            nameListEt.add(new EditText(this));
            LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            nameParams.setMargins(40, 50, 0, 0);
            nameListEt.get(i).setHint("Name");
            nameListEt.get(i).setTextColor(Color.BLACK);
            nameListEt.get(i).setLayoutParams(nameParams);


            nameKnownListEt.add(new EditText(this));
            LinearLayout.LayoutParams nameKnownL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            nameKnownL.setMargins(40, 0, 0, 0);
            nameKnownListEt.get(i).setHint("Name Known By");
            nameKnownListEt.get(i).setTextColor(Color.BLACK);
            nameKnownListEt.get(i).setLayoutParams(nameKnownL);


            birthListEt.add(new EditText(this));
            LinearLayout.LayoutParams birthL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            birthL.setMargins(40, 0, 0, 0);
            birthListEt.get(i).setHint("Birth Year");
            birthListEt.get(i).setTextColor(Color.BLACK);
            birthListEt.get(i).setLayoutParams(birthL);


            genderListEt.add(new EditText(this));
            LinearLayout.LayoutParams genderL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            genderL.setMargins(40, 0, 0, 0);
            genderListEt.get(i).setHint("Gender");
            genderListEt.get(i).setTextColor(Color.BLACK);
            genderListEt.get(i).setLayoutParams(genderL);


            relationListEt.add(new EditText(this));
            LinearLayout.LayoutParams relationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            relationL.setMargins(40, 0, 0, 0);
            relationListEt.get(i).setHint("Relation");
            relationListEt.get(i).setTextColor(Color.BLACK);
            relationListEt.get(i).setLayoutParams(relationL);


            ArrayList<String> relations = new ArrayList<>();
            relations.add("one");
            relations.add("two");
            relations.add("three");
            relations.add("four");
            relations.add("five");
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, relations);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            relationListSpinner.add(new Spinner(this));
            LinearLayout.LayoutParams relationspinL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            relationspinL.setMargins(40, 10, 0, 0);
            relationListSpinner.get(i).setLayoutParams(relationspinL);
            relationListSpinner.get(i).setVisibility(View.GONE);
            //   linearLayoutForCArdView.addView(relationListSpinner.get(i));


            String[] occupations = {
                    "Not Applicable",
                    "Peasant Farmer",
                    "Fisherman",
                    "Craftsman",
                    "Street Hawker",
                    "Policeman",
                    "Driver",
                    "Miner",
                    "Factory Worker",
                    "Cook",
                    "Cleaner","Teacher",
                    "Shoe Shiner",
                    "Security Guard",
                    "Body Massage","Self Employed",
                    "Working in Private Sector",
                    "Working forces (military service)",
                    "Recycling Work",
                    "Village Doctor",
                    "Small business owner",
                    "Migrant/overseas worker",
                    "Civil defence force",
                    "Local grape picker",
                    "Village Doctor",
            };

            ArrayAdapter<String> spinnerArrayAdapterOccupation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, occupations);
            spinnerArrayAdapterOccupation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            occupationListSpinner.add(new Spinner(this));
            LinearLayout.LayoutParams occupationspinL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            occupationspinL.setMargins(40, 10, 0, 0);
            occupationListSpinner.get(i).setLayoutParams(occupationspinL);
            occupationListSpinner.get(i).setVisibility(View.GONE);


            occupationListEt.add(new EditText(this));
            LinearLayout.LayoutParams occupationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            occupationL.setMargins(40, 0, 0, 0);
            occupationListEt.get(i).setHint("Occupation");
            occupationListEt.get(i).setTextColor(Color.BLACK);
            occupationListEt.get(i).setLayoutParams(occupationL);


            careerListEt.add(new EditText(this));
            LinearLayout.LayoutParams primaryCareerL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            primaryCareerL.setMargins(40, 0, 0, 0);
            careerListEt.get(i).setHint("Career");
            careerListEt.get(i).setTextColor(Color.BLACK);
            careerListEt.get(i).setLayoutParams(primaryCareerL);

            reasonForJoinListEt.add(new EditText(this));
            LinearLayout.LayoutParams reasonForJoinL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            reasonForJoinL.setMargins(40, 0, 0, 50);
            reasonForJoinListEt.get(i).setHint("Reason For Join");
            reasonForJoinListEt.get(i).setTextColor(Color.BLACK);
            reasonForJoinListEt.get(i).setLayoutParams(reasonForJoinL);

            View view = new View(this);
            view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 1));
            view.setBackgroundColor(Color.parseColor("#a33b07"));


            deleteBtn.get(i).setId(i);
            nameListEt.get(i).setId(i);
            nameKnownListEt.get(i).setId(i);
            birthListEt.get(i).setId(i);
            genderListEt.get(i).setId(i);
            relationListEt.get(i).setId(i);
            relationListSpinner.get(i).setId(i);
            occupationListSpinner.get(i).setId(i);
            occupationListEt.get(i).setId(i);
            careerListEt.get(i).setId(i);
            reasonForJoinListEt.get(i).setId(i);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setGravity(Gravity.RIGHT);
            linearLayout.addView(deleteBtn.get(i));

            linearLayoutForCArdView.addView(linearLayout);
            linearLayoutForCArdView.addView(nameListEt.get(i));
            linearLayoutForCArdView.addView(nameKnownListEt.get(i));
            linearLayoutForCArdView.addView(birthListEt.get(i));
            linearLayoutForCArdView.addView(genderListEt.get(i));
            linearLayoutForCArdView.addView(relationListEt.get(i));
            linearLayoutForCArdView.addView(relationListSpinner.get(i));
            linearLayoutForCArdView.addView(occupationListSpinner.get(i));
            linearLayoutForCArdView.addView(occupationListEt.get(i));
            linearLayoutForCArdView.addView(careerListEt.get(i));
            linearLayoutForCArdView.addView(reasonForJoinListEt.get(i));

            cardView.addView(linearLayoutForCArdView);
            familyInfoUpdateLayout.addView(cardView);


            nameListEt.get(i).setText(scFamilyInfosesList.get(i).getMember_full_name() + "");
            nameKnownListEt.get(i).setText(scFamilyInfosesList.get(i).getMember_name_known_by() + "");
            birthListEt.get(i).setText(scFamilyInfosesList.get(i).getMember_birth_year() + "");
            genderListEt.get(i).setText(scFamilyInfosesList.get(i).getMember_gender() + "");
            relationListEt.get(i).setText(scFamilyInfosesList.get(i).getMember_relationship() + "");
            relationListSpinner.get(i).setAdapter(spinnerArrayAdapter);
            occupationListSpinner.get(i).setAdapter(spinnerArrayAdapterOccupation);
            occupationListEt.get(i).setText(scFamilyInfosesList.get(i).getMember_occupation() + "");
            careerListEt.get(i).setText(scFamilyInfosesList.get(i).getMember_is_primary_carer() + "");
            reasonForJoinListEt.get(i).setText("Reason For joining ");

        }
    }

//    private JSONArray saveData2() {
//        JSONArray array = new JSONArray();
//        databaseManager.clearFamilyInfo(scInfoTableId);
//        for (int i = 0; i < familyMember; i++) {
//            databaseManager.saveFamilyInfo(nameListEt.get(i).getText().toString(),
//                    nameKnownListEt.get(i).getText().toString(),
//                    birthListEt.get(i).getText().toString(),
//                    genderListEt.get(i).getText().toString(),
//                    relationListSpinner.get(i).getSelectedItem().toString(),
//                    careerListEt.get(i).getText().toString(),
//                    occupationListSpinner.get(i).getSelectedItem().toString(),
//
//                    // occupationListEt.get(i).getText().toString(),
//                    reasonForJoinListEt.get(i).getText().toString(),
//                    scInfoTableId
//            );
//
//            JSONObject obj = new JSONObject();
//            try {
//                obj.put("q_3_2", nameListEt.get(i).getText().toString());
//                obj.put("q_3_2_A", nameKnownListEt.get(i).getText().toString());
//                obj.put("q_3_4", birthListEt.get(i).getText().toString());
//                obj.put("q_3_5", genderListEt.get(i).getText().toString());
//                obj.put("q_3_5_A", relationListSpinner.get(i).getSelectedItem().toString());
//                obj.put("q_3_6", careerListEt.get(i).getText().toString());
//                //  obj.put("q_3_7", occupationListEt.get(i).getText().toString());
//                obj.put("q_3_7", occupationListSpinner.get(i).getSelectedItem().toString());
//                obj.put("q_3_9", reasonForJoinListEt.get(i).getText().toString());
//                obj.put("sc_id", scInfoNumber);
//                array.put(obj);
//            } catch (Exception ex) {
//            }
//        }
////        for (int i = 0; i < nameListEt.size(); i++) {
////            Toast.makeText(this, nameList.get(i) + " \n" +
////                    nameKnownList.get(i) + "\n" +
////                    birthList.get(i) + "\n" +
////                    genderList.get(i) + "\n" +
////                    relationList.get(i) + "\n" +
////                    occupationList.get(i) + "\n" +
////                    careerList.get(i) + "\n" +
////                    reasonForJoinList.get(i), Toast.LENGTH_SHORT).show();
////            // Toast.makeText(this, nameList.get(i) + " \n" + birthList.get(i), Toast.LENGTH_SHORT).show();
////        }
//        return array;
//    }

    private JSONArray saveData2Copy() {
        JSONArray array = new JSONArray();
        databaseManager.clearFamilyInfo(scInfoTableId);
        for (int i = 0; i < scFamilyInfosesList.size(); i++) {

            if(!TextUtils.isEmpty(scFamilyInfosesList.get(i).getMember_full_name())){
                databaseManager.saveFamilyInfo(scFamilyInfosesList.get(i).getMember_full_name()+" ",
                        scFamilyInfosesList.get(i).getMember_full_name()+" ",
                        String.valueOf(scFamilyInfosesList.get(i).getMember_birth_year())+" ",
                        scFamilyInfosesList.get(i).getMember_gender()+" ",
                        scFamilyInfosesList.get(i).getMember_relationship()+" ",
                        scFamilyInfosesList.get(i).getMember_is_primary_carer()+" ",
                        scFamilyInfosesList.get(i).getMember_occupation()+" ",
                        scFamilyInfosesList.get(i).getWho_left_household()+" ",
                        scFamilyInfosesList.get(i).getNo_longer_in_household()+" ",
                        scFamilyInfosesList.get(i).getReason_family_lives_with_SC()+" ",
                        scInfoTableId
                );

                JSONObject obj = new JSONObject();
                try {
                    obj.put("q_3_2", scFamilyInfosesList.get(i).getMember_full_name()+" ");
                    obj.put("q_3_2_A", scFamilyInfosesList.get(i).getMember_full_name()+" ");
                    obj.put("q_3_4", String.valueOf(scFamilyInfosesList.get(i).getMember_birth_year()+" "));
                    obj.put("q_3_5", scFamilyInfosesList.get(i).getMember_relationship()+" ");
                    obj.put("q_3_5_A", scFamilyInfosesList.get(i).getMember_gender()+" ");
                    obj.put("q_3_6", scFamilyInfosesList.get(i).getMember_is_primary_carer()+" ");
                    //  obj.put("q_3_7", occupationListEt.get(i).getText().toString());
                    obj.put("q_3_7", scFamilyInfosesList.get(i).getMember_occupation()+" ");
                    obj.put("q_3_8", scFamilyInfosesList.get(i).getWho_left_household()+" ");
                    obj.put("q_3_8_A", scFamilyInfosesList.get(i).getNo_longer_in_household()+" ");
                    obj.put("q_3_9", scFamilyInfosesList.get(i).getReason_family_lives_with_SC()+" ");
                    obj.put("sc_id", scInfoNumber);
                    array.put(obj);
                } catch (Exception ex) {
                }
            }


        }
//        for (int i = 0; i < nameListEt.size(); i++) {
//            Toast.makeText(this, nameList.get(i) + " \n" +
//                    nameKnownList.get(i) + "\n" +
//                    birthList.get(i) + "\n" +
//                    genderList.get(i) + "\n" +
//                    relationList.get(i) + "\n" +
//                    occupationList.get(i) + "\n" +
//                    careerList.get(i) + "\n" +
//                    reasonForJoinList.get(i), Toast.LENGTH_SHORT).show();
//            // Toast.makeText(this, nameList.get(i) + " \n" + birthList.get(i), Toast.LENGTH_SHORT).show();
//        }
        return array;
    }

    public void photoWithMother(View view) {
        Operation.currentImageViewId = imvMotherPhoto.getId();
        Operation.currentPhotoQuestionId = "91";
        openImageChooser();
    }

    public void photoWithFather(View view) {
        Operation.currentImageViewId = imvFatherPhoto.getId();
        Operation.currentPhotoQuestionId = "92";
        openImageChooser();
    }

    public void photoWithGrandMother(View view) {
        Operation.currentImageViewId = imvGrandMotherPhoto.getId();
        Operation.currentPhotoQuestionId = "93";
        openImageChooser();
    }

    public void photoWithGrandFather(View view) {
        Operation.currentImageViewId = imvGrandFatherPhoto.getId();
        Operation.currentPhotoQuestionId = "94";
        openImageChooser();
    }

    public void photoWithBrother(View view) {
        Operation.currentImageViewId = imvBrotherPhoto.getId();
        Operation.currentPhotoQuestionId = "95";
        openImageChooser();
    }

    public void photoWithSister(View view) {
        Operation.currentImageViewId = imvSisterPhoto.getId();
        Operation.currentPhotoQuestionId = "96";
        openImageChooser();
    }

    Map<String, String> checkBoxAnswers = new HashMap<>();


    public void CheckBoxClick(View view) {

        CheckBox cb = (CheckBox) view;
        if (cb.isChecked())
            checkBoxAnswers.put(cb.getText().toString(), cb.getText().toString());
        else
            checkBoxAnswers.put(cb.getText().toString(), "");
    }




    String getCheckBoxAnswers() {
        String s = "";
        Object[] values = checkBoxAnswers.values().toArray();
        if (values.length == 0) return "";
        for (Object value : values) {
            s += value + ", ";
        }
        s = s.length() > 2 ? s.substring(0, s.length() - 2) : "";
        return s;
    }



    Map<String, String> checkBoxAnswersImp = new HashMap<>();
     public void CheckBoxClickImp(View view) {

        CheckBox cb = (CheckBox) view;
        if (cb.isChecked())
            checkBoxAnswersImp.put(cb.getText().toString(), cb.getText().toString());
        else
            checkBoxAnswersImp.put(cb.getText().toString(), "");
    }


    String getCheckBoxAnswersImp() {
        String s = "";
        Object[] values = checkBoxAnswersImp.values().toArray();
        if (values.length == 0) return "";
        for (Object value : values) {
            s += value + ", ";
        }
        s = s.length() > 2 ? s.substring(0, s.length() - 2) : "";
        return s;
    }



    public void btnLocationClick(View view) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkLocationPermission()) {
                if (mgr.mGoogleApiClient == null) {
                    mgr.buildGoogleApiClient();
                }
            }
        } else {
            if (mgr.mGoogleApiClient == null) {
                mgr.buildGoogleApiClient();
            }
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    LocationMgr mgr;

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (mgr.mGoogleApiClient == null) {
                            mgr.buildGoogleApiClient();
                        }
                        // mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }

    private class mDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            // getCalender();
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;
            scdobBTN.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("/").append(mDay).append("/")
                    .append(mYear).append(" "));
            System.out.println(scdobBTN.getText().toString());
        }
    }

}
