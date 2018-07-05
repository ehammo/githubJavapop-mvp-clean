package com.ehammo.githubjavapop_mvp_clean.data.repository;

import com.ehammo.githubjavapop_mvp_clean.data.repository.local.LocalDataSource;
import com.ehammo.githubjavapop_mvp_clean.data.repository.remote.RemoteDataSource;

public class DataSourceFactory implements IDataSourceFactory {

    private boolean validCache;

    public DataSourceFactory(){
        validCache = false;
    }

    @Override
    public DataSource getDataSource() {
        if (validCache) {
            return LocalDataSource.getInstance();
        } else {
            return RemoteDataSource.getInstance();
        }
    }
}
