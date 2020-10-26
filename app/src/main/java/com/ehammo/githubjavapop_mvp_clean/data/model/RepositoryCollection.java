
package com.ehammo.githubjavapop_mvp_clean.data.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryCollection {

    private ArrayList<Repository> mRepositories;

    public RepositoryCollection() {
        mRepositories = new ArrayList<>();
    }

    public Repository getElement(int position) {
        if (position < 0 || position > mRepositories.size()) {
            throw new IllegalArgumentException("Position is out of range");
        }

        return mRepositories.get(position);
    }

    public void addAll(List<Repository> rc){
        mRepositories.addAll(rc);
        mRepositories = mRepositories
                .stream()
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void clear() { mRepositories.clear(); }

    public int size() {
        return mRepositories.size();
    }

    public List<Repository> toList() {
        return new ArrayList<>(mRepositories);
    }
}
