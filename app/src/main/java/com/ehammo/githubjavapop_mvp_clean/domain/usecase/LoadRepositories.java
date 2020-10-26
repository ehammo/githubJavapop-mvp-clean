package com.ehammo.githubjavapop_mvp_clean.domain.usecase;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.repository.DataSource;
import com.ehammo.githubjavapop_mvp_clean.data.repository.IDataStore;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.IRepositoryInteractor;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.InteractorCallback;

import org.jetbrains.annotations.NotNull;

public class LoadRepositories implements IRepositoryInteractor, DataSource.RepositoryCallback {

    private final IDataStore mDataStore;
    private InteractorCallback mCallback;

    public LoadRepositories(@NotNull IDataStore dataStore) {
        this.mDataStore = dataStore;
    }

    public void load(@NotNull InteractorCallback callback, int page) {
        this.mCallback = callback;
        mDataStore.listRepositories(this, page);
    }

    @Override
    public void listRepositories(RepositoryCollection repositories, int page) {
        mCallback.onResultReceive(repositories);
    }

    @Override
    public void errorMessage(String message) {
        mCallback.onError(message);
    }

}
