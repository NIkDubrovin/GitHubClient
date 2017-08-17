package com.nikdubrovin.list_of_projects_github;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.nikdubrovin.list_of_projects_github.SelectTypeDataActivity.StorageClass.selfParseListStringArray;

/**
 * Created by NikDubrovin on 17.08.2017.
 */

public class SelectTypeDataActivity extends Activity {

    private GetGithubData getGithubData;
    private final String TAG = "SelectTypeDataActivity";
    private ArrayList<ListJSON_To_ListString> arrayList_ListJSON_To_ListStringArray;
    private ArrayList<JSONObject> result;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_selectdata);
    }

    public void onClickRepos(View view) {

        String name_login = getIntent().getExtras().getString("username");

        getGithubData = new GetGithubData();
        getGithubData.setUser(name_login);
        getGithubData.setData("repos");
        getGithubData.execute();
        try {
            result = getGithubData.get();
            arrayList_ListJSON_To_ListStringArray = new ArrayList<>();
            for (int i = 0;i < result.size();i++)
            arrayList_ListJSON_To_ListStringArray.add(new ListJSON_To_ListString(result.get(i)));

            selfParseListStringArray = arrayList_ListJSON_To_ListStringArray;
            Intent intent = new Intent(SelectTypeDataActivity.this, ListOfRepositories.class);

            startActivity(intent);

        }catch (Throwable cause){
            cause.printStackTrace();
            Toast.makeText(getApplicationContext(),"Отправка arrayList_ListJSON_To_ListStringArray не удалась", Toast.LENGTH_SHORT).show();
        }

    }

    static public class StorageClass{
        static ArrayList<ListJSON_To_ListString> selfParseListStringArray;
        public StorageClass() {}
    }

    public void onClickStarred(View view) {
    }

}
