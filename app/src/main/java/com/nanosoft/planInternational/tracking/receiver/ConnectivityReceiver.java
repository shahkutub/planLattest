package com.nanosoft.planInternational.tracking.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.nanosoft.planInternational.tracking.utility.AppControler;


public class ConnectivityReceiver extends BroadcastReceiver {

public static ConnectivityRecieverListener connectivityRecieverListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(connectivityRecieverListener !=null){
            connectivityRecieverListener.OnNetworkChange(isConnected);
        }
    }

public static boolean isConnected(){
    ConnectivityManager connectivityManager = (ConnectivityManager) AppControler.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

}

    public interface ConnectivityRecieverListener{
        public void OnNetworkChange(boolean inConnected);
    }
}
