package com.ehammo.githubjavapop_mvp_clean.presentation.contract;

import com.ehammo.githubjavapop_mvp_clean.ui.view.RepositoryRowView;
import com.ehammo.githubjavapop_mvp_clean.ui.view.View;

public interface RepositoryContract {

    interface BaseListPresenter {
        void attachView(View view);
        void detachView();
    }

    interface RepositoryPresenter extends BaseListPresenter {
        void onBindRepositoryRowViewAtPosition(int position, RepositoryRowView rowView);
        int getRepositoriesRowsCount();
        void onRepositoryChosen(int position);
        void loadData();
        void loadMoreData(int page);
    }
}
