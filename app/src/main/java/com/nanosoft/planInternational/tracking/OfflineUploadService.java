package com.nanosoft.planInternational.tracking;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nanosoft.planInternational.tracking.offlineupdate.DatabaseHandler;
import com.nanosoft.planInternational.tracking.offlineupdate.OfflineData;
import com.nanosoft.planInternational.tracking.utility.Operation;
import com.nanosoft.planInternational.tracking.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nanosoft-Android on 7/6/2017.
 */

public class OfflineUploadService extends BroadcastReceiver {
    DatabaseHandler db;
    List<OfflineData> listData = new ArrayList<>();

    int pos;
    @Override
    public void onReceive(Context context, Intent intent) {

        db = new DatabaseHandler(context);

        if(isOnline(context)){

            listData = db.getAllData();
            int listsize = listData.size();
            //Toast.makeText(context, "Got data conn.", Toast.LENGTH_SHORT).show();
            if(listsize>0){
                for (int i = 0; i <listData.size() ; i++) {
                    pos=i;
                    senddataToServer(context,listData.get(i).getJsonstr(),intent);

                }
            }




//        for (OfflineData cn : listData) {
//            String log = "Id: "+cn.get_id()+" ,Scid: " + cn.getScid() + " ,Jsonstr: " + cn.getJsonstr();
//            Toast.makeText(context, cn.getScid(), Toast.LENGTH_SHORT).show();
//            senddataToServer(context,cn.getJsonstr());
//        }




//            if (intent.getAction().equals(Intent.ACTION_USER_PRESENT))
//            {
//                Intent intent1 = new Intent(context,FeatureActivity.class);
//                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                context.startActivity(intent1);
           // }
        }

    }

    private void senddataToServer(final Context context, final String Jsonstr, final Intent intent) {
        RequestQueue rq = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Operation.BaseUrl + "/api/save-static-question-answer",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // Toast.makeText(context, "data send false", Toast.LENGTH_SHORT).show();
                       // Log.d("serversend data", response);

                        String responseValue =response;
                        if(responseValue.equalsIgnoreCase("true")){
                            db.deleteContact(listData.get(pos));
//                            Toast.makeText(context, "data send to server", Toast.LENGTH_SHORT).show();

                            sendNotification(context,intent,"Plan International","data send to server!");
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                Log.d("ERRROR", error.getMessage());
                //Toast.makeText(context, "Could not save!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                //String data = finalObject.toString();
                parameters.put("data", Jsonstr);
                return parameters;
            }
        };
        rq.add(stringRequest);
    }

    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }

    private void sendNotification(Context context, Intent intent,String title, String content) {

        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(R.mipmap.ic_app)
                .setContentTitle(title)
                .setContentText(content).setSound(alarmSound)
                .setAutoCancel(true).setWhen(when)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        notificationManager.notify(1, mNotifyBuilder.build());
    }


//    public void sendNotification(Context context) {
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
////        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.journaldev.com/"));
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(), 0);
//        builder.setContentIntent(pendingIntent);
//
//        builder.setContentTitle("Notification!");
//        builder.setContentText("data send to server.");
//        //builder.setSubText("Tap to view the website.");
//
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//
//        // Will display the notification in the notification bar
//        notificationManager.notify(1, builder.build());
//    }


}
