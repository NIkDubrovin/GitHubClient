package com.nikdubrovin.GithubProjects.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikdubrovin.GithubProjects.R;
import com.nikdubrovin.GithubProjects.data.api.GithubApi;
import com.nikdubrovin.GithubProjects.data.models.PostRepos;
import com.nikdubrovin.GithubProjects.data.models.PostStarred;

import com.nikdubrovin.GithubProjects.ui.adapters.viewholder.*;
import com.nikdubrovin.GithubProjects.ui.base.BaseViewHolder;

import java.util.List;

import static android.media.CamcorderProfile.get;
import static com.nikdubrovin.GithubProjects.common.constant.Constants.LOADING;
import static com.nikdubrovin.GithubProjects.common.constant.Constants.REPOS_VIEW;
import static com.nikdubrovin.GithubProjects.common.constant.Constants.STARRED_VIEW;

/**
 * Created by NikDubrovin on 04.11.2017.
 */

public class ReposAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<PostRepos> mPostReposes;

    private final int mViewType;

    public ReposAdapter(List<PostRepos> postReposes, int viewType) {
        mPostReposes = postReposes;
        mViewType = viewType;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //  LayoutInflater layoutInflater = LayoutInflater.from(getActivity())

        BaseViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = null;

        switch (mViewType) {
            case REPOS_VIEW:
                viewItem = inflater.inflate(R.layout.item_repos, parent, false);
                viewHolder = new ReposHolder(viewItem);
                break;
            case STARRED_VIEW:
                viewItem = inflater.inflate(R.layout.item_repos, parent, false);
                viewHolder = new StarredHolder(viewItem);
                break;
        }

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
            PostRepos postRepos = mPostReposes.get(position);
            holder.bindRepos(postRepos);
        }

    @Override
    public int getItemCount() {
        if(mPostReposes == null)
            return 0;
        else
            return mPostReposes.size();
    }

    public void add(PostRepos r) {
        mPostReposes.add(r);
        notifyItemInserted(mPostReposes.size());
    }

    public void addAll(List<PostRepos> postReposes) {
            mPostReposes.addAll(postReposes);
        notifyItemInserted(mPostReposes.size());
    }

    public void remove(PostRepos r) {
        int position = mPostReposes.indexOf(r);
        if (position > -1) {
            mPostReposes.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public PostRepos getItem(int position) {
        return mPostReposes.get(position);
    }

    public  int getViewType() {
        return mViewType;
    }
}
