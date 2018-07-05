package com.ehammo.githubjavapop_mvp_clean.data.manager;

import android.os.Handler;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.repository.local.LocalDataSource;


public class CacheManager implements ICacheManager {

    private Handler mHandler;

    public CacheManager(Handler handler){
        mHandler = handler;
    }

    @Override
    public void updateCache(RepositoryCollection collection) {
        LocalDataSource.getInstance().updateCache(collection);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidateCache();
            }
        }, 60*1000);
    }

    @Override
    public void invalidateCache() {
        LocalDataSource.getInstance().invalidateCache();
    }
}
