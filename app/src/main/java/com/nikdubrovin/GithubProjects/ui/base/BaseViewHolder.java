package com.nikdubrovin.GithubProjects.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by NikDubrovin on 04.11.2017.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

     public abstract <T> void bindRepos(T object);
}
