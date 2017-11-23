package com.nikdubrovin.GithubProjects.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.BoringLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nikdubrovin.GithubProjects.R;
import com.nikdubrovin.GithubProjects.common.utils.GLog;
import com.nikdubrovin.GithubProjects.common.utils.OutUtils;
import com.nikdubrovin.GithubProjects.data.StorageClass;
import com.nikdubrovin.GithubProjects.data.models.PostRepos;
import com.nikdubrovin.GithubProjects.data.network.RetrofitGithub;
import com.nikdubrovin.GithubProjects.ui.adapters.ReposAdapter;
import com.nikdubrovin.GithubProjects.ui.base.BaseScrollListener;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nikdubrovin.GithubProjects.common.constant.Constants.REPOS_VIEW;
import static com.nikdubrovin.GithubProjects.common.constant.Constants.TOTAL_PAGE;

/**
 * Created by NikDubrovin on 24.09.2017.
 */

public class ListReposFragment extends Fragment {

    private RecyclerView mReposRecyclerView;
    private ReposAdapter mReposAdapter;

    private ProgressBar mProgressBar;

    private List<PostRepos> mPostReposes;

    private int currentPage = 1;
    private int countRepos = 0;

    private boolean isEndLoading = false;
    private boolean isLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_item,container,false);

        mReposRecyclerView = view.findViewById(R.id.main_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mReposRecyclerView.setLayoutManager(linearLayoutManager);
        mReposRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBarRepos);
        mProgressBar.setVisibility(View.VISIBLE);

        mPostReposes = new ArrayList<>();

        // Get count repos
        countRepos = OutUtils.getPostUser(StorageClass.get().getPostUsers(),StorageClass.get().getUserName()).getPublicRepos();

        // First load
        loadNextPage(currentPage);
        mProgressBar.setVisibility(View.GONE);

        mReposRecyclerView.addOnScrollListener(new BaseScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                if(!isEndLoading) {
                    if(mPostReposes.size() == countRepos) {
                        isEndLoading = true;
                        return;
                    }

                    mProgressBar.setVisibility(View.VISIBLE);
                    if(isLoading) {
                        loadNextPage(++currentPage);
                        isLoading = false;
                    }
                    mProgressBar.setVisibility(View.GONE);
                }
                else
                    mProgressBar.setVisibility(View.GONE);
            }
        });

//        mRepoListView.addItemDecoration(new HorizontalDividerItemDecoration
//                .Builder(this)
//                .color(Color.TRANSPARENT)
//                .size(getResources().getDimensionPixelSize(R.dimen.divider_height))
//                .build());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
      //  updateUI();
    }

    private void updateUI() {
        if (mReposAdapter == null) {
            mReposAdapter = new ReposAdapter(mPostReposes, REPOS_VIEW);
            mReposRecyclerView.setAdapter(mReposAdapter);
        } else {
            mReposAdapter.notifyDataSetChanged();
        }
    }


    private void loadNextPage(int currentPage){
        RetrofitGithub.getApi().getListRepos(StorageClass.get().getUserName(), TOTAL_PAGE, currentPage).enqueue(new Callback<List<PostRepos>>() {
                @Override
                public void onResponse(Call<List<PostRepos>> call, Response<List<PostRepos>> response) {
                    isLoading = false;
                    mPostReposes.addAll(response.body());

                    if (mReposAdapter != null)
                        mReposAdapter.addAll(response.body());

                    updateUI();
                    isLoading = true;
                }

                @Override
                public void onFailure(Call<List<PostRepos>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
    }
}
