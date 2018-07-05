package com.ehammo.githubjavapop_mvp_clean.data.repository;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;

public interface DataSource {

    interface RepositoryCallback {
        void listRepositories(RepositoryCollection repositories);
        void errorMessage(String message);
    }

    void listRepositories(RepositoryCallback callback, int page);

    boolean isCacheable();

}
