package com.ehammo.githubjavapop_mvp_clean.presentation.presenter;

import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.IRepositoryInteractor;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.InteractorCallback;
import com.ehammo.githubjavapop_mvp_clean.presentation.contract.RepositoryContract;
import com.ehammo.githubjavapop_mvp_clean.ui.view.RepositoryRowView;
import com.ehammo.githubjavapop_mvp_clean.ui.view.View;

import org.jetbrains.annotations.NotNull;

public class RepositoryPresenter
        implements RepositoryContract.RepositoryPresenter, InteractorCallback{

    private final RepositoryCollection mCollection;
    private View mView;
    private final IRepositoryInteractor mInteractor;
    private boolean appendData = false;

    public RepositoryPresenter(@NotNull IRepositoryInteractor interactor) {
        this.mInteractor = interactor;
        this.mCollection = new RepositoryCollection();
    }

    @Override
    public void attachView(View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    @Override
    public void onResultReceive(RepositoryCollection collection) {
        mView.endProgress();
        if (!appendData) mCollection.clear();
        appendData = false;
        mCollection.addAll(collection.toList());
        mView.display(mCollection);
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
        Repository repository = mCollection.getElement(position);
        mView.displayPR(repository);
    }

    @Override
    public void loadData() {
        loadPage(0);
    }

    private void loadPage(int page) {
        if (mView != null) {
            mView.inProgress();
            mInteractor.load(this, page);
        }
    }

    @Override
    public void loadMoreData(int page) {
        appendData = true;
        loadPage(page);
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
