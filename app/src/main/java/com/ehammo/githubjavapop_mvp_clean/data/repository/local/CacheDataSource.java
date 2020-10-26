package com.ehammo.githubjavapop_mvp_clean.data.repository.local;

import com.ehammo.githubjavapop_mvp_clean.data.manager.ICacheManager;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.repository.DataSource;

public interface CacheDataSource extends ICacheManager, DataSource {
    boolean isValid();
    void updateCache(RepositoryCollection repositoryCollection, int page);
}
