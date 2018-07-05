package com.ehammo.githubjavapop_mvp_clean.data.manager;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;

public interface ICacheManager {

    void updateCache(RepositoryCollection collection);
    void invalidateCache();

    interface Callback {

        void onCompleted();

    }

}
