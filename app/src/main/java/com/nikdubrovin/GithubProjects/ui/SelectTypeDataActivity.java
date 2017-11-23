package com.nikdubrovin.GithubProjects.ui;


import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonParser;
import com.nikdubrovin.GithubProjects.R;
import com.nikdubrovin.GithubProjects.data.models.PostRepos;
import com.nikdubrovin.GithubProjects.data.network.RetrofitGithub;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NikDubrovin on 17.08.2017.
 */

public class SelectTypeDataActivity extends Activity {

 //   private GetGitHubData getGitHubData;
    private final String TAG = SelectTypeDataActivity.class.getSimpleName();
    private boolean result;
    private String name_login;
    private String selectLang;
    private boolean selfLists;
    private ArrayList<JsonParser> mArrayList_JsonParserArray;
    private String CheckRepos;
    private String CheckStarred;

    List<PostRepos> postReposes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // setContentView(R.layout.activity_selectdata);

//        name_login = getIntent().getExtras().getString("username");
//        selectLang = getIntent().getExtras().getString("selectLang");

//        postReposes = new ArrayList<>();
//        RetrofitGithub.getApi().getListRepos("as").enqueue(new Callback<List<PostRepos>>()  {
//            @Override
//            public void onResponse(Call<List<PostRepos>> call, Response<List<PostRepos>> response) {
//                postReposes.addAll(response.body());
//                Logger.i("Get PostRepos List");
//            }
//            @Override
//            public void onFailure(Call<List<PostRepos>> call, Throwable t) {
//                t.printStackTrace();
//                Logger.i("Not Get PostRepos List");
//                Toast.makeText(SelectTypeDataActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
//            }
//        });



       // try {
          //  Response response =  RetrofitGithub.getApi().getListRepos("NIkDubrovin").execute();
         //  if (response.isSuccessful())
        //    response.body();
          //  postReposes.addAll(RetrofitGithub.getApi().getListRepos("NIkDubrovin").execute().body());

      // }catch (Exception e) {e.printStackTrace();}


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        GithubApi service = retrofit.create(GithubApi.class);
//
//
//        Call<List<PostRepos>> repos = service.getListRepos(name_login);



       // com.nikdubrovin.GithubProjects.common.util.GLog.i("Get List Repos" + " / size: " + postReposes.size() + " / 1 : " + postReposes.get(1));



//        if(!selfNameLogin.equals(name_login)) {
//            GLog.i(TAG + " GitHub",selfLists + " / " + "name_login: " + name_login + " / selfNameLogin: " + selfNameLogin);
//            selfLists = false;
//        }
//        else selfLists = true;
//
//        selfNameLogin = name_login;
//
//        //region CountRepos
//        getGitHubData = new GetGitHubData();
//        getGitHubData.setUser(name_login);
//        getGitHubData.setSelectLang(selectLang);
//        getGitHubData.setChangeReposUser(false);
//        getGitHubData.setAddValueList(false);
//        getGitHubData.execute();
//        try {
//            result = getGitHubData.get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        getGitHubData.cancel(true);
//
//        if (result)
//            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
//        GLog.i(TAG + " GitHub", "CountRepos: " + CountRepos + " / CountFollowers: " + CountFollowers + " / CountFollowing: " + CountFollowing);
        //endregion CountRepos
    }

    public void onClickRepos(View view) {
     //   if(!selfLists && !sSelfArrayList_JsonParserArray.get(1).getName().equals(CheckRepos)) {
//            sSelfArrayList_JsonParserArray.clear();
//
//            CheckFollowers = false;
//            CheckFollowing = false;
//
//            getGitHubData.cancel(true);
//            int countPage = 0;
//            while (true) {
//                if (sSelfArrayList_JsonParserArray.size() != CountRepos) {
//                    GetGitHubData getGitHubData = new GetGitHubData();
//                    getGitHubData.setUser(name_login);
//                    getGitHubData.setSelectLang(selectLang);
//                    getGitHubData.setCountPage(++countPage);
//                    getGitHubData.setData("repos");
//                    getGitHubData.setChangeReposUser(true);
//                    if (countPage > CountRepos/100)
//                        getGitHubData.setAddValueList(true);
//                    else
//                        getGitHubData.setAddValueList(false);
//                    getGitHubData.setIsRateLimit(false);
//                    getGitHubData.execute();
//                    try {
//                        result = getGitHubData.get();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    getGitHubData.cancel(true);
//                    //   if(countPage == 3) break;
//                    if (result) break;
//                } else break;
//            }
//
//        if (result)
//            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
//
//            Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
//            startActivity(intent);
       // }else {
         //   Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
         //   startActivity(intent);
       // }


    }

//    public void onClickStarred(View view) {
//        mArrayList_JsonParserArray = new ArrayList<>();
//        sSelfArrayList_JsonParserArray.clear();
//
//        CheckFollowers = false;
//        CheckFollowing = false;
//
//        int countPage = 0;
//        int count = -1;
//
//        while (true) {
//            if (count != mArrayList_JsonParserArray.size()) {
//                GetGitHubData getGitHubData = new GetGitHubData();
//                getGitHubData.setUser(name_login);
//                getGitHubData.setSelectLang(selectLang);
//                getGitHubData.setCountPage(++countPage);
//                getGitHubData.setData("starred");
//                getGitHubData.setChangeReposUser(true);
//                getGitHubData.setAddValueList(false);
//                getGitHubData.setIsRateLimit(false);
//                getGitHubData.execute();
//                try {
//                    result = getGitHubData.get();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                count = mArrayList_JsonParserArray.size();
//                mArrayList_JsonParserArray.addAll(sSelfArrayList_JsonParserArray);
//                sSelfArrayList_JsonParserArray.clear();
//                GLog.i(TAG + " GitHub", "ArraySize: " + mArrayList_JsonParserArray.size());
//                getGitHubData.cancel(true);
//                // if(countPage == 3) break;
//                if (result) break;
//            } else break;
//        }
//        if (result)
//            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
//
//        sSelfArrayList_JsonParserArray.clear();
//        sSelfArrayList_JsonParserArray = mArrayList_JsonParserArray;
//
//        Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
//        startActivity(intent);
//    }
//
//    public void onClickFollowers(View view) {
//        sSelfArrayList_JsonParserArray.clear();
//
//        CheckFollowers = true;
//        CheckFollowing = false;
//        getGitHubData.cancel(true);
//        int countPage = 0;
//        while (true) {
//            if (sSelfArrayList_JsonParserArray.size() != CountFollowers) {
//                GetGitHubData getGitHubData = new GetGitHubData();
//                getGitHubData.setUser(name_login);
//                getGitHubData.setSelectLang(selectLang);
//                getGitHubData.setCountPage(++countPage);
//                getGitHubData.setData("followers");
//                getGitHubData.setChangeReposUser(true);
//                if (countPage > CountFollowers/100)
//                    getGitHubData.setAddValueList(true);
//                else
//                    getGitHubData.setAddValueList(false);
//                getGitHubData.setIsRateLimit(false);
//                getGitHubData.execute();
//                try {
//                    result = getGitHubData.get();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                getGitHubData.cancel(true);
//                if (result) break;
//                GLog.i(TAG + " GitHub","SelfSize: " + Integer.toString(sSelfArrayList_JsonParserArray.size()));
//            } else break;
//        }
//
//        if (result)
//            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
//        startActivity(intent);
//    }
//
//    public void onClickFollowing(View view) {
//        sSelfArrayList_JsonParserArray.clear();
//
//        CheckFollowers = false;
//        CheckFollowing = true;
//        getGitHubData.cancel(true);
//        int countPage = 0;
//        while (true) {
//            if (sSelfArrayList_JsonParserArray.size() != CountFollowing) {
//                GetGitHubData getGitHubData = new GetGitHubData();
//                getGitHubData.setUser(name_login);
//                getGitHubData.setSelectLang(selectLang);
//                getGitHubData.setCountPage(++countPage);
//                getGitHubData.setData("following");
//                getGitHubData.setChangeReposUser(true);
//               // if (countPage > CountFollowing/100)
//                //    getGitHubData.setAddValueList(true);
//              //  else
//                    getGitHubData.setAddValueList(false);
//                getGitHubData.setIsRateLimit(false);
//                getGitHubData.execute();
//                GLog.i(TAG + " Github", sSelfArrayList_JsonParserArray.get(1).getLogin() + " / CountPage: " + countPage);
//                try {
//                    result = getGitHubData.get();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                getGitHubData.cancel(true);
//                if(countPage == 2) break;
//                if (result) break;
//                GLog.i(TAG + " GitHub","SelfSize: " + Integer.toString(sSelfArrayList_JsonParserArray.size()));
//            } else break;
//        }
//
//        if (result)
//            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
//        startActivity(intent);
//    }
}
