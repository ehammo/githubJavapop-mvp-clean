package com.ehammo.githubjavapop_mvp_clean.domain;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;

public interface IRepositoryInteractor {

    void load(IRepositoryInteractor.Callback callback);

    interface Callback {
        void onResultReceive(RepositoryCollection collection);
        void onError(String message);
    }
}
