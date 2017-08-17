package com.nikdubrovin.list_of_projects_github;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by NikDubrovin on 17.08.2017.
 */

public class SelectDataActivity extends Activity {

    private GetGithubData getGithubData;
    private final String TAG = "SelectDataActivity";


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_selectdata);
    }

    public void onClickRepos(View view) {

        String[] strmas;
        String name_login = getIntent().getExtras().getString("username");

        strmas = name_login.split(" ");
        Log.i(TAG,  strmas[0] + strmas[1]);

        getGithubData = new GetGithubData();
        // getGithubData.setUser(name_login);
        getGithubData.setUser(strmas[0]);
        getGithubData.setData("repos");
        getGithubData.execute();
        try {
            ArrayList<JSONObject> result =  getGithubData.get();
            int count = Integer.parseInt(strmas[1]);
            Log.i(TAG, "result: " + result);
            String name = result.get(count).getString("name");
            URL url = new URL(result.get(count).getString("url_repos"));
            String desc = result.get(count).getString("description");
            String lang = result.get(count).getString("language");
            String fork = result.get(count).getString("fork");

//            Toast.makeText(getApplicationContext(),
//                    "Репозиторий : " + name + "\n" +
//                            "URL: " + url + "\n" +
//                            "Описание: " + desc + "\n" +
//                            "Язык: " + lang + "\n" +
//                            "Fork :" + fork, Toast.LENGTH_LONG).show();
            Log.i(TAG, "URI_REPOS: " + url);
            Intent intent = new Intent(SelectDataActivity.this, ListOfRepositories.class);

            intent.putExtra("name", name);
            intent.putExtra("url_repos", url);
            intent.putExtra("desc", desc);
            intent.putExtra("lang", lang);
            intent.putExtra("fork", fork);
            startActivity(intent);

        }catch (Throwable cause){
            cause.printStackTrace();
            Toast.makeText(getApplicationContext(),"Что-то пошло не так..", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickStarred(View view) {

    }
}
