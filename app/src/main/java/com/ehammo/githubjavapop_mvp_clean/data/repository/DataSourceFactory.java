package com.ehammo.githubjavapop_mvp_clean.data.repository;

import com.ehammo.githubjavapop_mvp_clean.data.repository.local.LocalDataSource;
import com.ehammo.githubjavapop_mvp_clean.data.repository.remote.RemoteDataSource;

public class DataSourceFactory implements IDataSourceFactory {


    @Override
    public DataSource getDataSource() {
        if (LocalDataSource.getInstance().isValid()) {
            return LocalDataSource.getInstance();
        } else {
            return RemoteDataSource.getInstance();
        }
    }
}
