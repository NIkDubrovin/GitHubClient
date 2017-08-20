package com.nikdubrovin.list_of_projects_github;


import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.CountRepos;
import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.selfArrayList_ListJSON_To_ListStringArray;

/**
 * Created by NikDubrovin on 17.08.2017.
 */

public class SelectTypeDataActivity extends Activity {

    private GetGitHubData getGitHubData;
    private final String TAG = SelectTypeDataActivity.class.getSimpleName();
    private boolean result;
    private String name_login;
    private String selectLang;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_selectdata);

        name_login = getIntent().getExtras().getString("username");
        selectLang = getIntent().getExtras().getString("selectLang");
    }

    public void onClickRepos(View view) {
        selfArrayList_ListJSON_To_ListStringArray.clear();

        // CountRepos
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

        Log.i( TAG + " GitHub" ,"CountRepos: " + CountRepos);
        // CountRepos

        if(getGitHubData.getBunGithub()){/* startActivity(intent);*/   Toast.makeText(getApplicationContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show();}
        getGitHubData = new GetGitHubData();
        getGitHubData.setUser(name_login);
        getGitHubData.setSelectLang(selectLang);
        getGitHubData.setData("repos");
        getGitHubData.setChangeReposUser(true);
        getGitHubData.setAddValueList(false);
        getGitHubData.execute();
        try {
            result = getGitHubData.get();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show();
        }
        if (result)
            Toast.makeText(getApplicationContext(), "Репозиториев с выбранным языком не существует", Toast.LENGTH_SHORT).show();
        getGitHubData.cancel(true);
        Log.i("GitHub", Integer.toString(selfArrayList_ListJSON_To_ListStringArray.size()) + " = / != " + CountRepos);
        int count = 1;
        while (true) {
            Log.i("GitHub + repos", Integer.toString(selfArrayList_ListJSON_To_ListStringArray.size()) + " = / != " + CountRepos);
            if (selfArrayList_ListJSON_To_ListStringArray.size() != CountRepos) {
                GetGitHubData getGitHubData = new GetGitHubData();
                getGitHubData.setUser(name_login);
                getGitHubData.setSelectLang(selectLang);
                getGitHubData.setCountPage(count++);
                getGitHubData.setData("repos");
                getGitHubData.setChangeReposUser(true);
                getGitHubData.setAddValueList(true);
                getGitHubData.execute();
                try {
                    boolean result = getGitHubData.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i("GitHub + repos", Integer.toString(selfArrayList_ListJSON_To_ListStringArray.size()) + " = / != " + CountRepos);
                getGitHubData.cancel(true);
                if(count == 4) break;
            } else break;
        }
        Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
        startActivity(intent);

    }

    public void onClickStarred(View view) {
        selfArrayList_ListJSON_To_ListStringArray.clear();
        if(getGitHubData.getBunGithub()){/* startActivity(intent);*/   Toast.makeText(getApplicationContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show();}
        getGitHubData = new GetGitHubData();
        getGitHubData.setUser(name_login);
        getGitHubData.setSelectLang(selectLang);
        getGitHubData.setData("starred");
        getGitHubData.setChangeReposUser(true);
        getGitHubData.setAddValueList(false);
        getGitHubData.execute();
        try {
            result = getGitHubData.get();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show();
        }
        if (result)
            Toast.makeText(getApplicationContext(), "Репозиториев с выбранным языком не существует", Toast.LENGTH_SHORT).show();
        getGitHubData.cancel(true);
        Log.i("GitHub", Integer.toString(selfArrayList_ListJSON_To_ListStringArray.size()) + " = / != " + CountRepos);

        Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
        startActivity(intent);
    }

}
