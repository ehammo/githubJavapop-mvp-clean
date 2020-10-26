package com.ehammo.githubjavapop_mvp_clean.data.repository;

import com.ehammo.githubjavapop_mvp_clean.data.manager.ICacheManager;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;

import org.jetbrains.annotations.NotNull;

public class DataStore implements IDataStore, DataSource.RepositoryCallback {

    private DataSource.RepositoryCallback mCallback;
    private final IDataSourceFactory factory;
    private final ICacheManager cacheManager;


    public DataStore(@NotNull IDataSourceFactory dataSourceFactory, @NotNull ICacheManager cacheManager) {
        this.factory = dataSourceFactory;
        this.cacheManager = cacheManager;
    }


    @Override
    public void listRepositories(@NotNull DataSource.RepositoryCallback callback, int page) {
        this.mCallback = callback;
        DataSource dataSource = factory.getDataSource();
        if (dataSource.isCacheable()) {
            dataSource.listRepositories(callback, page);
        } else {
            dataSource.listRepositories(this, page);
        }

    }

    @Override
    public void listRepositories(RepositoryCollection repositories, int page) {
        cacheManager.updateCache(repositories, page);
        mCallback.listRepositories(repositories, page);
    }

    @Override
    public void errorMessage(String message) {
        mCallback.errorMessage(message);
    }
}
