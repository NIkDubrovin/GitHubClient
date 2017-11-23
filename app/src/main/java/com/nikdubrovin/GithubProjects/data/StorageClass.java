package com.nikdubrovin.GithubProjects.data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;

import com.nikdubrovin.GithubProjects.data.models.PostRateLimit;
import com.nikdubrovin.GithubProjects.data.models.PostRepos;
import com.nikdubrovin.GithubProjects.data.models.PostStarred;
import com.nikdubrovin.GithubProjects.data.models.PostUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NikDubrovin on 17.09.2017.
 */

public class StorageClass {

    private static volatile StorageClass instance;

    private List<PostRepos> mPostReposes;
    private List<PostUser> mPostUsers;
    private List<PostRepos> mPostStarreds;
    private PostRateLimit mPostRateLimit;
    private String UserName;
    private Drawable mDrawable;

    private int countReqRepos = 0;
    private int countReqStarred = 0;


    private StorageClass(){
        mPostReposes = new ArrayList<>();
        mPostUsers = new ArrayList<>();
        mPostStarreds = new ArrayList<>();
    }

    public static StorageClass get(){
        if(instance == null){
          synchronized (StorageClass.class){
              if(instance == null)
                  instance = new StorageClass();
          }
        }
        return instance;
    }

    // region getters && setters
    public List<PostRepos> getPostReposes() {
        return mPostReposes;
    }

    public void setPostReposes(List<PostRepos> mPostReposes) {
            this.mPostReposes = mPostReposes;
    }

    public List<PostUser> getPostUsers() {
        return mPostUsers;
    }

    public void setPostUsers(List<PostUser> mPostUsers) {
        this.mPostUsers = mPostUsers;
    }

    public List<PostRepos> getPostStarreds() {
        return mPostStarreds;
    }

    public void setPostStarreds(List<PostRepos> postStarreds) {
        mPostStarreds = postStarreds;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }

    public PostRateLimit getPostRateLimit() {
        return mPostRateLimit;
    }

    public void setPostRateLimit(PostRateLimit postRateLimit) {
        mPostRateLimit = postRateLimit;
    }

    public int getCountReqRepos() {
        return countReqRepos;
    }

    public void setCountReqRepos(int countReqRepos) {
        this.countReqRepos = countReqRepos;
    }

    public int getCountReqStarred() {
        return countReqStarred;
    }

    public void setCountReqStarred(int countReqStarred) {
        this.countReqStarred = countReqStarred;
    }

    // endregion
}
