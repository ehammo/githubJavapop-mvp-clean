package com.ehammo.githubjavapop_mvp_clean.ui.activity;

import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private SwipeRefreshLayout mPlaceholder;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RepositoryAdapter mRepositoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        mPlaceholder = findViewById(R.id.swipePlaceholder);
        mPlaceholder.setOnRefreshListener(() -> mPresenter.loadData());
        mSwipeRefreshLayout = findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.loadData());

        // todo : qm inicia o data store?
        IDataSourceFactory dataSourceFactory =
                new DataSourceFactory(new NetworkManager(this));
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
        mPresenter.loadData();
    }

    public void inProgress(){
        Log.d("RepositoryActivity", "in progress");
        mPlaceholder.setVisibility(android.view.View.GONE);
        mSwipeRefreshLayout.setVisibility(android.view.View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    public void endProgress() {
        Log.d("RepositoryActivity", "end progress");
        mSwipeRefreshLayout.setRefreshing(false);
        mPlaceholder.setRefreshing(false);
    }

    @Override
    public void display(RepositoryCollection repositories) {
        Log.d("RepositoryActivity", "notify");
        mRepositoryAdapter.notifyDataSetChanged();
        if (mRepositoryAdapter.getItemCount() == 0) {
            showError("no repositories found");
        }
    }

    @Override
    public void showError(String message) {
        Log.e("RepositoryActivity", message);
        if(mPresenter.getRepositoriesRowsCount() == 0){
            mPlaceholder.setVisibility(android.view.View.VISIBLE);
            mSwipeRefreshLayout.setVisibility(android.view.View.GONE);
        }
    }

    @Override
    public void displayPR(Repository rep) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.dettachView();
    }
}
