package com.ehammo.githubjavapop_mvp_clean.domain.usecase;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.repository.DataSource;
import com.ehammo.githubjavapop_mvp_clean.data.repository.DataStore;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.IRepositoryInteractor;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.InteractorCallback;

public class LoadRepositories implements IRepositoryInteractor, DataSource.RepositoryCallback {

    private DataSource mDataStore;
    private InteractorCallback mCallback;

    public LoadRepositories(DataSource dataSource){
        // todo : Exception if dataStore == null
        this.mDataStore = dataSource;
    }

    @Override
    public void load(InteractorCallback callback) {
        // todo : Exception if callback == null
        mCallback = callback;
        mDataStore.listRepositories(this);
    }

    @Override
    public void listRepositories(RepositoryCollection repositories) {
        mCallback.onResultReceive(repositories);
    }

    @Override
    public void errorMessage(String message) {
        mCallback.onError(message);
    }

}
