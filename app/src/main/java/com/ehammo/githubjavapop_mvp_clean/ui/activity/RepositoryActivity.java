package com.ehammo.githubjavapop_mvp_clean.ui.activity;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;

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
import com.ehammo.githubjavapop_mvp_clean.databinding.ActivityRepositoryBinding;
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
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRepositoryBinding bind = ActivityRepositoryBinding
                .inflate(LayoutInflater.from(this));

        // todo : add Dagger
        IDataSourceFactory dataSourceFactory =
                new DataSourceFactory(new NetworkManager(this));
        ICacheManager cacheManager = new CacheManager(new Handler());
        IDataStore dataStore = new DataStore(dataSourceFactory, cacheManager);
//        IRepositoryInteractor interactor = new LoadRepositories(new LocalDataSource());
        IRepositoryInteractor interactor = new LoadRepositories(dataStore);
        mPresenter = new RepositoryPresenter(interactor);

        mPlaceholder = bind.swipePlaceholder;
        mPlaceholder.setOnRefreshListener(() -> mPresenter.loadData());
        mSwipeRefreshLayout = bind.swipeLayout;
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.loadData());
        mRecyclerView = bind.rvRepositories;

        mRepositoryAdapter = new RepositoryAdapter(mPresenter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRepositoryAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager mng = (LinearLayoutManager) recyclerView.getLayoutManager();
                if ((mng != null ? mng.findLastCompletelyVisibleItemPosition() : 0) ==
                    mRepositoryAdapter.getItemCount() - 1) {
                    mPresenter.loadMoreData(page);
                    page++;
                }
            }
        });
        setContentView(bind.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView(this);
        mPresenter.loadData();
    }

    public void inProgress(){
        Log.d("MainActivity", "in progress");
        mPlaceholder.setVisibility(android.view.View.GONE);
        mSwipeRefreshLayout.setVisibility(android.view.View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    public void endProgress() {
        Log.d("MainActivity", "end progress");
        mSwipeRefreshLayout.setRefreshing(false);
        mPlaceholder.setRefreshing(false);
    }

    @Override
    public void display(RepositoryCollection repositories) {
        Log.d("MainActivity", "notify");
        mRecyclerView.post(() -> mRepositoryAdapter.notifyDataSetChanged());
        if (mRepositoryAdapter.getItemCount() == 0) {
            showError("no repositories found");
        }
    }

    @Override
    public void showError(String message) {
        Log.e("MainActivity", message);
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
        mPresenter.detachView();
    }
}
