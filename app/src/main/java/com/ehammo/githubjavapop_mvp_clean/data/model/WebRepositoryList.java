
package com.ehammo.githubjavapop_mvp_clean.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class WebRepositoryList {

    @SerializedName("items")
    @Expose
    private List<Repository> repositories = null;

    public List<Repository> getItems() {
        return repositories;
    }

    public void setItems(List<Repository> items) {
        this.repositories = items;
    }

}
