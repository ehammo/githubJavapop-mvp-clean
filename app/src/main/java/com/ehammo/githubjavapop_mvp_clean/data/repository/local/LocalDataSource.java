package com.ehammo.githubjavapop_mvp_clean.data.repository.local;

import com.ehammo.githubjavapop_mvp_clean.data.model.Owner;
import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.repository.DataSource;

import java.util.HashMap;

public class LocalDataSource implements CacheDataSource {

    // TODO: use Room
    private final HashMap<Integer, RepositoryCollection> cache;
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
        cache = new HashMap<>();
        isValid = false;
    }

    @Override
    public void invalidateCache() {
        isValid = false;
    }

    @Override
    public void updateCache(RepositoryCollection repositoryCollection, int page) {
        cache.put(page, repositoryCollection);
        isValid = true;
    }

    @Override
    public void listRepositories(RepositoryCallback callback, int page) {
        RepositoryCollection rc = cache.get(page);
        if (rc == null) rc = new RepositoryCollection();
        callback.listRepositories(rc, page);
    }

    @Override
    public boolean isCacheable() {
        return true;
    }
}

