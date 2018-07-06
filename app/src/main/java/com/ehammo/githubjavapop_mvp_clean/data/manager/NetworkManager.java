package com.ehammo.githubjavapop_mvp_clean.data.manager;

import android.content.Context;
import android.net.ConnectivityManager;

import org.jetbrains.annotations.NotNull;

public class NetworkManager implements INetworkManager {

    private Context mContext;

    public NetworkManager(@NotNull Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public boolean isNetworkActive() {
        ConnectivityManager cm = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm !=null && cm.getActiveNetwork() != null;
    }
}
