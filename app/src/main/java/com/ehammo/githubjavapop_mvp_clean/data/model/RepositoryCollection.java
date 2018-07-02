
package com.ehammo.githubjavapop_mvp_clean.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RepositoryCollection {

    private List<Repository> mRepositories;

    public RepositoryCollection() {
        mRepositories = new ArrayList<>();
    }

    public void addRepository(Repository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Recipe could not be null");
        }

        mRepositories.add(repository);
    }

    public Iterator<Repository> iterator() {
        return mRepositories.iterator();
    }

    public Repository getElement(int position) {
        if (position <= 0 || position > mRepositories.size()) {
            throw new IllegalArgumentException("Position is out of range");
        }

        return mRepositories.get(position);
    }


    public int size() {
        return mRepositories.size();
    }


}
