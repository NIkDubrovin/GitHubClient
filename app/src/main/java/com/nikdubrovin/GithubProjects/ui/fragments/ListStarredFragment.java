package com.nikdubrovin.GithubProjects.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import static com.nikdubrovin.GithubProjects.common.constant.Constants.STARRED_VIEW;
import static com.nikdubrovin.GithubProjects.common.constant.Constants.TOTAL_PAGE;


/**
 * Created by NikDubrovin on 06.10.2017.
 */

public class ListStarredFragment extends Fragment{

    private RecyclerView mStarredRecyclerView;
    private ReposAdapter mReposAdapter;
    private ProgressBar mProgressBar;
    private List<PostRepos> mPostStarreds;

     int currentPage = 1;
    private int countRequists = 0;
    private boolean isEndLoading = false;
    private boolean isLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_item,container,false);
        loadView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPostStarreds == null || mPostStarreds.size() == 0) {
            //Init load. Load first page
            mProgressBar.setVisibility(View.VISIBLE);
            loadData(currentPage);
        }
    }

    private void loadView(View view){

        mStarredRecyclerView = view.findViewById(R.id.main_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mStarredRecyclerView.setLayoutManager(linearLayoutManager);

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBarRepos);
        GLog.get().i("Visibility : " + ((mProgressBar.getVisibility() == View.VISIBLE)?"Visible":"Gone") + " currentPage : " + currentPage + " countR : " + countRequists);

        mPostStarreds = new ArrayList<>();

        mStarredRecyclerView.addOnScrollListener(new BaseScrollListener(linearLayoutManager) {

            @Override
            protected void loadMoreItems() {
                if(!isEndLoading) {
                    if(currentPage == countRequists) {
                        isEndLoading = true;
                        return;
                    }

                    if(isLoading) {
                        mProgressBar.setVisibility(View.VISIBLE);
                        loadData(++currentPage);
                        isLoading = false;
                    }
                    GLog.get().i("Visibility : " + ((mProgressBar.getVisibility() == View.VISIBLE)?"Visible":"Gone") + " currentPage : " + currentPage + " countR : " + countRequists);
                }
                else if(isEndLoading) {
                    mProgressBar.setVisibility(View.GONE);
                    GLog.get().i("Visibility : " + ((mProgressBar.getVisibility() == View.VISIBLE)?"Visible":"Gone") + " currentPage : " + currentPage + " countR : " + countRequists);
                }
            }
        });
        GLog.get().i("Visibility : " + ((mProgressBar.getVisibility() == View.VISIBLE)?"Visible":"Gone") + " currentPage : " + currentPage + " countR : " + countRequists);
    }

    private void loadData(final int currentPage) {

            RetrofitGithub.getApi().getListStarred(StorageClass.get().getUserName(), TOTAL_PAGE, currentPage).enqueue(new Callback<List<PostRepos>>() {
                @Override
                public void onResponse(Call<List<PostRepos>> call, Response<List<PostRepos>> response) {
                    isLoading = false;

                    if(countRequists == 0)
                        countRequists = OutUtils.getCountRequests(response.headers().get("Link"));

                     mPostStarreds.addAll(response.body());
                 //    GLog.get().i("Size : " + response.body().size() + " | info : " + response.raw());

                    if (mReposAdapter == null) {
                        mReposAdapter = new ReposAdapter(response.body(), STARRED_VIEW);
                        mStarredRecyclerView.setAdapter(mReposAdapter);
                    }else
                        mReposAdapter.addAll(response.body());

                    // Load last page
                    if(currentPage == countRequists)
                        isEndLoading = true;

                    isLoading = true;
                    mProgressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<List<PostRepos>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
    }

    private void makeVisible() {
        if(mProgressBar.getVisibility() == View.VISIBLE)
        mProgressBar.setVisibility(View.GONE);
        else
            mProgressBar.setVisibility(View.VISIBLE);
    }

}
