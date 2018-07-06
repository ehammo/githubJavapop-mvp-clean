package com.ehammo.githubjavapop_mvp_clean.presentation.presenter;

import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.IRepositoryInteractor;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.InteractorCallback;
import com.ehammo.githubjavapop_mvp_clean.presentation.contract.RepositoryContract;
import com.ehammo.githubjavapop_mvp_clean.ui.view.RepositoryRowView;
import com.ehammo.githubjavapop_mvp_clean.ui.view.View;

import org.jetbrains.annotations.NotNull;

public class RepositoryPresenter implements RepositoryContract.RepositoryPresenter, InteractorCallback{

    private RepositoryCollection mCollection;
    private View mView;
    private IRepositoryInteractor mInteractor;

    public RepositoryPresenter(@NotNull IRepositoryInteractor interactor) {
        this.mInteractor = interactor;
        this.mCollection = new RepositoryCollection();
    }

    @Override
    public void attachView(View view) {
        this.mView = view;
    }

    @Override
    public void dettachView() {
        this.mView = null;
    }

    @Override
    public void onResume() {
        if (mView != null) {
            mView.inProgress();
            mInteractor.load(this);
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onRefresh() {
        if (mView != null) {
            mView.endProgress();
            mView.display(mCollection);
        }
    }

    @Override
    public void onResultReceive(RepositoryCollection collection) {
        mCollection.clear();
        mCollection.addAll(collection.iterator());
        this.onRefresh();
    }

    @Override
    public void onError(String message) {
        if (mView != null) {
            mView.endProgress();
            mView.showError(message);
        }
    }

    @Override
    public void onRepositoryChosen(int position) {

    }

    @Override
    public void onBindRepositoryRowViewAtPosition(int position, @NotNull RepositoryRowView holder) {
        Repository repository = mCollection.getElement(position);
        holder.setInfo(repository);
    }

    @Override
    public int getRepositoriesRowsCount() {
        return mCollection.size();
    }
}
