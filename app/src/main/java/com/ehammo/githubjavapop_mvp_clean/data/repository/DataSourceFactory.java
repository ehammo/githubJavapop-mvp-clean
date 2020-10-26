package com.ehammo.githubjavapop_mvp_clean.data.repository;

import android.util.Log;

import com.ehammo.githubjavapop_mvp_clean.data.manager.NetworkManager;
import com.ehammo.githubjavapop_mvp_clean.data.repository.local.LocalDataSource;
import com.ehammo.githubjavapop_mvp_clean.data.repository.remote.RemoteDataSource;

import org.jetbrains.annotations.NotNull;

public class DataSourceFactory implements IDataSourceFactory {

    private final NetworkManager networkManager;

    public DataSourceFactory(@NotNull NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    @Override
    public DataSource getDataSource() {
        Log.d("MainActivity", "Cache is valid: " +
                LocalDataSource.getInstance().isValid());
        Log.d("MainActivity", "network is valid: " +
                networkManager.isNetworkActive());
        // todo : rethink cache strategy
        if (LocalDataSource.getInstance().isValid()
                && !networkManager.isNetworkActive()) {
            return LocalDataSource.getInstance();
        } else {
            return RemoteDataSource.getInstance();
        }
    }
}
