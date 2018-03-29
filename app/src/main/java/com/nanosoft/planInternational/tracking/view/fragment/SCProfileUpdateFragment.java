package com.nanosoft.planInternational.tracking.view.fragment;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.rey.material.widget.FrameLayout;
import com.rey.material.widget.ImageButton;
import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.database.manager.DatabaseManager;
import com.nanosoft.planInternational.tracking.database.model.SCFamilyInfos;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class SCProfileUpdateFragment extends android.app.Fragment {


    public SCProfileUpdateFragment() {
        // Required empty public constructor
    }

    private LinearLayout familyInfoUpdateLayout, addFamilyInfoLayout;


    private View view;


    int familyMember;

    String[] name = {"A", "B", "C", "D", "E"};
    String[] birth = {"2011", "2012", "2013", "2014", "2015"};
    String[] relation = {"Father", "Mother", "Sister", "Brother", "Uncle"};
    String[] occupation = {"Farmer", "HomeMaker", "Doctor", "Student", "Engineer"};
    String[] career = {"No", "No", "Yes", "Yes", "Yes"};

    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> birthList = new ArrayList<>();
    ArrayList<String> relationList = new ArrayList<>();
    ArrayList<String> occupationList = new ArrayList<>();
    ArrayList<String> careerList = new ArrayList<>();

    ArrayList<EditText> nameListEt = new ArrayList<>();
    ArrayList<EditText> birthListEt = new ArrayList<>();
    ArrayList<EditText> relationListEt = new ArrayList<>();
    ArrayList<EditText> occupationListEt = new ArrayList<>();
    ArrayList<EditText> careerListEt = new ArrayList<>();


    ArrayList<ImageButton> deleteBtn = new ArrayList<>();

    private EditText[] nameEt, birthEt, relationEt, occupationEt, careerEt;

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "SCProfileUpdateFragment";
    private ImageView scProfleEdit, imgAddMember, imageView3;
    private RoundedImageView usericon;
    private Button scdobBTN;

/*DATE PICKER*/


    //private EditText nameEt,birthEt,relationEt,occupationEt,careerEt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_scprofile_update, container, false);


        initialize();

        return view;
    }

    private String addSC = "1";

    private void initialize() {
        familyInfoUpdateLayout = (LinearLayout) view.findViewById(R.id.familyInfoUpdateLayout);
        addFamilyInfoLayout = (LinearLayout) view.findViewById(R.id.addFamilyInfoLayout);
        scProfleEdit = (ImageView) view.findViewById(R.id.scProfleEdit);
        imgAddMember = (ImageView) view.findViewById(R.id.imgAddMember);
        imageView3 = (ImageView) view.findViewById(R.id.imageView3);
        usericon = (RoundedImageView) view.findViewById(R.id.usericon);

        databaseManager = new DatabaseManager(getActivity());
        scFamilyInfosesList = new ArrayList<>();
        scFamilyInfosesList = databaseManager.getFamilyInfo(scInfoTableId);
        familyMember = scFamilyInfosesList.size();

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });


      /*  Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("ADDSC")){
                addSC = bundle.getString("ADDSC");
            scInfoTableIdBundle = bundle.getInt("scInfoTableId");
        }*/

        setArrayListValue();

        scProfleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // saveData();
                //saveData1();
                saveData2();
            }
        });

        imgAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addMember();

                addMember1();

            }
        });

        // setData();
        //setData1();
        if (addSC.equals("1")) {
            setData2();
        }

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {


                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                usericon.setImageBitmap(bitmap);
                // Get the url from data
               /* Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    String path = getPathFromURI(selectedImageUri);
                    Log.i(TAG, "Image Path : " + path);
                    // Set the image in ImageView
                    usericon.setImageURI(selectedImageUri);
                }*/
            }
        }
    }

    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);

        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    private void openImageChooser() {
       /* Intent intent = new Intent();
        intent.setType("image*//*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);*/
        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent1, SELECT_PICTURE);
    }

    private void setArrayListValue() {

        for (int i = 0; i < familyMember; i++) {
            nameList.add(name[i]);
            birthList.add(birth[i]);
            relationList.add(relation[i]);
            occupationList.add(occupation[i]);
            careerList.add(career[i]);
        }
    }


    private void addMember() {
        familyMember = familyMember;
        familyMember = familyMember + 1;

        nameEt = new EditText[familyMember];
        birthEt = new EditText[familyMember];
        relationEt = new EditText[familyMember];
        occupationEt = new EditText[familyMember];
        careerEt = new EditText[familyMember];

        name = new String[familyMember];
        birth = new String[familyMember];
        relation = new String[familyMember];
        occupation = new String[familyMember];
        career = new String[familyMember];

        nameEt[familyMember] = new EditText(getActivity());
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        llp.setMargins(40, 50, 0, 0);
        nameEt[familyMember].setHint("Name");
        nameEt[familyMember].setTextColor(Color.BLACK);
        nameEt[familyMember].setLayoutParams(llp);

        // EditText birthEt = new EditText(getActivity());
        birthEt[familyMember] = new EditText(getActivity());
        LinearLayout.LayoutParams birthL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        birthL.setMargins(40, 0, 0, 0);
        birthEt[familyMember].setHint("Birth Year");
        birthEt[familyMember].setTextColor(Color.BLACK);
        birthEt[familyMember].setLayoutParams(birthL);

        //EditText relationEt = new EditText(getActivity());
        relationEt[familyMember] = new EditText(getActivity());
        LinearLayout.LayoutParams relationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        relationL.setMargins(40, 0, 0, 0);
        relationEt[familyMember].setHint("Relation");
        relationEt[familyMember].setTextColor(Color.BLACK);
        relationEt[familyMember].setLayoutParams(relationL);

        //EditText occupationEt = new EditText(getActivity());
        occupationEt[familyMember] = new EditText(getActivity());
        LinearLayout.LayoutParams occupationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        occupationL.setMargins(40, 0, 0, 0);
        occupationEt[familyMember].setHint("Occupation");
        occupationEt[familyMember].setTextColor(Color.BLACK);
        occupationEt[familyMember].setLayoutParams(occupationL);

        //EditText careerEt = new EditText(getActivity());
        careerEt[familyMember] = new EditText(getActivity());
        LinearLayout.LayoutParams primaryCareerL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        primaryCareerL.setMargins(40, 0, 0, 50);
        careerEt[familyMember].setHint("Career");
        careerEt[familyMember].setTextColor(Color.BLACK);
        careerEt[familyMember].setLayoutParams(primaryCareerL);


        View view = new View(getActivity());
        view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 1));
        view.setBackgroundColor(Color.parseColor("#a33b07"));

        nameEt[familyMember].setId(familyMember);
        birthEt[familyMember].setId(familyMember);
        relationEt[familyMember].setId(familyMember);
        occupationEt[familyMember].setId(familyMember);
        careerEt[familyMember].setId(familyMember);

        addFamilyInfoLayout.addView(nameEt[familyMember]);
        addFamilyInfoLayout.addView(birthEt[familyMember]);
        addFamilyInfoLayout.addView(relationEt[familyMember]);
        addFamilyInfoLayout.addView(occupationEt[familyMember]);
        addFamilyInfoLayout.addView(careerEt[familyMember]);
        addFamilyInfoLayout.addView(view);

        familyMember = familyMember + 1;


    }


    private void addMember1() {

        final CardView cardView = new CardView(getActivity());
        LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        cardLp.setMargins(0, 50, 0, 0);
        cardView.setLayoutParams(cardLp);
        LinearLayout linearLayoutForCArdView = new LinearLayout(getActivity());
        linearLayoutForCArdView.setOrientation(LinearLayout.VERTICAL);


        nameList.add("");
        birthList.add("");
        relationList.add("");
        occupationList.add("");
        careerList.add("");


        deleteBtn.add(new ImageButton(getActivity()));
        deleteBtn.get(familyMember).setBackgroundColor(Color.parseColor("#ffffff"));
        deleteBtn.get(familyMember).setImageResource(R.drawable.ic_action_delete);


        deleteBtn.get(familyMember).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageButton b = (ImageButton) view;
                //int id = (int) b.getTag();
                int id = deleteBtn.indexOf(b);

                nameListEt.get(id).setText("");
                birthListEt.get(id).setText("");
                relationListEt.get(id).setText("");
                occupationListEt.get(id).setText("");
                careerListEt.get(id).setText("");

                familyInfoUpdateLayout.removeView(deleteBtn.get(id));
                familyInfoUpdateLayout.removeView(nameListEt.get(id));
                familyInfoUpdateLayout.removeView(birthListEt.get(id));
                familyInfoUpdateLayout.removeView(relationListEt.get(id));
                familyInfoUpdateLayout.removeView(occupationListEt.get(id));
                familyInfoUpdateLayout.removeView(careerListEt.get(id));
                familyInfoUpdateLayout.removeView(cardView);


                deleteBtn.remove(id);

                nameList.remove(id);
                birthList.remove(id);
                relationList.remove(id);
                occupationList.remove(id);
                careerList.remove(id);

                nameListEt.remove(id);
                birthListEt.remove(id);
                relationListEt.remove(id);
                occupationListEt.remove(id);
                careerListEt.remove(id);

                familyMember = familyMember - 1;


            }
        });


        nameListEt.add(new EditText(getActivity()));

        LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        nameParams.setMargins(40, 50, 0, 0);
        nameListEt.get(familyMember).setHint("Name");
        nameListEt.get(familyMember).setTextColor(Color.BLACK);
        nameListEt.get(familyMember).setLayoutParams(nameParams);


        birthListEt.add(new EditText(getActivity()));
        LinearLayout.LayoutParams birthL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        birthL.setMargins(40, 0, 0, 0);
        birthListEt.get(familyMember).setHint("Birth Year");
        birthListEt.get(familyMember).setTextColor(Color.BLACK);
        birthListEt.get(familyMember).setLayoutParams(birthL);


        relationListEt.add(new EditText(getActivity()));
        LinearLayout.LayoutParams relationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        relationL.setMargins(40, 0, 0, 0);
        relationListEt.get(familyMember).setHint("Relation");
        relationListEt.get(familyMember).setTextColor(Color.BLACK);
        relationListEt.get(familyMember).setLayoutParams(relationL);


        occupationListEt.add(new EditText(getActivity()));
        LinearLayout.LayoutParams occupationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        occupationL.setMargins(40, 0, 0, 0);
        occupationListEt.get(familyMember).setHint("Occupation");
        occupationListEt.get(familyMember).setTextColor(Color.BLACK);
        occupationListEt.get(familyMember).setLayoutParams(occupationL);


        careerListEt.add(new EditText(getActivity()));
        LinearLayout.LayoutParams primaryCareerL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        primaryCareerL.setMargins(40, 0, 0, 50);
        careerListEt.get(familyMember).setHint("Career");
        careerListEt.get(familyMember).setTextColor(Color.BLACK);
        careerListEt.get(familyMember).setLayoutParams(primaryCareerL);


        View view = new View(getActivity());
        view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 1));
        view.setBackgroundColor(Color.parseColor("#a33b07"));


        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.RIGHT);
        linearLayout.addView(deleteBtn.get(familyMember));
//
        linearLayoutForCArdView.addView(linearLayout);
        linearLayoutForCArdView.addView(nameListEt.get(familyMember));
        linearLayoutForCArdView.addView(birthListEt.get(familyMember));
        linearLayoutForCArdView.addView(relationListEt.get(familyMember));
        linearLayoutForCArdView.addView(occupationListEt.get(familyMember));
        linearLayoutForCArdView.addView(careerListEt.get(familyMember));
        // linearLayoutForCArdView.addView(com.tutorials.hp.recyclersqlite.view);

        cardView.addView(linearLayoutForCArdView);
        familyInfoUpdateLayout.addView(cardView);


        nameListEt.get(familyMember).setText("");
        birthListEt.get(familyMember).setText("");
        relationListEt.get(familyMember).setText("");
        occupationListEt.get(familyMember).setText("");
        careerListEt.get(familyMember).setText("");


        familyMember = familyMember + 1;

    }

    private void setData1() {

        nameEt = new EditText[familyMember];  //nameEt[familyMemberIndex] = new EditText(getActivity());
        birthEt = new EditText[familyMember];
        relationEt = new EditText[familyMember];
        occupationEt = new EditText[familyMember];
        careerEt = new EditText[familyMember];

        for (int i = 0; i < familyMember; i++) {
            nameEt[i] = new EditText(getActivity());
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            llp.setMargins(40, 50, 0, 0);
            nameEt[i].setHint("Name");
            nameEt[i].setTextColor(Color.BLACK);
            nameEt[i].setLayoutParams(llp);

            // EditText birthEt = new EditText(getActivity());
            birthEt[i] = new EditText(getActivity());
            LinearLayout.LayoutParams birthL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            birthL.setMargins(40, 0, 0, 0);
            birthEt[i].setHint("Birth Year");
            birthEt[i].setTextColor(Color.BLACK);
            birthEt[i].setLayoutParams(birthL);

            //EditText relationEt = new EditText(getActivity());
            relationEt[i] = new EditText(getActivity());
            LinearLayout.LayoutParams relationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            relationL.setMargins(40, 0, 0, 0);
            relationEt[i].setHint("Relation");
            relationEt[i].setTextColor(Color.BLACK);
            relationEt[i].setLayoutParams(relationL);

            //EditText occupationEt = new EditText(getActivity());
            occupationEt[i] = new EditText(getActivity());
            LinearLayout.LayoutParams occupationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            occupationL.setMargins(40, 0, 0, 0);
            occupationEt[i].setHint("Occupation");
            occupationEt[i].setTextColor(Color.BLACK);
            occupationEt[i].setLayoutParams(occupationL);

            //EditText careerEt = new EditText(getActivity());
            careerEt[i] = new EditText(getActivity());
            LinearLayout.LayoutParams primaryCareerL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            primaryCareerL.setMargins(40, 0, 0, 50);
            careerEt[i].setHint("Career");
            careerEt[i].setTextColor(Color.BLACK);
            careerEt[i].setLayoutParams(primaryCareerL);


            View view = new View(getActivity());
            view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 1));
            view.setBackgroundColor(Color.parseColor("#a33b07"));

            nameEt[i].setId(i);
            birthEt[i].setId(i);
            relationEt[i].setId(i);
            occupationEt[i].setId(i);
            careerEt[i].setId(i);

            familyInfoUpdateLayout.addView(nameEt[i]);
            familyInfoUpdateLayout.addView(birthEt[i]);
            familyInfoUpdateLayout.addView(relationEt[i]);
            familyInfoUpdateLayout.addView(occupationEt[i]);
            familyInfoUpdateLayout.addView(careerEt[i]);
            familyInfoUpdateLayout.addView(view);

            if (nameEt[i].getId() == i) {
                nameEt[i].setText(name[i]);
            }
            if (birthEt[i].getId() == i) {
                birthEt[i].setText(birth[i]);
            }
            if (relationEt[i].getId() == i) {
                relationEt[i].setText(relation[i]);
            }
            if (occupationEt[i].getId() == i) {
                occupationEt[i].setText(occupation[i]);
            }
            if (careerEt[i].getId() == i) {
                careerEt[i].setText(career[i]);

            }

        }
    }

    public static int scInfoTableId;
    private int scInfoTableIdBundle;
    private ArrayList<SCFamilyInfos> scFamilyInfosesList;
    private DatabaseManager databaseManager;


    private void setData2() {



       /* nameEt = new EditText[familyMember];  //nameEt[familyMemberIndex] = new EditText(getActivity());
        birthEt = new EditText[familyMember];
        relationEt = new EditText[familyMember];
        occupationEt = new EditText[familyMember];
        careerEt = new EditText[familyMember];*/

        for (int i = 0; i < familyMember; i++) {

            final CardView cardView = new CardView(getActivity());
            LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            cardLp.setMargins(0, 50, 0, 0);
            cardView.setLayoutParams(cardLp);
            LinearLayout linearLayoutForCArdView = new LinearLayout(getActivity());
            linearLayoutForCArdView.setOrientation(LinearLayout.VERTICAL);

            ImageButton btn = new ImageButton(getActivity());
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
                    birthListEt.get(id).setText("");
                    relationListEt.get(id).setText("");
                    occupationListEt.get(id).setText("");
                    careerListEt.get(id).setText("");


                    familyInfoUpdateLayout.removeView(deleteBtn.get(id));
                    familyInfoUpdateLayout.removeView(nameListEt.get(id));
                    familyInfoUpdateLayout.removeView(birthListEt.get(id));
                    familyInfoUpdateLayout.removeView(relationListEt.get(id));
                    familyInfoUpdateLayout.removeView(occupationListEt.get(id));
                    familyInfoUpdateLayout.removeView(careerListEt.get(id));
                    familyInfoUpdateLayout.removeView(cardView);


                    deleteBtn.remove(id);

                    nameList.remove(id);
                    birthList.remove(id);
                    relationList.remove(id);
                    occupationList.remove(id);
                    careerList.remove(id);

                    nameListEt.remove(id);
                    birthListEt.remove(id);
                    relationListEt.remove(id);
                    occupationListEt.remove(id);
                    careerListEt.remove(id);

                    familyMember = familyMember - 1;

                }
            });


            nameListEt.add(new EditText(getActivity()));
            LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            nameParams.setMargins(40, 50, 0, 0);
            nameListEt.get(i).setHint("Name");
            nameListEt.get(i).setTextColor(Color.BLACK);
            nameListEt.get(i).setLayoutParams(nameParams);

            birthListEt.add(new EditText(getActivity()));
            LinearLayout.LayoutParams birthL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            birthL.setMargins(40, 0, 0, 0);
            birthListEt.get(i).setHint("Birth Year");
            birthListEt.get(i).setTextColor(Color.BLACK);
            birthListEt.get(i).setLayoutParams(birthL);


            relationListEt.add(new EditText(getActivity()));
            LinearLayout.LayoutParams relationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            relationL.setMargins(40, 0, 0, 0);
            relationListEt.get(i).setHint("Relation");
            relationListEt.get(i).setTextColor(Color.BLACK);
            relationListEt.get(i).setLayoutParams(relationL);


            occupationListEt.add(new EditText(getActivity()));
            LinearLayout.LayoutParams occupationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            occupationL.setMargins(40, 0, 0, 0);
            occupationListEt.get(i).setHint("Occupation");
            occupationListEt.get(i).setTextColor(Color.BLACK);
            occupationListEt.get(i).setLayoutParams(occupationL);


            careerListEt.add(new EditText(getActivity()));
            LinearLayout.LayoutParams primaryCareerL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            primaryCareerL.setMargins(40, 0, 0, 50);
            careerListEt.get(i).setHint("Career");
            careerListEt.get(i).setTextColor(Color.BLACK);
            careerListEt.get(i).setLayoutParams(primaryCareerL);

            View view = new View(getActivity());
            view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 1));
            view.setBackgroundColor(Color.parseColor("#a33b07"));


            deleteBtn.get(i).setId(i);
            nameListEt.get(i).setId(i);
            birthListEt.get(i).setId(i);
            relationListEt.get(i).setId(i);
            occupationListEt.get(i).setId(i);
            careerListEt.get(i).setId(i);

            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setGravity(Gravity.RIGHT);
            linearLayout.addView(deleteBtn.get(i));

            linearLayoutForCArdView.addView(linearLayout);
            linearLayoutForCArdView.addView(nameListEt.get(i));
            linearLayoutForCArdView.addView(birthListEt.get(i));
            linearLayoutForCArdView.addView(relationListEt.get(i));
            linearLayoutForCArdView.addView(occupationListEt.get(i));
            linearLayoutForCArdView.addView(careerListEt.get(i));

            cardView.addView(linearLayoutForCArdView);
            familyInfoUpdateLayout.addView(cardView);


          /*  nameListEt.get(i).setText(nameList.get(i));
            birthListEt.get(i).setText(birthList.get(i));
            relationListEt.get(i).setText(relationList.get(i));
            occupationListEt.get(i).setText(occupationList.get(i));
            careerListEt.get(i).setText(careerList.get(i));
            */

            nameListEt.get(i).setText(scFamilyInfosesList.get(i).getMember_full_name() + "");
            birthListEt.get(i).setText(scFamilyInfosesList.get(i).getMember_birth_year() + "");
            relationListEt.get(i).setText(scFamilyInfosesList.get(i).getMember_relationship() + "");
            occupationListEt.get(i).setText(scFamilyInfosesList.get(i).getMember_occupation() + "");
            careerListEt.get(i).setText(scFamilyInfosesList.get(i).getMember_is_primary_carer() + "");


        }
    }

    private void setData() {

        for (int i = 0; i < familyMember; i++) {
            EditText nameEt = new EditText(getActivity());
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            llp.setMargins(40, 50, 0, 0);
            nameEt.setHint("Name");
            nameEt.setTextColor(Color.BLACK);
            nameEt.setLayoutParams(llp);

            EditText birthEt = new EditText(getActivity());
            LinearLayout.LayoutParams birthL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            birthL.setMargins(40, 0, 0, 0);
            birthEt.setHint("Birth Year");
            birthEt.setTextColor(Color.BLACK);
            birthEt.setLayoutParams(birthL);

            EditText relationEt = new EditText(getActivity());
            LinearLayout.LayoutParams relationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            relationL.setMargins(40, 0, 0, 0);
            relationEt.setHint("Relation");
            relationEt.setTextColor(Color.BLACK);
            relationEt.setLayoutParams(relationL);

            EditText occupationEt = new EditText(getActivity());
            LinearLayout.LayoutParams occupationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            occupationL.setMargins(40, 0, 0, 0);
            occupationEt.setHint("Occupation");
            occupationEt.setTextColor(Color.BLACK);
            occupationEt.setLayoutParams(occupationL);

            EditText careerEt = new EditText(getActivity());
            LinearLayout.LayoutParams primaryCareerL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            primaryCareerL.setMargins(40, 0, 0, 50);
            careerEt.setHint("Career");
            careerEt.setTextColor(Color.BLACK);
            careerEt.setLayoutParams(primaryCareerL);


            View view = new View(getActivity());
            view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 1));
            view.setBackgroundColor(Color.parseColor("#a33b07"));

            nameEt.setId(i);
            birthEt.setId(i);
            relationEt.setId(i);
            occupationEt.setId(i);
            careerEt.setId(i);

            familyInfoUpdateLayout.addView(nameEt);
            familyInfoUpdateLayout.addView(birthEt);
            familyInfoUpdateLayout.addView(relationEt);
            familyInfoUpdateLayout.addView(occupationEt);
            familyInfoUpdateLayout.addView(careerEt);
            familyInfoUpdateLayout.addView(view);

            if (nameEt.getId() == i) {
                nameEt.setText(name[i]);
            }
            if (birthEt.getId() == i) {
                birthEt.setText(birth[i]);
            }
            if (relationEt.getId() == i) {
                relationEt.setText(relation[i]);
            }
            if (occupationEt.getId() == i) {
                occupationEt.setText(occupation[i]);
            }
            if (careerEt.getId() == i) {
                careerEt.setText(career[i]);

            }

        }
    }

    private void saveData1() {

        for (int i = 0; i < familyMember; i++) {

            name[i] = nameEt[i].getText().toString();

            birth[i] = birthEt[i].getText().toString();

            relation[i] = relationEt[i].getText().toString();

            occupation[i] = occupationEt[i].getText().toString();

            career[i] = careerEt[i].getText().toString();

        }
        for (int i = 0; i < familyMember; i++) {

            nameEt[i].setText(name[i]);

            birthEt[i].setText(birth[i]);

            relationEt[i].setText(relation[i]);

            occupationEt[i].setText(occupation[i]);

            careerEt[i].setText(career[i]);

            Toast.makeText(getActivity(), name[i], Toast.LENGTH_SHORT).show();
        }
    }

    private void saveData2() {


        for (int i = 0; i < familyMember; i++) {
            if (!nameListEt.get(i).isShown()) {

                return;
            }
            // int id = nameList.indexOf(nameListEt.get(i));

            if (nameListEt.get(i).getText().toString().length() > 0) {
                nameList.add(i, nameListEt.get(i).getText().toString());
            }
            if (birthListEt.get(i).getText().toString().length() > 0) {
                birthList.add(i, birthListEt.get(i).getText().toString());
            }
            if (relationListEt.get(i).getText().toString().length() > 0) {
                relationList.add(i, relationListEt.get(i).getText().toString());
            }
            if (occupationListEt.get(i).getText().toString().length() > 0) {
                occupationList.add(i, occupationListEt.get(i).getText().toString());
            }
            if (careerListEt.get(i).getText().toString().length() > 0) {
                careerList.add(i, careerListEt.get(i).getText().toString());
            }


        }
        for (int i = 0; i < nameListEt.size(); i++) {

          /*  nameEt[i].setText(name[i]);

            birthEt[i].setText(birth[i]);

            relationEt[i].setText(relation[i]);

            occupationEt[i].setText(occupation[i]);

            careerEt[i].setText(career[i]);*/

            Toast.makeText(getActivity(), nameList.get(i), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveData() {
       /* for (int i =0; i<familyMember;i++){

            if (nameEt.getId() == i) {
               name[i] =  "Akbar";//nameEt.getText().toString();
            }
            if (birthEt.getId() == i) {
                birth[i] =  birthEt.getText().toString();
            }
            if (relationEt.getId() == i) {
                relation[i] =  relationEt.getText().toString();
            }
            if (occupationEt.getId() == i) {
                occupation[i] =  occupationEt.getText().toString();
            }
            if (careerEt.getId() == i) {
                career[i] =  careerEt.getText().toString();
            }

        }
        for (int i =0; i<familyMember;i++) {

            if (nameEt.getId() == i) {
                nameEt.setText(name[i]);
            }
            if (birthEt.getId() == i) {
                birthEt.setText(birth[i]);
            }
            if (relationEt.getId() == i) {
                relationEt.setText(relation[i]);
            }
            if (occupationEt.getId() == i) {
                occupationEt.setText(occupation[i]);
            }
            if (careerEt.getId() == i) {
                careerEt.setText(career[i]);

            }

            Toast.makeText(getActivity(), name[i], Toast.LENGTH_SHORT).show();
        }*/
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
