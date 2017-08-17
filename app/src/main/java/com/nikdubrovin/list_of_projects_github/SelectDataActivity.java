package com.nikdubrovin.list_of_projects_github;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;


import static com.nikdubrovin.list_of_projects_github.SelectDataActivity.StorageClass.selfparseListJsonArray;

/**
 * Created by NikDubrovin on 17.08.2017.
 */

public class SelectDataActivity extends Activity {

    private GetGithubData getGithubData;
    private ParseListJson parseListJson;
    private final String TAG = "SelectDataActivity";
    private ArrayList<ParseListJson> parseListJsonArray;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_selectdata);
    }

    public void onClickRepos(View view) {

        String[] strmas;
        String name_login = getIntent().getExtras().getString("username");

       // strmas = name_login.split(" ");
       // Log.i(TAG,  strmas[0] + strmas[1]);

        getGithubData = new GetGithubData();
         getGithubData.setUser(name_login);
        //getGithubData.setUser(strmas[0]);
        getGithubData.setData("repos");
        getGithubData.execute();
        try {
            ArrayList<JSONObject> result =  getGithubData.get();
            parseListJsonArray = new ArrayList<>();
            for (int i = 0;i<result.size();i++)
            parseListJsonArray.add(new ParseListJson(result.get(i)));

            selfparseListJsonArray = parseListJsonArray;
            Intent intent = new Intent(SelectDataActivity.this, ListOfRepositories.class);


            startActivity(intent);

        }catch (Throwable cause){
            cause.printStackTrace();
            Toast.makeText(getApplicationContext(),"Отправка parseListJsonArray не удалась", Toast.LENGTH_SHORT).show();
        }

    }

    static public class StorageClass{
        static ArrayList<ParseListJson> selfparseListJsonArray;
        public StorageClass() {}
    }

    public void onClickStarred(View view) {
    }

}
