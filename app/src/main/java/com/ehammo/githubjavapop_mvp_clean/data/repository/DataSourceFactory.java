package com.ehammo.githubjavapop_mvp_clean.data.repository;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.repository.local.LocalDataSource;
import com.ehammo.githubjavapop_mvp_clean.data.repository.remote.RemoteDataSource;

public class DataSourceFactory implements DataStore, DataSource.RepositoryCallback {

    private DataSource cacheDataSource;
    private DataSource remoteDataSource;
    private boolean validCache;
    private DataSource.RepositoryCallback mCallback;

    public DataSourceFactory(String url){
        cacheDataSource = new LocalDataSource();
        remoteDataSource = new RemoteDataSource(url);
        validCache = false;
    }

    private void invalidateCache(){ validCache = false; }

    @Override
    public void listRepositories(DataSource.RepositoryCallback callback, int page) {
        if (validCache) {
            cacheDataSource.listRepositories(callback, page);
        } else {
            mCallback = callback;
            remoteDataSource.listRepositories(this, page);
        }
    }

    @Override
    public void listRepositories(RepositoryCollection repositories) {
        // todo : update cache
        mCallback.listRepositories(repositories);
    }

    @Override
    public void errorMessage(String message) {

    }
}
