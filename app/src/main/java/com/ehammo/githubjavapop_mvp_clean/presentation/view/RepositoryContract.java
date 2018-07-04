package com.ehammo.githubjavapop_mvp_clean.presentation.view;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryModel;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;

public interface RepositoryContract {

    interface BasePresenter {
        void attachView(RepositoryContract.View view);
        void dettachView(RepositoryContract.View view);
        void onResume();
        void onPause();
        void onRefresh();
    }

    interface RepositoryPresenter extends BasePresenter{
        void onBindRepositoryRowViewAtPosition(int position, RepositoryRowView rowView);
        int getRepositoriesRowsCount();
        void onRepositoryChosen(int position);
    }

    interface View {
        void display(RepositoryCollection repositories);
        void showError(String message);
        void displayPR(RepositoryModel rep);
    }
}
