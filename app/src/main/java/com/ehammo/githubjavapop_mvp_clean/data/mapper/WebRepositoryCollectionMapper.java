package com.ehammo.githubjavapop_mvp_clean.data.mapper;

import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.model.WebRepositoryList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WebRepositoryCollectionMapper {

    public RepositoryCollection fromWebToCollection(WebRepositoryList webRepositoryList){
        RepositoryCollection repositoryCollection = new RepositoryCollection();
        // todo : null checks
        repositoryCollection.addAll(webRepositoryList.getItems().iterator());
        return repositoryCollection;
    }

    public WebRepositoryList fromCollectionToWeb(RepositoryCollection repositoryCollection){
        WebRepositoryList webRepositoryList = new WebRepositoryList();
        List<Repository> repositories = fromCollectionToList(repositoryCollection);
        webRepositoryList.setItems(repositories);
        return webRepositoryList;
    }

    private List<Repository> fromCollectionToList(RepositoryCollection repositoryCollection){
        List<Repository> repositories = new ArrayList<>();
        for(Iterator<Repository> iterator = repositoryCollection.iterator(); iterator.hasNext();){
            repositories.add(iterator.next());
        }
        return repositories;
    }

}
