package com.ehammo.githubjavapop_mvp_clean.data.repository;

import android.util.Log;

import com.ehammo.githubjavapop_mvp_clean.data.manager.NetworkManager;
import com.ehammo.githubjavapop_mvp_clean.data.repository.local.LocalDataSource;
import com.ehammo.githubjavapop_mvp_clean.data.repository.remote.RemoteDataSource;

public class DataSourceFactory implements IDataSourceFactory {

    private NetworkManager networkManager;

    public DataSourceFactory(NetworkManager networkManager){
        this.networkManager = networkManager;
    }

    @Override
    public DataSource getDataSource() {
        Log.d("DataSourceFactory", "Cache is valid: "+LocalDataSource.getInstance().isValid());
        Log.d("DataSourceFactory", "network is valid: "+networkManager.isNetworkActive());
        if (LocalDataSource.getInstance().isValid()
                || !networkManager.isNetworkActive()) {
            return LocalDataSource.getInstance();
        } else {
            return RemoteDataSource.getInstance();
        }
    }
}
