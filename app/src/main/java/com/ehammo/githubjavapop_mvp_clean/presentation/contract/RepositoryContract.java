package com.ehammo.githubjavapop_mvp_clean.presentation.contract;

import com.ehammo.githubjavapop_mvp_clean.ui.view.RepositoryRowView;
import com.ehammo.githubjavapop_mvp_clean.ui.view.View;

public interface RepositoryContract {

    interface BasePresenter {
        void attachView(View view);
        void dettachView(View view);
        void onResume();
        void onPause();
        void onRefresh();
    }

    interface RepositoryPresenter extends BasePresenter{
        void onBindRepositoryRowViewAtPosition(int position, RepositoryRowView rowView);
        int getRepositoriesRowsCount();
        void onRepositoryChosen(int position);
    }
}
