package com.nikdubrovin.GithubProjects.data.api;

import com.nikdubrovin.GithubProjects.data.models.PostRateLimit;
import com.nikdubrovin.GithubProjects.data.models.PostRepos;
import com.nikdubrovin.GithubProjects.data.models.PostStarred;
import com.nikdubrovin.GithubProjects.data.models.PostUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by NikDubrovin on 17.09.2017.
 */

public interface GithubApi {
    @GET("/users/{username}/repos?client_id=5b074b14a3c166278774&client_secret=a2c539a256cd861cd9dcc5c86e51a53baf4c96a5")
    Call<List<PostRepos>> getListRepos(@Path("username") String username, @Query("per_page") int countRepos, @Query("page") int countPage/*, @Query("client_id") String client_id, @Query("client_secret") String client_secret*/);

    @GET("/rate_limit?client_id=5b074b14a3c166278774&client_secret=a2c539a256cd861cd9dcc5c86e51a53baf4c96a5")
    Call<PostRateLimit> getRateLimit();

    @GET("/users/{username}?client_id=5b074b14a3c166278774&client_secret=a2c539a256cd861cd9dcc5c86e51a53baf4c96a5")
    Call<PostUser> getUser(@Path("username") String username);

    @Headers("Cache-Control: public, max-age=600")
    @GET("/users/{username}/starred?client_id=5b074b14a3c166278774&client_secret=a2c539a256cd861cd9dcc5c86e51a53baf4c96a5")
    Call<List<PostRepos>> getListStarred(@Path("username") String username, @Query("per_page") int countRepos, @Query("page") int countPage/*, @Query("client_id") String client_id, @Query("client_secret") String client_secret*/);
}
