package com.nikdubrovin.GithubProjects.data;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.nikdubrovin.GithubProjects.R;
import com.nikdubrovin.GithubProjects.common.config.GitHubConfig;
import com.nikdubrovin.GithubProjects.common.constant.Constants;
import com.nikdubrovin.GithubProjects.common.utils.GLog;
import com.nikdubrovin.GithubProjects.common.utils.NetworkUtil;
import com.nikdubrovin.GithubProjects.common.utils.OutUtils;
import com.nikdubrovin.GithubProjects.data.models.PostRateLimit;
import com.nikdubrovin.GithubProjects.data.models.PostRepos;
import com.nikdubrovin.GithubProjects.data.models.PostStarred;
import com.nikdubrovin.GithubProjects.data.models.PostUser;
import com.nikdubrovin.GithubProjects.data.network.RetrofitGithub;
import com.nikdubrovin.GithubProjects.ui.SelectTypeDataActivity;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.mikepenz.iconics.Iconics.TAG;
import static com.nikdubrovin.GithubProjects.common.config.GitHubConfig.SCOPES.RATE;
import static com.nikdubrovin.GithubProjects.common.config.GitHubConfig.SCOPES.REPOS;
import static com.nikdubrovin.GithubProjects.common.constant.Constants.TOTAL_PAGE;
import static com.nikdubrovin.GithubProjects.common.utils.GLog.i;

/**
 * Created by NikDubrovin on 16.08.2017.
 */

public class GetGitHubData {

    private GitHubConfig.SCOPES mSCOPES;
    private String nameLogin;

    private int countRequests = 0;
    private int currertPage = 1;


    public void loadDataEnqueue() {
        Response response = null;
        nameLogin = StorageClass.get().getUserName();

        switch (mSCOPES) {
            case REPOS:
                StorageClass.get().getPostReposes().clear();
                countRequests = 0;
                RetrofitGithub.getApi().getListRepos(nameLogin, TOTAL_PAGE, currertPage).enqueue(new Callback<List<PostRepos>>() {
                    @Override
                    public void onResponse(Call<List<PostRepos>> call, Response<List<PostRepos>> response) {
                        StorageClass.get().getPostReposes().addAll((List<PostRepos>) response.body());
                        countRequests = OutUtils.getCountRequests(response.headers().get("Link"));
                        StorageClass.get().setCountReqRepos(countRequests);
                        GLog.get().i("linkHeader = " + response.headers().get("Link") + "\n" + "Count Requests : " + String.valueOf(countRequests));
                    }

                    @Override
                    public void onFailure(Call<List<PostRepos>> call, Throwable t) {
                        t.printStackTrace();
                        GLog.get().i("Load Repos on fail");
                    }
                });
                break;

            case STARRED:
                RetrofitGithub.getApi().getListStarred(nameLogin, TOTAL_PAGE, currertPage).enqueue(new Callback<List<PostRepos>>() {
                    @Override
                    public void onResponse(Call<List<PostRepos>> call, Response<List<PostRepos>> response) {
                        StorageClass.get().getPostStarreds().addAll((List<PostRepos>) response.body());
                        countRequests = OutUtils.getCountRequests(response.headers().get("Link"));
                        StorageClass.get().setCountReqStarred(countRequests);
                        GLog.get().i("linkHeader = " + response.headers().get("Link") + "\n" + "Count Requests : " + String.valueOf(countRequests));
                    }

                    @Override
                    public void onFailure(Call<List<PostRepos>> call, Throwable t) {
                        t.printStackTrace();
                        GLog.get().i("Load STARRED on fail");
                    }
                });
                break;

            case USER:
                RetrofitGithub.getApi().getUser(nameLogin).enqueue(new Callback<PostUser>() {
                    @Override
                    public void onResponse(Call<PostUser> call, Response<PostUser> response) {
                        StorageClass.get().getPostUsers().add(response.body());
                    }

                    @Override
                    public void onFailure(Call<PostUser> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
                break;

            case RATE:
                    RetrofitGithub.getApi().getRateLimit().enqueue(new Callback<PostRateLimit>() {
                    @Override
                    public void onResponse(Call<PostRateLimit> call, Response<PostRateLimit> response) {
                        StorageClass.get().setPostRateLimit(response.body());
                    }

                    @Override
                    public void onFailure(Call<PostRateLimit> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                break;

            case ALL:
                RetrofitGithub.getApi().getListRepos(nameLogin, TOTAL_PAGE, currertPage).enqueue(new Callback<List<PostRepos>>() {
                    @Override
                    public void onResponse(Call<List<PostRepos>> call, Response<List<PostRepos>> response) {
                        StorageClass.get().getPostReposes().addAll((List<PostRepos>) response.body());
                        countRequests = OutUtils.getCountRequests(response.headers().get("Link"));
                        StorageClass.get().setCountReqRepos(countRequests);
                        GLog.get().i("linkHeader = " + response.headers().get("Link") + "\n" + "Count Requests : " + String.valueOf(countRequests));
                    }

                    @Override
                    public void onFailure(Call<List<PostRepos>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                RetrofitGithub.getApi().getListStarred(nameLogin, TOTAL_PAGE, currertPage).enqueue(new Callback<List<PostRepos>>() {
                    @Override
                    public void onResponse(Call<List<PostRepos>> call, Response<List<PostRepos>> response) {
                        StorageClass.get().getPostStarreds().addAll((List<PostRepos>) response.body());
                        countRequests = OutUtils.getCountRequests(response.headers().get("Link"));
                        StorageClass.get().setCountReqStarred(countRequests);
                        GLog.get().i("linkHeader = " + response.headers().get("Link") + "\n" + "Count Requests : " + String.valueOf(countRequests));
                    }

                    @Override
                    public void onFailure(Call<List<PostRepos>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                RetrofitGithub.getApi().getUser(nameLogin).enqueue(new Callback<PostUser>() {
                    @Override
                    public void onResponse(Call<PostUser> call, Response<PostUser> response) {
                        StorageClass.get().getPostUsers().add(response.body());
                    }

                    @Override
                    public void onFailure(Call<PostUser> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                RetrofitGithub.getApi().getRateLimit().enqueue(new Callback<PostRateLimit>() {
                    @Override
                    public void onResponse(Call<PostRateLimit> call, Response<PostRateLimit> response) {
                        StorageClass.get().setPostRateLimit(response.body());
                    }

                    @Override
                    public void onFailure(Call<PostRateLimit> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                break;
        }
    }

    public void loadDataExecute(){
        LoadDataExecute loadDataExecute = new LoadDataExecute();
        loadDataExecute.execute();
        try {
            loadDataExecute.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class LoadDataExecute extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                Response response = null;
                StorageClass storageClass = StorageClass.get();

                switch (mSCOPES) {
                    case RATE:
                        response = RetrofitGithub.getApi().getRateLimit().execute();
                        if (NetworkUtil.isSuccessfulRequest(response))
                            storageClass.setPostRateLimit((PostRateLimit) response.body());
                        return String.valueOf(storageClass.getPostRateLimit().getRateLimit());

                    case USER:
                        response = RetrofitGithub.getApi().getUser(nameLogin).execute();
                        if (NetworkUtil.isSuccessfulRequest(response))
                            storageClass.getPostUsers().add((PostUser) response.body());
                        return String.valueOf(OutUtils.getPostUser(storageClass.getPostUsers(), nameLogin).getPublicRepos());

                    case REPOS:
                        storageClass.getPostReposes().clear();
                        for (int i = 0; i < OutUtils.getCountRequests(OutUtils.getPostUser(storageClass.getPostUsers(), nameLogin)); i++) {
                            response = RetrofitGithub.getApi().getListRepos(nameLogin, TOTAL_PAGE, i + 1).execute();
                            if (NetworkUtil.isSuccessfulRequest(response))
                                for (PostRepos postRepos : (List<PostRepos>) response.body())
                                    storageClass.getPostReposes().add(postRepos);
                        }
                        GLog.get().i(String.valueOf(storageClass.getPostReposes().size()));
                        return String.valueOf(storageClass.getPostReposes().size());

                    case STARRED:
                        storageClass.getPostStarreds().clear();
                        response = RetrofitGithub.getApi().getListStarred(nameLogin, TOTAL_PAGE, 1).execute();
                        int count = OutUtils.getCountRequests(response.headers().get("Link"));
                        GLog.get().i("linkHeader = " + response.headers().get("Link") + "\n" + "Count Requests : " + String.valueOf(count));
                        if (NetworkUtil.isSuccessfulRequest(response)) {
                            for (PostRepos postStarred : (List<PostRepos>) response.body())
                                storageClass.getPostStarreds().add(postStarred);

                            if(count != 0)
                            for (int i = 1; i < count; i++) {
                                response = RetrofitGithub.getApi().getListStarred(nameLogin, TOTAL_PAGE, i + 1).execute();
                                for (PostRepos postStarred : (List<PostRepos>) response.body())
                                    storageClass.getPostStarreds().add(postStarred);
                            }

                            GLog.get().i("linkHeader = " + response.headers().get("Link") + "\n" + "Count Requests = " + String.valueOf(count) + "\n" + "Count Starred = " + String.valueOf(storageClass.getPostStarreds().size()));
                        }
                        return String.valueOf(storageClass.getPostStarreds().size());

                    case ALL:
                        response = RetrofitGithub.getApi().getRateLimit().execute();
                        if (NetworkUtil.isSuccessfulRequest(response))
                            storageClass.setPostRateLimit((PostRateLimit) response.body());

                        response = RetrofitGithub.getApi().getUser(nameLogin).execute();
                        if (NetworkUtil.isSuccessfulRequest(response))
                            storageClass.getPostUsers().add((PostUser) response.body());

                        storageClass.getPostReposes().clear();
                        for (int i = 0; i < OutUtils.getCountRequests(OutUtils.getPostUser(storageClass.getPostUsers(), nameLogin)); i++) {
                            response = RetrofitGithub.getApi().getListRepos(nameLogin, TOTAL_PAGE, i + 1).execute();
                            if (NetworkUtil.isSuccessfulRequest(response))
                                for (PostRepos postRepos : (List<PostRepos>) response.body())
                                    storageClass.getPostReposes().add(postRepos);
                        }

                        storageClass.getPostStarreds().clear();
                        response = RetrofitGithub.getApi().getListStarred(nameLogin, TOTAL_PAGE, 1).execute();
                        int countRequests = OutUtils.getCountRequests(response.headers().get("Link"));
                        if (NetworkUtil.isSuccessfulRequest(response)) {
                            for (PostRepos postStarred : (List<PostRepos>) response.body())
                                storageClass.getPostStarreds().add(postStarred);

                            if(countRequests != 0)
                                for (int i = 1; i < countRequests; i++) {
                                    response = RetrofitGithub.getApi().getListStarred(nameLogin, TOTAL_PAGE, i + 1).execute();
                                    for (PostRepos postStarred : (List<PostRepos>) response.body())
                                        storageClass.getPostStarreds().add(postStarred);
                                }
                        }
                        return "All";
                }
            }
            catch(Exception e){e.printStackTrace();}
               return Constants.WARING_ERROR;
        }

    }


    //region getters && setters

    public String getNameLogin() {
        return nameLogin;
    }

    public void setNameLogin(String nameLogin) {
        this.nameLogin = nameLogin;
    }

    public int getCurrertPage() {
        return currertPage;
    }

    public void setCurrertPage(int currertPage) {
        this.currertPage = currertPage;
    }

    public GitHubConfig.SCOPES getSCOPES() {
        return mSCOPES;
    }

    public void setSCOPES(GitHubConfig.SCOPES SCOPES) {
        mSCOPES = SCOPES;
    }

    public int getCountRequests() {
        return countRequests;
    }

    public void setCountRequests(int countRequests) {
        this.countRequests = countRequests;
    }

    //endregion
}


