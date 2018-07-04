package com.ehammo.githubjavapop_mvp_clean.domain.usecase;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.repository.Repository;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.IRepositoryInteractor;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.InteractorCallback;

public class LoadRepositories implements IRepositoryInteractor, Repository.RepositoryCallback {

    private Repository mRepository;
    private InteractorCallback mCallback;

    public LoadRepositories(){

    }

    @Override
    public void load(InteractorCallback callback) {
        mRepository.listRepositories(this);
        mCallback = callback;
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