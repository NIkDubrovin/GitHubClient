package com.nikdubrovin.list_of_projects_github;


import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import android.support.v4.app.NotificationCompatSideChannelService;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.CheckFollowers;
import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.CheckFollowing;
import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.CountFollowers;
import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.CountFollowing;
import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.CountRepos;
import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.selfArrayList_ListJSON_To_ListStringArray;
import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.selfNameLogin;

/**
 * Created by NikDubrovin on 17.08.2017.
 */

public class SelectTypeDataActivity extends Activity {

    private GetGitHubData getGitHubData;
    private final String TAG = SelectTypeDataActivity.class.getSimpleName();
    private boolean result;
    private String name_login;
    private String selectLang;
    private boolean selfLists;
    private ArrayList<ListJSON_To_ListString> ArrayList_ListJSON_To_ListStringArray;
    private String CheckRepos;
    private String CheckStarred;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_selectdata);

        name_login = getIntent().getExtras().getString("username");
        selectLang = getIntent().getExtras().getString("selectLang");

        if(!selfNameLogin.equals(name_login)) {
            Log.i(TAG + " GitHub",selfLists + " / " + "name_login: " + name_login + " / selfNameLogin: " + selfNameLogin);
            selfLists = false;
        }
        else selfLists = true;

        selfNameLogin = name_login;

        //region CountRepos
        getGitHubData = new GetGitHubData();
        getGitHubData.setUser(name_login);
        getGitHubData.setSelectLang(selectLang);
        getGitHubData.setChangeReposUser(false);
        getGitHubData.setAddValueList(false);
        getGitHubData.execute();
        try {
            result = getGitHubData.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        getGitHubData.cancel(true);

        if (result)
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
        Log.i(TAG + " GitHub", "CountRepos: " + CountRepos + " / CountFollowers: " + CountFollowers + " / CountFollowing: " + CountFollowing);
        //endregion CountRepos
    }

    public void onClickRepos(View view) {
     //   if(!selfLists && !selfArrayList_ListJSON_To_ListStringArray.get(1).getName().equals(CheckRepos)) {
            selfArrayList_ListJSON_To_ListStringArray.clear();

            CheckFollowers = false;
            CheckFollowing = false;

            getGitHubData.cancel(true);
            int countPage = 0;
            while (true) {
                if (selfArrayList_ListJSON_To_ListStringArray.size() != CountRepos) {
                    GetGitHubData getGitHubData = new GetGitHubData();
                    getGitHubData.setUser(name_login);
                    getGitHubData.setSelectLang(selectLang);
                    getGitHubData.setCountPage(++countPage);
                    getGitHubData.setData("repos");
                    getGitHubData.setChangeReposUser(true);
                    if (countPage > CountRepos/100)
                        getGitHubData.setAddValueList(true);
                    else
                        getGitHubData.setAddValueList(false);
                    getGitHubData.setIsRateLimit(false);
                    getGitHubData.execute();
                    try {
                        result = getGitHubData.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    getGitHubData.cancel(true);
                    //   if(countPage == 3) break;
                    if (result) break;
                } else break;
            }

        if (result)
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
            startActivity(intent);
       // }else {
         //   Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
         //   startActivity(intent);
       // }

    }

    public void onClickStarred(View view) {
        ArrayList_ListJSON_To_ListStringArray = new ArrayList<>();
        selfArrayList_ListJSON_To_ListStringArray.clear();

        CheckFollowers = false;
        CheckFollowing = false;

        int countPage = 0;
        int count = -1;

        while (true) {
            if (count != ArrayList_ListJSON_To_ListStringArray.size()) {
                GetGitHubData getGitHubData = new GetGitHubData();
                getGitHubData.setUser(name_login);
                getGitHubData.setSelectLang(selectLang);
                getGitHubData.setCountPage(++countPage);
                getGitHubData.setData("starred");
                getGitHubData.setChangeReposUser(true);
                getGitHubData.setAddValueList(false);
                getGitHubData.setIsRateLimit(false);
                getGitHubData.execute();
                try {
                    result = getGitHubData.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                count = ArrayList_ListJSON_To_ListStringArray.size();
                ArrayList_ListJSON_To_ListStringArray.addAll(selfArrayList_ListJSON_To_ListStringArray);
                selfArrayList_ListJSON_To_ListStringArray.clear();
                Log.i(TAG + " GitHub", "ArraySize: " + ArrayList_ListJSON_To_ListStringArray.size());
                getGitHubData.cancel(true);
                // if(countPage == 3) break;
                if (result) break;
            } else break;
        }
        if (result)
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();

        selfArrayList_ListJSON_To_ListStringArray.clear();
        selfArrayList_ListJSON_To_ListStringArray = ArrayList_ListJSON_To_ListStringArray;

        Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
        startActivity(intent);
    }

    public void onClickFollowers(View view) {
        selfArrayList_ListJSON_To_ListStringArray.clear();

        CheckFollowers = true;
        CheckFollowing = false;
        getGitHubData.cancel(true);
        int countPage = 0;
        while (true) {
            if (selfArrayList_ListJSON_To_ListStringArray.size() != CountFollowers) {
                GetGitHubData getGitHubData = new GetGitHubData();
                getGitHubData.setUser(name_login);
                getGitHubData.setSelectLang(selectLang);
                getGitHubData.setCountPage(++countPage);
                getGitHubData.setData("followers");
                getGitHubData.setChangeReposUser(true);
                if (countPage > CountFollowers/100)
                    getGitHubData.setAddValueList(true);
                else
                    getGitHubData.setAddValueList(false);
                getGitHubData.setIsRateLimit(false);
                getGitHubData.execute();
                try {
                    result = getGitHubData.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getGitHubData.cancel(true);
                if (result) break;
                Log.i(TAG + " GitHub","SelfSize: " + Integer.toString(selfArrayList_ListJSON_To_ListStringArray.size()));
            } else break;
        }

        if (result)
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
        startActivity(intent);
    }

    public void onClickFollowing(View view) {
        selfArrayList_ListJSON_To_ListStringArray.clear();

        CheckFollowers = false;
        CheckFollowing = true;
        getGitHubData.cancel(true);
        int countPage = 0;
        while (true) {
            if (selfArrayList_ListJSON_To_ListStringArray.size() != CountFollowing) {
                GetGitHubData getGitHubData = new GetGitHubData();
                getGitHubData.setUser(name_login);
                getGitHubData.setSelectLang(selectLang);
                getGitHubData.setCountPage(++countPage);
                getGitHubData.setData("following");
                getGitHubData.setChangeReposUser(true);
               // if (countPage > CountFollowing/100)
                //    getGitHubData.setAddValueList(true);
              //  else
                    getGitHubData.setAddValueList(false);
                getGitHubData.setIsRateLimit(false);
                getGitHubData.execute();
                Log.i(TAG + " Github",selfArrayList_ListJSON_To_ListStringArray.get(1).getLogin() + " / CountPage: " + countPage);
                try {
                    result = getGitHubData.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getGitHubData.cancel(true);
                if(countPage == 2) break;
                if (result) break;
                Log.i(TAG + " GitHub","SelfSize: " + Integer.toString(selfArrayList_ListJSON_To_ListStringArray.size()));
            } else break;
        }

        if (result)
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
        startActivity(intent);
    }
}
