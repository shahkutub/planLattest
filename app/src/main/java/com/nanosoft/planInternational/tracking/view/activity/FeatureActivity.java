package com.nanosoft.planInternational.tracking.view.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cocosw.bottomsheet.BottomSheet;
import com.cocosw.bottomsheet.BottomSheetHelper;
import com.cocosw.query.CocoQuery;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.database.manager.DatabaseManager;
import com.nanosoft.planInternational.tracking.database.manager.QuestioneryManager;
import com.nanosoft.planInternational.tracking.database.model.ScInfoModel;
import com.nanosoft.planInternational.tracking.utility.AppConstant;
import com.nanosoft.planInternational.tracking.utility.Operation;
import com.nanosoft.planInternational.tracking.view.fragment.SCProfileUpdateFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FeatureActivity extends AppCompatActivity implements
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMarkerDragListener,
        SeekBar.OnSeekBarChangeListener,
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowLongClickListener,
        GoogleMap.OnInfoWindowCloseListener, AdapterView.OnItemClickListener {
    //private ExpandableMenuOverlay menuOverlay;

    private String url;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    private double currentLat;
    private double currentLong;
    private GoogleMap mMap;
    private Marker mDinajpur, mKurigram, Lalmonirhat, mBarguna, Nilphamari,
            mDhaka, mBarisal, mBhola, mBandarban, mChittagong, mKhagrachari,
            mGazipur, mSunamganj, mSylhet, mRangpur;

    /*FOR MAP*/
    private static final LatLng LALMONIRHAT = new LatLng(25.9216304, 89.4321976);

    private static final LatLng NILPHAMARI = new LatLng(25.9374234, 88.7096093);

    private static final LatLng KURIGRAM = new LatLng(25.8327425, 89.4141963);

    private static final LatLng BARGUNA = new LatLng(22.1141102, 89.828267);

    private static final LatLng DINAJPUR = new LatLng(25.6324867, 88.3906223);

    private static final LatLng DHAKA = new LatLng(23.7921954, 90.4111983);

    private static final LatLng BARISAL = new LatLng(22.695541, 90.2831919);
    private static final LatLng BHOLA = new LatLng(22.6753766, 90.4675846);

    private static final LatLng BANDARBAN = new LatLng(22.1588834, 91.9460285);

    private static final LatLng CHITTAGONG = new LatLng(22.3284784, 91.6828992);

    private static final LatLng KHAGRACHARI = new LatLng(23.1473542, 91.7110674);

    private static final LatLng GAZIPUR = new LatLng(24.0302627, 90.0932451);

    private static final LatLng SUNAMGANJ = new LatLng(25.0445543, 91.1019276);


    private static final LatLng SYLHET = new LatLng(24.9442206, 91.5713786);


    private static final LatLng RANGPUR = new LatLng(25.7481124, 88.9260772);

    //private static final LatLng ALICE_SPRINGS = new LatLng(-24.6980, 133.8807);

    /**
     * Demonstrates customizing the info window and/or its contents.
     */
    private Operation operation;


    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        // These are both viewgroups containing an ImageView with id "badge" and two TextViews with id
        // "title" and "snippet".
        // private final View mWindow;

        private final View mContents;

        CustomInfoWindowAdapter() {
            //  mWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);
            mContents = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }


        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {

            render(marker, mContents);
            return mContents;
        }

        private void render(Marker marker, View view) {
            int badge;
            // Use the equals() method on a Marker to check for equals.  Do not use ==.
            if (marker.equals(Lalmonirhat)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(mBarguna)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(mKurigram)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(Nilphamari)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(mDinajpur)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(mDhaka)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(mBarisal)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(mBhola)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(mBandarban)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(mChittagong)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(mKhagrachari)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(mGazipur)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(mSunamganj)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(mSylhet)) {
                badge = R.drawable.ic_action_marker;
            } else if (marker.equals(mRangpur)) {
                badge = R.drawable.ic_action_marker;
            } else {
                // Passing 0 to setImageResource will clear the image com.tutorials.hp.recyclersqlite.view.
                badge = 0;

            }
            ((ImageView) view.findViewById(R.id.badge)).setImageResource(badge);

            String title = marker.getTitle();
            TextView titleUi = ((TextView) view.findViewById(R.id.title));
            if (title != null) {
                // Spannable string allows us to edit the formatting of the text.
                SpannableString titleText = new SpannableString(title);
                titleText.setSpan(new ForegroundColorSpan(Color.RED), 0, titleText.length(), 0);
                titleUi.setText(titleText);
            } else {
                titleUi.setText("");
            }

            String snippet = marker.getSnippet();
            TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
            if (snippet != null && snippet.length() > 12) {
                SpannableString snippetText = new SpannableString(snippet);
                snippetText.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 10, 0);
                snippetText.setSpan(new ForegroundColorSpan(Color.BLUE), 12, snippet.length(), 0);
                snippetUi.setText(snippetText);
            } else {
                snippetUi.setText("");
            }
        }
    }


    private Marker mLastSelectedMarker;

    private final List<Marker> mMarkerRainbow = new ArrayList<Marker>();

    private TextView mTopText;

    private TextView mTagText;


    private final Random mRandom = new Random();
    /*FOR MAP*/

    RecyclerView rv;


    private Fragment sCProfileUpdateFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    Toolbar myToolbar;

    /*BOTTOM SHEET*/
    private CocoQuery q;
    private int action;
    private ArrayAdapter<String> adapterBoom;
    private BottomSheet sheet;

    /*BOTTOM SHEET*/
    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //  creatBoomMenu();

        databaseManager = new DatabaseManager(this);
        initializer();

             /*bottom menu*/
        q = new CocoQuery(this);
        String[] items = new String[]{"Menu"};
        q.id(R.id.listView)
                .adapter(adapterBoom = new ArrayAdapter<>(this, R.layout.menu_list, R.id.tvMenu, items))
                .itemClicked(this);


/*FOR MAP*/
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //recycler
        rv = (RecyclerView) findViewById(R.id.myRecycler);

        //SET ITS PROPS
        rv.setLayoutManager(new LinearLayoutManager(this.getBaseContext()));
        rv.setItemAnimator(new DefaultItemAnimator());


        //retrieve();
        QuestioneryManager qmgr = new QuestioneryManager(this);
        qmgr.UploadAnswers();
    }

    private void initializer() {
        sCProfileUpdateFragment = new SCProfileUpdateFragment();
        fragmentManager = getFragmentManager();
        operation = new Operation(this);
    }


    //BOTTOM MENU
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        showDialog(position);
    }

    private Drawable getRoundedBitmap(int imageId) {
        Bitmap src = BitmapFactory.decodeResource(getResources(), imageId);
        Bitmap dst;
        if (src.getWidth() >= src.getHeight()) {
            dst = Bitmap.createBitmap(src, src.getWidth() / 2 - src.getHeight() / 2, 0, src.getHeight(), src.getHeight());
        } else {
            dst = Bitmap.createBitmap(src, 0, src.getHeight() / 2 - src.getWidth() / 2, src.getWidth(), src.getWidth());
        }
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), dst);
        roundedBitmapDrawable.setCornerRadius(dst.getWidth() / 2);
        roundedBitmapDrawable.setAntiAlias(true);
        return roundedBitmapDrawable;
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    protected Dialog onCreateDialog(final int position, Bundle args) {
        switch (action) {
            case 0:
            /*    sheet = new BottomSheet.Builder(this).icon(getRoundedBitmap(R.drawable.icon)).title("To " + adapter.getItem(position)).sheet(R.menu.list).listener(new DialogInterface.MyCustomClass() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListAcitivty.this.onClick(adapter.getItem(position), which);
                    }
                }).build();*/

                sheet = new BottomSheet.Builder(this, R.style.BottomSheet_StyleDialog).icon(getRoundedBitmap(R.drawable.splash)).title(R.string.bottomsheet).sheet(R.menu.list).listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FeatureActivity.this.onClick(adapterBoom.getItem(position), which);
                    }
                }).build();
                break;

        }
        return sheet;
    }

    private BottomSheet.Builder getShareActions(String text) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);

        return BottomSheetHelper.shareAction(this, shareIntent);
    }


    void onClick(String name, int which) {
        switch (which) {
            case R.id.sc_scheduling:
                //  q.toast("Share to " + name);
                operation = new Operation(this);

                String userEmail = operation.getString("email", "");
                String userPassword = operation.getString("password", "");
                if (userEmail.length() > 1 && userPassword.length() > 1) {

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(this, "Login First To Schedule", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                }
                break;
            case R.id.sc_servey:

                for (int i = 0; i <AppConstant.loadSharedPreferencesLogList(getApplicationContext()).size() ; i++) {
                    databaseManager.priorityUpdate(AppConstant.loadSharedPreferencesLogList(getApplicationContext()).get(i).getScInfoTableId(),"1",AppConstant.loadSharedPreferencesLogList(getApplicationContext()).get(i).getDateFlag());
                }

                startActivity(new Intent(FeatureActivity.this, SurveyListActivity.class));

                break;
            case R.id.general_survey:
                Intent intent = new Intent(FeatureActivity.this, QuestioneryActivity.class);
                intent.putExtra("surveyEntryId", 0);
                intent.putExtra("scNumber", 0);
                startActivity(intent);

//                showDialogGeneralSurveys();
                break;
    /*        case R.id.survey_history:
                startActivity(new Intent(FeatureActivity.this, SurveyHistoryActivity.class));
                break;*/
            case R.id.sync_server:

                ArrayList<String> dateArrayList = new ArrayList<>();
                dateArrayList.clear();
                dateArrayList = databaseManager.getDateList("1");
                ArrayList<ScInfoModel> sponsoredChildInfoArrayList = new ArrayList<>();


                if(dateArrayList.size()>0){
                    sponsoredChildInfoArrayList.clear();
                    for (int i = 0; i <dateArrayList.size() ; i++) {

                        sponsoredChildInfoArrayList = databaseManager.getPrioritySCListByDate(dateArrayList.get(i));
                    }

                    AppConstant.saveSharedPreferencesLogList(getApplicationContext(),sponsoredChildInfoArrayList);
                }


                startActivity(new Intent(FeatureActivity.this, UpdateDatabaseActivity.class));
                break;
            case R.id.server_url:
             /*   startActivity(new Intent(FeatureActivity.this, UpdateDatabaseActivity.class));*/
                showDialog();
                break;
            case R.id.signout:
                operation.LogOut();
                //  startActivity(new Intent(FeatureActivity.this,WelcomeActivity.class));
                // new DatabaseManager(FeatureActivity.this).getAllTable();
                // startActivity(new Intent(FeatureActivity.this, AlertActivity.class));
                break;
        }
    }

    private void getData() {
        Type listOfObjects = new TypeToken<ArrayList<ScInfoModel>>(){}.getType();
        Gson gson= new Gson();
        SharedPreferences myPrefs = getSharedPreferences("data", Context.MODE_PRIVATE);
        String json = myPrefs.getString("data", "");
        ArrayList<ScInfoModel> list2 = gson.fromJson(json, listOfObjects);
    }

    private void save(ArrayList<ScInfoModel> spChildInfoList) {

        Type listOfObjects = new TypeToken<ArrayList<ScInfoModel>>(){}.getType();

        Gson gson= new Gson();
        String strObject = gson.toJson(spChildInfoList, listOfObjects); // Here list is your List<CUSTOM_CLASS> object
        SharedPreferences myPrefs = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putString("MyList", strObject);
        prefsEditor.commit();
    }


    public void showDialog() {

        String savedUrl = operation.getString("URL", "");
        Operation.BaseUrl = TextUtils.isEmpty(savedUrl) ? Operation.BaseUrl : savedUrl;

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(FeatureActivity.this);
        alertDialog.setTitle("Enter the URL you would like to connect:");
        // alertDialog.setTitle("IP ADDRESS");
        //  alertDialog.setMessage("Enter The URL You Would Like To Connect:");

        final EditText input = new EditText(FeatureActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        alertDialog.setIcon(R.drawable.custom_img);
        input.setText(operation.getString("URL", ""));
        alertDialog.setPositiveButton("Connect",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (!TextUtils.isEmpty(input.getText().toString())) {
                            url = input.getText().toString();
                            if (url.equalsIgnoreCase(operation.getString("URL", ""))) {
                                Toast.makeText(FeatureActivity.this, "URL Already Entered" + operation.getString("URL", ""), Toast.LENGTH_SHORT).show();
                                Operation.BaseUrl = url;

                            } else if (!url.equalsIgnoreCase(operation.getString("URL", ""))) {
                                Toast.makeText(getApplicationContext(),
                                        "Entered new URL!", Toast.LENGTH_SHORT).show();
                                operation.saveString("URL", url);
                                startActivity(new Intent(FeatureActivity.this, UpdateDatabaseActivity.class));
                            }
                        } else {
                            alertDialog.setCancelable(false);
                            Toast.makeText(FeatureActivity.this, "Entered Your Url", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        alertDialog.setNegativeButton("Skip",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
        alertDialog.setCancelable(false);

        alertDialog.show();


    }


    @Override
    protected void onResume() {
        super.onResume();
        QuestioneryManager qmgr = new QuestioneryManager(this);
        qmgr.UploadAnswers();

    }


    /*START TO SHOW MAP WITH SC INFORMATION*/


    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;


        // Hide the zoom controls as the button panel will cover it.
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Add lots of markers to the map.
        addMarkersToMap();

        // Setting an info window adapter allows us to change the both the contents and look of the
        // info window.
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

        // Set listeners for marker events.  See the bottom of this class for their behavior.
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerDragListener(this);
        mMap.setOnInfoWindowCloseListener(this);
        mMap.setOnInfoWindowLongClickListener(this);

        // Override the default content description on the com.tutorials.hp.recyclersqlite.view, for accessibility mode.
        // Ideally this string would be localised.
        map.setContentDescription("Map with lots of markers.");

        // Pan to see all markers in com.tutorials.hp.recyclersqlite.view.
        // Cannot zoom to bounds until the map has a size.
        final View mapView = getSupportFragmentManager().findFragmentById(R.id.map).getView();
        if (mapView.getViewTreeObserver().isAlive()) {
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @SuppressWarnings("deprecation") // We use the new method when supported
                @SuppressLint("NewApi") // We check which build version we are using.
                @Override
                public void onGlobalLayout() {
                    LatLngBounds bounds = new LatLngBounds.Builder()
                            .include(DINAJPUR)
                            .include(KURIGRAM)
                            .include(BARGUNA)
                            .include(LALMONIRHAT)
                            .include(NILPHAMARI)
                            .include(DHAKA)
                            .include(BARISAL)
                            .include(BHOLA)
                            .include(BANDARBAN)
                            .include(CHITTAGONG)
                            .include(KHAGRACHARI)
                            .include(GAZIPUR)
                            .include(SUNAMGANJ)
                            .include(SYLHET)
                            .include(RANGPUR)
                            .build();
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
                }
            });
        }
    }


    private void addMarkersToMap() {
        // Uses a colored icon.
        Lalmonirhat = mMap.addMarker(new MarkerOptions()
                .position(LALMONIRHAT)
                .title("Lalmonirhat")
                .snippet("Population: 5138 SC")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        // Uses a custom icon with the info window popping out of the center of the icon.
        mKurigram = mMap.addMarker(new MarkerOptions()
                .position(KURIGRAM)
                .title("Kurigram")
                .snippet("Operational Area"));


        // Creates a draggable marker. Long press to drag.
        Nilphamari = mMap.addMarker(new MarkerOptions()
                .position(NILPHAMARI)
                .title("Nilphamari")
                .snippet("Population: 9,000 SC")
                .draggable(true));

        // A few more markers for good measure.
        mDinajpur = mMap.addMarker(new MarkerOptions()
                .position(DINAJPUR)
                .title("Dinajpur")
                .snippet("Population: 2,684 SC"));

        mBarguna = mMap.addMarker(new MarkerOptions()
                .position(BARGUNA)
                .title("Barguna")
                .snippet("Population: 4,768 SC"));

        // Uses a custom icon with the info window popping out of the center of the icon.
        mDhaka = mMap.addMarker(new MarkerOptions()
                .position(DHAKA)
                .title("Dhaka")
                .snippet("Population: 5,302 SC(Country Office)")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow))
                .infoWindowAnchor(0.5f, 0.5f));

        mBarisal = mMap.addMarker(new MarkerOptions()
                .position(BARISAL)
                .title("Barisal")
                .snippet("Operational Area"));

        mBhola = mMap.addMarker(new MarkerOptions()
                .position(BHOLA)
                .title("Bhola")
                .snippet("Population: 2,97 SC"));

        mBandarban = mMap.addMarker(new MarkerOptions()
                .position(BANDARBAN)
                .title("Bandarban")
                .snippet("Operational Area"));

        mChittagong = mMap.addMarker(new MarkerOptions()
                .position(CHITTAGONG)
                .title("Chittagong")
                .snippet("Operational Area"));

        mKhagrachari = mMap.addMarker(new MarkerOptions()
                .position(KHAGRACHARI)
                .title("Khagrachari")
                .snippet("Operational Area"));

        mGazipur = mMap.addMarker(new MarkerOptions()
                .position(GAZIPUR)
                .title("Gazipur")
                .snippet("Population: 2,778 SC"));

        mSunamganj = mMap.addMarker(new MarkerOptions()
                .position(SUNAMGANJ)
                .title("Sunamganj")
                .snippet("Operational Area"));

        mSylhet = mMap.addMarker(new MarkerOptions()
                .position(SYLHET)
                .title("Sylhet")
                .snippet("Operational Area"));

        mRangpur = mMap.addMarker(new MarkerOptions()
                .position(RANGPUR)
                .title("Rangpur")
                .snippet("Operational Area"));


    }

    /**
     * Demonstrates converting a {@link Drawable} to a {@link BitmapDescriptor},
     * for use as a marker icon.
     */
    private BitmapDescriptor vectorToBitmap(@DrawableRes int id, @ColorInt int color) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(vectorDrawable, color);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(this, R.string.map_not_ready, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Called when the Clear button is clicked.
     */
    public void onClearMap(View view) {
        if (!checkReady()) {
            return;
        }
        mMap.clear();
    }

    /**
     * Called when the Reset button is clicked.
     */
    public void onResetMap(View view) {
        if (!checkReady()) {
            return;
        }
        // Clear the map because we don't want duplicates of the markers.
        mMap.clear();
        addMarkersToMap();
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!checkReady()) {
            return;
        }
        float rotation = seekBar.getProgress();
        for (Marker marker : mMarkerRainbow) {
            marker.setRotation(rotation);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Do nothing.
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Do nothing.
    }


    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (marker.equals(mDinajpur)
                | marker.equals(mDhaka)
                | marker.equals(mBarisal)
                | marker.equals(mBhola)
                | marker.equals(mBandarban)
                | marker.equals(mChittagong)
                | marker.equals(mKhagrachari)
                | marker.equals(mGazipur)
                | marker.equals(mSunamganj)
                | marker.equals(mSylhet)
                | marker.equals(mRangpur)
                | marker.equals(mKurigram)
                | marker.equals(Nilphamari)
                | marker.equals(Lalmonirhat)

                ) {
            // This causes the marker at Perth to bounce into position when it is clicked.
            final Handler handler = new Handler();
            final long start = SystemClock.uptimeMillis();
            final long duration = 1500;

            final Interpolator interpolator = new BounceInterpolator();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    long elapsed = SystemClock.uptimeMillis() - start;
                    float t = Math.max(
                            1 - interpolator.getInterpolation((float) elapsed / duration), 0);
                    marker.setAnchor(0.5f, 1.0f + 2 * t);

                    if (t > 0.0) {
                        // Post again 16ms later.
                        handler.postDelayed(this, 16);
                    }
                }
            });
        } else if (marker.equals(mBarguna)) {
            // This causes the marker at Adelaide to change color and alpha.
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(mRandom.nextFloat() * 360));
            marker.setAlpha(mRandom.nextFloat());
        }


        mLastSelectedMarker = marker;
        // We return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Click Info Window", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInfoWindowClose(Marker marker) {
        //Toast.makeText(this, "Close Info Window", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInfoWindowLongClick(Marker marker) {
        Toast.makeText(this, "Info Window long click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        mTopText.setText("onMarkerDragStart");
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        mTopText.setText("onMarkerDragEnd");
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        mTopText.setText("onMarkerDrag.  Current Position: " + marker.getPosition());
    }


}
