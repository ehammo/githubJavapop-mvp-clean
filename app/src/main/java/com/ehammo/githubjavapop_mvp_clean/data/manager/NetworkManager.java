package com.ehammo.githubjavapop_mvp_clean.data.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

public class NetworkManager implements INetworkManager {

    private Context mContext;

    public NetworkManager(Context mContext){
        this.mContext = mContext;
    }

    @Override
    public boolean isNetworkActive() {
        ConnectivityManager cm = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm !=null && cm.getActiveNetwork() != null;
    }
}
