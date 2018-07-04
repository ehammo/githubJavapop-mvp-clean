
package com.ehammo.githubjavapop_mvp_clean.data.model;

import com.ehammo.githubjavapop_mvp_clean.data.repository.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class RepositoryCollection {

    private List<RepositoryModel> mRepositories;

    public RepositoryCollection() {
        mRepositories = new ArrayList<>();
    }

    public void addRepository(RepositoryModel repositoryModel) {
        if (repositoryModel == null) {
            throw new IllegalArgumentException("Recipe could not be null");
        }

        mRepositories.add(repositoryModel);
    }

    public Iterator<RepositoryModel> iterator() {
        return mRepositories.iterator();
    }

    public RepositoryModel getElement(int position) {
        if (position <= 0 || position > mRepositories.size()) {
            throw new IllegalArgumentException("Position is out of range");
        }

        return mRepositories.get(position);
    }

    public void addAll(RepositoryCollection collection){
        Iterator<RepositoryModel> iterator = collection.iterator();
        while (iterator.hasNext()){
            this.addRepository(iterator.next());
        }
    }

    public void clear() { mRepositories.clear(); }

    public int size() {
        return mRepositories.size();
    }


}
