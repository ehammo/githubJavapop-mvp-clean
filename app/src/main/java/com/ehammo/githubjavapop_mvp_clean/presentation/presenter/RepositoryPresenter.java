package com.ehammo.githubjavapop_mvp_clean.presentation.presenter;

import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.presentation.view.RepositoryContract;
import com.ehammo.githubjavapop_mvp_clean.presentation.view.RepositoryRowView;

public class RepositoryPresenter implements RepositoryContract.RepositoryPresenter{

    RepositoryCollection mCollection;
    RepositoryContract.View mView;

    @Override
    public void attachView(RepositoryContract.View view) {
        mView = view;
    }

    @Override
    public void dettachView(RepositoryContract.View view) {
        mView = null;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onRepositoryChosen(int position) {

    }

    @Override
    public void onBindRepositoryRowViewAtPosition(int position, RepositoryRowView holder) {
        Repository repository = mCollection.getElement(position);
        holder.setInfo(repository);
    }

    @Override
    public int getRepositoriesRowsCount() {
        return mCollection.size();
    }
}
