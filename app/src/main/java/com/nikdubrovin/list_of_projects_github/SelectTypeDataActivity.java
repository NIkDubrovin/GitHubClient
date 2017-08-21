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

        Log.i( TAG + " GitHub" ,"CountRepos: " + CountRepos);
        //endregion CountRepos

        if (result)
            Toast.makeText(getApplicationContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show();

        getGitHubData.cancel(true);
        int count = 0;
        while (true) {
            if (selfArrayList_ListJSON_To_ListStringArray.size() != CountRepos) {
                GetGitHubData getGitHubData = new GetGitHubData();
                getGitHubData.setUser(name_login);
                getGitHubData.setSelectLang(selectLang);
                getGitHubData.setCountPage(count++);
                getGitHubData.setData("repos");
                getGitHubData.setChangeReposUser(true);
                getGitHubData.setAddValueList(true);
                getGitHubData.setIsRateLimit(false);
                getGitHubData.execute();
                try {
                    boolean result = getGitHubData.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getGitHubData.cancel(true);
                if(count == 3) break;
            } else break;
        }
        Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
        startActivity(intent);
    }

    public void onClickStarred(View view) {
        selfArrayList_ListJSON_To_ListStringArray.clear();

        getGitHubData = new GetGitHubData();
        getGitHubData.setUser(name_login);
        getGitHubData.setSelectLang(selectLang);
        getGitHubData.setData("starred");
        getGitHubData.setChangeReposUser(true);
        getGitHubData.setAddValueList(false);
        getGitHubData.setIsRateLimit(false);
        getGitHubData.execute();
        try {
            result = getGitHubData.get();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show();
        }
        if (result)
            Toast.makeText(getApplicationContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show();
        getGitHubData.cancel(true);

        Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
        startActivity(intent);
    }

}
