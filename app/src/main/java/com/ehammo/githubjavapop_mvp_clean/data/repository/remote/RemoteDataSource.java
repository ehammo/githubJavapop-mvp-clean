package com.ehammo.githubjavapop_mvp_clean.data.repository.remote;

import android.provider.ContactsContract;

import com.ehammo.githubjavapop_mvp_clean.data.mapper.WebRepositoryCollectionMapper;
import com.ehammo.githubjavapop_mvp_clean.data.model.WebRepositoryList;
import com.ehammo.githubjavapop_mvp_clean.data.repository.DataSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements DataSource {

    private String BASE_URL;
    private Retrofit retrofit;

    public RemoteDataSource(String url){
        this.BASE_URL = url;
        buildRetrofit();
    }

    private void buildRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    @Override
    public void listRepositories(final RepositoryCallback callback, int page) {
        retrofit.create(RetrofitDataSource.class).
                getRepositories("language:Java", "stars", page)
                .enqueue(new Callback<WebRepositoryList>() {
            @Override
            public void onResponse(Call<WebRepositoryList> call, Response<WebRepositoryList> response) {
                if(response.isSuccessful()) {
                    WebRepositoryList webRepositoryList = response.body();
                    if(webRepositoryList!=null) {
                        WebRepositoryCollectionMapper mapper = new WebRepositoryCollectionMapper();
                        callback.listRepositories(mapper.fromWebToCollection(webRepositoryList));
                    } else {
                        callback.errorMessage("No repositories");
                    }
                } else {
                    callback.errorMessage(response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<WebRepositoryList> call, Throwable t) {
                callback.errorMessage(t.getMessage());
            }
        });
    }
}
