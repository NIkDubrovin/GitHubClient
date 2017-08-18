package com.nikdubrovin.list_of_projects_github;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

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

        getGitHubData = new GetGitHubData();
        getGitHubData.setUser(name_login);
        getGitHubData.setSelectLang(selectLang);
    }

    public void onClickRepos(View view) {
        getGitHubData.setData("repos");
        Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
        getGitHubData.execute();
        try {
            result = getGitHubData.get();
            if(result) { Toast.makeText(getApplicationContext(),"Репозиториев с выбранным языком не существует", Toast.LENGTH_SHORT).show();}

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Возникла ошибка", Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);
    }

    public void onClickStarred(View view) {
        getGitHubData.setData("starred");
        Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);
        getGitHubData.execute();
        try {
            result = getGitHubData.get();
            if(result) { Toast.makeText(getApplicationContext(),"Репозиториев с выбранным языком не существует", Toast.LENGTH_SHORT).show();}

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Возникла ошибка", Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);
    }

}
