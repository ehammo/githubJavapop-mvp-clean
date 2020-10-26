package com.ehammo.githubjavapop_mvp_clean.data.manager;

import android.os.Handler;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.repository.local.LocalDataSource;

import org.jetbrains.annotations.NotNull;


public class CacheManager implements ICacheManager {

    private final Handler mHandler;

    public CacheManager(@NotNull Handler handler) {
        mHandler = handler;
    }

    @Override
    public void updateCache(RepositoryCollection collection, int page) {
        if (collection != null) {
            LocalDataSource.getInstance().updateCache(collection, page);
            mHandler.postDelayed(() -> invalidateCache(), 60 * 1000);
        }
    }

    @Override
    public void invalidateCache() {
        LocalDataSource.getInstance().invalidateCache();
    }
}
