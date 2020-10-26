package com.ehammo.githubjavapop_mvp_clean.data.repository;

public interface IDataStore {

    void listRepositories(DataSource.RepositoryCallback callback, int page);

}
