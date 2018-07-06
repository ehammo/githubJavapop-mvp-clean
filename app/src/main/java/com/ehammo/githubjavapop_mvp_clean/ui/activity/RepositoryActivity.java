package com.ehammo.githubjavapop_mvp_clean.ui.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;

import com.ehammo.githubjavapop_mvp_clean.R;
import com.ehammo.githubjavapop_mvp_clean.data.manager.NetworkManager;
import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.repository.DataSourceFactory;
import com.ehammo.githubjavapop_mvp_clean.data.repository.DataStore;
import com.ehammo.githubjavapop_mvp_clean.data.repository.IDataStore;
import com.ehammo.githubjavapop_mvp_clean.data.manager.CacheManager;
import com.ehammo.githubjavapop_mvp_clean.data.manager.ICacheManager;
import com.ehammo.githubjavapop_mvp_clean.data.repository.IDataSourceFactory;
import com.ehammo.githubjavapop_mvp_clean.domain.interactor.IRepositoryInteractor;
import com.ehammo.githubjavapop_mvp_clean.domain.usecase.LoadRepositories;
import com.ehammo.githubjavapop_mvp_clean.ui.adapter.RepositoryAdapter;
import com.ehammo.githubjavapop_mvp_clean.presentation.presenter.RepositoryPresenter;
import com.ehammo.githubjavapop_mvp_clean.presentation.contract.RepositoryContract;
import com.ehammo.githubjavapop_mvp_clean.ui.view.View;

public class RepositoryActivity extends AppCompatActivity
        implements View {

    private RepositoryContract.RepositoryPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private RelativeLayout mProgress;
    private RelativeLayout mPlaceholder;

    private RepositoryAdapter mRepositoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);


        mProgress = findViewById(R.id.progress);
        mPlaceholder = findViewById(R.id.noRepositories);

        // todo : qm inicia o data store?
        IDataSourceFactory dataSourceFactory = new DataSourceFactory(new NetworkManager(this));
        ICacheManager cacheManager = new CacheManager(new Handler());
        IDataStore dataStore = new DataStore(dataSourceFactory, cacheManager);
//        IRepositoryInteractor interactor = new LoadRepositories(new LocalDataSource());
        IRepositoryInteractor interactor = new LoadRepositories(dataStore);
        mPresenter = new RepositoryPresenter(interactor);
        mRepositoryAdapter = new RepositoryAdapter(mPresenter);

        mRecyclerView = findViewById(R.id.rvRepositories);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRepositoryAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView(this);
        mPresenter.onResume();
    }

    public void inProgress(){
        mPlaceholder.setVisibility(android.view.View.GONE);
        mProgress.setVisibility(android.view.View.VISIBLE);
        mRecyclerView.setVisibility(android.view.View.VISIBLE);
    }

    public void endProgress(){ mProgress.setVisibility(android.view.View.GONE); }

    @Override
    public void display(RepositoryCollection repositories) {
        Log.d("MainActivity", "notify");
        mRepositoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Log.e("MainActivity",message);
        if(mPresenter.getRepositoriesRowsCount() == 0){
            mPlaceholder.setVisibility(android.view.View.VISIBLE);
            mRecyclerView.setVisibility(android.view.View.GONE);
        }
    }

    @Override
    public void displayPR(Repository rep) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        // todo : remove View form detach / check onPause
        mPresenter.dettachView();
        mPresenter.onPause();
    }
}
