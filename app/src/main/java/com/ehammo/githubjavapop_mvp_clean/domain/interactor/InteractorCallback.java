package com.ehammo.githubjavapop_mvp_clean.domain.interactor;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;

public interface InteractorCallback {

    void onResultReceive(RepositoryCollection collection);
    void onError(String message);

}
