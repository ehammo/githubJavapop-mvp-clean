package com.ehammo.githubjavapop_mvp_clean.data.repository.local;

import com.ehammo.githubjavapop_mvp_clean.data.model.Owner;
import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.repository.DataSource;

public class LocalDataSource implements CacheDataSource {

    private RepositoryCollection repositoryCollection;
    private static CacheDataSource instance;
    private boolean isValid;

    public static CacheDataSource getInstance(){
        if (instance == null) {
            instance = new LocalDataSource();
        }
        return instance;
    }

    private LocalDataSource(){
        repositoryCollection = new RepositoryCollection();
        isValid = false;
        Repository repository1 = new Repository();
        Repository repository2 = new Repository();
        Repository repository3 = new Repository();
        Repository repository4 = new Repository();
        Repository repository5 = new Repository();
        Repository repository6 = new Repository();
        Owner owner = new Owner();
        owner.setLogin("meu_login");

        repository1.setName("repo1");
        repository1.setDescription("repositorio 1");
        repository1.setForksCount(1);
        repository1.setStargazersCount(0);
        repository1.setOwner(owner);

        repository2.setName("repo2");
        repository2.setDescription("repositorio 2");
        repository2.setForksCount(2);
        repository2.setStargazersCount(1);
        repository2.setOwner(owner);

        repository3.setName("repo3");
        repository3.setDescription("repositorio 3");
        repository3.setForksCount(3);
        repository3.setStargazersCount(2);
        repository3.setOwner(owner);

        repository4.setName("repo4");
        repository4.setDescription("repositorio 4");
        repository4.setForksCount(4);
        repository4.setStargazersCount(3);
        repository4.setOwner(owner);

        repository5.setName("repo5");
        repository5.setDescription("repositorio 5");
        repository5.setForksCount(5);
        repository5.setStargazersCount(4);
        repository5.setOwner(owner);

        repository6.setName("repo6");
        repository6.setDescription("repositorio 6");
        repository6.setForksCount(6);
        repository6.setStargazersCount(5);
        repository6.setOwner(owner);

        repositoryCollection.addRepository(repository1);
        repositoryCollection.addRepository(repository2);
        repositoryCollection.addRepository(repository3);
        repositoryCollection.addRepository(repository4);
        repositoryCollection.addRepository(repository5);
        repositoryCollection.addRepository(repository6);
    }

    @Override
    public void invalidateCache() {
        isValid = false;
    }

    @Override
    public void updateCache(RepositoryCollection repositoryCollection) {
        this.repositoryCollection.clear();
        this.repositoryCollection.addAll(repositoryCollection.iterator());
        isValid = true;
    }

    @Override
    public void listRepositories(RepositoryCallback callback, int page) {
        callback.listRepositories(repositoryCollection);
    }

    @Override
    public boolean isCacheable() {
        return true;
    }
}

