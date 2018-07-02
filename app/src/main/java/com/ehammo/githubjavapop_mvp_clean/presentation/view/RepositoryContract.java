package com.ehammo.githubjavapop_mvp_clean.presentation.view;

import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;

public interface RepositoryContract {

    interface Presenter {
        void attachView(RepositoryContract.View view);
        void dettachView(RepositoryContract.View view);
        void onResume();
        void onPause();
        void onRepositoryChosen(Repository repository);

    }

    interface View {
        void display(RepositoryCollection repositories);
        void showError(String message);
        void displayPR(Repository rep);
    }
}
