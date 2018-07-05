package com.ehammo.githubjavapop_mvp_clean.data.repository.remote;

import com.ehammo.githubjavapop_mvp_clean.data.model.WebRepositoryList;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitDataSource {

    @GET("search/repositories")
    Call<WebRepositoryList> getRepositories(@Query("q") String language,
                                            @Query("sort") String sort,
                                            @Query("page") int page);
}
