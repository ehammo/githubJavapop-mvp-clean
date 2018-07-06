package com.ehammo.githubjavapop_mvp_clean.data.repository.local;

import com.ehammo.githubjavapop_mvp_clean.data.model.Owner;
import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.repository.DataSource;

public class LocalDataSource implements CacheDataSource {

    private RepositoryCollection repositoryCollection;
    private static CacheDataSource instance;
    private boolean isValid;

    public static CacheDataSource getInstance(){
        if (instance == null) {
            instance = new LocalDataSource();
        }
        return instance;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    private LocalDataSource(){
        // todo : transform this into SQLlite? maybe?
        repositoryCollection = new RepositoryCollection();
        isValid = false;
    }

    @Override
    public void invalidateCache() {
        isValid = false;
    }

    @Override
    public void updateCache(RepositoryCollection repositoryCollection) {
        this.repositoryCollection.clear();
        this.repositoryCollection.addAll(repositoryCollection.iterator());
        isValid = true;
    }

    @Override
    public void listRepositories(RepositoryCallback callback, int page) {
        callback.listRepositories(repositoryCollection);
    }

    @Override
    public boolean isCacheable() {
        return true;
    }
}

