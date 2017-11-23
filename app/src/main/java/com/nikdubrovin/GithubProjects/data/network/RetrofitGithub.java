package com.nikdubrovin.GithubProjects.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nikdubrovin.GithubProjects.common.config.GitHubConfig;
import com.nikdubrovin.GithubProjects.data.StorageClass;
import com.nikdubrovin.GithubProjects.data.api.GithubApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NikDubrovin on 17.09.2017.
 */

public class RetrofitGithub {

    private RetrofitGithub() {}

    public static GithubApi getApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GithubApi githubApi = retrofit.create(GithubApi.class);
        return githubApi;
    }
}
