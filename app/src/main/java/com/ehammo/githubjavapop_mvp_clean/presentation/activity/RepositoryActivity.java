package com.ehammo.githubjavapop_mvp_clean.presentation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ehammo.githubjavapop_mvp_clean.R;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryModel;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.IRepositoryInteractor;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.InteractorCallback;
import com.ehammo.githubjavapop_mvp_clean.domain.usecase.LoadRepositories;
import com.ehammo.githubjavapop_mvp_clean.presentation.adapter.RepositoryAdapter;
import com.ehammo.githubjavapop_mvp_clean.presentation.presenter.RepositoryPresenter;
import com.ehammo.githubjavapop_mvp_clean.presentation.view.RepositoryContract;

public class RepositoryActivity extends AppCompatActivity
        implements RepositoryContract.View {

    private RepositoryContract.RepositoryPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private RepositoryAdapter mRepositoryAdapter;
//    private Looper mLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        mRecyclerView = findViewById(R.id.rvRepositories);
        mRecyclerView.setAdapter(mRepositoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        IRepositoryInteractor interactor = new LoadRepositories();
        mPresenter = new RepositoryPresenter(interactor);
        mRepositoryAdapter = new RepositoryAdapter(mPresenter);

        mPresenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void display(RepositoryCollection repositories) {
        mRepositoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void displayPR(RepositoryModel rep) {

    }
}
