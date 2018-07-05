package com.ehammo.githubjavapop_mvp_clean.data.repository;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;

public interface IDataStore {

    void listRepositories(DataSource.RepositoryCallback callback, int page);

}
