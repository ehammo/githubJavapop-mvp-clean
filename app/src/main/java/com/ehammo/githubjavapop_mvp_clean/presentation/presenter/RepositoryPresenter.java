package com.ehammo.githubjavapop_mvp_clean.presentation.presenter;

import android.util.Log;

import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.IRepositoryInteractor;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.InteractorCallback;
import com.ehammo.githubjavapop_mvp_clean.presentation.contract.RepositoryContract;
import com.ehammo.githubjavapop_mvp_clean.ui.view.RepositoryRowView;
import com.ehammo.githubjavapop_mvp_clean.ui.view.View;

public class RepositoryPresenter implements RepositoryContract.RepositoryPresenter, InteractorCallback{

    private RepositoryCollection mCollection;
    private View mView;
    private IRepositoryInteractor mInteractor;

    public RepositoryPresenter(IRepositoryInteractor interactor){
        // todo : exception if interactor == null
        this.mInteractor = interactor;
        mCollection = new RepositoryCollection();
    }

    @Override
    public void attachView(View view) {
        this.mView = view;
    }

    @Override
    public void dettachView(View view) {
        this.mView = null;
    }

    @Override
    public void onResume() {
        Log.d("Presenter", "onResume");
        mView.inProgress();
        mInteractor.load(this);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onRefresh() {
        Log.d("Presenter", "onRefresh");
        if (mView != null) {
            mView.endProgress();
            mView.display(mCollection);
        }
    }

    @Override
    public void onResultReceive(RepositoryCollection collection) {
        Log.d("Presenter", "onResult");
        mCollection.clear();
        mCollection.addAll(collection);
        Log.d("Presenter", "CollectionSize="+mCollection.size());
        this.onRefresh();
    }

    @Override
    public void onError(String message) {
        Log.d("Presenter", "onError");
        if (mView != null) {
            mView.endProgress();
            mView.showError(message);
        }
    }

    @Override
    public void onRepositoryChosen(int position) {

    }

    @Override
    public void onBindRepositoryRowViewAtPosition(int position, RepositoryRowView holder) {
        Log.d("Presenter", "pos="+position);
        Repository repository = mCollection.getElement(position);
        holder.setInfo(repository);
    }

    @Override
    public int getRepositoriesRowsCount() {
        return mCollection.size();
    }
}
