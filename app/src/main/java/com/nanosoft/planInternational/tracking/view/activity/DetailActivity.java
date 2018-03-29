package com.nanosoft.planInternational.tracking.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rey.material.widget.FrameLayout;
import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.database.manager.DatabaseManager;
import com.nanosoft.planInternational.tracking.database.model.SCFamilyInfos;
import com.nanosoft.planInternational.tracking.database.model.ScGeneralQuestionAnswer;
import com.nanosoft.planInternational.tracking.database.model.ScInfoModel;
import com.nanosoft.planInternational.tracking.jsinterface.DataBroker;
import com.nanosoft.planInternational.tracking.utility.AppControler;
import com.nanosoft.planInternational.tracking.utility.Operation;
import com.nanosoft.planInternational.tracking.utility.customfonts.MyTextView;
import com.nanosoft.planInternational.tracking.view.fragment.SCProfileUpdateFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {
    ImageView scProfleEdit;

    private Button btnEditProfile, btnSurvey;
    RelativeLayout myRelativeLayout;
    private LinearLayout familyInfoLayout;
    private LinearLayout scGeneralQuestionLayout;
    private Fragment sCProfileUpdateFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DatabaseManager databaseManager;
    private ArrayList<SCFamilyInfos> scFamilyInfosesList;
    private ArrayList<ScGeneralQuestionAnswer> scGeneralQuestionList;
    private ScInfoModel scInfoModel;
    private final String photoUrl = Operation.BaseUrl + "/public/uploads/scProfilePhoto/";
    int reminderMonths;
    private int scInfoTableId;
    private MyTextView tvscID, tvscOtherName, tvscDOB, tvReligion, tvLocation, tvLanguage, tvscGender, tvscJoinDate, tvChildFullName, tvChildID, tvscDateOfBirth;
    private ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        findViewById();
        tvChildFullName.setText(scInfoModel.getScInfoScFullName());
        tvscOtherName.setText(scInfoModel.getScInfoScNameKnownBy());
        tvChildID.setText(String.valueOf(scInfoModel.getScInfoScNumber()));
        tvscID.setText(String.valueOf(scInfoModel.getScInfoScNumber()));
        tvscGender.setText(scInfoModel.getScInfoScGender());
        tvscJoinDate.setText(scInfoModel.getJoining_date());
        tvLanguage.setText(scInfoModel.getLanguage());
        tvLocation.setText(scInfoModel.getScInfoLocationAddress());
        tvReligion.setText(scInfoModel.getScInfoReligion());
        tvscDOB.setText(scInfoModel.getScInfoScAge());
        tvscDateOfBirth.setText(scInfoModel.getScInfoDateOfBirth());


        // joiningDateDifference();

     /*   if(reminderMonths<=9){
          //  btnEditProfile.setVisibility(View.VISIBLE);
          //  btnSurvey.setVisibility(View.VISIBLE);
            btnEditProfile.setEnabled(false);
            btnSurvey.setEnabled(true);

        }else if(reminderMonths>9){
            btnEditProfile.setEnabled(true);
            btnSurvey.setEnabled(true);
        }*/


        if (scInfoModel.getPhoto().length() > 0) {
            Glide.with(AppControler.getInstance().getApplicationContext()).load(photoUrl + scInfoModel.getPhoto())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(profile_image);
        } else {
            profile_image.setImageResource(R.drawable.circle_2);
        }

        scProfleEdit = (ImageView) findViewById(R.id.scProfleEdit);

        fillFieldsWithData();
    }

    private void joiningDateDifference() {
        String str[] = "2014-12-1".split("-");
        //  String str[] = scInfoModel.getJoining_date().split("-");
        int year = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        int day = Integer.parseInt(str[2]);

        Calendar thatDay = Calendar.getInstance();
        thatDay.set(Calendar.DAY_OF_MONTH, day);
        thatDay.set(Calendar.MONTH, month); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, year);

        Calendar today = Calendar.getInstance();

        long diff = today.getTimeInMillis() - thatDay.getTimeInMillis(); //result in millis

        long days = diff / (24 * 60 * 60 * 1000);
        long months = days / 30;

        Toast.makeText(this, "Months " + months, Toast.LENGTH_SHORT).show();

        if (months > 12) {
            reminderMonths = (int) (months % 12);
        } else {
            reminderMonths = (int) months;
        }
    }

    List<View> viewList = new ArrayList<>();

    private void GetAllViews(View v) {
        ViewGroup viewgroup = (ViewGroup) v;
        for (int i = 0; i < viewgroup.getChildCount(); i++) {
            View v1 = viewgroup.getChildAt(i);
            if (v1 instanceof TextView)
                viewList.add(v1);
            if (v1 instanceof ViewGroup) GetAllViews(v1);
        }
    }

    private void fillFieldsWithData() {
        Map<String, String> data = databaseManager.getStaticAnswers(scInfoModel.getScInfoScNumber());
        CoordinatorLayout pd = (CoordinatorLayout) findViewById(R.id.profleDetailLayout);
        GetAllViews(pd);
        if (viewList.size() > 0) {
            for (View v : viewList) {
                Object tag = v.getTag();
                String qsid = tag != null ? tag.toString() : "";
                if (qsid.length() > 0) {
                    Object oans = data.get(qsid);
                    String ans = oans != null ? oans.toString() : "";
                    if (v instanceof TextView)
                        ((TextView) v).setText("Answer: " + ans);
                }
            }
        }
    }


    static final int F = 0;

    private void findViewById() {
        databaseManager = new DatabaseManager(this);
        scInfoTableId = getIntent().getIntExtra("ID", 1);
        scInfoModel = databaseManager.getScProfile(scInfoTableId);
        DataBroker.scInfoModel = scInfoModel;
        tvChildFullName = (MyTextView) findViewById(R.id.tvChildFullName);
        tvscOtherName = (MyTextView) findViewById(R.id.tvscOtherName);
        tvChildID = (MyTextView) findViewById(R.id.tvChildID);
        tvscID = (MyTextView) findViewById(R.id.tvscID);
        tvscDOB = (MyTextView) findViewById(R.id.tvscDOB);
        tvscDateOfBirth = (MyTextView) findViewById(R.id.tvscDateOfBirth);
        tvscGender = (MyTextView) findViewById(R.id.tvscGender);
        tvscJoinDate = (MyTextView) findViewById(R.id.tvscJoinDate);
        tvLocation = (MyTextView) findViewById(R.id.tvJoining);
        tvLanguage = (MyTextView) findViewById(R.id.tvLeaving);
        tvReligion = (MyTextView) findViewById(R.id.tvStatus);
        profile_image = (ImageView) findViewById(R.id.profile_imageView);
        familyInfoLayout = (LinearLayout) findViewById(R.id.familyInfoLayout);
        scGeneralQuestionLayout = (LinearLayout) findViewById(R.id.scGeneralQuestionLayout);
        btnEditProfile = (Button) findViewById(R.id.btnEditProfile);
        myRelativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        btnSurvey = (Button) findViewById(R.id.btnSurvey);
        sCProfileUpdateFragment = new SCProfileUpdateFragment();
        fragmentManager = getFragmentManager();
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!scInfoModel.getJoining_date().equalsIgnoreCase("null") && !scInfoModel.getJoining_date().isEmpty()) {
                    reminderMonths = Operation.joiningDateDifference(DetailActivity.this, scInfoModel.getJoining_date());
                    if (reminderMonths < 9) {
                        //  btnEditProfile.setVisibility(View.VISIBLE);
                        //  btnSurvey.setVisibility(View.VISIBLE);
                        showSnackBar();
//                    btnEditProfile.setEnabled(false);
//                    btnSurvey.setEnabled(true);

                    } else if (reminderMonths >= 9) {
                        // btnEditProfile.setEnabled(true);
                        Button button = (Button) view;
                        int id = button.getId();
                        if (id == R.id.btnEditProfile) {
                            btnEditProfile.setBackgroundColor(Color.BLUE);
                            btnSurvey.setBackgroundColor(Color.parseColor("#fb423d"));
                        }
                        SCProfileUpdateFragment.scInfoTableId = scInfoTableId;
                        Intent intent = new Intent(DetailActivity.this, UpdateProfileActivity.class);
                        intent.putExtra("SCINFOTABLEID", scInfoTableId);
                        startActivity(intent);
                        btnSurvey.setEnabled(true);
                    }
                } else {
                    Button button = (Button) view;
                    int id = button.getId();
                    if (id == R.id.btnEditProfile) {
                        btnEditProfile.setBackgroundColor(Color.BLUE);
                        btnSurvey.setBackgroundColor(Color.parseColor("#fb423d"));
                    }
                    SCProfileUpdateFragment.scInfoTableId = scInfoTableId;
                    Intent intent = new Intent(DetailActivity.this, UpdateProfileActivity.class);
                    intent.putExtra("SCINFOTABLEID", scInfoTableId);
                    startActivity(intent);
                    btnSurvey.setEnabled(true);
                }


            }
        });
        horizontallyDesignFamilyInfo();
        horizontallyDesignScGeneralQuestion();
    }


    private void horizontallyDesignFamilyInfo() {
        scFamilyInfosesList = new ArrayList<>();
        scFamilyInfosesList = databaseManager.getFamilyInfo(scInfoTableId);
        for (int i = 0; i < scFamilyInfosesList.size(); i++) {

            CardView cardView = new CardView(this);
            LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            cardLp.setMargins(0, 20, 0, 0);
            cardView.setLayoutParams(cardLp);
            LinearLayout linearLayoutForCArdView = new LinearLayout(this);
            linearLayoutForCArdView.setOrientation(LinearLayout.VERTICAL);
            TextView serialTv = new TextView(this);
            LinearLayout.LayoutParams serialp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            serialp.setMargins(40, 50, 0, 0);
            serialTv.setTextSize(25);
            serialTv.setTextColor(Color.BLACK);
            serialTv.setLayoutParams(serialp);
            TextView nameTv = new TextView(this);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            llp.setMargins(40, 0, 0, 0);
            serialTv.setTextSize(25);
            nameTv.setTextColor(Color.BLACK);
            nameTv.setLayoutParams(llp);
            TextView nameKnownTv = new TextView(this);
            LinearLayout.LayoutParams nkp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            nkp.setMargins(40, 0, 0, 0);
            serialTv.setTextSize(25);
            nameKnownTv.setTextColor(Color.BLACK);
            nameKnownTv.setLayoutParams(nkp);
            TextView birthTv = new TextView(this);
            LinearLayout.LayoutParams birthL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            birthL.setMargins(40, 0, 0, 0);
            serialTv.setTextSize(25);
            birthTv.setTextColor(Color.BLACK);
            birthTv.setLayoutParams(birthL);
            TextView genderTv = new TextView(this);
            LinearLayout.LayoutParams genderL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            genderL.setMargins(40, 0, 0, 0);
            serialTv.setTextSize(25);
            genderTv.setTextColor(Color.BLACK);
            genderTv.setLayoutParams(genderL);
            TextView relationTv = new TextView(this);
            LinearLayout.LayoutParams relationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            relationL.setMargins(40, 0, 0, 0);
            serialTv.setTextSize(25);
            relationTv.setTextColor(Color.BLACK);
            relationTv.setLayoutParams(relationL);
            TextView occupationTv = new TextView(this);
            LinearLayout.LayoutParams occupationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            occupationL.setMargins(40, 0, 0, 0);
            serialTv.setTextSize(25);
            occupationTv.setTextColor(Color.BLACK);
            occupationTv.setLayoutParams(occupationL);
            TextView primaryCareerTv = new TextView(this);
            LinearLayout.LayoutParams primaryCareerL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            primaryCareerL.setMargins(40, 0, 0, 0);
            serialTv.setTextSize(25);
            primaryCareerTv.setTextColor(Color.BLACK);
            primaryCareerTv.setLayoutParams(primaryCareerL);

            TextView reasonJoinTv = new TextView(this);
            LinearLayout.LayoutParams reasonJoinL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            reasonJoinL.setMargins(40, 0, 0, 50);
            serialTv.setTextSize(25);
            reasonJoinTv.setTextColor(Color.BLACK);
            reasonJoinTv.setLayoutParams(reasonJoinL);
            View view = new View(this);
            view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 1));
            view.setBackgroundColor(Color.parseColor("#a33b07"));
            serialTv.setId(i);
            nameTv.setId(i);
            nameKnownTv.setId(i);
            birthTv.setId(i);
            genderTv.setId(i);
            relationTv.setId(i);
            occupationTv.setId(i);
            primaryCareerTv.setId(i);
            reasonJoinTv.setId(i);
            linearLayoutForCArdView.addView(serialTv);
            linearLayoutForCArdView.addView(nameTv);
            linearLayoutForCArdView.addView(nameKnownTv);
            linearLayoutForCArdView.addView(birthTv);
            linearLayoutForCArdView.addView(genderTv);
            linearLayoutForCArdView.addView(relationTv);
            linearLayoutForCArdView.addView(primaryCareerTv);
            linearLayoutForCArdView.addView(occupationTv);

            linearLayoutForCArdView.addView(reasonJoinTv);

            cardView.addView(linearLayoutForCArdView);
            familyInfoLayout.addView(cardView);
            if (serialTv.getId() == i) {
                //nameTv.setText("NAME : " + name[i]);
                serialTv.setText("SERIAL NO : " + (i + 1));
            }
            if (nameTv.getId() == i) {
                //nameTv.setText("NAME : " + name[i]);
                nameTv.setText("3.2) FULL NAME : " + scFamilyInfosesList.get(i).getMember_full_name());
            }
            if (nameKnownTv.getId() == i) {
                //nameTv.setText("NAME : " + name[i]);
                nameKnownTv.setText("3.2.A) NAME KNOWN BY : " + scFamilyInfosesList.get(i).getMember_name_known_by());
            }
            if (birthTv.getId() == i) {
                //   birthTv.setText("BIRTH : " + birth[i]);
                birthTv.setText("3.4) BIRTH YEAR : " + scFamilyInfosesList.get(i).getMember_birth_year());
            }
            if (genderTv.getId() == i) {
                //   birthTv.setText("BIRTH : " + birth[i]);
                genderTv.setText("3.5) GENDER : " + scFamilyInfosesList.get(i).getMember_gender());
            }
            if (relationTv.getId() == i) {
                relationTv.setText("3.5.A) RELATIONSHIP  : " + scFamilyInfosesList.get(i).getMember_relationship());
            }
            if (primaryCareerTv.getId() == i) {
                primaryCareerTv.setText("3.6) CARER : " + scFamilyInfosesList.get(i).getMember_is_primary_carer());
            }
            if (occupationTv.getId() == i) {
                occupationTv.setText("3.7) OCCUPATION : " + scFamilyInfosesList.get(i).getMember_occupation());
            }
            if (reasonJoinTv.getId() == i) {
                reasonJoinTv.setText("3.9) REASON FOR JOIN : "+ scFamilyInfosesList.get(i).getReason_family_lives_with_SC());
            }
        }
    }


    private void horizontallyDesignScGeneralQuestion() {
        scGeneralQuestionList = new ArrayList<>();
        scGeneralQuestionList = databaseManager.getScGeneralQuestionAnswerList(scInfoModel.getScInfoScNumber());


        ArrayList<ScGeneralQuestionAnswer> filteredList = new ArrayList<>();
        String currentSer = "";
        for (int i = 0; i < scGeneralQuestionList.size(); i++) {
            ScGeneralQuestionAnswer answer= scGeneralQuestionList.get(i);
            if (!currentSer.equalsIgnoreCase(answer.getQuestion_serial_no())) {
                currentSer = answer.getQuestion_serial_no();
                filteredList.add(answer);
            }
        }

//        fAdded = false;
//        for (int i = 0; i < scGeneralQuestionList.size(); i++) {
//            if (!fAdded && scGeneralQuestionList.get(i).getQuestion_serial_no().equalsIgnoreCase("5.5")) {
//                fAdded = true;
//                filteredList.add(scGeneralQuestionList.get(i));
//            }
//            if (!scGeneralQuestionList.get(i).getQuestion_serial_no().equalsIgnoreCase("5.5")) {
//                filteredList.add(scGeneralQuestionList.get(i));
//            }
//        }

        ScGeneralQuestionAnswer scGenQuestionAnswer = new ScGeneralQuestionAnswer();

        scGenQuestionAnswer.setQuestion_name("SC Favourite Subject:");

        for (int i = 0; i < filteredList.size(); i++) {

            if(filteredList.get(i).getQuestion_serial_no().equalsIgnoreCase("5.7")){
                scGenQuestionAnswer.setAnswer(filteredList.get(i).getAnswer());
                scGenQuestionAnswer.setDate(filteredList.get(i).getDate());
                scGenQuestionAnswer.setQuestion_serial_no(filteredList.get(i).getQuestion_serial_no());
                scGenQuestionAnswer.setSc_id(filteredList.get(i).getSc_id());
                scGenQuestionAnswer.setStatic_ans_id(filteredList.get(i).getStatic_ans_id());
                scGenQuestionAnswer.setStatic_question_id(filteredList.get(i).getStatic_question_id());


                filteredList.set(i,scGenQuestionAnswer);
            }
        }


        for (int i = 0; i < filteredList.size(); i++) {


            ScGeneralQuestionAnswer scGeneralQuestionAnswer = filteredList.get(i);
            double qsNumber = Double.parseDouble(scGeneralQuestionAnswer.getQuestion_serial_no().substring(0, 3));
            if (qsNumber > 9.1)
                break;
            CardView cardView = new CardView(this);
            LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            cardLp.setMargins(0, 20, 0, 0);
            cardView.setLayoutParams(cardLp);
            LinearLayout linearLayoutForCArdView = new LinearLayout(this);
            linearLayoutForCArdView.setOrientation(LinearLayout.VERTICAL);

            if (qsNumber == 9.1) {
                String ans = scGeneralQuestionAnswer.getAnswer();
                if (ans.length() > 4) {
                    ImageView imageView = new ImageView(this);
                    LinearLayout.LayoutParams lpforImageView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                    lpforImageView.gravity = Gravity.CENTER;
                    imageView.setLayoutParams(lpforImageView);
                    byte[] decodedString = Base64.decode(ans, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    imageView.setImageBitmap(decodedByte);

                    TextView questionNameTv = new TextView(this);
                    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                    llp.setMargins(40, 40, 0, 0);
                    questionNameTv.setTextColor(Color.BLACK);
                    questionNameTv.setTextSize(20);
                    questionNameTv.setLayoutParams(llp);
                    LinearLayout.LayoutParams birthL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                    birthL.setMargins(40, 0, 0, 0);
                    TextView dateTv = new TextView(this);
                    LinearLayout.LayoutParams dateL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                    dateL.setMargins(40, 0, 0, 40);
                    dateTv.setTextSize(16);
                    dateTv.setTextColor(Color.BLACK);
                    dateTv.setLayoutParams(birthL);
                    View view = new View(this);
                    view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 1));
                    view.setBackgroundColor(Color.parseColor("#a33b07"));
                    questionNameTv.setId(i);
                    dateTv.setId(i);
                    linearLayoutForCArdView.addView(questionNameTv);
                    linearLayoutForCArdView.addView(imageView);
                    linearLayoutForCArdView.addView(dateTv);
                    cardView.addView(linearLayoutForCArdView);
                    scGeneralQuestionLayout.addView(cardView);
                    if (questionNameTv.getId() == i) {
                        questionNameTv.setText(filteredList.get(i).getQuestion_serial_no() + " : " + filteredList.get(i).getQuestion_name());
                    }
                    if (dateTv.getId() == i) {
                        dateTv.setText("Last Update Date : " + filteredList.get(i).getDate());
                    }
                    continue;
                }
            }
            TextView questionNameTv = new TextView(this);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            llp.setMargins(40, 40, 0, 0);
            questionNameTv.setTextColor(Color.BLACK);
            questionNameTv.setTextSize(20);
            questionNameTv.setLayoutParams(llp);
            TextView answerTv = new TextView(this);
            LinearLayout.LayoutParams birthL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            birthL.setMargins(40, 0, 0, 0);
            answerTv.setTextSize(16);
            answerTv.setTextColor(Color.BLACK);
            answerTv.setLayoutParams(birthL);
            TextView dateTv = new TextView(this);
            LinearLayout.LayoutParams dateL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            dateL.setMargins(40, 0, 0, 40);
            dateTv.setTextSize(16);
            dateTv.setTextColor(Color.BLACK);
            dateTv.setLayoutParams(birthL);
            View view = new View(this);
            view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 1));
            view.setBackgroundColor(Color.parseColor("#a33b07"));
            questionNameTv.setId(i);
            answerTv.setId(i);
            dateTv.setId(i);
            linearLayoutForCArdView.addView(questionNameTv);
            linearLayoutForCArdView.addView(answerTv);
            linearLayoutForCArdView.addView(dateTv);


            cardView.addView(linearLayoutForCArdView);
            scGeneralQuestionLayout.addView(cardView);
            if (questionNameTv.getId() == i) {
                questionNameTv.setText(filteredList.get(i).getQuestion_serial_no() + " : " + filteredList.get(i).getQuestion_name());
            }
            if (answerTv.getId() == i) {
                answerTv.setText("Ans : " + filteredList.get(i).getAnswer());
                answerTv.setTag(filteredList.get(i).getQuestion_serial_no());
            }
            if (dateTv.getId() == i) {
                dateTv.setText("Last Update Date : " + filteredList.get(i).getDate());
            }
        }
    }


    public void surveyProject(View view) {
        Button button = (Button) view;
        int id = button.getId();
        if (id == R.id.btnSurvey) {
            btnSurvey.setBackgroundColor(Color.BLUE);
            btnEditProfile.setBackgroundColor(Color.parseColor("#fb423d"));
        }

        Intent intent = new Intent(DetailActivity.this, QuestioneryActivity.class);
        intent.putExtra("surveyEntryId", 0);
        intent.putExtra("scNumber", scInfoModel.getScInfoScNumber());
        startActivity(intent);

    }


    public void showSnackBar() {

        //into threa

        Snackbar.make(myRelativeLayout, getString(R.string.sc_status), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.btn_settings), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    }
                }).setActionTextColor(Color.RED).show();


    }

}
