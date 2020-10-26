package com.ehammo.githubjavapop_mvp_clean.data.mapper;

import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.model.WebRepositoryList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WebRepositoryCollectionMapper {

    public RepositoryCollection fromWebToCollection(@NotNull WebRepositoryList webRepositoryList) {
        RepositoryCollection repositoryCollection = new RepositoryCollection();
        repositoryCollection.addAll(webRepositoryList.getItems());
        return repositoryCollection;
    }

    public WebRepositoryList fromCollectionToWeb(@NotNull RepositoryCollection repositoryCollection) {
        WebRepositoryList webRepositoryList = new WebRepositoryList();
        List<Repository> repositories = fromCollectionToList(repositoryCollection);
        webRepositoryList.setItems(repositories);
        return webRepositoryList;
    }

    private List<Repository> fromCollectionToList(RepositoryCollection repositoryCollection){
        return repositoryCollection.toList();
    }

}
