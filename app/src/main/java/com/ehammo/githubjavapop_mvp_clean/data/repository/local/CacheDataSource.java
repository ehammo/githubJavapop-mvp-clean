package com.ehammo.githubjavapop_mvp_clean.data.repository.local;

import com.ehammo.githubjavapop_mvp_clean.data.repository.DataSource;

public interface CacheDataSource extends ICacheManager, DataSource {
    boolean isValid();
}
