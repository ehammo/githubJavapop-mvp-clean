package com.ehammo.githubjavapop_mvp_clean.presentation.presenter;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryModel;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.IRepositoryInteractor;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.InteractorCallback;
import com.ehammo.githubjavapop_mvp_clean.presentation.view.RepositoryContract;
import com.ehammo.githubjavapop_mvp_clean.presentation.view.RepositoryRowView;

public class RepositoryPresenter implements RepositoryContract.RepositoryPresenter, InteractorCallback{

    private RepositoryCollection mCollection;
    private RepositoryContract.View mView;
    private IRepositoryInteractor mInteractor;

    public RepositoryPresenter(IRepositoryInteractor interactor){
        this.mInteractor = interactor;
    }

    @Override
    public void attachView(RepositoryContract.View view) {
        this.mView = view;
    }

    @Override
    public void dettachView(RepositoryContract.View view) {
        this.mView = null;
    }

    @Override
    public void onResume() {
        mInteractor.load(this);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onRefresh() {
        mView.display(mCollection);
    }

    @Override
    public void onResultReceive(RepositoryCollection collection) {
        mCollection.clear();
        mCollection.addAll(collection);
        this.onRefresh();
    }

    @Override
    public void onError(String message) {
        mView.showError(message);
    }

    @Override
    public void onRepositoryChosen(int position) {

    }

    @Override
    public void onBindRepositoryRowViewAtPosition(int position, RepositoryRowView holder) {
        RepositoryModel repositoryModel = mCollection.getElement(position);
        holder.setInfo(repositoryModel);
    }

    @Override
    public int getRepositoriesRowsCount() {
        return mCollection.size();
    }
}
