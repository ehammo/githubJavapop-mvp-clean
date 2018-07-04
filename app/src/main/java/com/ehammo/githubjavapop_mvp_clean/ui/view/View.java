package com.ehammo.githubjavapop_mvp_clean.ui.view;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;

public interface View {
    void display(RepositoryCollection repositories);
    void showError(String message);
    void inProgress();
    void endProgress();
    void displayPR(Repository rep);
}
