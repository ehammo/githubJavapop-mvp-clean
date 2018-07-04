package com.ehammo.githubjavapop_mvp_clean.ui.view;

import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryModel;

public interface View {
    void display(RepositoryCollection repositories);
    void showError(String message);
    void displayPR(RepositoryModel rep);
}
